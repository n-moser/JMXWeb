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
import com.moser.jmxweb.web.model.tree.TreeComposite;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * TreeResource
 * <p/>
 * Author: Nicolas Moser
 * Date: 07.11.13
 * Time: 00:02
 */
@Path("tree")
public class TreeResource {

	private JMXWebLogger logger = JMXWebLoggerFactory.getLogger(TreeResource.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<TreeComposite> getComposite(@QueryParam("node") String node) {

		Set<TreeComposite> nodes = new HashSet<TreeComposite>();

		try {
			JMXServer server = new JMXServer();
			Map<String, MBeanDomain> domains = server.getDomains();

			if (node == null || node.isEmpty()) {

				for (MBeanDomain domain : domains.values()) {
					TreeComposite rootNode = new TreeComposite();
					rootNode.setId(domain.getName());
					rootNode.setLabel(domain.getName());
					nodes.add(rootNode);
				}

				return nodes;
			}

			for (Map.Entry<String, MBeanDomain> entry : domains.entrySet()) {

				if (entry.getKey().equalsIgnoreCase(node)) {

					for (MBean mBean : entry.getValue().getAllMbeans()) {
						TreeComposite child = new TreeComposite();
						child.setId(mBean.getType());
						child.setLabel(mBean.getType());
						nodes.add(child);
					}

					return nodes;

				} else {
					List<MBean> mBeans = entry.getValue().getMbeans(node);

					if (!mBeans.isEmpty()) {
						if (mBeans.size() > 1) {

							for (MBean mBean : entry.getValue().getAllMbeans()) {
								TreeComposite child = new TreeComposite();
								child.setId(mBean.getName());
								child.setLabel(mBean.getName());
								nodes.add(child);
							}

							return nodes;
						} else {

							TreeComposite child = new TreeComposite();
							child.setId("Attributes");
							child.setLabel("Attributes");
							child.setLoad_on_demand(false);
							nodes.add(child);

							child = new TreeComposite();
							child.setId("Operations");
							child.setLabel("Operations");
							child.setLoad_on_demand(false);
							nodes.add(child);

							return nodes;
						}
					}
				}
			}

		} catch (JMXConnectionException e) {
			logger.error("Error creating tree resource.", e);
		}

		return Collections.emptySet();
	}

}
