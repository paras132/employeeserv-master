package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDTO;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.exception.BadRequestException;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void postEmployee(Employee employee) {
        validateRequest(employee);
        employeeRepository.save(convertToEmployeeDTO(employee));
    }

    public Employee getEmployeeById(String id) {
        EmployeeDTO employeeDTO = employeeRepository.findById(Integer.parseInt(id)).orElse(null);
        return convertToEmployee(employeeDTO);
    }

    private void validateRequest(Employee employee) {
        if(employee == null){
            throw new BadRequestException("Employee can't be blank");
        }else if(employee.getId()==null){
            throw new BadRequestException("Employee Id can't be blank");
        }else if(StringUtils.isBlank(employee.getFirstName())){
            throw new BadRequestException("Employee first name can't be blank");
        }else if(StringUtils.isBlank(employee.getLastName())){
            throw new BadRequestException("Employee last name can't be blank");
        }else if(StringUtils.isBlank(employee.getDateOfBirth())){
            throw new BadRequestException("Employee date of birth can't be blank");
        }else if(employee.getAddress() == null){
            throw new BadRequestException("Employee address can't be blank");
        }else if(StringUtils.isBlank(employee.getAddress().getLine1())){
            throw new BadRequestException("Employee address line1 can't be blank");
        }else if(StringUtils.isBlank(employee.getAddress().getCity())){
            throw new BadRequestException("Employee address city can't be blank");
        }else if(StringUtils.isBlank(employee.getAddress().getState())){
            throw new BadRequestException("Employee address state can't be blank");
        }else if(StringUtils.isBlank(employee.getAddress().getCountry())){
            throw new BadRequestException("Employee address country can't be blank");
        }else if(employee.getAddress().getZipCode() == null){
            throw new BadRequestException("Employee address zip code can't be blank");
        }
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setLine1(employee.getAddress().getLine1());
        employeeDTO.setLine2(employee.getAddress().getLine2());
        employeeDTO.setCity(employee.getAddress().getCity());
        employeeDTO.setState(employee.getAddress().getState());
        employeeDTO.setCountry(employee.getAddress().getCountry());
        employeeDTO.setZipCode(employee.getAddress().getZipCode());
        return employeeDTO;
    }

    private Employee convertToEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());

        Address address = new Address();
        address.setLine1(employeeDTO.getLine1());
        address.setLine2(employeeDTO.getLine2());
        address.setCity(employeeDTO.getCity());
        address.setState(employeeDTO.getState());
        address.setCountry(employeeDTO.getCountry());
        address.setZipCode(employeeDTO.getZipCode());

        employee.setAddress(address);

        return employee;
    }
}
