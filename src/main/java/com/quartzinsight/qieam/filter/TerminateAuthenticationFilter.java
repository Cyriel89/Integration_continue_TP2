/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import spark.Filter;
import spark.Request;
import spark.Response;

public class TerminateAuthenticationFilter implements Filter {

    @Override
    public void handle(Request request, Response response) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }
    }

}
