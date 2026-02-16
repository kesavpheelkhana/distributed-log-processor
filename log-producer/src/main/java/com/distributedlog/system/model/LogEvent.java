package com.distributedlog.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class LogEvent {
    private String id;
    private String organizationId;
    private String level;
    private String message;
    private String source;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public LogEvent() {
        this.timestamp = LocalDateTime.now();
    }

    public LogEvent(String organizationId, String level, String message, String source) {
        this();
        this.organizationId = organizationId;
        this.level = level;
        this.message = message;
        this.source = source;
        this.id = java.util.UUID.randomUUID().toString();
    }
}
