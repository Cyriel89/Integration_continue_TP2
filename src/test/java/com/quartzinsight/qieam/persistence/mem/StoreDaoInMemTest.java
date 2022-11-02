/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.persistence.mem;

import com.quartzinsight.qieam.model.Game;
import com.quartzinsight.qieam.model.Games;
import junit.framework.Assert;
import org.junit.Test;

public class StoreDaoInMemTest {

    public StoreDaoInMemTest() {
    }

    @Test
    public void testStoreGetGames() {
        StoreDaoInMem instance = StoreDaoInMem.getInstance();
        Games expected = new Games();
        expected.addGame(new Game("Zelda"));
        expected.addGame(new Game("Spiderman"));
        expected.addGame(new Game("Anno 1800"));
        expected.addGame(new Game("Frostpunk"));
        Games result = instance.getGames();
        Assert.assertTrue(expected.getGames().containsAll(result.getGames()));
    }

}
