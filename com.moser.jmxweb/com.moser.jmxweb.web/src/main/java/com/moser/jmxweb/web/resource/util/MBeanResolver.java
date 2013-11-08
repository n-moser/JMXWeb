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

package com.moser.jmxweb.web.resource.util;

import com.moser.jmxweb.core.connection.JMXConnectionException;
import com.moser.jmxweb.core.logger.JMXWebLogger;
import com.moser.jmxweb.core.logger.JMXWebLoggerFactory;
import com.moser.jmxweb.core.mbean.*;
import com.moser.jmxweb.web.exception.RESTException;
import com.moser.jmxweb.web.resource.MBeanResource;

import java.util.ArrayList;
import java.util.List;

/**
 * MBeanResolver
 * <p/>
 * Author: Nicolas Moser
 * Date: 06.11.13
 * Time: 11:25
 */
public class MBeanResolver {

	private JMXWebLogger logger = JMXWebLoggerFactory.getLogger(MBeanResource.class);

	/**
	 * Resolve the MBean domain for the given name.
	 *
	 * @return the resolved mbean domain
	 *
	 * @throws com.moser.jmxweb.web.exception.RESTException
	 * 		when the domain cannot be resolved
	 */
	public List<MBeanDomain> resolveDomains() throws RESTException {

		try {
			JMXServer server = new JMXServer();

			return new ArrayList<MBeanDomain>(server.getDomains().values());

		} catch (JMXConnectionException e) {
			logger.error("Cannot load MBean domains.", e);
			throw new RESTException("Cannot load MBeans Domains.");
		}
	}

	/**
	 * Resolve the MBean domain for the given name.
	 *
	 * @param domainName
	 * 		the domain name
	 *
	 * @return the resolved mbean domain
	 *
	 * @throws com.moser.jmxweb.web.exception.RESTException
	 * 		when the domain cannot be resolved
	 */
	public MBeanDomain resolveDomain(String domainName) throws RESTException {

		try {
			JMXServer server = new JMXServer();
			MBeanDomain domain = server.getDomains().get(domainName);

			if (domain == null) {
				throw new RESTException("MBean domain '" + domainName + "' does not exist.");
			}
			return domain;

		} catch (JMXConnectionException e) {
			logger.error("Cannot load MBeans for domain '{}'.", domainName, e);
			throw new RESTException("Cannot load MBeans.");
		}
	}

	/**
	 * Resolve all MBeans for the given domain names.
	 *
	 * @param domainName
	 * 		the domain name
	 *
	 * @return the resolved mbeans
	 *
	 * @throws com.moser.jmxweb.web.exception.RESTException
	 * 		when the domain cannot be resolved
	 */
	public List<MBean> resolveMBeans(String domainName) throws RESTException {

		MBeanDomain domain = this.resolveDomain(domainName);
		return domain.getAllMbeans();
	}

	/**
	 * Resolve the MBean for the given domain and mbean names.
	 *
	 * @param domainName
	 * 		the domain name
	 * @param mBeanName
	 * 		the mbean name
	 *
	 * @return the resolved mbean
	 *
	 * @throws com.moser.jmxweb.web.exception.RESTException
	 * 		when the domain cannot be resolved
	 */
	public MBean resolveMBean(String domainName, String mBeanName) throws RESTException {

		MBeanDomain domain = this.resolveDomain(domainName);

		for (MBean mBean : domain.getAllMbeans()) {
			if (mBean.getCanonicalName().equalsIgnoreCase(mBeanName)) {
				return mBean;
			}
		}

		throw new RESTException("MBean '" + mBeanName + "' of domain '" + domainName + "' does not exist.");
	}

	/**
	 * Resolve the MBean Attributes for the given MBean.
	 *
	 * @param domainName
	 * 		the domain name
	 * @param mBeanName
	 * 		the mbean name
	 *
	 * @return the resolved mbean attributes
	 *
	 * @throws com.moser.jmxweb.web.exception.RESTException
	 * 		when the mbean cannot be resolved
	 */
	public List<MBeanAttribute> resolveMBeanAttributes(String domainName, String mBeanName) throws RESTException {

		MBean mBean = this.resolveMBean(domainName, mBeanName);
		return mBean.getAttributes();
	}

	/**
	 * Resolve the MBean Attributes for the given MBean.
	 *
	 * @param domainName
	 * 		the domain name
	 * @param mBeanName
	 * 		the mbean name
	 *
	 * @return the resolved mbean attributes
	 *
	 * @throws com.moser.jmxweb.web.exception.RESTException
	 * 		when the mbean cannot be resolved
	 */
	public List<MBeanOperation> resolveMBeanOperations(String domainName, String mBeanName) throws RESTException {

		MBean mBean = this.resolveMBean(domainName, mBeanName);
		return mBean.getOperations();
	}

}
