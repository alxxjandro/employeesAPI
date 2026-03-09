package com.alonso.employees.service;

import com.alonso.employees.dao.EmployeeDAO;
import com.alonso.employees.entity.Employee;
import com.alonso.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImp(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(long theID) {
        Employee theEmployee = employeeDAO.findById(theID);
        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest theEmployeeRequest) {
        Employee theEmployee = convertToEmployee(0, theEmployeeRequest);
        return employeeDAO.save(theEmployee);
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee theEmployee = convertToEmployee(id, employeeRequest);
        return employeeDAO.save(theEmployee);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(
            id,
            employeeRequest.getFirstName(),
            employeeRequest.getLastName(),
            employeeRequest.getEmail());
    }

    @Transactional
    @Override
    public void deleteById(long theID) {
        employeeDAO.deleteById(theID);
    }
}
