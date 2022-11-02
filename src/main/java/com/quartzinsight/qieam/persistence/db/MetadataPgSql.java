/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */

package com.quartzinsight.qieam.persistence.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetadataPgSql {

    private static final Logger LOGGER = Logger.getLogger(MetadataPgSql.class.getName());
    private static final String CREATE_GAME_FILE = "src/main/resources/sql/model-pg-games.sql";
    private static String env;

    private MetadataPgSql() {

    }

    public static void resetDb(String environment) {
        env = environment;
        executeQueryFromFile(CREATE_GAME_FILE);
    }

    private static void executeQueryFromFile(String file) {
        DBConnectionPGSQL psql = DBConnectionPGSQL.getInstance(env);
        String query = null;
        try {
            query = String.join(" ", Files.readAllLines(Paths.get(file)));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to read domain metadata file", ex);
        }
        try {
            Statement statement = psql.getConnection().createStatement();
            statement.execute(query);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to resetDB", ex);
        }
    }
}
