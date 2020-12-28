package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

public class EmployeeResourceImplTest {

    @InjectMocks
    private EmployeeResourceImpl employeeResource;

    @Mock
    private EmployeeService employeeService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testController() {

        Mockito.when(employeeService.getEmployeeById(ArgumentMatchers.anyString())).thenReturn(getEmployee());
        ResponseEntity<Employee> employeeResponseEntity = employeeResource.employeeGetById("1");
        Assert.assertNotNull(employeeResponseEntity);
    }


    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Joe");
        employee.setLastName("Doe");
        employee.setDateOfBirth("2010-01-01");
        return employee;
    }
}