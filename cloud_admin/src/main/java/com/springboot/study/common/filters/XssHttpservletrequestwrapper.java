/**
 * Copyright (C) 2014 Minghao Technology. All rights reserved.
 *
 * @Title:XssHttpServletRequestWrapper.java
 * @Package:com.minghao.common.filte
 * @Date:2016-06-02 17:32:01
 *
 * @Version V1.0
 */
package com.springboot.study.common.filters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 将传入的数据过滤/包装类
 * @author Administrator
 */
public class XssHttpservletrequestwrapper extends HttpServletRequestWrapper {

    public XssHttpservletrequestwrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {

        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public String getParameter(String parameter) {

        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public String[] getParameterValues(String parameter) {

        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    /**
     * xss处理
     *
     * @param value
     * @return
     */
    private String cleanXSS(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }

        /*value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");*/
        String s = HtmlUtils.htmlEscape(value);
        return s;
    }
}