package com.project.employee_management.service.impl;

import com.project.employee_management.dto.DepartmentDto;
import com.project.employee_management.entity.Department;
import com.project.employee_management.exception.ResourceNotFoundException;
import com.project.employee_management.repository.DepartmentRepository;
import com.project.employee_management.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository=departmentRepository;
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department saved = departmentRepository.save(department);
        return modelMapper.map(saved,DepartmentDto.class);
//        // Debug print: check if value really coming from controller
//        System.out.println("DTO Name = " + departmentDto.getDepartmentName());
//        System.out.println("DTO Desc = " + departmentDto.getDepartmentDescription());
//
//        Department department = new Department();
//        department.setDepartmentName(departmentDto.getDepartmentName());
//        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
//
//        // Debug print before save
//        System.out.println("Entity Name BEFORE SAVE = " + department.getDepartmentName());
//        System.out.println("Entity Desc BEFORE SAVE = " + department.getDepartmentDescription());
//
//        Department saved = departmentRepository.save(department);
//
//      return  new DepartmentDto(saved.getDepartmentId(),saved.getDepartmentName(),
//                saved.getDepartmentDescription());
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with id "+ departmentId));
        return modelMapper.map(department,DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAllDepartrments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = departments.stream().map((d) -> modelMapper.map(d, DepartmentDto.class)).collect(Collectors.toList());
        return departmentDtoList;
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentId).
                orElseThrow(() -> new ResourceNotFoundException("Department not found with id "+ departmentId));
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        Department saved = departmentRepository.save(department);
        return modelMapper.map((saved),DepartmentDto.class);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with id "+ departmentId));
        departmentRepository.delete(department);
    }
}
