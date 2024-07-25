package org.sc.tracing.tracelinker.service;

import java.util.List;

import org.sc.tracing.tracelinker.model.TraceLink;
import org.sc.tracing.tracelinker.repository.TraceLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@Service
public class TraceLinkServiceImpl implements TraceLinkService {

    @Autowired
    TraceLinkRepository traceLinkRepository;

    @Override
    @WithSpan
    public TraceLink createTraceLink(TraceLink traceLink) {
        return traceLinkRepository.save(traceLink);
    }

    @Override
    @WithSpan
    public TraceLink readTraceLinkByExternalEntity(
        @SpanAttribute("externalEntityName") String externalEntityName, 
        @SpanAttribute("externalEntityId") String externalEntityId) {
        List<TraceLink> traceLinkList = traceLinkRepository.findByExternalEntityNameAndExternalEntityId(externalEntityName, externalEntityId);
        return CollectionUtils.isEmpty(traceLinkList)?null:traceLinkList.get(0);
    }

    @Override
    @WithSpan
    public void deleteTraceLink(@SpanAttribute("id") Long id) {
        traceLinkRepository.deleteById(id);
    }
}
