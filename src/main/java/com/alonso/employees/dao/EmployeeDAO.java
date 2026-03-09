package com.alonso.employees.dao;

import com.alonso.employees.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(long theID);
    Employee save (Employee theEmployee);
    void deleteById (long theID);
}
