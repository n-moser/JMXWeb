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

import com.moser.jmxweb.core.exception.JMXWebException;
import com.moser.jmxweb.core.mbean.JMXServer;
import com.moser.jmxweb.core.mbean.MBean;
import com.moser.jmxweb.core.mbean.MBeanAttribute;
import com.moser.jmxweb.web.exception.RESTException;
import com.moser.jmxweb.web.model.MBeanAttributeAccessType;
import com.moser.jmxweb.web.model.MBeanAttributeModel;
import com.moser.jmxweb.web.model.MBeanEntryModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * MBeanFinderResource
 * <p/>
 * Author: Nicolas Moser
 * Date: 08.11.13
 * Time: 17:37
 */
@Path("mbeansearch")
public class MBeanFinderResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MBeanEntryModel findMBeanByObjectName(@QueryParam("objectName") String objectName) throws RESTException {

		try {
			JMXServer server = new JMXServer();

			MBean mbean = server.findMBean(objectName);

			MBeanEntryModel model = new MBeanEntryModel();
			model.setName(mbean.getName());
			model.setDescription(mbean.getDescription());
			model.setType(mbean.getType());
			model.setmBeanClass(mbean.getMBeanClass());

			for (MBeanAttribute attribute : mbean.getAttributes()) {
				MBeanAttributeModel attributeModel = new MBeanAttributeModel();
				attributeModel.setName(attribute.getName());
				attributeModel.setType(attribute.getType());
				attributeModel.setDescription(attribute.getDescription());

				if (attribute.isReadable()) {
					attributeModel.setAccessType(MBeanAttributeAccessType.READ);
					attributeModel.setValue(attribute.getValueAsString());
				}
				if (attribute.isWritable()) {
					attributeModel.setAccessType(MBeanAttributeAccessType.WRITE);
				}

				model.getAttributes().add(attributeModel);
			}

			return model;

		} catch (JMXWebException e) {
			throw new RESTException("Cannot load MBean Details.", e);
		}
	}
}
