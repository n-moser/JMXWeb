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

import javax.management.Attribute;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * MBeanAttribute
 * <p/>
 * Author: Nicolas Moser
 * Date: 09.08.13
 * Time: 11:15
 */
public class MBeanAttribute {

    private final MBeanAttributeInfo info;
    private final MBean mBean;

    public MBeanAttribute(MBean mBean, MBeanAttributeInfo attributeInfo) {
        this.mBean = mBean;
        this.info = attributeInfo;
    }

    public boolean isReadable() {
        return this.info.isReadable();
    }

    public boolean isWritable() {
        return this.info.isWritable();
    }

    public String getName() {
        return this.info.getName();
    }

    public String getType() {
        return this.info.getType();
    }

    public String getDescription() {
        return this.info.getDescription();
    }

    public Object getValue() throws MBeanInvocationException {
        MBeanServer mbeanServer = this.mBean.getMbeanServer();
        ObjectName objectName = mBean.getObjectName();
        try {
            return mbeanServer.getAttribute(objectName, this.getName());
        } catch (Exception e) {
            throw new MBeanInvocationException("Error retrieving Attribute value of " + this.getName() + ".", e);
        }
    }

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

    @Override
    public String toString() {
        return this.getType() + " " + this.getName();
    }
}
