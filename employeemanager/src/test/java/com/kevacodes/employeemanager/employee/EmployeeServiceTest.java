package com.kevacodes.employeemanager.employee;

import com.kevacodes.employeemanager.model.Employee;
import com.kevacodes.employeemanager.repository.EmployeeRepository;
import com.kevacodes.employeemanager.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService underTest;

    @BeforeEach
    void setUp(){
        underTest = new EmployeeService(employeeRepository);
    }
    @AfterEach
    void tearDown() throws Exception{

    }
    @Test
    void canAddEmployee(){
        //given
        Employee testEmployee = new Employee("Testica", "test@email.com", "engineer", "3132000000","https://www.bootdey.com/img/Content/avatar/avatar7.png","23hhgg66");
        //when
        underTest.addEmployee(testEmployee);
        //then
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee).isEqualTo(testEmployee);
    }
    @Test
    void canGetAllEmployees(){
        //when
        underTest.getAllEmployees();

        //then
        verify(employeeRepository).findAll();

    }
    @Test
    void canUpdateEmployee(){
        //given
        Employee updatedEmployee = new  Employee("Testica", "test@email.com", "engineer", "3132000000","https://www.bootdey.com/img/Content/avatar/avatar7.png","23hhgg66");

        //when
        underTest.updateEmployee(updatedEmployee);

        //then
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee).isEqualTo(updatedEmployee);
    }
    @Test
    void canDeleteEmployeeById(){
        //given
        Employee testEmployee = new Employee("Testica", "test@email.com", "engineer", "3132000000","https://www.bootdey.com/img/Content/avatar/avatar7.png","23hhgg66");
        testEmployee.setId(1L);
        //when
        underTest.deleteEmployee(testEmployee.getId());

        //then
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        employeeRepository.delete(testEmployee);
        verify(employeeRepository).delete(employeeArgumentCaptor.capture());
        System.out.println(employeeArgumentCaptor.getValue());
        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        assertThat(capturedEmployee).isEqualTo(testEmployee);

    }
    @Test
    @Disabled
    void canFindEmployeeById(){

    }
}
