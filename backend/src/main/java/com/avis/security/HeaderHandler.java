package com.avis.security;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//questa classe si occupa di gestire le CROS policy, chiamata prima di mandare una response 
@Component
public class HeaderHandler {

    static final String ALLOW_METHODS = "Access-Control-Allow-Methods";
    static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    static final String ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    static final String ALLOW_HEADERS = "Access-Control-Allow-Headers";
    static final String OPTIONS = "OPTIONS";
    static final String OK = "OK";
    static final String REQUEST_HEADERS = "Access-Control-Request-Headers";
    static final String STAR = "*";
    static final String TRUE = "true";

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader(ALLOW_ORIGIN, STAR);
        response.setHeader(ALLOW_CREDENTIALS, TRUE);
        response.setHeader(ALLOW_HEADERS,  request.getHeader(REQUEST_HEADERS));
        response.setHeader(ALLOW_METHODS, "POST, GET, PUT, OPTIONS, DELETE");
        if (request.getMethod().equals(OPTIONS)) {
            response.getWriter().print(OK);
            response.getWriter().flush();
        }
    }
}
