package com.example.integration.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.integration.services.AbcFlowService;
import com.example.integration.services.XzyFlowService;

@RestController
public class IntegrationController {

    private final AbcFlowService abcFlowService;
    private final XzyFlowService xzyFlowService;

    public IntegrationController(AbcFlowService abcFlowService,
            XzyFlowService xzyFlowService) {
        this.abcFlowService = abcFlowService;
        this.xzyFlowService = xzyFlowService;
    }

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> abcHandle(@RequestBody(required = false) Object request) {
        Object result = abcFlowService.execute(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "zyx", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> xzyHandle() {
        Object result = xzyFlowService.execute(null);
        return ResponseEntity.ok(result);
    }
}
