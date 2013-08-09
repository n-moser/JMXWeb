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

import java.util.*;

/**
 * MBean
 * <p/>
 * Author: Nicolas Moser
 * Date: 09.08.13
 * Time: 11:14
 */
public class MBean {

    private String name;
    private Map<String, String> properties;
    private List<MBeanAttribute> attributes;
    private List<MBeanOperation> operations;


    public MBean() {
        this(null);
    }

    public MBean(Map<String, String> properties) {
        if (properties == null) {
            this.properties = Collections.emptyMap();
        } else {
            this.properties = new HashMap<String, String>(properties);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MBeanAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<MBeanAttribute>();
        }
        return attributes;
    }

    public List<MBeanOperation> getOperations() {
        if (operations == null) {
            operations = new ArrayList<MBeanOperation>();
        }
        return operations;
    }

    public Map<String, String> getProperties() {
        return new HashMap<String, String>(properties);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
