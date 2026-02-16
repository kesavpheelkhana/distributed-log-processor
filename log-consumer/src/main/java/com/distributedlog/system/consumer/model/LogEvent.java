package com.distributedlog.system.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_events")
@Setter
@Getter
@AllArgsConstructor
public class LogEvent {
    @Id
    private String id;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "source")
    private String source;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;
}
