package com.example.integration.services;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AbcFlowService {

    private static final Logger log = LoggerFactory.getLogger(AbcFlowService.class);

    public Object execute(Object request) {
        Object payload = request;

        // Step 1: Logger
        log.info("[{}] hello", "Logger");

        return payload;
    }
}
