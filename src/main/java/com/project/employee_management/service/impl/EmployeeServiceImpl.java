package com.project.employee_management.service.impl;

import com.project.employee_management.dto.EmployeeDto;
import com.project.employee_management.entity.Department;
import com.project.employee_management.entity.Employee;
import com.project.employee_management.exception.BadRequestException;
import com.project.employee_management.exception.ResourceNotFoundException;
import com.project.employee_management.repository.DepartmentRepository;
import com.project.employee_management.repository.EmployeeRepository;
import com.project.employee_management.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository,
                               ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto addEmployee(Long departmentId, EmployeeDto employeeDto) {
        Department department = departmentRepository.findById((departmentId)).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with id " + departmentId));
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setDepartment(department);
        Employee saved = employeeRepository.save(employee);
        EmployeeDto dto = modelMapper.map(saved, EmployeeDto.class);
        return dto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with id " + departmentId));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id " + employeeId));

        if(!employee.getDepartment().getDepartmentId().equals(departmentId)){
            throw new BadRequestException("Employee's department id does not match department Id");
        }

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployees(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with id " + departmentId));
        List<Employee> employees = employeeRepository.findByDepartmentDepartmentId(departmentId);
        List<EmployeeDto> employeeDtos = employees.stream().map((e) ->
                modelMapper.map(e, EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public EmployeeDto updateEmployee(Long departmentId, Long employeeId, EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with id " + departmentId));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id " + employeeId));
        if(!employee.getDepartment().getDepartmentId().equals(departmentId)){
            throw new BadRequestException("Employee's department id does not match department Id");
        }
//        Employee employee1 = modelMapper.map(employeeDto, Employee.class);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee saved = employeeRepository.save(employee);
        EmployeeDto dto = modelMapper.map(saved, EmployeeDto.class);
        return dto;
    }

    @Override
    public void deleteEmployee(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with id " + departmentId));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id " + employeeId));
        if(!employee.getDepartment().getDepartmentId().equals(departmentId)){
            throw new BadRequestException("Employee's department id does not match department Id");
        }
        employeeRepository.delete(employee);
    }
}
