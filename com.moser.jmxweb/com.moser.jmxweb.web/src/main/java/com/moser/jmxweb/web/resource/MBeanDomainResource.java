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

import com.moser.jmxweb.core.mbean.MBeanDomain;
import com.moser.jmxweb.web.exception.RESTException;
import com.moser.jmxweb.web.model.MBeanDomainModel;
import com.moser.jmxweb.web.resource.util.MBeanResolver;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * MBeanDomainResource
 * <p/>
 * Author: Nicolas Moser
 * Date: 05.11.13
 * Time: 20:27
 */
@Path("domains")
public class MBeanDomainResource {

	private MBeanResolver resolver = new MBeanResolver();

	/**
	 * Getter for all MBean Domains.
	 *
	 * @return the list of all existing MBean Domains.
	 *
	 * @throws RESTException
	 * 		when the MBean Domains cannot be loaded
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MBeanDomainModel> getAllDomains() throws RESTException {

		List<MBeanDomain> domains = this.resolver.resolveDomains();

		List<MBeanDomainModel> modelList = new ArrayList<MBeanDomainModel>();
		for (MBeanDomain domain : domains) {
			MBeanDomainModel model = new MBeanDomainModel();
			model.setName(domain.getName());
			modelList.add(model);
		}

		return modelList;
	}

	/**
	 * Getter for the MBean Domain with the given name.
	 *
	 * @param domainName
	 * 		the domain name
	 *
	 * @return the MBean Domain
	 *
	 * @throws RESTException
	 * 		when the MBean Domains cannot be loaded
	 */
	@GET
	@Path("{domainName}")
	@Produces(MediaType.APPLICATION_JSON)
	public MBeanDomainModel getDomain(@PathParam("domainName") String domainName) throws RESTException {

		MBeanDomain domain = this.resolver.resolveDomain(domainName);

		MBeanDomainModel model = new MBeanDomainModel();
		model.setName(domain.getName());

		return model;
	}

}
