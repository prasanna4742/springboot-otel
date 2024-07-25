package org.sc.tracing.tracelinker.repository;

import java.util.List;

import org.sc.tracing.tracelinker.model.TraceLink;
import org.springframework.data.repository.CrudRepository;

public interface TraceLinkRepository extends CrudRepository<TraceLink, Long>{
        
    List<TraceLink> findByExternalEntityNameAndExternalEntityId(String externalEntityName, String externalEntityId);
}
