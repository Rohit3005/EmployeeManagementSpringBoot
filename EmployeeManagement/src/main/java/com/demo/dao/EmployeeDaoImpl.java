package com.demo.dao;

import com.demo.model.Employee;
import com.demo.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepo employeeRepoImpl;

    public Employee signUp(Employee employee) {
        return employeeRepoImpl.save(employee);
    }

    public List<Employee> saveBulkOfData(List<Employee> employees) {
        return employeeRepoImpl.saveAll(employees);
    }

    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;

        if (employeeRepoImpl.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword) != null) {
            flag = true;
        }
        return flag;
    }

    public List<Employee> getAllData() {
        return employeeRepoImpl.findAll();
    }

    public Optional<Employee> getDataById(int empId) {
        return employeeRepoImpl.findById(empId);
    }

    public Employee getDataByContactNumber(long empContactNumber) {
        return employeeRepoImpl.findByEmpContactNumber(empContactNumber);
    }

    public List<Employee> getDataByFirstName(String empFirstName) {
        return employeeRepoImpl.findByEmpFirstName(empFirstName);
    }

    public Employee getDataByEmailId(String empEmailId) {
        return employeeRepoImpl.findByEmpEmailId(empEmailId);
    }

    public List<Employee> getDataByName(String empFirstName, String empLastName) {
        return employeeRepoImpl.findByEmpFirstNameAndEmpLastName(empFirstName, empLastName);
    }

    public List<Employee> getDataByDOB(String empDOB) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dobDate = dateFormat.parse(empDOB);
        return employeeRepoImpl.findByEmpDOB(dobDate);
    }

    public Employee updateData(Employee employee) {
        return employeeRepoImpl.save(employee);
    }

    public void deleteById(int empId) {
        employeeRepoImpl.deleteById(empId);
    }

    public void deleteAllData() {
        employeeRepoImpl.deleteAll();
    }

}
