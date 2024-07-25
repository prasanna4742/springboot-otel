package org.sc.tracing.tracelinker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"externalEntityName", "externalEntityId"})
}) 
@Entity
@Data
public class TraceLink {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String traceId;

    @Column    
    private String externalEntityName;

    @Column    
    private String externalEntityId;

    @Column
    private String traceContext;
}