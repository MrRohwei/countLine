package com.project.controller;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Created by Looveh on 2018/1/22.
 */
@WebServlet("/myController")
public class Test1Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        req.setAttribute("name",name);
        System.out.println("name = " + name);
        req.getRequestDispatcher("index.jsp").forward(req,resp);

    }
}
