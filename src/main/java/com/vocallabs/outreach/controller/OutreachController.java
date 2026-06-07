package com.vocallabs.outreach.controller;

import com.vocallabs.outreach.dto.DomainRequest;
import com.vocallabs.outreach.service.PipelineService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OutreachController {

    private final PipelineService pipelineService;

    @PostMapping("/run")
    public String runPipeline(
            @RequestBody DomainRequest request) {

        pipelineService.execute(
                request.getDomain());

        return "Pipeline Completed";
    }
}