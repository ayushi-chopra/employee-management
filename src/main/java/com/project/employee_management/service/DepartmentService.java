package com.project.employee_management.service;

import com.project.employee_management.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long departmentId);
    List<DepartmentDto> getAllDepartrments();
    DepartmentDto updateDepartment(Long departmentId,DepartmentDto departmentDto);
    void deleteDepartment(Long departmentId);

}
