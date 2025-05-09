package com.task.Employee.Service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.Employee.Service.model.dto.EmployeeDto;
import com.task.Employee.Service.model.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createEmployeeTest() throws Exception {
        Employee employee = new Employee();
        employee.setId(7);
        employee.setName("sameh");
        employee.setAge(25);
        employee.setEmail("sameh7@yahoo.com");
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(7);
        employeeDto.setName("sameh");
        employeeDto.setAge(25);
        employeeDto.setEmail("sameh7@yahoo.com");
        mockMvc.perform(post("/employee/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDto)))
                        .andExpect(status().isCreated());
    }
}
