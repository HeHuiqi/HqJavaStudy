package com.hhq.study.HqReflect;

import java.lang.reflect.Field;

class  Food{

    private  String name;
    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {

        return " Food [name:"+name+","+"weight:"+weight+"]";
    }
}

public class HqFieldTest {


    public  static void main(String[] args) throws  Exception{


        Food food = new Food();
        Class<Food> foodClazz = Food.class;
        //获取声明的属性
        Field nameField = foodClazz.getDeclaredField("name");
        //设置取消权限访问检查
        nameField.setAccessible(true);
        //设置name
        nameField.set(food,"葡萄");

        //获取weight
        Field weightField = foodClazz.getDeclaredField("weight");
        weightField.setAccessible(true);
        weightField.set(food,10);

        System.out.println("===="+food);

    }

}
