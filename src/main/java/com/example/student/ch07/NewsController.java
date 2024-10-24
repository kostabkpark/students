package com.example.student.ch07;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@WebServlet("/news")
@Slf4j
public class NewsController extends HttpServlet {
    private NewsDAO newsDAO ; ;
    private ServletContext ctx;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        newsDAO = new NewsDAO();
        ctx = getServletContext();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        String action = req.getParameter("action");
        log.info("action {}", action);
        log.info("method {}", method);
        String path = "/ch07/";
        String view = "";

        if(action == null) {
            resp.sendRedirect("/news?action=list");
        } else {
            switch (action) {
                case "list" :
                    view = list(req,resp);
                    ctx.getRequestDispatcher(path + view).forward(req, resp);
                    break;
            }
        }
    }

    private String list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<News> news = newsDAO.findAll();
        req.setAttribute("news", news);
        return "newsList.jsp";
    }
}
