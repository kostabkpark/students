package com.example.student.ch07;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
                String title = rs.getString("title");
                news.setAid(rs.getInt("aid"));
                news.setTitle(title);
                news.setDate(rs.getString("cdate"));
                //log.info("title = {}",title);
                newsList.add(news);
            }
            // 5. 사용한 리소스를 닫아준다.
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 6. 반환한다.
        return newsList;
    }

    // 2. aid 로 뉴스 가져오기
    public News findByAid(int aid) throws Exception{
        // 1) connection을 얻어낸다.
        Connection conn = getConnection();
        News news = new News();
        // 2) sql 을 작성한다.
        String sql = "select aid, title, img, date_format(date, '%Y-%m-%d %h:%m:%s') as cdate, content from news where aid = ?";
        // 3) pstmt 에 sql 을 적용한다 , 매개변수가 있다면 argument를 설정한다.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,aid);
        // 4) pstmt sal문을 실행한다. ==> rs 에 담는다.
        ResultSet rs = pstmt.executeQuery();
        // 5) 가져온 데이터를 처리한다.
        if(rs == null) {
            log.info("result = {}", rs);
        }
        else{
            rs.next();
            news.setAid(rs.getInt("aid"));
            news.setTitle(rs.getString("title"));
            news.setDate(rs.getString("cdate"));
            news.setContent(rs.getString("content"));
            news.setImg(rs.getString("img"));
        }
        // 6) 리소스를 닫는다.
        rs.close();
        pstmt.close();
        conn.close();
        // 7) 데이터를 반환한다.
        return news;
    }
}
