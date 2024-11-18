package com.example.redisdemo1.service;

import com.example.redisdemo1.entity.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEE_KEY = "Employee";

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Employee> hashOperations;

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }


    public EmployeeServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(Employee employee) {
            hashOperations.put(EMPLOYEE_KEY, String.valueOf(employee.getId()), employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return hashOperations.get(EMPLOYEE_KEY, id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return hashOperations.values(EMPLOYEE_KEY);
    }

    @Override
    public Map<String, Employee> getEmployeeMap() {
        return hashOperations.entries(EMPLOYEE_KEY);
    }

    @Override
    public Map<String, Integer> getEmployeeHashCodes() {
        Map<String, Employee> employeeMap = hashOperations.entries(EMPLOYEE_KEY);
        Map<String, Integer> hashCodes = new HashMap<>();
        for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
            hashCodes.put(entry.getKey(), entry.getValue().hashCode());
        }
        return hashCodes;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        hashOperations.delete(EMPLOYEE_KEY, id);
    }



    @Override
    public void updateEmployee(Long id, Employee updatedEmployee) {
        if (hashOperations.hasKey(EMPLOYEE_KEY, String.valueOf(updatedEmployee.getId()))) {
            updatedEmployee.setId(id); // Ensure the ID remains consistent
            hashOperations.put(EMPLOYEE_KEY, String.valueOf(id), updatedEmployee);
        } else {
            throw new IllegalArgumentException("Employee with ID " + id + " does not exist.");
        }
    }

}
