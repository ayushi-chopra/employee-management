package com.project.employee_management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDto {
    private Long departmentId;
    private String departmentName;
    private String departmentDescription;
    private List<EmployeeDto> employees;

}
//
//public class DepartmentDto {
//
//
//    private Long departmentId;
//    private String departmentName;
//    private String departmentDescription;
//
//    public DepartmentDto() {}
//
//    public DepartmentDto(Long departmentId, String departmentName, String departmentDescription) {
//        this.departmentId = departmentId;
//        this.departmentName = departmentName;
//        this.departmentDescription = departmentDescription;
//    }
//
//    public Long getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(Long departmentId) {
//        this.departmentId = departmentId;
//    }
//
//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }
//
//    public String getDepartmentDescription() {
//        return departmentDescription;
//    }
//
//    public void setDepartmentDescription(String departmentDescription) {
//        this.departmentDescription = departmentDescription;
//    }
//}

