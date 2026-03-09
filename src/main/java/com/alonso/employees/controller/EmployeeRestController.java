package com.alonso.employees.controller;

import com.alonso.employees.entity.Employee;
import com.alonso.employees.request.EmployeeRequest;
import com.alonso.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    @Operation(summary = "Create a new employee", description = "Add a new employee to the database")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @Operation(summary = "Update an employee", description = "Update the data of an employee")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(
            @PathVariable @Min(value = 1) long employeeId,
            @Valid @RequestBody EmployeeRequest employeeRequest
    ) {
        Employee dbEmployee = employeeService.update(employeeId, employeeRequest);
        return dbEmployee;
    }

    @Operation(summary = "Delete an employee", description = "Delete an employee from the db")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable @Min(value = 1) long employeeId) {
        employeeService.deleteById(employeeId);
    }


}
