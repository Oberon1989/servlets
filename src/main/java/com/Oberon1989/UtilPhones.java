package com.Oberon1989;
import java.sql.*;
import java.util.ArrayList;

public class UtilPhones {
    public static String getTable(phoneList phones) throws Exception {
        StringBuilder builder = new StringBuilder();
     builder.append("<form id = \"form1\" method = \"GET\" action=\"\" ><ul><li class=\"phonesHeader\">Id</li class=\"phonesHeader\"><li class=\"phonesHeader\">Марка</li><li class=\"phonesHeader\">Модель</li>");
     builder.append("<li class=\"phonesHeader\">Количество ядер</li class=\"phonesHeader\"><li class=\"phonesHeader\">Диагональ экрана</li>");
        builder.append("<li class=\"phonesHeader\">Вес</li><li class=\"phonesHeader\">Цвет</li><li class=\"phonesHeader\">Цена</li></ul>");
        for (int i = 0; i < phones.getLength(); i++) {
            int id=phones.getPhoneByIndex(i).getID();
            builder.append(String.format("<ul style cursor=\"pointer\" id=\"%s\" onclick = selectPhone(%s)>",id,id));

            builder.append(String.format("<li class=\"phonesList\">%s</li>",id));
            builder.append(String.format("<li class=\"phonesList\">%s</li>",phones.getPhoneByIndex(i).getBrand()));
            builder.append(String.format("<li class=\"phonesList\">%s</li>",phones.getPhoneByIndex(i).getModel()));
            builder.append(String.format("<li class=\"phonesList\">%s</li>",phones.getPhoneByIndex(i).getCpuCoreCont()));
            builder.append(String.format("<li class=\"phonesList\">%s</li>",phones.getPhoneByIndex(i).getScreenDiagonal()));
            builder.append(String.format("<li class=\"phonesList\">%s</li>",phones.getPhoneByIndex(i).getWeight()));
            builder.append(String.format("<li class=\"phonesList\">%s</li>",phones.getPhoneByIndex(i).getColor()));
            builder.append(String.format("<li class=\"phonesList\">%s</li>",phones.getPhoneByIndex(i).getPrice()));
            builder.append("</ul>");
        }
        builder.append("<ul class controls style cursor=\"pointer\">");
        builder.append(String.format("<li class=\"phonesButton\"><button input type = \"submit\" formaction = \"addPhone.jsp\">Добавить</button></li>"));
        builder.append(String.format("<li class=\"phonesButton\"><button input type =\"submit\" onclick=goToUpdatePage()>Изменить</button></li>"));
        builder.append(String.format("<li class=\"phonesButton\"><button class=\"rmButton\" input type = \"submit\" onclick=goToDeletePage()>Удалить</button></li>"));
        builder.append("</ul>");
        builder.append("<input type=\"hidden\" name=\"id\" value=\"-1\">");
        builder.append("</form>");
        return builder.toString();
    }

    public static String getPhoneString(Phone phone) {
        return String.format("<font color=\"green\">ID: %s</font> <font color=\"blue\">Марка: %s</font> <font color=\"blue\">Модель: %s</font>", phone.getID(), phone.getBrand(), phone.getModel());
    }

    public static String getAddAndUpdatePhoneForm(Phone phone) {

        String brand;
        String model;
        String coreCount;
        String diagonal;
        String weight;
        String price;
        Phone.Color color;

        if (phone != null) {
            brand = phone.getBrand();
            model = phone.getModel();
            coreCount = Integer.toString(phone.getCpuCoreCont());
            diagonal = Float.toString(phone.getScreenDiagonal());
            weight = Integer.toString(phone.getWeight());
            price = Integer.toString(phone.getPrice());
            color = phone.getColor();
        } else {
            brand = "";
            model = "";
            coreCount = "";
            diagonal = "";
            weight = "";
            price = "";
            color = Phone.Color.values()[0];
        }
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("<div>" +
                "<form action=\"add\" method=\"post\">" + "Введите Марку <input name=\"brand\" value=\"%s\" required pattern=\"[a-z_A-Z]{1,10}\"> " +
                "Название должно состоять из букв a-z A-Z не более 10 символов </br> Введите Модель <input name=\"model\" value=\"%s\" required pattern=\"[a-z_A-Z_0-9]{1," +
                "10}[1-9]?\"> Название должно состоять из букв a-z A-Z и цифр не более 10 символов </br> Введите количество ядер <input name=\"cpuCount\" value = \"%s\" required " +
                "pattern=\"[1-8]{1}\"> Минимум 1 ядро максимум 8 ядер </br> Введите диагональ экрана <input name=\"diagonal\" value=\"%s\" required pattern=\"([4-9]{1})||" +
                "([4-9]{1}\\.[0-9]{1}[0-9]?)\"> Минимум 4 максимум 9.99 дюймов </br> Введите вес <input name=\"weight\" value=\"%s\" required " +
                "pattern=\"[1-9]{1}[0-9]{1}[0-9]?\"> Минимум 10 максимум 999 грамм </br> Введите цену <input name=\"price\" value=\"%s\" required pattern=\"[1-9]{1}[0-9]{0-4}\"> Минимум 1 максимум 99999 </br> Введите цвет <select name = \"color\">", brand, model, coreCount, diagonal, weight, price));

        for (int i = 0; i < Phone.Color.values().length; i++) {
            if (Phone.Color.values()[i].equals(color)) {
                builder.append(String.format("<option value = \"%s\" selected value=\"%s\">%s</option>", i, i, Phone.Color.values()[i]));
            } else {
                builder.append(String.format("<option value = \"%s\" >%s</option>", i, Phone.Color.values()[i]));
            }
        }
        builder.append("</select></br>\n" +
                "<input type=\"submit\"/>"+
                "</form>\n" +
                "    <form><button id=\"main\" formaction=\"index.jsp\">Вернуться на главную</button></form>\n" +
                "\n" +
                "</div>");

        return builder.toString();
    }

    public static Connection postgresConnection() throws SQLException,ClassNotFoundException
    {
        try {
            Class.forName("org.postgresql.Driver");
            String login="phone_manager";
            String pass = "qwerty1989Pass1989";
            Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/rezni",login,pass);
            return connection;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }

    }

    public static ArrayList<Phone> getPhoneListFromDB(Connection connection) throws SQLException {
        ArrayList<Phone> phones = new ArrayList<Phone>();
        String query = "SELECT * FROM phones";
        Statement statement= connection.createStatement();
        ResultSet result = statement.executeQuery(query);

       while (result.next())
       {
           String brand = result.getString("brand");
           String model = result.getString("model");
           int coreCount = result.getInt("corecount");
           double diagonal = result.getDouble("diagonal");
           int weight = result.getInt("weight");
           String color = result.getString("color");
           int price = result.getInt("price");
           int id = result.getInt("id");

           phones.add(new Phone(id,brand,model,coreCount,(float) diagonal,weight, Phone.Color.valueOf(color),price));
       }
        statement.close();
        connection.close();
       return phones;
    }

    public static void addPhoneFromDB(Connection connection,Phone phone) throws SQLException {
        String color = phone.getColor().name();
        String query = String.format("INSERT INTO phones(brand,model,corecount,diagonal,weight,color,price) VALUES ('%s','%s',%s,%s,%s,'%s',%s);",phone.getBrand(),phone.getModel(),phone.getCpuCoreCont(),phone.getScreenDiagonal(),phone.getWeight(),color,phone.getPrice());
        Statement statement=connection.createStatement();
        statement.execute(query);
        statement.close();
        connection.close();
    }
    public static void updatePhoneById(Connection connection,int id,Phone phone) throws SQLException {
        String color = phone.getColor().name();
        String query = String.format("UPDATE phones SET(brand,model,corecount,diagonal,weight,color,price) = ('%s','%s',%s,%s,%s,'%s',%s) WHERE id = %s;",phone.getBrand(),phone.getModel(),phone.getCpuCoreCont(),phone.getScreenDiagonal(),phone.getWeight(),color,phone.getPrice(),phone.getID());
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.close();
        connection.close();
    }
    public static void removePhoneFromTableById(Connection connection,int id) throws SQLException {
        String query = "DELETE FROM phones WHERE id="+id+";";
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.close();
        connection.close();
    }

    public static int getNextPhoneId(Connection connection) throws SQLException {
        String query = "SELECT id FROM phones ORDER BY ID DESC LIMIT 1;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        int id=-1;
        while (result.next())
        {
            id= result.getInt("id");
        }
      if(id==-1)
      {
          id=0;
      }
      else id++;
      statement.close();
      connection.close();
      System.out.println(id);
      return id;
    }
}
