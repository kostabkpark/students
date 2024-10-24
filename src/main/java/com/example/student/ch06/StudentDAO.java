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

    public Student findById(int id){
        //  open();
        Student student = null;

        String sql = "select * from student where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("univ"),
                        rs.getDate("birth"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            close();
//        }
        return student;
    }

    public void updateStudent(Student s){
        // univ , email 만 변경 가능함
        String sql = "update student set univ = ?, email = ? where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getUniv());
            pstmt.setString(2, s.getEmail());
            pstmt.setInt(3, s.getId());
            int rc = pstmt.executeUpdate();
            if(rc == 1){
                log.info("수정 완료");
            } else {
                log.error("수정 오류");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id){
        // 1. sql 작성
        String sql = "delete from student where id = ?";
        try {
            //2. pstmt 준비
            pstmt = conn.prepareStatement(sql);
            //3. pstmt 매개변수 설정
            pstmt.setInt(1, id);
            //4. pstmst의 sql을 실행
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
