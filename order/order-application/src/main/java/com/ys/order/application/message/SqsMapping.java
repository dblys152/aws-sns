package com.ys.order.application.message;

import java.util.HashMap;
import java.util.Map;

public class SqsMapping implements MessageQueueMapping {

    private final Map<String, String> queue = new HashMap<>();

    @Override
    public String get(String type) {
        if (!queue.containsKey(type)){
            throw new RuntimeException("Not found Properties in Message queues");
        }
        return queue.get(type);
    }

    public void add(String key, String value) {
        queue.put(key, value);
    }
}

