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

import javax.management.*;
import java.io.Serializable;
import java.util.*;

/**
 * MBean
 * <p/>
 * Author: Nicolas Moser
 * Date: 09.08.13
 * Time: 11:14
 */
public class MBean implements Serializable {

	private static final String PROPERTY_TYPE = "type";

	private static final String PROPERTY_NAME = "name";

	private final ObjectInstance objectInstance;

	private final ObjectName objectName;

	private final MBeanInfo mbeanInfo;

	private Map<String, String> properties;

	private List<MBeanAttribute> attributes;

	private List<MBeanOperation> operations;

	private MBeanServer mbeanServer;

	/**
	 * Creates a new MBean instance.
	 *
	 * @param objectInstance
	 * 		the object instance
	 * @param mBeanInfo
	 * 		the mbean information
	 */
	MBean(ObjectInstance objectInstance, MBeanInfo mBeanInfo, MBeanServer mBeanServer) {

		this.objectInstance = objectInstance;
		this.objectName = objectInstance.getObjectName();
		this.mbeanInfo = mBeanInfo;
		this.mbeanServer = mBeanServer;

		this.attributes = new ArrayList<MBeanAttribute>();
		for (MBeanAttributeInfo attributeInfo : this.mbeanInfo.getAttributes()) {
			this.attributes.add(new MBeanAttribute(this, attributeInfo));
		}

		this.operations = new ArrayList<MBeanOperation>();
		for (MBeanOperationInfo operationInfo : this.mbeanInfo.getOperations()) {
			this.operations.add(new MBeanOperation(this, operationInfo));
		}

		this.properties = new HashMap<String, String>();
		this.properties.putAll(objectName.getKeyPropertyList());
	}

	/**
	 * Getter for the MBean name.
	 *
	 * @return the name of the mbean
	 */
	public String getName() {

		return this.objectName.getKeyProperty(PROPERTY_NAME);
	}

	/**
	 * Getter for the canonical MBean name.
	 *
	 * @return the full qualified name of the mbean
	 */
	public String getCanonicalName() {

		return this.objectName.getCanonicalName();
	}

	/**
	 * Getter for the MBean description.
	 *
	 * @return the description of the mbean
	 */
	public String getDescription() {

		return this.mbeanInfo.getDescription();
	}

	/**
	 * Getter for the Domain name.
	 *
	 * @return the name of the mbeans domain
	 */
	public String getDomainName() {

		return this.objectName.getDomain();
	}

	/**
	 * Getter for the MBean type.
	 *
	 * @return the MBean type
	 */
	public String getType() {

		return this.properties.get(PROPERTY_TYPE);
	}

	/**
	 * Getter for the MBean object name.
	 *
	 * @return the object name
	 */
	public ObjectName getObjectName() {

		return this.objectName;
	}

	/**
	 * Getter for the MBean Attributes.
	 *
	 * @return the list of attributes
	 */
	public List<MBeanAttribute> getAttributes() {

		return Collections.unmodifiableList(attributes);
	}

	/**
	 * Getter for the MBean Operations.
	 *
	 * @return the list of operations
	 */
	public List<MBeanOperation> getOperations() {

		return Collections.unmodifiableList(operations);
	}

	/**
	 * Getter for the MBean Properties.
	 *
	 * @return the list of properties
	 */
	public Map<String, String> getProperties() {

		return Collections.unmodifiableMap(properties);
	}

	@Override
	public String toString() {

		return this.getCanonicalName();
	}

	/**
	 * Getter for the mbean server.
	 *
	 * @return the mbean server
	 */
	public MBeanServer getMbeanServer() {

		return mbeanServer;
	}

	/**
	 * Getter for the class name.S
	 *
	 * @return the class name
	 */
	public String getMBeanClass() {

		return this.mbeanInfo.getClassName();
	}
}
