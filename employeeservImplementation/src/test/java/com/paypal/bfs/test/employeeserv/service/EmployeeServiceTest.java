package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDTO;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        EmployeeDTO employeeDTO = getEmployeeDTO();

        Mockito.when(employeeRepository.findById(employeeDTO.getId()))
                .thenReturn(java.util.Optional.of(employeeDTO));
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        Employee found = employeeService.getEmployeeById("1");
        Assert.assertEquals(found.getFirstName(),"Joe");
    }

    private EmployeeDTO getEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1);
        employeeDTO.setFirstName("Joe");
        employeeDTO.setLastName("Doe");
        employeeDTO.setDateOfBirth("2010-01-01");
        employeeDTO.setLine1("line1");
        employeeDTO.setCity("city");
        employeeDTO.setState("state");
        employeeDTO.setCountry("country");
        employeeDTO.setZipCode(101010);
        return employeeDTO;
    }
}