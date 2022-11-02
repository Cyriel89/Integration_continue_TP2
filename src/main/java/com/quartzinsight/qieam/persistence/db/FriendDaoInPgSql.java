/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.persistence.db;

import com.quartzinsight.qieam.model.Friends;
import com.quartzinsight.qieam.model.Friend;
import com.quartzinsight.qieam.service.FriendService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FriendDaoInPgSql implements FriendService {

    private static final Logger LOGGER = Logger.getLogger(FriendDaoInPgSql.class.getName());
    private static final String GET_FRIENDS = "";
    private static FriendDaoInPgSql instance;
    private static String env;

    private FriendDaoInPgSql() {
    }

    public static FriendDaoInPgSql getInstance(String environment) {
        env = environment;
        if (instance == null) {
            instance = new FriendDaoInPgSql();
        }
        return instance;
    }

    @Override
    public Friends getFriends() {
        Friends friends = new Friends();
        try {
            Statement statement = DBConnectionPGSQL.getInstance(env).getConnection().createStatement();
            ResultSet rs = statement.executeQuery(GET_FRIENDS);
            while (rs.next()) {
                String strName = rs.getString("name");
                Friend friend = new Friend(strName);
                friends.addFriend(friend);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to get friends from DB", ex);
        }
        return friends;
    }

}
