package com.dingding.dddaily.repository;

import com.dingding.dddaily.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    boolean existsByEmpUserId(String userId);
   Employee findByEmpUserId(String userId);
  List<Employee>findAllByEmpUserIdIn(List<String>userIds);
}
