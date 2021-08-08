package com.Oberon1989;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class addPhone extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String message;
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            UtilPhones.removePhoneFromTableById(UtilPhones.postgresConnection(), id);
            message = "<font color = \"green\">Успешно удалено</font>";
            req.getSession().setAttribute("msg", message);
        } catch (SQLException | ClassNotFoundException throwables) {
            message = "<font color=\"red\">Ошибка добавления/Изменения</br>" + throwables.getMessage() + "</font>";
            throwables.printStackTrace();
            req.getSession().setAttribute("msg", message);
            throwables.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String message;
        try {
            int id = (int) req.getSession().getAttribute("id");
            if (id == -1) {
                UtilPhones.addPhoneFromDB(UtilPhones.postgresConnection(), createPhone(req, UtilPhones.getNextPhoneId(UtilPhones.postgresConnection())));
            } else {
                UtilPhones.updatePhoneById(UtilPhones.postgresConnection(), id, createPhone(req, id));
            }
            message = "<font color = \"green\">Успешно добавлено/Изменено</font>";
            req.getSession().setAttribute("msg", message);

        } catch (Exception ex) {
            message = "<font color=\"red\">Ошибка добавления/Изменения</br>" + ex.getMessage() + "</font>";
            ex.printStackTrace();
            req.getSession().setAttribute("msg", message);

        }
        resp.sendRedirect(req.getContextPath());


    }
    public Phone createPhone(HttpServletRequest req, int id) {

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