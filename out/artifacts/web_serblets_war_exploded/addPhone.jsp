<%@ page import="com.Oberon1989.UtilPhones" %>
<%@ page import="com.Oberon1989.phoneList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Добавить телефон</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String msg = (String) request.getSession().getAttribute("msg");
    String _id = request.getParameter("id");

    if (msg != null) {
        out.print(msg);

    }
    session.removeAttribute("msg");

    if (_id == null || _id.equals("-1")) {
        out.print(UtilPhones.getAddAndUpdatePhoneForm(null));
        request.getSession().setAttribute("id", -1);
    } else {
        int id = Integer.parseInt(_id);
        phoneList phones = (phoneList) request.getSession().getAttribute("phones");
        request.getSession().setAttribute("id", id);
        try {
            out.print(UtilPhones.getAddAndUpdatePhoneForm(phones.getPhoneById(id)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
%>
</body>
</html>
