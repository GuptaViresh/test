package com.example.integration.services;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.integration.utilities.JsonataEvaluationHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class XzyFlowService {

    private static final Logger log = LoggerFactory.getLogger(XzyFlowService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Object execute(Object request) {
        Object payload = request;

        // Step 1: Logger
        log.info("[{}] hey", "Logger");

        // Step 2: Logger
        log.info("[{}] {}", "Logger", JsonataEvaluationHelper.evaluate("$", payload));

        // Step 3: Transform: Transform
try {
    payload = com.example.integration.utilities.JsonataEvaluationHelper.evaluate("$", payload);
} catch (RuntimeException e) {
    throw new RuntimeException("Transform failed at step 'Transform': " + e.getMessage(), e);
}

        return payload;
    }
}
