package com.hhq.study.HqTest;

import org.junit.Before;
import org.junit.Test;

import com.hhq.study.HqJDBC.HqMySqlManager;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by macpro on 2017/7/24.
 */
public class HqJDBCTest {

    public  HqMySqlManager hqMySqlManager = null;
    @Before
    public void setUp() throws Exception {

        hqMySqlManager = new HqMySqlManager();
        System.out.println("初始化"+hqMySqlManager);

//        String url = "jdbc:mysql://localhost:3306/work";
//        String username = "root";
//        String password = "xwf123";
//        hqMySqlManager.connectMySql(url,username,password);

    }

    @Test
    public  void filePathTest() throws  Exception{

//        //对于同一个包下的文件，其路径要包含包名各个层
//        String path = HqJDBCTest.class.getClassLoader().getResource("com/hhq/study/HqJDBC/HqMySqlConfig.properties").getPath();
//        System.out.println("HqMySqlConfigPath=="+path);
//
        String currentPath = new File(".").getAbsolutePath();
        System.out.println("currentPath==="+currentPath);

        //注意inputstream路径
        String datPath = HqJDBCTest.class.getClassLoader().getResource("com/hhq/study/HqJDBC/Data.txt").getPath();
        System.out.println("dataPath="+datPath);
        FileInputStream fileInputStream = new FileInputStream(datPath);
        fileInputStream.close();






    }

    @Test
    public  void  msqlConfigInfoTest() throws  Exception{

        Properties sqlproperties = new Properties();
        String sqlConfigPath = "com/hhq/study/HqJDBC/HqMySqlConfig.properties";
        //这中方式会缓存数据
//        sqlproperties.load(HqMySqlManager.class.getClassLoader().getResourceAsStream(sqlConfigPath));

        String sqlConifg = HqJDBCTest.class.getClassLoader().getResource(sqlConfigPath).getPath();
        FileInputStream inputStream = new FileInputStream(sqlConifg);
        sqlproperties.load(inputStream);

        String url = sqlproperties.getProperty("url");
        String username = sqlproperties.getProperty("username");
        String password = sqlproperties.getProperty("password");

        System.out.println("url ="+url+"\n"+"username="+username+"\n"+"password="+password);

        hqMySqlManager.closeMySql();


    }

    @Test
    public  void  mysqlConnectTest() throws Exception {


        String url = "jdbc:mysql://localhost:3306/work";
        String username = "root";
        String password = "xwf123";
        hqMySqlManager.connectMySql(url,username,password);
    }

    @Test
    public void  mysqlCreateTableTest() throws  Exception{


        //创建表
        String createTableSql = "create table if not exists user ( id int auto_increment primary key ," +
                "name varchar(50) ," +
                "sex char(20) default '男'); ";
        int isCreate = hqMySqlManager.createTable(createTableSql);
        //DDL语句返回0
        if (isCreate==0){
            System.out.println("创建表成功");
        }else {
            System.out.println("创建表失败");
        }
        hqMySqlManager.closeMySql();
    }

    @Test
    public void  mysqlQueryTest() throws  Exception{


        //查询
        String sql = "select * from project";
        ResultSet resultSet =  hqMySqlManager.query(sql);
        System.out.println("===="+hqMySqlManager.mysqlcon);
        while (resultSet.next()){

            System.out.println(
                    resultSet.getInt("id")+"\t"
                            +resultSet.getString("name")+"\t"
                            +resultSet.getInt("commits"));

        }
        hqMySqlManager.closeMySql();
    }

    @Test
    public  void mysqlHqQueryTest() throws  Exception{

        //查询
        String sql = "select * from project";
        ResultSet resultSet =  hqMySqlManager.hqQuery("");
        while (resultSet.next()){

            System.out.println(
                    resultSet.getInt("id")+"\t"
                            +resultSet.getString("name")+"\t"
                            +resultSet.getInt("commits"));

        }
    }


    @Test
    public  void  mysqlInsertTest()throws  Exception{
        //插入
        String[] names = {"张三","李四","王五","周六","孙七","李白"};
        for (int i = 0; i<names.length ; i++){

            String insertSql = String.format("insert into user (name) values ('%s')",names[i]);
            int isInsert = hqMySqlManager.insert(insertSql);
            if (isInsert>0){
                System.out.println("插入成功");
            }else{

                System.out.println("插入失败");

            }

        }
    }


    @Test

    public  void  mysqlUpdateTest() throws  Exception{


        //更新
        String updateSql = "update project set name = '脸书' where id = 2";
        int isUpdate = hqMySqlManager.update(updateSql);
        if (isUpdate>0){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }
        //关闭数据库
        hqMySqlManager.closeMySql();

    }

    public  void  mysqlDeleteTest() throws Exception{


        //删除
        String deleteSql = "delete from project where id>12";
        int isDelete = hqMySqlManager.delete(deleteSql);
        if (isDelete>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

    }

}