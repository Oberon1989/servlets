<%@ page import="com.Oberon1989.UtilPhones" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.Oberon1989.phoneList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%
    String msg = (String) request.getSession().getAttribute("msg");

    if (msg != null) {
        out.print(msg);

    }
    session.removeAttribute("msg");

    phoneList phones = phoneList.getInstance();

    try {
        phones.copyOfPhones(UtilPhones.getPhoneListFromDB(UtilPhones.postgresConnection()));

    } catch (SQLException | ClassNotFoundException throwables) {
        throwables.printStackTrace();
    }


    try {
        out.print(UtilPhones.getTable(phones));
        request.getSession().setAttribute("phones", phones);
    } catch (Exception exception) {
        exception.printStackTrace();
    }

%>

<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="script\css\pageStyles.css">
    <script src="script\test.js"></script>
</head>
<body>
<%

%>
</body>
</html>
