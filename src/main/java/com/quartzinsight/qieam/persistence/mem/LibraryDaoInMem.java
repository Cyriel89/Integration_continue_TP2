/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.persistence.mem;

import com.quartzinsight.qieam.model.Game;
import com.quartzinsight.qieam.model.Games;
import com.quartzinsight.qieam.service.LibraryService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryDaoInMem implements LibraryService {

    private static LibraryDaoInMem instance;
    private static final Logger LOGGER = Logger.getLogger(LibraryDaoInMem.class.getName());
    private final Games games;

    private LibraryDaoInMem() {
        games = new Games();
        games.addGame(new Game("Call of Duty"));
        games.addGame(new Game("Battlefield"));
        games.addGame(new Game("Homeworld"));
        games.addGame(new Game("Max Payne"));
    }

    public static LibraryDaoInMem getInstance() {
        if (instance == null) {
            instance = new LibraryDaoInMem();
        }
        return instance;
    }

    @Override
    public Games getGames() {
        LOGGER.log(Level.INFO, "Get games");
        return games;
    }

}
