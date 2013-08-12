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

package com.moser.jmxweb.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * SLF4J Logger Implementation.
 * <p/>
 * User: Nicolas Moser
 * Date: 07.08.13
 * Time: 08:43
 */
class Slf4jLogger implements JMXWebLogger {

    private final Logger logger;

    /**
     * Creates a new Slf4jLogger instance.
     *
     * @param loggingClass the logging class
     */
    public Slf4jLogger(Class<?> loggingClass) {
        this.logger = LoggerFactory.getLogger(loggingClass);
    }

    @Override
    public void error(String message, Object... parameters) {
        this.logger.error(message, this.createParameters(null, parameters));
    }

    @Override
    public void error(Exception exception, String message, Object... parameters) {
        this.logger.error(message, this.createParameters(exception, parameters));
    }

    @Override
    public void warn(String message, Object... parameters) {
        this.logger.warn(message, this.createParameters(null, parameters));
    }

    @Override
    public void warn(Exception exception, String message, Object... parameters) {
        this.logger.warn(message, this.createParameters(exception, parameters));
    }

    @Override
    public void info(String message, Object... parameters) {
        this.logger.info(message, this.createParameters(null, parameters));
    }

    @Override
    public void info(Exception exception, String message, Object... parameters) {
        this.logger.info(message, this.createParameters(exception, parameters));
    }

    @Override
    public void debug(String message, Object... parameters) {
        this.logger.debug(message, this.createParameters(null, parameters));
    }

    @Override
    public void debug(Exception exception, String message, Object... parameters) {
        this.logger.debug(message, this.createParameters(exception, parameters));
    }

    @Override
    public void trace(String message, Object... parameters) {
        this.logger.trace(message, this.createParameters(null, parameters));
    }

    @Override
    public void trace(Exception exception, String message, Object... parameters) {
        this.logger.trace(message, this.createParameters(exception, parameters));
    }

    /**
     * Create the parameters array.
     *
     * @param exception  an optional exception
     * @param parameters the original parameters
     * @return the new parameters
     */
    private Object[] createParameters(Exception exception, Object[] parameters) {
        Object[] clonedParameters = Arrays.copyOf(parameters, parameters.length + 1);

        clonedParameters[clonedParameters.length - 1] = exception;

        return clonedParameters;
    }

}
