package com.Oberon1989;

import java.io.Serializable;
import java.util.Comparator;

public class Phone implements Serializable {
    private int ID;
    private String brand;
    private String model;
    private int cpuCoreCont;
    private float screenDiagonal;
    private int weight;
    private Color color;
    private int price;


    //region ctor
    public Phone(int id, String brand, String model, int coreCount, float screenDiagonal, int weight, Color color, int price) {
        this.ID = id;
        createPhone(brand, model, coreCount, screenDiagonal, weight, color, price);
    }

    public Phone(Phone phone, int id) {
        this.ID = id;
        this.brand = phone.brand;
        this.model = phone.model;
        this.cpuCoreCont = phone.cpuCoreCont;
        this.screenDiagonal = phone.screenDiagonal;
        this.weight = phone.weight;
        this.color = phone.color;
        this.price = phone.price;
    }


    //endregion
    private final void createPhone(String brand, String model, int coreCount, float screenDiagonal, int weight, Color color, int price) {

        this.model = model;
        this.brand = brand;
        this.cpuCoreCont = coreCount;
        this.screenDiagonal = screenDiagonal;
        this.weight = weight;
        this.color = color;
        this.price = price;


    }

    //region getters
    public int getID() {
        return ID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getCpuCoreCont() {
        return cpuCoreCont;
    }

    public float getScreenDiagonal() {
        return screenDiagonal;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }
    //endregion
    //region setters

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("<td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s грамм</td><td>%s</td><td>%s рублей</td>", ID, brand, model, cpuCoreCont, screenDiagonal, weight, color, price);
    }

    //endregion
    public enum Color implements Serializable {
        RED("Красный"),
        BLACK("Черный"),
        YELLOW("Желтый"),
        BLUE("Голубой"),
        GREEN("Зеленый"),
        GRAY("Серый"),
        DARK_GRAY("Темной-серый");

        private String color;

        private Color(String value) {
            this.color = value;
        }

        @Override
        public String toString() {
            return color;
        }

        public String getColor() {
            return color;
        }
    }
}

class compareBrand implements Comparator<Phone>, Serializable {
    @Override
    public int compare(Phone o1, Phone o2) {
        return o1.getBrand().compareTo(o2.getBrand());
    }
}

class compareModel implements Comparator<Phone>, Serializable {
    @Override
    public int compare(Phone o1, Phone o2) {
        return o1.getModel().compareTo(o2.getModel());
    }
}

class compareCountCpu implements Comparator<Phone>, Serializable {
    @Override
    public int compare(Phone o1, Phone o2) {
        if (o1.getCpuCoreCont() > o2.getCpuCoreCont()) return 1;
        else if (o1.getCpuCoreCont() < o2.getCpuCoreCont()) return -1;
        else return 0;
    }
}

class compareScreenDiagonal implements Comparator<Phone>, Serializable {
    @Override
    public int compare(Phone o1, Phone o2) {
        if (o1.getScreenDiagonal() > o2.getScreenDiagonal()) return 1;
        else if (o1.getScreenDiagonal() < o2.getScreenDiagonal()) return -1;
        else return 0;
    }
}

class compareWeight implements Comparator<Phone>, Serializable {
    @Override
    public int compare(Phone o1, Phone o2) {
        if (o1.getWeight() > o2.getWeight()) return 1;
        else if (o1.getWeight() < o2.getWeight()) return -1;
        else return 0;
    }
}

class compareColor implements Comparator<Phone>, Serializable {
    @Override
    public int compare(Phone o1, Phone o2) {
        return o1.getColor().compareTo(o2.getColor());
    }
}

class comparePrice implements Comparator<Phone>, Serializable {
    @Override
    public int compare(Phone o1, Phone o2) {
        if (o1.getPrice() > o2.getPrice()) return 1;
        else if (o1.getPrice() < o2.getPrice()) return -1;
        else return 0;
    }
}
