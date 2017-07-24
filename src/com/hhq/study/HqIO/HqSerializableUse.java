package com.hhq.study.HqIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by macpro on 2017/7/24.
 */
public class HqSerializableUse {

    public  void  savePerson() throws Exception{
        Person p =  new Person("小滑",20);
        ObjectOutputStream ots = new ObjectOutputStream(new FileOutputStream("person.txt"));
        ots.writeObject(p);
        ots.close();;
    }

    public  void  readPerson() throws  Exception{

        ObjectInputStream ois = new ObjectInputStream( new FileInputStream("person.txt"));

        Person p = (Person)ois.readObject();

        System.out.println(p);
    }
}
