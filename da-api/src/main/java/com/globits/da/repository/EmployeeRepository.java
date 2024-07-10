package com.globits.da.repository;

import com.globits.da.domain.Category;
import com.globits.da.domain.Employee;
import com.globits.da.dto.CategoryDto;
import com.globits.da.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select new com.globits.da.dto.EmployeeDto(ed) from Employee ed")
    List<EmployeeDto> getAllEmployee();

    boolean existsByCode(String code);



}
