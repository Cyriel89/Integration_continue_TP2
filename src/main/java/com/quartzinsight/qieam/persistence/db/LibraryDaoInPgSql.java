/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.persistence.db;

import com.quartzinsight.qieam.model.Games;
import com.quartzinsight.qieam.model.Game;
import com.quartzinsight.qieam.service.LibraryService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryDaoInPgSql implements LibraryService {

    private static final Logger LOGGER = Logger.getLogger(LibraryDaoInPgSql.class.getName());
    private static final String GET_GAMES = "";
    private static LibraryDaoInPgSql instance;
    private static String env;

    private LibraryDaoInPgSql() {
    }

    public static LibraryDaoInPgSql getInstance(String environment) {
        env = environment;
        if (instance == null) {
            instance = new LibraryDaoInPgSql();
        }
        return instance;
    }

    @Override
    public Games getGames() {
        Games games = new Games();
        try {
            Statement statement = DBConnectionPGSQL.getInstance(env).getConnection().createStatement();
            ResultSet rs = statement.executeQuery(GET_GAMES);
            while (rs.next()) {
                String strName = rs.getString("name");
                Game game = new Game(strName);
                games.addGame(game);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to get games from DB", ex);
        }
        return games;
    }

}
