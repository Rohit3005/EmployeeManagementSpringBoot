package com.demo.repo;

import com.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    public List<Employee> findByEmpFirstName(String empFirstName);

    public Employee findByEmpContactNumber(long empContactNumber);

    public Employee findByEmpEmailId(String empEmailId);

    public Employee findByEmpEmailIdAndEmpPassword(String empEmailId, String empPassword);

    public List<Employee> findByEmpFirstNameAndEmpLastName(String empFirstName, String empLastName);

    public List<Employee> findByEmpDOB(Date empDOB);
}
