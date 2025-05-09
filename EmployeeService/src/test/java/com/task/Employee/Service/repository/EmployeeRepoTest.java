package com.task.Employee.Service.repository;

import com.task.Employee.Service.model.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeRepoTest {
    @Autowired
    EmployeeRepo employeeRepo;
    @Test
    void createEmployeeTest(){
        Employee employee = new Employee();
        employee.setId(6);
        employee.setName("saleh");
        employee.setAge(24);
        employee.setEmail("saleh6@yahoo.com");
        employeeRepo.save(employee);
        Assertions.assertNotNull(employee);
    }
    @Test
    void viewEmployeeTest(){
        Employee employee = new Employee();
        employee.setName("saleh");
        employee.setAge(24);
        employee.setEmail("saleh6@yahoo.com");
        employeeRepo.save(employee);
        Employee emp = employeeRepo.findById(employee.getId()).get();
        Assertions.assertNotNull(emp);
    }
}
