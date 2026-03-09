package com.alonso.employees.service;

import com.alonso.employees.entity.Employee;
import com.alonso.employees.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(long theID);
    Employee save(EmployeeRequest theEmployeeRequest);
    Employee update(long id, EmployeeRequest employeeRequest);
    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);
    void deleteById (long theID);
}
