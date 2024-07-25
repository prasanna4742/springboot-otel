package org.sc.tracing.tracelinker.service;

import org.sc.tracing.tracelinker.model.TraceLink;

public interface TraceLinkService {
 
    public TraceLink createTraceLink(TraceLink traceLink);

    public TraceLink readTraceLinkByExternalEntity(
        String externalEntityName, String externalEntityId);
    
    public void deleteTraceLink(Long id);    
    
}
