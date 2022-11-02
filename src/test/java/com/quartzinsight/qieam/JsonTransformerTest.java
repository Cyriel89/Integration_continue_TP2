/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam;

import com.quartzinsight.qieam.model.Game;
import org.junit.Test;

public class JsonTransformerTest {

    public JsonTransformerTest() {
    }

    @Test
    public void testWriteJsonDomain() throws Exception {
        Game game = new Game("mygame");
        JsonTransformer jsonTransformer = new JsonTransformer();
        System.out.println(jsonTransformer.render(game));
    }

}
