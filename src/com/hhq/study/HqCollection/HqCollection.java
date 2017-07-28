package com.hhq.study.HqCollection;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
public class HqCollection{
    public static void main(String[] args){
        System.out.println("集合");

        Collection c = new ArrayList();
        c.add("100百万");
        c.add("haha");
        c.add(9999);
        System.out.println("size="+c.size());
        c.remove(9999);
        System.out.println("size="+c.size());
        if (c.contains("haha")) {
            System.out.println("c = "+ c);
        }


        Collection bookSet = new HashSet();
        bookSet.add("java权威指南");
        bookSet.add("今天吃饭指南");
        bookSet.add("今天吃饭指南2");
        System.out.println("bookSet="+bookSet);

        c.removeAll(bookSet);
        System.out.println("size="+c.size());
        c.clear();
        System.out.println("size="+c.size());

        Iterator iterator = bookSet.iterator();
        while(iterator.hasNext()){
            String book = (String)iterator.next();
            System.out.println("book = "+book);
            if (book.equals("今天吃饭指南")) {
                iterator.remove();
            }
        }
        for (Object obj : bookSet) {
            String book = (String)obj;
            System.out.println(book);
        }
        System.out.println("bookSet = "+bookSet);

    }
}
