package com.demo.controller;

import com.demo.exception.RecordNotFoundException;
import com.demo.model.Employee;
import com.demo.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee) {
        log.info("#######Trying to save data for : " + employee.getEmpFirstName());
        return new ResponseEntity<>(employeeServiceImpl.signUp(employee), HttpStatus.CREATED);
    }

    @PostMapping("/savebulkofdata")
    public ResponseEntity<List<Employee>> saveBulkOfData(@RequestBody List<Employee> employees){
        return ResponseEntity.ok(employeeServiceImpl.saveBulkOfData(employees));
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")
    public Optional<Employee> getDataById(@PathVariable int empId) {
        return employeeServiceImpl.getDataById(empId);
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/getdatabyfirstname/{empFirstName}")
    public ResponseEntity<List<Employee>> getDataByFirstName(@PathVariable String empFirstName) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByFirstName(empFirstName));
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee> getDataByContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByContactNumber(empContactNumber));
    }

    @GetMapping("/getdatabyemailid/{empEmailId}")
    public ResponseEntity<Employee> getDataByEmailId(@PathVariable String empEmailId) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmailId(empEmailId));
    }

    @GetMapping("/getdatabyname/{empFirstName}/{empLastName}")
    public ResponseEntity<List<Employee>> getDataByName(@PathVariable String empFirstName, @PathVariable String empLastName) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByName(empFirstName, empLastName));
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable int empId,@Valid @RequestBody Employee employee) {
        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("ID DOES NOT EXIST"));

        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpLastName(employee.getEmpLastName());
        employee1.setEmpFirstName(employee.getEmpFirstName());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpAge(employee.getEmpAge());


        return new ResponseEntity<>(employeeServiceImpl.updateData(employee1), HttpStatus.CREATED);
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>> sortDataById() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortDataByName() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpFirstName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortDataBySalary() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbydob")
    public ResponseEntity<List<Employee>> sortDataByDOB() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabyage")
    public ResponseEntity<List<Employee>> sortDataByAge() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpAge)).collect(Collectors.toList()));
    }

    @GetMapping("/getdatabydob/{empDOB}")
    public ResponseEntity<List<Employee>> getDataByDOB(@PathVariable String empDOB) throws ParseException {
        return ResponseEntity.ok(employeeServiceImpl.getDataByDOB(empDOB));
    }

    @GetMapping("/filterdatabysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterDataBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(employee -> employee.getEmpSalary() >= empSalary).collect(Collectors.toList()));
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId) {
        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("DATA Deleted");
    }

    @GetMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("ALL DATA DELETED");
    }
}
