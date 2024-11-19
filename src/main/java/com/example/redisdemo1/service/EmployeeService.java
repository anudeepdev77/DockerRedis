package com.example.redisdemo1.service;

import com.example.redisdemo1.entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EmployeeService {
    void save(Employee employee);
    Employee getEmployeeById(String id);
//    List<Employee> getAllEmployees();

    Map<String, Integer> getEmployeeHashCodes();

    void deleteEmployeeById(Long id);

    Map<String, Employee> getEmployeeMap(String id);

    Map<String, Employee> getEmployeeMaps();

    void updateEmployee(Long id, Employee updatedEmployee);

    void save1(Employee employee);
}
