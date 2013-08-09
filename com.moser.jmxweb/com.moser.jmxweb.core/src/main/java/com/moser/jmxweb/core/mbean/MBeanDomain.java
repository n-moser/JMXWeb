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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MBeanDomain
 * <p/>
 * Author: Nicolas Moser
 * Date: 09.08.13
 * Time: 11:28
 */
public class MBeanDomain {

    private String name;
    private Map<String, List<MBean>> mbeanMap = new HashMap<String, List<MBean>>();

    public MBeanDomain() {
    }

    public MBeanDomain(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MBean> getAllMbeans() {

        List<MBean> mbeans = new ArrayList<MBean>();

        for (Map.Entry<String, List<MBean>> entry : mbeanMap.entrySet()) {
            List<MBean> entryMbeans = entry.getValue();
            if (entryMbeans != null) {
                mbeans.addAll(entryMbeans);
            }
        }

        return mbeans;
    }

    public List<MBean> getMbeans(String type) {
        List<MBean> mBeans = mbeanMap.get(type);

        if (mBeans == null) {
            mBeans = new ArrayList<MBean>();
            this.mbeanMap.put(type, mBeans);
        }

        return mBeans;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
