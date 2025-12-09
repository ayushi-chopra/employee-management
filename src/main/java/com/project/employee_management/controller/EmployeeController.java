package com.project.employee_management.controller;

import com.project.employee_management.dto.EmployeeDto;
import com.project.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PostMapping("/{id}/employees")
    public ResponseEntity<EmployeeDto> addEmployee(@PathVariable("id") Long departmentId,
                                                   @RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.addEmployee(departmentId,employeeDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long departmentId,
                                                       @PathVariable Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(departmentId, employeeId);
    return  ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@PathVariable("id") Long departmentId){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees(departmentId);
        return ResponseEntity.ok(allEmployees);
    }

    @PutMapping("/{id}/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long departmentId,
                                                      @PathVariable Long employeeId,
                                                      @RequestBody EmployeeDto employeeDto){
        EmployeeDto dto = employeeService.updateEmployee(departmentId, employeeId, employeeDto);
    return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long departmentId,
                                                 @PathVariable Long employeeId){
        employeeService.deleteEmployee(departmentId, employeeId);
        return ResponseEntity.ok("Employee Deleted successfully");
    }
}
