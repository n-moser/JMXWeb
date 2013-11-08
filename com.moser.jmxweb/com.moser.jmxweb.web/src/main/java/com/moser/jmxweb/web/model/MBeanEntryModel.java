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

import java.util.ArrayList;
import java.util.List;

/**
 * MBeanEntryModel
 * <p/>
 * Author: Nicolas Moser
 * Date: 05.11.13
 * Time: 22:13
 */
public class MBeanEntryModel extends MBeanModel {

	private String name;

	private String mBeanClass;

	private String description;

	private List<MBeanAttributeModel> attributes = new ArrayList<MBeanAttributeModel>();

	/** Creates a new MBeanEntryModel instance. */
	public MBeanEntryModel() {

		super(MBeanModelType.ENTRY);
	}

	/**
	 * Getter for the MBean name.
	 *
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * Setter for the MBean name.
	 *
	 * @param name
	 * 		the name to set
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * Getter for the MBean description.
	 *
	 * @return the description
	 */
	public String getmBeanClass() {

		return mBeanClass;
	}

	/**
	 * Setter for the MBean class.
	 *
	 * @param mBeanClass
	 * 		the mBeanClass to set
	 */
	public void setmBeanClass(String mBeanClass) {

		this.mBeanClass = mBeanClass;
	}

	/**
	 * Getter for the MBean description.
	 *
	 * @return the description
	 */
	public String getDescription() {

		return description;
	}

	/**
	 * Setter for the MBean description.
	 *
	 * @param description
	 * 		the description to set
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/**
	 * Getter for the attribute list.
	 *
	 * @return the attribute list
	 */
	public List<MBeanAttributeModel> getAttributes() {

		return attributes;
	}

	/**
	 * Setter for the attribute list.
	 *
	 * @param attributes
	 * 		the attribute list to set
	 */
	protected void setAttributes(List<MBeanAttributeModel> attributes) {

		this.attributes = attributes;
	}
}
