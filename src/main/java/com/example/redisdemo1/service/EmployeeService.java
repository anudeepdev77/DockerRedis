package com.example.redisdemo1.service;

import com.example.redisdemo1.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    void save(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();

    Map<String, Integer> getEmployeeHashCodes();

    void deleteEmployeeById(Long id);

    Map<String, Employee> getEmployeeMap();

    void updateEmployee(Long id, Employee updatedEmployee);
}
