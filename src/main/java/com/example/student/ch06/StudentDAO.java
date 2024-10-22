package com.example.student.ch06;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StudentDAO {
    // driver
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String JDBC_URL = "jdbc:mysql://localhost:3306/backend?serverTimezone=Asia/Seoul";
    // db connection
    Connection conn ;
    // Statement
    PreparedStatement pstmt ;
    // sql

    public void open(){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, "root", "1111");
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            log.info("데이터베이스에 접속 완료...");
        }
    }
    public void close(){
        try {
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            log.info("데이터베이스 종료");
        }
    }
    public void insert(Student s){
//        open();
        String sql = "insert into student(name, univ, birth, email) values(?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getName());
            pstmt.setString(2, s.getUniv());
            pstmt.setDate(3, s.getBirth());
            pstmt.setString(4, s.getEmail());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            close();
//        }
    }
    public List<Student> findAll(){
      //  open();
        List<Student> students = new ArrayList<Student>();

        String sql = "select * from student";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("univ"),
                        rs.getDate("birth"),
                        rs.getString("email")
                        );
                students.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            close();
//        }
        return students;
    }
}
