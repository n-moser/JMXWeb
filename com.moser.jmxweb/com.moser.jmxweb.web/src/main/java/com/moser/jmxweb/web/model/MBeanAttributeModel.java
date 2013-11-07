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

package com.moser.jmxweb.web.model;

/**
 * MBeanEntryModel
 * <p/>
 * Author: Nicolas Moser
 * Date: 05.11.13
 * Time: 22:13
 */
public class MBeanAttributeModel {

	private String name;

	private String type;

	private String description;

	private Object value;

	private boolean readable;

	private boolean writable;

	/**
	 * Getter for the MBean Attribute name.
	 *
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * Setter for the MBean Attribute name.
	 *
	 * @param name
	 * 		the name to set
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * Getter for the MBean Attribute type.
	 *
	 * @return the type
	 */
	public String getType() {

		return type;
	}

	/**
	 * Setter for the MBean Attribute type.
	 *
	 * @param type
	 * 		the type to set
	 */
	public void setType(String type) {

		this.type = type;
	}

	/**
	 * Getter for the MBean Attribute description.
	 *
	 * @return the description
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * Setter for the MBean Attribute description.
	 *
	 * @param description
	 * 		the name to description
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/**
	 * Getter for the MBean Attribute value.
	 *
	 * @return the value
	 */
	public Object getValue() {

		return value;
	}

	/**
	 * Setter for the MBean Attribute value.
	 *
	 * @param value
	 * 		the name to value
	 */
	public void setValue(Object value) {

		this.value = value;
	}

	/**
	 * Getter for the MBean Attribute readable flag.
	 *
	 * @return <b>true</b> if the attribute is readanly, <b>false</b> if not
	 */
	public boolean isReadable() {

		return readable;
	}

	/**
	 * Setter for the MBean Attribute readable flag.
	 *
	 * @param readable
	 * 		the readable flag
	 */
	public void setReadable(boolean readable) {

		this.readable = readable;
	}

	/**
	 * Getter for the MBean Attribute writable flag.
	 *
	 * @return <b>true</b> if the attribute is writable, <b>false</b> if not
	 */
	public boolean isWritable() {

		return writable;
	}

	/**
	 * Setter for the MBean Attribute writable flag.
	 *
	 * @param writable
	 * 		the writable flag
	 */
	public void setWritable(boolean writable) {

		this.writable = writable;
	}
}
