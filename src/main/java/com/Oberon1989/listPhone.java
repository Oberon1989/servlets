package com.Oberon1989;

import java.io.Serializable;
import java.util.Comparator;

public class listPhone implements Serializable {

    private Phone[] phones;
    private Phone[] displayPhones;

    public listPhone() {
        phones = new Phone[0];
        displayPhones = phones.clone();
    }

    public String listPrint() {
        return UtilPhones.getTable(phones);
    }

    public int getLength() {
        return phones.length;
    }


    private void set(Phone value, int index) {
        phones[index] = new Phone(value, phones[index].getID());
    }

    public Phone getPhoneById(int id) throws Exception {
        int searchIndex = binarySearch(id);
        if (searchIndex == -1) {
            throw new Exception("Выход за границы массивы");
        }

        return phones[searchIndex];
    }

    public Phone getPhoneByIndex(int index) throws Exception {
        if (index < 0 || index > phones.length) {
            throw new Exception("Out of range");
        }
        return phones[index];
    }


    public void deletePhoneById(int id) throws Exception {
        int searchIndex = binarySearch(id);
        if (searchIndex == -1) {
            throw new Exception("Элемент для удаления не найден");
        }
        delete(searchIndex);
        displayPhones = phones.clone();
    }


    public void add(Phone phone) {
        Phone[] tempArray = new Phone[phones.length + 1];

        for (int i = 0; i < phones.length; i++) {
            tempArray[i] = phones[i];
        }

        tempArray[tempArray.length - 1] = phone;
        phones = tempArray;
        displayPhones = phones.clone();
    }


    private void delete(int index) {

        Phone[] tempArray = new Phone[phones.length - 1];

        for (int i = 0; i < index; i++) {
            tempArray[i] = phones[i];
        }

        for (int i = index + 1; i < phones.length; i++) {
            tempArray[i - 1] = phones[i];
        }
        displayPhones = phones.clone();

    }


    public int searchBrandName(String brand) {
        int index = -1;
        int length = phones.length;

        for (int i = 0; i < length; i++) {
            if (phones[i].getBrand().equals(brand)) {
                index = i;
            }
        }
        return index;
    }

    public void updatePhoneByIndex(int index, Phone value) throws Exception {
        int searchIndex = binarySearch(index);
        if (searchIndex == -1) {
            throw new Exception("Не найден элемент для изменения");
        }
        set(value, searchIndex);
        displayPhones = phones.clone();
    }


    public void bubbleSort(Comparator<Phone> comparator) {
        int offset = 0;
        boolean isSorted;
        do {
            isSorted = true;
            for (int i = 0; i < displayPhones.length - 1 - offset; i++) {
                int compareResult = comparator.compare(displayPhones[i], displayPhones[i + 1]);
                if (compareResult == 1) {
                    isSorted = false;
                    Phone tmp = displayPhones[i];
                    displayPhones[i] = displayPhones[i + 1];
                    displayPhones[i + 1] = tmp;
                }
            }
            offset++;
        }
        while (!isSorted);
    }

    public int binarySearch(int searchIndex) {
        int index = -1;
        int middle = phones.length / 2;
        int first = 0;
        int last = phones.length - 1;

        if (searchIndex < phones[first].getID() || searchIndex > phones[last].getID()) return -1;

        do {
            if (searchIndex < phones[middle].getID()) {
                last = middle - 1;
                middle = (first + last) / 2;
            } else if (searchIndex > phones[middle].getID()) {
                first = middle + 1;
                middle = (first + last) / 2;

            } else {
                index = middle;
                break;
            }
        }
        while (first <= last);

        return index;
    }


}
