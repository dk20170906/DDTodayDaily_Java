package com.dingding.dddaily.repository;

import com.dingding.dddaily.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepRepository extends JpaRepository<Department,Integer> {
    boolean existsByDepId(Long depId);
}
