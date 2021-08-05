package com.Oberon1989;

import java.io.*;
import java.nio.charset.Charset;

public class UtilPhones {
    public static final String PATH_PHONES = "phones.dat";

    public static String getTable(Phone[] phones) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table border=\"2\"><tr bgcolor=\"lime\"><td>Id</td><td>Марка</td><td>Модель</td><td>Количество ядер</td><td>Диагонаь экрана</td><td>Вес</td><td>Цвет</td> <td> Цена </td> </tr>");
        if (phones != null) {
            for (int i = 0; i < phones.length; i++) {
                builder.append(String.format("<tr id =\"%s\"style=\"cursor:crosshair\"onclick=test(this.id)>", phones[i].getID()));
                builder.append(phones[i]);
                builder.append("</tr>");
            }

            builder.append("<tr><td><button id=\"addd\" type=\"submit\" formaction=\"addPhone.jsp\">Добавить телефон</button></td><td><button id=\"upd\"onclick=updatePhone()>Изменить телефон</button></td><td><button id=\"delete\"font color = \"red\">Удалить телефон</button></td>");
            builder.append("</table>");
        }
        return builder.toString();
    }

    public static void serializeListPhones(listPhone phones, String path) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        objectOutputStream.writeObject(phones);
    }

    public static listPhone deserializeListPhones(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
        listPhone phones = (listPhone) objectInputStream.readObject();
        return phones;
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
                "Название должно состоять из букв a-z A-Z не более 10 символов </br> Введите Модель <input name=\"model\" value=\"%s\" required pattern=\"[a-z_A-Z]{1," +
                "10}[1-9]?\"> Название должно состоять из букв a-z A-Z не более 10 символов </br> Введите количество ядер <input name=\"cpuCount\" value = \"%s\" required " +
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
                "<input type=\"submit\">\n" +
                "\n" +
                "</form>\n" +
                "    <form><button id=\"main\" formaction=\"index.jsp\">Вернуться на главную</button></form>\n" +
                "\n" +
                "</div>");

        return builder.toString();
    }
}
