/**
 * Copyright (C) 2016 Minghao Technology. All rights reserved.
 * @author Administrator
 *
 */
package com.springboot.study.common.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 处理xss及SQL脚本攻击过滤器类
 * @author Administrator
 */
@WebFilter(filterName = "XssFilter", urlPatterns = "/*")
public class XssFilter implements Filter {

    FilterConfig filterConfig = null;

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ServletRequest xssWrapper = new XssHttpservletrequestwrapper((HttpServletRequest) request);
        chain.doFilter(xssWrapper, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        filterConfig = arg0;
    }

}
