package com.task.Employee.Service.service;

import com.task.Employee.Service.model.dto.EmployeeDto;
import com.task.Employee.Service.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto);
//    Employee approveEmployeeByAdmin(int id);
    List<EmployeeDto> viewAllEmployees(int pageNo , int pageSize);
    EmployeeDto viewEmployee(int id);
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    void deleteEmployee(int id);


    //String getStatus(String workflow,String role);
}
