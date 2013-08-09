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

package com.moser.jmxweb.core.mbean;

import com.moser.jmxweb.core.connection.JMXConnection;
import com.moser.jmxweb.core.connection.JMXConnectionException;
import com.moser.jmxweb.core.connection.JMXConnectionFactory;
import com.moser.jmxweb.core.logger.JMXWebLogger;
import com.moser.jmxweb.core.logger.JMXWebLoggerFactory;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import java.util.*;

/**
 * MBeanServer
 * <p/>
 * Author: Nicolas Moser
 * Date: 08.08.13
 * Time: 23:58
 */
public class JMXServer {

    private final JMXConnection connection;
    private final Map<String, MBeanDomain> domains = new HashMap<String, MBeanDomain>();
    private JMXWebLogger logger = JMXWebLoggerFactory.getLogger(JMXServer.class);

    /**
     * Creates a new JMXServer instance based on the default connection.
     *
     * @throws JMXConnectionException when the connection cannot be established
     */
    public JMXServer() throws JMXConnectionException {
        this(null);
    }

    /**
     * Creates a new JMXServer instance based on the given connection.
     *
     * @param connection the connection
     * @throws JMXConnectionException when the connection cannot be established
     */
    public JMXServer(JMXConnection connection) throws JMXConnectionException {

        if (connection == null) {
            JMXConnectionFactory factory = new JMXConnectionFactory();
            connection = factory.getConnection();
        }

        this.connection = connection;

        this.initMBeanDomains();
    }

    /**
     * Load and initialize the MBean Domains.
     */
    private void initMBeanDomains() throws JMXConnectionException {

        List<MBeanServer> mBeanServers = this.connection.getMBeanServers();

        for (MBeanServer mBeanServer : mBeanServers) {

            for (String domainName : mBeanServer.getDomains()) {
                MBeanDomain domain = new MBeanDomain(domainName);
                this.domains.put(domainName, domain);

            }

            Set<ObjectInstance> objectInstances = mBeanServer.queryMBeans(null, null);

            for (ObjectInstance objectInstance : objectInstances) {
                ObjectName objectName = objectInstance.getObjectName();

                MBean mbean = new MBean();
                mbean.setName(objectName.getCanonicalName());

                MBeanDomain domain = domains.get(objectName.getDomain());

                logger.info(objectName.getKeyPropertyListString());
                domain.getMbeans(objectName.getKeyProperty("type")).add(mbean);
            }
        }
    }

    public Map<String, MBeanDomain> getDomains() {
        return Collections.unmodifiableMap(domains);
    }
}
