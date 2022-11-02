/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.persistence.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionPGSQL {

    private static final Logger LOGGER = Logger.getLogger(DBConnectionPGSQL.class.getName());
    private final Connection connection;
    private static DBConnectionPGSQL instance;
    private static String env;

    private DBConnectionPGSQL(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }

    public static DBConnectionPGSQL getInstance(String environment) {
        env = environment;
        if (instance == null) {
            setConnectionPostgreSql();
        }
        return instance;
    }

    private static DBConnectionPGSQL setConnectionPostgreSql() {
        
        String databaseUrl = System.getenv(env);
        if (databaseUrl == null) {
            throw new IllegalStateException("Missing variable environment DATABASE_URL or DATABASE_URL_TEST");
        }
        try {
            URI dbUri = new URI(databaseUrl);
            String[] userInfo = dbUri.getUserInfo().split(":");
            String username = userInfo[0];
            String password = "";
            if (userInfo.length > 1) {
                password = userInfo[1];
            }
            String dbUrl = String.format("jdbc:postgresql://%s:%s%s", dbUri.getHost(), dbUri.getPort(), dbUri.getPath());
            instance = new DBConnectionPGSQL(dbUrl, username, password);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to connect to database", ex);
        } catch (URISyntaxException ex) {
            LOGGER.log(Level.SEVERE, "Unable to parse URL", ex);
        }
        return instance;
    }

}
