package com.vgalloy.server.aspect.security;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 16/12/15.
 */
@Component
public class SecurityFilter implements Filter {

    @Autowired
    private SecurityApi securityApi;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nothing to do
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String username = null;
        String password = null;

        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Basic")) {
            String base64Credentials = authorization.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
            String[] values = credentials.split(":", 2);
            username = values[0];
            password = values[1];
        }

        securityApi.setUser(username, password);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // Nothing to do
    }
}
