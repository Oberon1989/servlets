package com.Oberon1989;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class addPhone extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String message;
        try {
            listPhone phones = (listPhone) req.getSession().getAttribute("phones");
            phones.add(createPhone(req));
            req.getSession().setAttribute("id", (int) req.getSession().getAttribute("id") + 1);
            message = "<font color = \"green\">Успешно добавлено</font>";
            req.getSession().setAttribute("msg", message);
            UtilPhones.serializeListPhones(phones, UtilPhones.PATH_PHONES);
        } catch (Exception ex) {
            message = "<font color=\"red\">Ошибка добавления</br>" + ex.getMessage() + "</font>";
            req.getSession().setAttribute("msg", message);
        }
        resp.sendRedirect(req.getContextPath() + "/addPhone.jsp");


    }

    public Phone createPhone(HttpServletRequest req) {
        int id = (int) req.getSession().getAttribute("id");
        String brand = req.getParameter("brand");

        String model = req.getParameter("model");

        int cpuCount = Integer.parseInt(req.getParameter("cpuCount"));

        float diagonal = Float.parseFloat(req.getParameter("diagonal"));

        int weight = Integer.parseInt(req.getParameter("weight"));

        int price = Integer.parseInt(req.getParameter("price"));

        int color = Integer.parseInt(req.getParameter("color"));

        return new Phone(id, brand, model, cpuCount, diagonal, weight, Phone.Color.values()[color], price);
    }
}