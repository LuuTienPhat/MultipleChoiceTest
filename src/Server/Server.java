package Server;

import Models.MultipleChoiceQuestion;
import Models.Student;
import Models.Exam;
import Models.Record;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Phat
 */
public class Server {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        int port = 1234;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server is running at port " + port);

        while (true) {
            Socket client = server.accept();
            System.out.println(client.getInetAddress().getCanonicalHostName() + " connected to Server");
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
//            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
//            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());

//           DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
//           ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
//           ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            String option = dataInputStream.readUTF();
            System.out.println(option);
            switch (option) {
                case "signin": {
                    System.out.println("Sign In");
                    String username = dataInputStream.readUTF();
                    String password = dataInputStream.readUTF();

                    System.out.println(username + " " + password);
                    Student student = new Student();
                    try {
                        DBAccess db = new DBAccess();
                        String GET_STUDENT = "SELECT * FROM SINHVIEN WHERE Username = '" + username + "' AND Password = '" + password + "'";
                        System.out.println(GET_STUDENT);
                        ResultSet rs = db.Query(GET_STUDENT);
                        String studentId = "";
                        if (rs.next()) {
                            studentId = rs.getString("MASV").trim();

                            student = new Student();
                            student.setStudentId(rs.getString("MASV").trim());
                            student.setStudentName(rs.getString("HO").trim() + " " + rs.getString("TEN").trim());
                            student.setDateOfBirth(rs.getTimestamp("NGAYSINH").toLocalDateTime());
                            student.setAddress(rs.getString("DIACHI").trim());

                        }

//                        db = new DBAccess();
                        String GET_RECORD = "SELECT * FROM BANGDIEM WHERE MASV = '" + studentId + "' ORDER BY NGAYTHI DESC";
                        ResultSet rs1 = db.Query(GET_RECORD);
                        System.out.println(GET_RECORD);

                        while (rs1.next()) {
                            Record record = new Record();
                            record.setDate(rs1.getTimestamp("NGAYTHI").toLocalDateTime().toLocalDate());
                            record.setTime(rs1.getTimestamp("NGAYTHI").toLocalDateTime().toLocalTime());
                            record.setScore(rs1.getInt("DIEMTHI"));
                            record.setLevel(rs1.getString("TRINHDO"));

                            student.getRecords().add(record);
                        }

                        objectOutputStream.writeObject(student);
                        System.out.println("student object sent");

                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }

                    break;
                }

                case "write": {
                    String studentId = dataInputStream.readUTF();
                    String datetime = dataInputStream.readUTF();
                    String score = dataInputStream.readUTF();
                    String level = dataInputStream.readUTF();

                    DBAccess db = new DBAccess();
                    String WRITE_RESULT = "INSERT INTO BANGDIEM(MASV, NGAYTHI, DIEMTHI, TRINHDO) VALUES('" + studentId + "', '" + datetime + "', '" + score + "', '" + level + "')";
                    System.out.println(WRITE_RESULT);
                    db.Update(WRITE_RESULT);
                    System.out.println("WRITE SUCCESSFULLY");

                    ArrayList<Record> records = new ArrayList<>();
                    try {
                        String GET_RECORD = "SELECT * FROM BANGDIEM WHERE MASV = '" + studentId + "' ORDER BY NGAYTHI DESC";
                        System.out.println(GET_RECORD);
                        ResultSet rs = db.Query(GET_RECORD);

                        while (rs.next()) {
                            Record record = new Record();
                            record.setDate(rs.getTimestamp("NGAYTHI").toLocalDateTime().toLocalDate());
                            record.setTime(rs.getTimestamp("NGAYTHI").toLocalDateTime().toLocalTime());
                            record.setScore(rs.getInt("DIEMTHI"));
                            record.setLevel(rs.getString("TRINHDO"));

                            records.add(record);
                        }

                        objectOutputStream.writeObject(records);
                        System.out.println("records sent");

                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }
                    break;
                }

                case "examhistories": {
                    String studentId = dataInputStream.readUTF();
                    ArrayList<Record> records = new ArrayList<>();

                    try {
                        DBAccess db = new DBAccess();
                        String GET_RECORD = "SELECT * FROM BANGDIEM WHERE MASV = '" + studentId + "' ORDER BY NGAYTHI DESC";
                        System.out.println(GET_RECORD);
                        ResultSet rs = db.Query(GET_RECORD);

                        while (rs.next()) {
                            Record record = new Record();
                            record.setDate(rs.getTimestamp("NGAYTHI").toLocalDateTime().toLocalDate());
                            record.setTime(rs.getTimestamp("NGAYTHI").toLocalDateTime().toLocalTime());
                            record.setScore(rs.getInt("DIEMTHI"));
                            record.setLevel(rs.getString("TRINHDO"));

                            records.add(record);
                        }

                        objectOutputStream.writeObject(records);
                        System.out.println("multipleChoiceQuestion sent");

                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }

                    break;
                }

                case "gettest": {
                    String level = dataInputStream.readUTF();

                    Exam test = new Exam();
                    test.setLevel(level);

                    try {
                        DBAccess db = new DBAccess();
                        String GET_RANDOM_QUESTION = "SELECT TOP 20 * FROM BODE WHERE TRINHDO = '" + test.getLevel() + "' ORDER BY NEWID()";
                        System.out.println(GET_RANDOM_QUESTION);
                        ResultSet rs = db.Query(GET_RANDOM_QUESTION);

                        while (rs.next()) {
                            MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
                            multipleChoiceQuestion.setQuestion(rs.getString("NOIDUNG").trim());
                            multipleChoiceQuestion.setAnswer(rs.getString("DAP_AN").trim());

                            multipleChoiceQuestion.getChoices().put("A", rs.getString("A").trim());
                            multipleChoiceQuestion.getChoices().put("B", rs.getString("B").trim());
                            multipleChoiceQuestion.getChoices().put("C", rs.getString("C").trim());
                            multipleChoiceQuestion.getChoices().put("D", rs.getString("D").trim());

                            ArrayList<MultipleChoiceQuestion> list = test.getMultipleChoiceQuestion();
                            list.add(multipleChoiceQuestion);
                            test.setMultipleChoiceQuestion(list);
                            //test.getMultipleChoiceQuestion().add(multipleChoiceQuestion);
                            //System.out.println(test.getMultipleChoiceQuestion().size());
                        }

                        objectOutputStream.writeObject(test);
                        System.out.println("multipleChoiceQuestion sent");

                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }

                    break;
                }
                default: {
                    break;
                }
            }

            client.close();
            System.out.println(client.getInetAddress().getHostName() + " disconnected");
        }
    }
}
