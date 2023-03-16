package com.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private int empId;

    @Size(min = 2, message = "FIRST NAME SHOULD CONTAIN AR LEAST 2 CHARACTER")
    private String empFirstName;

    @Pattern(regexp = "[a-zA-z]*" ,message = "LAST NAME DOES NOT CONTAIN ANY SPECIAL CHARACTER AND SPACE")
    private String empLastName;

    private String empAddress;

    @Range(min = 1000000000 , max = 9999999999L,message = "CONTACT NUMBER MUST BE VALID")
    @Column(unique = true)
    private long empContactNumber;

    private double empSalary;

    private int empAge;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date empDOB;

    @Email(message = "EMAIL ID MUST BE VALID")
    private String empEmailId;

    @Size(min = 4 , message = "PASSWORD SHOULD CONTAIN AT LEAST 4 CHARACTER")
    private String empPassword;
}
