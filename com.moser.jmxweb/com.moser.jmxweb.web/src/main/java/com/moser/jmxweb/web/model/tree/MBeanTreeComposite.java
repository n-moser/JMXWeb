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

package com.moser.jmxweb.web.model.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * MBeanTreeComposite
 * <p/>
 * Author: Nicolas Moser
 * Date: 06.11.13
 * Time: 23:50
 */
public class MBeanTreeComposite {

	private String id;

	private String objectName;

	private String label;

	private MBeanTreeCompositeType type;

	private boolean load_on_demand = true;

	private Set<MBeanTreeComposite> children = new HashSet<MBeanTreeComposite>();

	/**
	 * Getter for the id.
	 *
	 * @return the id
	 */
	public String getId() {

		return id;
	}

	/**
	 * Setter for the id.
	 *
	 * @param id
	 * 		the id to set
	 */
	public void setId(String id) {

		this.id = id;
	}

	/**
	 * Getter for the composite type.
	 *
	 * @return the type
	 */
	public MBeanTreeCompositeType getType() {

		return type;
	}

	/**
	 * Setter for the composite type.
	 *
	 * @param type
	 * 		the composite to set
	 */
	public void setType(MBeanTreeCompositeType type) {

		this.type = type;
	}

	/**
	 * Getter for the MBean Object name.
	 *
	 * @return the object name, or null if it is no mbean
	 */
	public String getObjectName() {

		return objectName;
	}

	/**
	 * Setter for the object name.
	 *
	 * @param objectName
	 * 		the object name to set
	 */
	public void setObjectName(String objectName) {

		this.objectName = objectName;
	}

	/**
	 * Getter for the load_on_demand flag.
	 *
	 * @return <b>true</b> if load on demand is enabled, <b>false</b> if not
	 */
	public boolean isLoad_on_demand() {

		return load_on_demand;
	}

	/**
	 * Setter for the load_on_demand flag.
	 *
	 * @param load_on_demand
	 * 		the flag to set
	 */
	public void setLoad_on_demand(boolean load_on_demand) {

		this.load_on_demand = load_on_demand;
	}

	/**
	 * Getter for the label.
	 *
	 * @return the label
	 */
	public String getLabel() {

		return label;
	}

	/**
	 * Setter for the label.
	 *
	 * @param label
	 * 		the label
	 */
	public void setLabel(String label) {

		this.label = label;
	}

	/**
	 * Getter for the children.
	 *
	 * @return the children
	 */
	public Set<MBeanTreeComposite> getChildren() {

		return children;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		MBeanTreeComposite that = (MBeanTreeComposite) o;

		if (id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {

		return id != null ? id.hashCode() : 0;
	}
}
