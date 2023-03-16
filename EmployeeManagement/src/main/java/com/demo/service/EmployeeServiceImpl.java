package com.demo.service;

import com.demo.dao.EmployeeDaoImpl;
import com.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeDaoImpl employeeDaoImpl;

    public Employee signUp(Employee employee) {
        return employeeDaoImpl.signUp(employee);
    }

    public List<Employee> saveBulkOfData(List<Employee> employees) {
        return employeeDaoImpl.saveBulkOfData(employees);
    }

    public boolean signIn(String empEmailId, String empPassword) {

        return employeeDaoImpl.signIn(empEmailId, empPassword);
    }

    public List<Employee> getAllData() {
        return employeeDaoImpl.getAllData();
    }

    @Cacheable(value = "empId")
    public Optional<Employee> getDataById(int empId) {
        return employeeDaoImpl.getDataById(empId);
    }

    public Employee getDataByContactNumber(long empContactNumber) {
        return employeeDaoImpl.getDataByContactNumber(empContactNumber);
    }

    public List<Employee> getDataByFirstName(String empFirstName) {
        return employeeDaoImpl.getDataByFirstName(empFirstName);
    }

    public Employee getDataByEmailId(String empEmailId) {
        return employeeDaoImpl.getDataByEmailId(empEmailId);
    }

    public List<Employee> getDataByName(String empFirstName, String empLastName) {
        return employeeDaoImpl.getDataByName(empFirstName, empLastName);
    }

    public List<Employee> getDataByDOB(String empDOB) throws ParseException {

        return employeeDaoImpl.getDataByDOB(empDOB);
    }

    public Employee updateData(Employee employee) {
        return employeeDaoImpl.updateData(employee);
    }

    public void deleteById(int empId) {
        employeeDaoImpl.deleteById(empId);
    }

    public void deleteAllData() {
        employeeDaoImpl.deleteAllData();
    }
}
