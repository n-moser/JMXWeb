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

import javax.management.MBeanParameterInfo;
import java.io.Serializable;

/**
 * MBeanParameter
 * <p/>
 * Author: Nicolas Moser
 * Date: 09.08.13
 * Time: 11:18
 */
public class MBeanParameter implements Serializable {

	private final MBeanParameterInfo info;

	/**
	 * Creates a new MBeanParameter instance.
	 *
	 * @param parameterInfo
	 * 		the parameter information
	 */
	MBeanParameter(MBeanParameterInfo parameterInfo) {

		this.info = parameterInfo;
	}

	/**
	 * Getter for teh parameter name.
	 *
	 * @return the name
	 */
	public String getName() {

		return this.info.getName();
	}

	/**
	 * Getter for the parameter type.
	 *
	 * @return the type
	 */
	public String getType() {

		return this.info.getType();
	}

	/**
	 * Getter for the parameter description.
	 *
	 * @return the description
	 */
	public String getDescription() {

		return this.info.getDescription();
	}

	@Override
	public String toString() {

		return this.getType() + " " + this.getName();
	}
}
