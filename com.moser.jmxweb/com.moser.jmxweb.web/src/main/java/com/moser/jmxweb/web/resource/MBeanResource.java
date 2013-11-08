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

import com.moser.jmxweb.core.mbean.MBean;
import com.moser.jmxweb.core.mbean.MBeanDomain;
import com.moser.jmxweb.web.exception.RESTException;
import com.moser.jmxweb.web.model.MBeanEntryModel;
import com.moser.jmxweb.web.model.MBeanGroupModel;
import com.moser.jmxweb.web.model.MBeanModel;
import com.moser.jmxweb.web.resource.util.MBeanResolver;

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
@Path("domains/{domainName}/mbeans")
public class MBeanResource {

	private MBeanResolver resolver = new MBeanResolver();

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
	public List<MBeanModel> getAllMBeans(@PathParam("domainName") String domain) throws RESTException {

		MBeanDomain mBeanDomain = this.resolver.resolveDomain(domain);

		List<MBeanModel> modelList = new ArrayList<MBeanModel>();

		for (String type : mBeanDomain.getMBeanTypes()) {
			List<MBean> mbeans = mBeanDomain.getMbeans(type);

			if (!mbeans.isEmpty()) {
				if (mbeans.size() > 1) {
					MBeanGroupModel model = new MBeanGroupModel();
					model.setType(type);
					modelList.add(model);
				} else {
					MBean mBean = mbeans.get(0);
					MBeanEntryModel model = new MBeanEntryModel();
					model.setName(mBean.getCanonicalName());
					model.setType(mBean.getType());
					model.setDescription(mBean.getDescription());
					modelList.add(model);
				}
			}
		}

		return modelList;
	}

	/**
	 * Getter for all MBeans of the given domain.
	 *
	 * @param domainName
	 * 		the MBean domain
	 *
	 * @return the list of mbeans
	 *
	 * @throws RESTException
	 * 		when the mbeans cannot be loaded
	 */
	@GET
	@Path("{mBeanType}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MBeanEntryModel> getMBeans(@PathParam("domainName") String domainName,
										   @PathParam("mBeanType") String mBeanType) throws RESTException {

		MBeanDomain mBeanDomain = this.resolver.resolveDomain(domainName);
		List<MBean> mbeans = mBeanDomain.getMbeans(mBeanType);

		List<MBeanEntryModel> modelList = new ArrayList<MBeanEntryModel>();

		for (MBean mBean : mbeans) {
			MBeanEntryModel model = new MBeanEntryModel();
			model.setName(mBean.getCanonicalName());
			model.setType(mBean.getType());
			model.setDescription(mBean.getDescription());
			modelList.add(model);
		}

		return modelList;
	}

}
