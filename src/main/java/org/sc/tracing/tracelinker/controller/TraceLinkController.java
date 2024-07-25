package org.sc.tracing.tracelinker.controller;

import org.sc.tracing.tracelinker.model.TraceLink;
import org.sc.tracing.tracelinker.service.TraceLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@RestController
@RequestMapping("/tracelink")
public class TraceLinkController {

    @Autowired
    TraceLinkService traceLinkService;

    @PostMapping
    @WithSpan
    public TraceLink createTraceLink(@RequestBody TraceLink traceLink){
        return traceLinkService.createTraceLink(traceLink);
    }

    @GetMapping
    @WithSpan
    public TraceLink getTraceLinkByExtNameAndExtId(
        @SpanAttribute("extEnityName") @RequestParam("extEnityName") String externalEntityName, 
        @SpanAttribute("extEnityId") @RequestParam("extEntityId") String externalEntityId){
        return traceLinkService.readTraceLinkByExternalEntity(externalEntityName, externalEntityId);
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    @WithSpan
    public ResponseEntity deleteTraceLink(@PathVariable("id") @SpanAttribute("id") Long id ){
        traceLinkService.deleteTraceLink(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}