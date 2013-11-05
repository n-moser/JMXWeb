/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Nicolas Moser
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.moser.jmxweb.web.resource;

import com.moser.jmxweb.core.connection.JMXConnectionException;
import com.moser.jmxweb.core.logger.JMXWebLogger;
import com.moser.jmxweb.core.logger.JMXWebLoggerFactory;
import com.moser.jmxweb.core.mbean.JMXServer;
import com.moser.jmxweb.core.mbean.MBean;
import com.moser.jmxweb.core.mbean.MBeanDomain;
import com.moser.jmxweb.web.exception.RESTException;
import com.moser.jmxweb.web.model.MBeanModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * MBeanResource
 * <p/>
 * Author: Nicolas Moser
 * Date: 05.11.13
 * Time: 20:13
 */
@Path("domains/{domain}/mbeans")
public class MBeanResource {

	private JMXWebLogger logger = JMXWebLoggerFactory.getLogger(MBeanResource.class);

	/**
	 * Getter for all MBeans of the given domain.
	 *
	 * @param domain
	 * 		the MBean domain
	 *
	 * @return the list of mbeans
	 *
	 * @throws RESTException
	 * 		when the mbeans cannot be loaded
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MBeanModel> getAllMBeans(@PathParam("domain") String domain) throws RESTException {

		try {
			JMXServer server = new JMXServer();
			MBeanDomain mBeanDomain = server.getDomains().get(domain);

			if (mBeanDomain == null) {
				throw new RESTException("MBean domain '" + domain + "' does not exist.");
			}

			List<MBeanModel> modelList = new ArrayList<MBeanModel>();
			for(MBean mbean : mBeanDomain.getAllMbeans()) {

				MBeanModel model = new MBeanModel();
				model.setName(mbean.getName());
				model.setType(mbean.getType());
				model.setDescription(mbean.getDescription());
				modelList.add(model);
			}

			return modelList;

		} catch (JMXConnectionException e) {
			logger.error("Cannot load MBeans for domain '{}'.", domain, e);
			throw new RESTException("Cannot load MBeans.");
		}
	}

}
