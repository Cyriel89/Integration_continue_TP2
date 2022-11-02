/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Games {

    private List<Game> games;

    public Games() {
        games = new ArrayList<Game>();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public List<Game> getGames() {
        return Collections.unmodifiableList(games);
    }

}