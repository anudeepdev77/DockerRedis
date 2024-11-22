package com.example.redisdemo1.controller;

import com.example.redisdemo1.entity.Employee;
import com.example.redisdemo1.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/save1")
    public ResponseEntity<Object> saveEmployee1(@RequestBody Employee employee) {
        employeeService.save1(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

//   @GetMapping("/getall")
//        public ResponseEntity<Object> getAllEmployees() {
//        return ResponseEntity.ok().body(employeeService.getAllEmployees());
//        }

        @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployee(@PathVariable String id) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
        }

        @GetMapping("/getallemp/{id}")
    public ResponseEntity<Object> geAll(@PathVariable  String id) {
            Map<String, Employee> employees = employeeService.getEmployeeMap(id);
            return ResponseEntity.ok().body(employees);
        }

//    @GetMapping("/getallemp")
//    public ResponseEntity<Object> geAllEmp() {
//        Map<String, String> employees = employeeService.getEmployeeMaps();
//        return ResponseEntity.ok().body(employees);
//    }

    @GetMapping("/getallemp")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getEmployeeMaps();

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no employees found
        }

        return ResponseEntity.ok(employees);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        try {
            employeeService.updateEmployee(id, updatedEmployee);
            return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hashcodes")
    public Map<String, Integer> getEmployeeHashCodes() {
        return employeeService.getEmployeeHashCodes();
    }

}
