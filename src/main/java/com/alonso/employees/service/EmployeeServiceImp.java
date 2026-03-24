package com.alonso.employees.service;

import com.alonso.employees.dao.EmployeeRepository;
import com.alonso.employees.entity.Employee;
import com.alonso.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository _employeeRepository) {
        employeeRepository = _employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long theID) {
        Optional<Employee> result = employeeRepository.findById(theID);
        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Did not found employee with ID - " + theID);
        }
        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest theEmployeeRequest) {
        Employee theEmployee = convertToEmployee(0, theEmployeeRequest);
        return employeeRepository.save(theEmployee);
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee theEmployee = convertToEmployee(id, employeeRequest);
        return employeeRepository.save(theEmployee);
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
        employeeRepository.deleteById(theID);
    }
}
