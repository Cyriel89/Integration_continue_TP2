/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.controller;

import com.quartzinsight.qieam.service.FriendService;
import com.quartzinsight.qieam.persistence.mem.FriendDaoInMem;
import com.quartzinsight.qieam.persistence.db.FriendDaoInPgSql;
import spark.Request;
import spark.Response;
import spark.Route;

public class FriendController {

    public final Route getFriends;

    private final FriendService friendService;
    private static final String env = "DATABASE_URL";

    public FriendController(boolean useJdbc) {
        if (useJdbc) {
            friendService = FriendDaoInPgSql.getInstance(env);
        } else {
            friendService = FriendDaoInMem.getInstance();
        }

        getFriends = (Request request, Response response) -> {
            return friendService.getFriends();
        };

    }
}
