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

import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MBeanOperation
 * <p/>
 * Author: Nicolas Moser
 * Date: 09.08.13
 * Time: 11:15
 */
public class MBeanOperation {

    private final MBeanOperationInfo info;
    private final MBean mBean;
    private List<MBeanParameter> parameters;

    /**
     * Creates a new MBean operation instance.
     *
     * @param mBean         the MBean instance
     * @param operationInfo the operation information
     */
    MBeanOperation(MBean mBean, MBeanOperationInfo operationInfo) {

        this.mBean = mBean;
        this.info = operationInfo;

        this.parameters = new ArrayList<MBeanParameter>();
        for (MBeanParameterInfo parameterInfo : this.info.getSignature()) {
            this.parameters.add(new MBeanParameter(parameterInfo));
        }
    }

    /**
     * Getter for the operation name.
     *
     * @return the name
     */
    public String getName() {
        return this.info.getName();
    }

    /**
     * Getter for the operation description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.info.getDescription();
    }

    /**
     * Getter for the operation type.
     *
     * @return the return type
     */
    public String getReturnType() {
        return this.info.getReturnType();
    }

    /**
     * Getter for the operation parameters.
     *
     * @return the unmodifiable list of parameters
     */
    public List<MBeanParameter> getParameters() {
        return Collections.unmodifiableList(this.parameters);
    }

    /**
     * Invoke the MBean Operation with the given arguments.
     *
     * @param arguments the invocation arguments
     * @return the operation result
     * @throws MBeanInvocationException when the operation cannot be invoked or raised an exception
     */
    public Object invoke(Object... arguments) throws MBeanInvocationException {
        MBeanServer mbeanServer = this.mBean.getMbeanServer();
        ObjectName objectName = mBean.getObjectName();

        try {
            String[] parameters = new String[this.parameters.size()];
            for (int i = 0; i < parameters.length; i++) {
                MBeanParameter parameter = this.parameters.get(i);

                if (parameter != null) {
                    parameters[i] = parameter.getName();
                }
            }

            return mbeanServer.invoke(objectName, this.getName(), arguments, parameters);
        } catch (Exception e) {
            throw new MBeanInvocationException("Error retrieving Attribute value of " + this.getName() + ".", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (this.getReturnType() == null) {
            result.append("void");
        } else {
            result.append(this.getReturnType());
        }

        result.append(' ');
        result.append(this.getName());
        result.append(' ');
        result.append('(');

        List<MBeanParameter> parameters = this.getParameters();
        for (int i = 0; i < parameters.size(); i++) {
            MBeanParameter parameter = parameters.get(i);
            result.append(parameter);

            if (i < parameters.size() - 1) {
                result.append(',');
                result.append(' ');
            }
        }

        result.append(')');

        return result.toString();
    }
}
