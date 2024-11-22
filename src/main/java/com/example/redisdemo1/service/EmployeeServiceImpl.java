package com.example.redisdemo1.service;

import com.example.redisdemo1.entity.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEE_KEY = "Employee";

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Employee> hashOperations;
    private HashOperations<String,String,String> hashOperations1;

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @PostConstruct
    public void init1() {
        this.hashOperations1 = redisTemplate.opsForHash();
    }


    public EmployeeServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(Employee employee) {

            hashOperations.put(String.valueOf(employee.getId()), String.valueOf(employee.getId()), employee);
    }

    @Override
    public void save1(Employee employee) {
        String key = String.valueOf(employee.getId()); // Use employee ID as the Redis key
        hashOperations1.put(key, "id", String.valueOf(employee.getId())); // Save the ID field
        hashOperations1.put(key, "name", employee.getName()); // Save the name field
    }


    @Override
    public Employee getEmployeeById(String id) {
        return hashOperations.get(String.valueOf(id), id);
    }

//    @Override
//    public List<Employee> getAllEmployees() {
//        Set<String> keys=hashOperations.keys("*");
//
//        return hashOperations.keys(keys);
//    }

    @Override
    public Map<String, Employee> getEmployeeMap(String id) {
        return hashOperations.entries(id);
    }

//    @Override
//    public Map<String, String> getEmployeeMaps() {
//
//        Set<String> keys = redisTemplate.keys("*");
//        Map<String,Employee> map = new HashMap<>();
//        for (String key : keys) {
//            Map<String, String> empdata=hashOperations1.entries(key);
//            Employee employee = new Employee();
//
//            employee.setId(empdata.get(key).getId());
//            employee.setName( empdata.get(key).getName());
//            map.put(key, employee);
//            System.out.println(empdata.get(key).getName());
//        }
//        return map;
//    }

    public List<Employee> getEmployeeMaps() {
        // Fetch all keys matching the employee pattern (e.g., numeric IDs or a specific prefix)
        Set<String> keys = redisTemplate.keys("*");

        // If no keys are found, return an empty list
        if (keys == null || keys.isEmpty()) {
            return Collections.emptyList();
        }

        List<Employee> employees = new ArrayList<>();

        // Iterate over the keys and fetch each hash
        for (String key : keys) {
            Map<Object, Object> employeeData = redisTemplate.opsForHash().entries(key);

            if (!employeeData.isEmpty()) {
                // Map the hash data to an Employee object
                Employee employee = new Employee();
                employee.setId(Long.valueOf((String) employeeData.get("id")));
                employee.setName((String) employeeData.get("name"));
//                employee.setDepartment((String) employeeData.get("department"));
                System.out.println(employee.getId());
                employees.add(employee);
            }
        }

        return employees;
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


//    @Override
//    public void save1(Employee employee) {
//        String key = String.valueOf(employee.getId()); // Create a unique key for each employee
//        redisTemplate.opsForValue().set(key, employee); // Save the employee object in Redis
//    }

}
