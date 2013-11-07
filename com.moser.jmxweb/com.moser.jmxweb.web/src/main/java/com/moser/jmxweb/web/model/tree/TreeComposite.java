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
 * TreeComposite
 * <p/>
 * Author: Nicolas Moser
 * Date: 06.11.13
 * Time: 23:50
 */
public class TreeComposite {

	private String id;

	private String label;

	private boolean load_on_demand = true;

	private Set<TreeComposite> children = new HashSet<TreeComposite>();

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public boolean isLoad_on_demand() {

		return load_on_demand;
	}

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
	public Set<TreeComposite> getChildren() {

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

		TreeComposite that = (TreeComposite) o;

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
