package com.alonso.employees.controller;

import com.alonso.employees.entity.Employee;
import com.alonso.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee REST API endpoints", description = "Operations related to employees")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService TheEmployeeService){
        employeeService = TheEmployeeService;
    }

    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @Operation(summary = "Fetch single employee", description = "Get a single employee from db")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable @Min(1) long employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        return theEmployee;
    }
}
