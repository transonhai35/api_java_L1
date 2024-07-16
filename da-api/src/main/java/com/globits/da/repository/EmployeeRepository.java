package com.globits.da.repository;

import com.globits.da.domain.Category;
import com.globits.da.domain.Employee;
import com.globits.da.dto.CategoryDto;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDto;
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

    @Query("SELECT new com.globits.da.dto.EmployeeDto(ed) from Employee ed WHERE " +
            "(:#{#dto.getId()} is null or ed.id = :#{#dto.getId()}) AND " +
            "(:#{#dto.getCode()} is null or ed.code = :#{#dto.getCode()}) AND " +
            "(:#{#dto.getName()} is null or ed.name = :#{#dto.getName()}) AND " +
            "(:#{#dto.getEmail()} is null or ed.email = :#{#dto.getEmail()}) AND " +
            "(:#{#dto.getPhone()} is null or ed.phone = :#{#dto.getPhone()}) AND " +
            "(:#{#dto.getAge()} is null or ed.age = :#{#dto.getAge()})")
    List<EmployeeDto> findAllByCriteria(EmployeeSearchDto dto);

    boolean existsByCode(String code);



}
