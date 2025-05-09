package com.task.Employee.Service.service;

import com.task.Employee.Service.model.dto.EmployeeDto;
import com.task.Employee.Service.model.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    EmployeeServiceImpl employeeService;

    @Test
    void createEmployeeTest(){
        Employee employee = new Employee();
        employee.setId(6);
        employee.setName("saleh");
        employee.setAge(24);
        employee.setEmail("saleh6@yahoo.com");
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(6);
        employeeDto.setName("saleh");
        employeeDto.setAge(24);
        employeeDto.setEmail("saleh6@yahoo.com");
        employeeService.createEmployee(employeeDto);
        Assertions.assertNotNull(employeeDto);
    }
    @Test
    void viewEmployeeTest(){
        Employee employee = new Employee();
        employee.setName("saleh");
        employee.setAge(24);
        employee.setEmail("saleh6@yahoo.com");
        EmployeeDto employeeDto = employeeService.viewEmployee(employee.getId());
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setName("saleh");
//        employeeDto.setAge(24);
//        employeeDto.setEmail("saleh6@yahoo.com");
        Assertions.assertNotNull(employeeDto);
        Assertions.assertEquals(6,employeeDto.getId());
    }
}
