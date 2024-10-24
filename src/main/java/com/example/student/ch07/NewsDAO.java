package com.example.student.ch07;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
    // DB connection 을 얻어온다.
    public Connection getConnection(){
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String JDBC_URL = "jdbc:mysql://localhost:3306/backend?serverTimezone=Asia/Seoul";

        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL,"root", "1111");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    // CRUD
    // 1. 뉴스 목록 가져오기
    public List<News> findAll() {
        Connection conn = getConnection();
        List<News> newsList = new ArrayList<>();

        // 1. sql
        String sql = "select aid, title, date_format(date, '%Y-%m-%d %h:%m:%s') as cdate from news";
        // 2. pstmt
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            // 3. pstmt를 이용하여 쿼리 실행
            ResultSet rs = pstmt.executeQuery();
            // 4. 결과를 리스트에 담는다.
            while(rs.next()) {
                News news = new News();
                news.setAid(rs.getInt("aid"));
                news.setTitle(rs.getString("title"));
                news.setDate(rs.getString("cdate"));

                newsList.add(news);
            }
            // 5. 사용한 리소스를 닫아준다.
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 6. 반환한다.
        return newsList;
    }
}
