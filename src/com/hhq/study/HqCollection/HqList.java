package com.hhq.study.HqCollection;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;//操作集合的工具类

import java.util.Comparator;
class HqComparator implements Comparator{

    public int compare(Object obj1,Object obj2){
        if (obj1.equals(obj2)) {
            return -1;
        }
        return 1;
    }
}
public class HqList{
    public static void main(String[] args){
        System.out.println("List");

        ArrayList  phones = new ArrayList();
        //一次增加10个空间，减少分配操作
        phones.ensureCapacity(10);
        System.out.println("capacity");
        phones.add("5s");
        phones.add("6plus");
        phones.add("9s");
        System.out.println("phones = "+phones);

        ListIterator listIt = phones.listIterator();
        while(listIt.hasNext()){
            String ph  = (String)listIt.next();
            System.out.println("ph = "+ph);
        }
        System.out.println("---------------");

        while(listIt.hasPrevious()){
            String ph = (String)listIt.previous();
            System.out.println("ph1 = "+ph);
        }

        //固定长度的list,只能查询
        List list = Arrays.asList(1,8,4,6,0,9);
        System.out.println(list);

        //LinkedList实现了Queue队列接口，也实现了List接口
        LinkedList linkList = new LinkedList();
        linkList.offer("haha");
        linkList.push("heihei");
        linkList.offerFirst("hehe");
        System.out.println(linkList);

        //排序
        Collections.sort(list);
        System.out.println(list);
        //发转
        Collections.reverse(list);
        System.out.println(list);

        //指定比较器排序
        Collections.sort(list,new HqComparator());
        System.out.println(list);

        //创建线程安全的list

        Collection books = Collections.synchronizedCollection(new ArrayList());
        books.add("12345677");
        System.out.println(books);
        List bags = Collections.synchronizedList(new ArrayList());
        bags.add("qwerr");
        System.out.println(bags);

    }
}