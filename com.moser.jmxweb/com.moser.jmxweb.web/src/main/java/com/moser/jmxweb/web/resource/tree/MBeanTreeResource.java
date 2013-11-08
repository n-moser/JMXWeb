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

package com.moser.jmxweb.web.resource.tree;

import com.moser.jmxweb.core.connection.JMXConnectionException;
import com.moser.jmxweb.core.logger.JMXWebLogger;
import com.moser.jmxweb.core.logger.JMXWebLoggerFactory;
import com.moser.jmxweb.core.mbean.JMXServer;
import com.moser.jmxweb.core.mbean.MBean;
import com.moser.jmxweb.core.mbean.MBeanDomain;
import com.moser.jmxweb.web.model.tree.MBeanTreeComposite;
import com.moser.jmxweb.web.model.tree.MBeanTreeCompositeType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * MBeanTreeResource
 * <p/>
 * Author: Nicolas Moser
 * Date: 07.11.13
 * Time: 00:02
 */
@Path("mbeantree")
public class MBeanTreeResource {

	private JMXWebLogger logger = JMXWebLoggerFactory.getLogger(MBeanTreeResource.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<MBeanTreeComposite> getComposite(@QueryParam("node") String node) {

		try {
			JMXServer server = new JMXServer();
			Map<String, MBeanDomain> domains = server.getDomains();

			// Root Nodes
			if (node == null || node.isEmpty()) {
				return this.mapDomains(domains);
			}

			for (Map.Entry<String, MBeanDomain> entry : domains.entrySet()) {

				MBeanDomain domain = entry.getValue();

				if (entry.getKey().equalsIgnoreCase(node)) {
					return this.mapMBeansByDomain(domain);
				} else {
					List<MBean> mBeans = domain.getMbeans(node);
					if (mBeans.size() > 1) {
						return this.mapMBeansByType(node, mBeans);
					}
				}
			}

		} catch (JMXConnectionException e) {
			logger.error("Error creating tree resource.", e);
		}

		return Collections.emptySet();
	}

	/**
	 * Filter the MBean domains and create Composite instances.
	 *
	 * @param domains
	 * 		the domains
	 *
	 * @return the list of mbean composites
	 */
	private Set<MBeanTreeComposite> mapDomains(Map<String, MBeanDomain> domains) {

		Set<MBeanTreeComposite> nodes = new HashSet<MBeanTreeComposite>();
		for (MBeanDomain domain : domains.values()) {
			MBeanTreeComposite rootNode = new MBeanTreeComposite();
			rootNode.setId(domain.getName());
			rootNode.setLabel(domain.getName());
			rootNode.setType(MBeanTreeCompositeType.DOMAIN);
			nodes.add(rootNode);
		}

		return nodes;
	}

	/**
	 * Filter the MBean domain and add Composite instances for each MBean.
	 *
	 * @param domain
	 * 		the domain to filter
	 *
	 * @return the list of mbean composites
	 */
	private Set<MBeanTreeComposite> mapMBeansByDomain(MBeanDomain domain) {

		Set<MBeanTreeComposite> nodes = new HashSet<MBeanTreeComposite>();

		for (MBean mBean : domain.getAllMbeans()) {
			MBeanTreeComposite child = new MBeanTreeComposite();
			child.setId(mBean.getType());
			child.setLabel(mBean.getType());
			child.setObjectName(mBean.getCanonicalName());
			child.setLoad_on_demand(false);
			child.setType(MBeanTreeCompositeType.MBEAN);

			if (!nodes.contains(child)) {
				nodes.add(child);
			} else {
				nodes.remove(child);
				child.setLoad_on_demand(true);
				child.setType(MBeanTreeCompositeType.GROUP);
				nodes.add(child);
			}
		}

		return nodes;
	}

	/**
	 * Filter the MBeans for the given type and create Composite instances.
	 *
	 * @param type
	 * 		the type to filter
	 * @param mBeans
	 * 		the list of mbeans
	 *
	 * @return the list of mbean composites
	 */
	private Set<MBeanTreeComposite> mapMBeansByType(String type, List<MBean> mBeans) {

		Set<MBeanTreeComposite> nodes = new HashSet<MBeanTreeComposite>();

		for (MBean mBean : mBeans) {

			if (mBean.getType().equalsIgnoreCase(type)) {
				MBeanTreeComposite child = new MBeanTreeComposite();
				child.setId(mBean.getName());
				child.setLabel(mBean.getName());
				child.setObjectName(mBean.getCanonicalName());
				child.setType(MBeanTreeCompositeType.MBEAN);
				child.setLoad_on_demand(false);
				nodes.add(child);
			}
		}

		return nodes;
	}

}
