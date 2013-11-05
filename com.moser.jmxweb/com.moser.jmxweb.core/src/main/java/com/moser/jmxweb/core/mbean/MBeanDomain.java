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

import java.io.Serializable;
import java.util.*;

/**
 * MBeanDomain
 * <p/>
 * Author: Nicolas Moser
 * Date: 09.08.13
 * Time: 11:28
 */
public class MBeanDomain implements Serializable {

	private String name;

	private int mbeanCount;

	private Map<String, List<MBean>> mbeanMap;

	/**
	 * Creates a new domain for the given name.
	 *
	 * @param name
	 * 		the domain name
	 */
	MBeanDomain(String name) {

		this.name = name;
		this.mbeanMap = new HashMap<String, List<MBean>>();
	}

	/**
	 * Getter for the domain Name.
	 *
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * Returns an unmodifyable list of all contained MBean instances.
	 *
	 * @return the list of MBeans for the given type
	 */
	public List<MBean> getAllMbeans() {

		List<MBean> mbeans = new ArrayList<MBean>();

		for (Map.Entry<String, List<MBean>> entry : mbeanMap.entrySet()) {
			List<MBean> entryMbeans = entry.getValue();
			if (entryMbeans != null) {
				mbeans.addAll(entryMbeans);
			}
		}

		return Collections.unmodifiableList(mbeans);
	}

	/**
	 * Returns an unmodifyable list of all contained MBean instances of the given type.
	 *
	 * @param type
	 * 		the MBean type
	 *
	 * @return the list of MBeans for the given type
	 */
	public List<MBean> getMbeans(String type) {

		List<MBean> mBeans = mbeanMap.get(type);

		if (mBeans == null) {
			mBeans = new ArrayList<MBean>();
			this.mbeanMap.put(type, mBeans);
		}

		return Collections.unmodifiableList(mBeans);
	}

	/**
	 * Stores the given MBean in the domain.
	 *
	 * @param mbean
	 * 		the mbean to add
	 */
	void putMBean(MBean mbean) {

		List<MBean> mBeans = mbeanMap.get(mbean.getType());

		if (mBeans == null) {
			mBeans = new ArrayList<MBean>();
			this.mbeanMap.put(mbean.getType(), mBeans);
		}

		mBeans.add(mbean);

		this.mbeanCount++;
	}

	@Override
	public String toString() {

		return this.getName() + " (" + mbeanCount + ")";
	}
}
