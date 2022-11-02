/*
 *  Copyright (c) 2017-2027 Quartz Insight
 *  This file is part of projects developed by Quartz Insight.
 *  Projects developed by Quartz Insight can not be copied and/or distributed without the express permission of Quartz Insight.
 */
package com.quartzinsight.qieam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private final Gson gson;

    public JsonTransformer() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }

}
