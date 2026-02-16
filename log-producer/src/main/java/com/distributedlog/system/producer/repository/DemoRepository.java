package com.distributedlog.system.producer.repository;

import com.distributedlog.system.producer.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemoRepository  extends JpaRepository<DemoEntity, UUID> {

}
