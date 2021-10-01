/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Phat
 */
public class Student implements Serializable {

    private String StudentId;
    private String StudentName;
    private LocalDateTime dateOfBirth;
    private String address;
    private ArrayList<Record> records;

    public Student() {
        super();
        records = new ArrayList<>();
    }

    public Student(String StudentId, String StudentName, LocalDateTime dateOfBirth, String address, ArrayList<Record> records) {
        super();
        this.StudentId = StudentId;
        this.StudentName = StudentName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.records = records;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

}
