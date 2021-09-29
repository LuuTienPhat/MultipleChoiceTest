/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author DTTM
 */
public class Student {
    private String StudentId;
    private String StudentName;

    public Student() {
    }

    public Student(String StudentId, String StudentName) {
        this.StudentId = StudentId;
        this.StudentName = StudentName;
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
}
