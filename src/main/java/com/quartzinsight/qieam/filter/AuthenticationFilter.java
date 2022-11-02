/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.filter;

import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import spark.Filter;
import spark.Request;
import spark.Response;
import static spark.Spark.halt;

public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class.getName());

    public AuthenticationFilter() {
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        if (!"OPTIONS".equals(request.requestMethod())) {
            Subject currentUser = SecurityUtils.getSubject();
            final String authorizationHeader = getAuthorizationHeader(request);
            final String encodedAuth = getEncodedAuth(authorizationHeader);
            final String credentials = decodeAuth(encodedAuth);
            final String[] usernamePassword = splitCredentials(credentials);
            final String username = usernamePassword[0];
            final String password = usernamePassword[1];
            if (!currentUser.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                try {
                    currentUser.login(token);
                } catch (AuthenticationException ex) {
                    LOGGER.log(Level.WARNING, String.format("Unable to authenticate user %s", username), ex);
                    response.header("WWW-Authenticate", "Basic");
                    halt(401);
                }
            }
            LOGGER.log(Level.INFO, String.format("Authenticated %s", username));
        }
    }

    private String getAuthorizationHeader(Request request) {
        if (request == null) {
            LOGGER.log(Level.INFO, "Unable to read request because of null value");
            return null;
        }
        return request.headers("Authorization");
    }

    private String getEncodedAuth(String authorizationHeader) {
        if (authorizationHeader == null) {
            LOGGER.log(Level.INFO, "authorizationHeader is null");
            return null;
        }
        return authorizationHeader.substring("Basic ".length());
    }

    private String decodeAuth(String encodedAuth) {
        if (encodedAuth == null) {
            LOGGER.log(Level.INFO, "encodeAuth is null");
            return null;
        }
        final byte[] decodedAuth = Base64.getDecoder().decode(encodedAuth);
        if (decodedAuth == null) {
            LOGGER.log(Level.INFO, "decodedAuth is null");
            return null;
        }
        return new String(decodedAuth);
    }

    private String[] splitCredentials(String credentials) {
        if (credentials == null) {
            LOGGER.log(Level.INFO, "credentials is null");
            return null;
        }
        final String[] usernamePassword = credentials.split(":");
        if (usernamePassword.length != 2) {
            LOGGER.log(Level.INFO, "usernamePassword does not contain only 2 items");
            return null;
        }
        return usernamePassword;
    }

}
