package com.Oberon1989;

import java.util.ArrayList;
import java.util.List;

public final class phoneList {
    private ArrayList<Phone> phones;
    private static volatile phoneList instance;
    private phoneList()
    {

    }

    public static phoneList getInstance() {
       phoneList result = instance;
       if(instance!=null)
       {
           return result;
       }
       synchronized (phoneList.class)
       {
           if(instance==null)
           {
               instance=new phoneList();
           }
           return instance;
       }
    }
    public int getLength()
    {
        return phones.size();
    }

    public Boolean isListNotNull()
    {
        return phones!=null;
    }
    public Phone getPhoneByIndex(int index)
    {
        if(index<0||index>phones.size())
        {
            System.out.println(index);
            throw new IndexOutOfBoundsException();
        }
        return phones.get(index);
    }

    public void copyOfPhones(ArrayList<Phone> phones)
    {
       this.phones= (ArrayList<Phone>) phones.clone();
    }

    public Phone getPhoneById(int id) throws Exception {
        int index = getPhoneIndexById(id);
        System.out.println(index);
        System.out.println(id);
        if(index==-1)
        {
           return null;
        }
        return getPhoneByIndex(index);

    }

    public void updatePhoneById(int id,Phone phone) throws Exception {
        int index = getPhoneIndexById(id);
        if(index==-1)
        {
            throw new Exception("Phone with id: "+id+" not found");
        }
        phones.set(index,new Phone(phone,phone.getID()));
    }

    public void removePhoneById(int id) throws Exception {
        int index = getPhoneIndexById(id);
        if(index==-1)
        {
            throw new Exception("Phone with id: "+id+" not found");
        }
        removePhoneByIndex(index);
    }

    private void  removePhoneByIndex(int index)
    {
        if(index<0||index>phones.size())
        {
            throw new IndexOutOfBoundsException();
        }
        phones.remove(index);
    }

    public int getPhoneIndexById(int id)
    {
        int result = -1;
        for (int i = 0; i < phones.size(); i++) {
            if(id==phones.get(i).getID())
            {
                result=i;
            }
        }
        return result;
    }





}
