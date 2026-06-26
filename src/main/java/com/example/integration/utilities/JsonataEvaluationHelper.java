package com.example.integration.utilities;

import com.api.jsonata4java.expressions.EvaluateException;
import com.api.jsonata4java.expressions.Expressions;
import com.api.jsonata4java.expressions.ParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public final class JsonataEvaluationHelper {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ConcurrentHashMap<String, Expressions> CACHE = new ConcurrentHashMap<>();

    private JsonataEvaluationHelper() {}

    public static JsonNode evaluate(String expression, Object input) {
        Expressions expr = CACHE.computeIfAbsent(expression, e -> {
            try {
                return Expressions.parse(e);
            } catch (ParseException | IOException ex) {
                throw new RuntimeException("Failed to parse JSONata expression: " + ex.getMessage(), ex);
            }
        });
        try {
            JsonNode inputNode = MAPPER.valueToTree(input);
            return expr.evaluate(inputNode);
        } catch (EvaluateException e) {
            throw new RuntimeException("JSONata evaluation failed: " + e.getMessage(), e);
        }
    }
}
