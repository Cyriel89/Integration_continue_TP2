/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam.persistence.db;

import com.quartzinsight.qieam.model.Game;
import com.quartzinsight.qieam.model.Games;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class StoreDaoInPgSqlTest {

    private static final String env = "DATABASE_URL_TEST";

    public StoreDaoInPgSqlTest() {
    }

    @Before
    public void resetDb() {
        MetadataPgSql.resetDb(env);
    }

    @Ignore
    @Test
    public void testGetGames() {
        StoreDaoInPgSql instance = StoreDaoInPgSql.getInstance(env);
        Games expected = new Games();
        Games result = instance.getGames();
        Assert.assertEquals(expected, result);
    }

}
