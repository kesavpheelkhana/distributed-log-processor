package com.distributedlog.system.consumer.repository;

import com.distributedlog.system.consumer.model.LogEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LogEventRepository extends JpaRepository<LogEvent,String> {

    List<LogEvent> findByOrganizationIdAndTimestampBetween (String orgid, LocalDateTime start,
                                                            LocalDateTime end);

    @Query("SELECT COUNT(l) FROM LogEvent l WHERE l.organizationId = :orgId AND l.level = :level")
    long countByOrganizationIdAndLevel(@Param("orgId") String organizationId, @Param("level") String level);

    List<LogEvent> findByLevelAndTimestampAfter(String level, LocalDateTime timestamp);

}
