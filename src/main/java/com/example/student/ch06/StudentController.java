package com.example.student.ch06;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Slf4j
@WebServlet("/students")
public class StudentController extends HttpServlet {
    StudentDAO service ;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = new StudentDAO();
        service.open();
    }

    @Override
    public void destroy() {
        service.close();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String method = req.getMethod();
        String view = "";
        String path = "/ch06/";
        log.info("action: {} " , action);
        log.info("method: {} " , method);

        if(action == null) {
            resp.sendRedirect("/students?action=list");
        } else {
            switch (action) {
                case "list":
                    view = list(req,resp);
                    req.getRequestDispatcher(path + view).forward(req,resp);
                    break;
                case "insert":
                    view = insert(req, resp);
                    if(method.equals("GET")) {
                        req.getRequestDispatcher(path + view).forward(req,resp);
                    } else if(method.equals("POST")) {
                        resp.sendRedirect(view);
                    }
                    break;
            }
        }


//    Insert ==>    service.insert(); ==> PRG
//        service.findAll();
    }

    private String list(HttpServletRequest req, HttpServletResponse resp) {
        List<Student> students = service.findAll();
        req.setAttribute("students", students);
        return "studentList.jsp";
    }

    private String insert(HttpServletRequest req, HttpServletResponse resp) {
        String method = req.getMethod();
        String view="";
        if(method.equals("POST")) {
            Student s = new Student(
                 0,
                    req.getParameter("name"),
                    req.getParameter("univ"),
                    Date.valueOf(req.getParameter("birth")),
                    req.getParameter("email")
            );
            service.insert(s);
            view="/students?action=list";
        } else if (method.equals("GET")) {
            view="studentInsert.jsp";
        }
        return view;
    }
}
