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

import com.moser.jmxweb.core.mbean.MBeanAttribute;
import com.moser.jmxweb.web.exception.RESTException;
import com.moser.jmxweb.web.model.MBeanAttributeModel;

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
@Path("domains/{domainName}/mbeans/{mbeanName}/")
public class MBeanAttributeResource {

	private MBeanResolver resolver = new MBeanResolver();

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
	@Produces(MediaType.APPLICATION_JSON)
	public List<MBeanAttributeModel> getMBeanAttributes(@PathParam("domainName") String domainName,
														@PathParam("mBeanName") String mBeanName) throws RESTException {

		List<MBeanAttribute> attributes = this.resolver.resolveMBeanAttributes(domainName, mBeanName);

		List<MBeanAttributeModel> modelList = new ArrayList<MBeanAttributeModel>();

		for (MBeanAttribute attribute : attributes) {

			MBeanAttributeModel model = new MBeanAttributeModel();
			model.setName(attribute.getName());
			model.setType(attribute.getType());
			model.setDescription(attribute.getDescription());
			model.setReadable(attribute.isReadable());
			model.setWritable(attribute.isWritable());
			model.setValue(attribute.getValueAsString());

			modelList.add(model);
		}

		return modelList;
	}

}
