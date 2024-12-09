package com.http.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var paramValue = req.getParameter("param");
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println();


//        var headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            var header = headerNames.nextElement();
//            System.out.println(header + " --- " + req.getHeader(header));
//        }
//
//        resp.setContentType("text/html; charset=UTF-8");
////        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setHeader("token", "12345");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1>Hello from First Servlet</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedReader reader = req.getReader();
             Stream<String> lines = reader.lines()) {

            lines.forEach(System.out::println);
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
