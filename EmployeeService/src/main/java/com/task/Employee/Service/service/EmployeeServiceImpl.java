package com.task.Employee.Service.service;

import com.task.Employee.Service.exception.EmployeeAlreadyExistsException;
import com.task.Employee.Service.exception.EmployeeNotFoundException;
import com.task.Employee.Service.mapper.EmployeeMapper;
import com.task.Employee.Service.model.dto.EmployeeDto;
import com.task.Employee.Service.model.entity.Employee;
import com.task.Employee.Service.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private final EmployeeRepo employeeRepo;
    @Autowired
    private final EmployeeMapper employeeMapper;
//    @Autowired
//    private final RestTemplate restTemplate;

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employee = employeeRepo.findById(employeeDto.getId());
        if (employee.isEmpty()) {
            return employeeRepo.save(employeeMapper.toEntity(employeeDto));
        } else {
            log.info("Employee is already existing");
            throw new EmployeeAlreadyExistsException("Employee is already existing");
        }
    }
/*
    @Override
    public Employee approveEmployeeByAdmin(int id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));
        employee.setStatus("FORBIDDEN");
        return employeeRepo.save(employee);
    }*/

    @Query(value = "SELECT name FROM employees WHERE pageNo=?0 AND pageSize=?2")
    @Override
    public List<EmployeeDto> viewAllEmployees(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Employee> pages = employeeRepo.findAll(pageable);
        List<Employee> employeeList = pages.getContent();
        return employeeList.stream().map(s -> employeeMapper.toDto(s)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto viewEmployee(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            return employeeMapper.toDto(employee.get());
        } else {
            log.error("Employee with this id is not found");
            throw new EmployeeNotFoundException("Employee with this id is not found");
        }
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepo.findById(employeeDto.getId()).get();
        existingEmployee.setId(employeeDto.getId());
        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setAge(employeeDto.getAge());
        existingEmployee.setEmail(employeeDto.getEmail());
        return employeeMapper.toDto(employeeRepo.save(existingEmployee));
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepo.deleteById(id);
    }

//    @Override
//    public String getStatus(String workflow, String role) {
//        String entity_type = "employee";
//        String url = "http://localhost:9092/workflow-serivce/Workflow"+workflow+role+entity_type;
//        return restTemplate.getForObject(url, String.class);
//    }
}