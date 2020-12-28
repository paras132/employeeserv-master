package com.paypal.bfs.test.employeeserv.repository;

import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenGetById_thenReturnEmployee() {
        EmployeeDTO employeeDTO = getEmployeeDTO();
        entityManager.persist(employeeDTO);
        entityManager.flush();

        EmployeeDTO found = employeeRepository.findById(employeeDTO.getId()).get();

        assertEquals(found.getFirstName(), employeeDTO.getFirstName());
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