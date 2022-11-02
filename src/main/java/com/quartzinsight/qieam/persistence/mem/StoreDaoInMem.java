/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.persistence.mem;

import com.quartzinsight.qieam.model.Game;
import com.quartzinsight.qieam.model.Games;
import com.quartzinsight.qieam.service.StoreService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreDaoInMem implements StoreService {

    private static StoreDaoInMem instance;
    private static final Logger LOGGER = Logger.getLogger(StoreDaoInMem.class.getName());
    private final Games games;

    private StoreDaoInMem() {
        games = new Games();
        games.addGame(new Game("Zelda"));
        games.addGame(new Game("Spiderman"));
        games.addGame(new Game("Anno 1800"));
        games.addGame(new Game("Frostpunk"));
    }

    public static StoreDaoInMem getInstance() {
        if (instance == null) {
            instance = new StoreDaoInMem();
        }
        return instance;
    }

    @Override
    public Games getGames() {
        LOGGER.log(Level.INFO, "Get games");
        return games;
    }

}
