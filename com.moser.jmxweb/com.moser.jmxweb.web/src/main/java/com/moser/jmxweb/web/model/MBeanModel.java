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
 * MBeanModel
 * <p/>
 * Author: Nicolas Moser
 * Date: 06.11.13
 * Time: 11:58
 */
public abstract class MBeanModel {

	private String type;

	private MBeanModelType modelType;

	/**
	 * Creates a new MBeanModel instance for the given model type.
	 *
	 * @param modelType
	 * 		the mbean model type
	 */
	protected MBeanModel(MBeanModelType modelType) {

		this.modelType = modelType;
	}

	/**
	 * Getter for the MBean type.
	 *
	 * @return the type
	 */
	public String getType() {

		return type;
	}

	/**
	 * Setter for the MBean type.
	 *
	 * @param type
	 * 		the type to set
	 */
	public void setType(String type) {

		this.type = type;
	}

	/**
	 * Getter for the MBean model type.
	 *
	 * @return the model type
	 */
	public MBeanModelType getModelType() {

		return this.modelType;
	}


}
