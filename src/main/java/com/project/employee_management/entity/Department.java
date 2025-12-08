package com.project.employee_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Column(nullable = false)
    private String departmentName;
    @Column(nullable = false)
    private String departmentDescription;

//    public Department() {
//    }
//
//    public Department(Long departmentId, String departmentName, String departmentDescription) {
//        this.departmentId = departmentId;
//        this.departmentName = departmentName;
//        this.departmentDescription = departmentDescription;
//    }
//
//    public Long getDepartmentId() {
//        return departmentId;
//    }
//
//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public String getDepartmentDescription() {
//        return departmentDescription;
//    }
//
//    public void setDepartmentId(Long departmentId) {
//        this.departmentId = departmentId;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }
//
//    public void setDepartmentDescription(String departmentDescription) {
//        this.departmentDescription = departmentDescription;
//    }
}
