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

import com.moser.jmxweb.core.logger.JMXWebLogger;
import com.moser.jmxweb.core.logger.JMXWebLoggerFactory;

import javax.management.Attribute;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.Serializable;

/**
 * MBeanAttribute
 * <p/>
 * Author: Nicolas Moser
 * Date: 09.08.13
 * Time: 11:15
 */
public class MBeanAttribute implements Serializable {

	private final MBeanAttributeInfo info;

	private final MBean mBean;

	private final Class<?> type;

	private JMXWebLogger logger = JMXWebLoggerFactory.getLogger(MBeanAttribute.class);

	/**
	 * Creates a new MBeanAttribute instance.
	 *
	 * @param mBean
	 * 		the parent mbean
	 * @param attributeInfo
	 * 		the attribute information
	 */
	MBeanAttribute(MBean mBean, MBeanAttributeInfo attributeInfo) {

		this.mBean = mBean;
		this.info = attributeInfo;

		Class<?> typeClass = null;
		try {
			typeClass = Class.forName(attributeInfo.getType());
		} catch (Exception e) {
			logger.warn("Cannot load Type for Attribute {} of MBean {}.", attributeInfo.getName(), mBean.getName());
		}
		this.type = typeClass;
	}

	/**
	 * Checks whether the attribute is readable or not.
	 *
	 * @return <b>true</b> if the attribute is readable, <b>false</b> if not
	 */
	public boolean isReadable() {

		return this.info.isReadable();
	}

	/**
	 * Checks whether the attribute is writable or not.
	 *
	 * @return <b>true</b> if the attribute is writable, <b>false</b> if not
	 */
	public boolean isWritable() {

		return this.info.isWritable();
	}

	/**
	 * Getter for the attribute name.
	 *
	 * @return name of the attribute
	 */
	public String getName() {

		return this.info.getName();
	}

	/**
	 * Getter for the attribute type.
	 *
	 * @return type of the attribute
	 */
	public String getType() {

		if (this.type == null) {
			return this.info.getType();
		}

		return this.type.getCanonicalName();
	}

	/**
	 * Getter for the attribute description.
	 *
	 * @return the attribute description.
	 */
	public String getDescription() {

		return this.info.getDescription();
	}

	/**
	 * Getter for the attribute value.
	 *
	 * @return the attribute value.
	 *
	 * @throws MBeanInvocationException
	 * 		when the attribute cannot be retrieved
	 */
	public Object getValue() throws MBeanInvocationException {

		MBeanServer mbeanServer = this.mBean.getMbeanServer();
		ObjectName objectName = mBean.getObjectName();
		try {
			return mbeanServer.getAttribute(objectName, this.getName());
		} catch (Exception e) {
			throw new MBeanInvocationException("Error retrieving Attribute value of " + this.getName() + ".", e);
		}
	}

	/**
	 * Setter for the attribute value.
	 *
	 * @param value
	 * 		the attribute value to set
	 *
	 * @throws MBeanInvocationException
	 * 		when the attribute cannot be set
	 */
	public void setValue(Object value) throws MBeanInvocationException {

		MBeanServer mbeanServer = this.mBean.getMbeanServer();
		ObjectName objectName = mBean.getObjectName();
		try {
			Attribute attribute = new Attribute(this.getName(), value);
			mbeanServer.setAttribute(objectName, attribute);
		} catch (Exception e) {
			throw new MBeanInvocationException("Error setting Attribute value of " + this.getName() + ".", e);
		}
	}

	/**
	 * Getter for the attribute value as string.
	 *
	 * @return the attribute value as string, if the value is null a 'null' string is returned
	 */
	public String getValueAsString() {

		try {
			Object value = this.getValue();

//			if (this.type != null) {
//				if (this.type.isArray()) {
//					Object[] array = (Object[]) value;
//					StringBuilder result = new StringBuilder();
//					result.append("[");
//					for (Object entry : array) {
//						result.append(String.valueOf(entry));
//						result.append(", ");
//					}
//					result.append("]");
//					return result.toString();
//				}
//			}

			return String.valueOf(value);

		} catch (MBeanInvocationException e) {
			logger.error("Error retrieving value of MBean Attribute {}.", this.getName(), e);
		}

		return "null";
	}

	@Override
	public String toString() {

		return this.getType() + " " + this.getName();
	}
}
