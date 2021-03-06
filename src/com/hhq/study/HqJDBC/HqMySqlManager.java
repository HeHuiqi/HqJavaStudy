package com.hhq.study.HqJDBC;

/**
 * Created by macpro on 2017/7/21.
 */
import com.hhq.study.Main;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class HqMySqlManager {

    public  Connection mysqlcon = null;
    public Statement statement = null;
    // PreparedStatement
    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;

    public boolean  connectMySql(String sqlurl,String username,String password) throws Exception{

        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        mysqlcon = DriverManager.getConnection(sqlurl,username,password);
        boolean isSuc = mysqlcon.isValid(20);
        if (isSuc){
            //创建StateMent
            statement = mysqlcon.createStatement();
            System.out.println("连接MySql成功");
        }else {
            System.out.println("连接MySql失败");
        }
        return  isSuc;
    }

    public boolean connectMysqlWithProperties(String propertiesPath) throws Exception {

        Properties sqlproperties = new Properties();

//        sqlproperties.load(HqMySqlManager.class.getClassLoader().getResourceAsStream(propertiesPath));

        String path = Main.class.getClassLoader().getResource(propertiesPath).getPath();
        FileInputStream inputStream = new FileInputStream(path);
        sqlproperties.load(inputStream);

        String url = sqlproperties.getProperty("url");
        String username = sqlproperties.getProperty("username");
        String password = sqlproperties.getProperty("password");

        return connectMySql(url,username,password);

    }
    public  void closeMySql() throws  Exception{

        if (resultSet != null){
            resultSet.close();
        }
        if (statement  != null){
            statement.close();
        }
        if (mysqlcon != null){
            mysqlcon.close();
        }
    }

    public  int createTable(String sql) throws  Exception{
        //executeUpdate 对于DDL语句返回0，对于DML语句返回受影响的行数
        return statement.executeUpdate(sql);
    }



    public ResultSet query(String sql) throws  Exception{

        //executeQuery只能用于查询语句
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public  ResultSet hqQuery(String sql) throws Exception{
        sql = "select * from project where id = ?";
        preparedStatement = mysqlcon.prepareStatement(sql);
        preparedStatement.setInt(1,2);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }




    public int insert(String sql) throws  Exception{

       return createTable(sql);
    }

    public  int delete(String sql) throws  Exception{

        return insert(sql);
    }

    public  int update(String sql) throws  Exception{

        return insert(sql);
    }

    //创建存储过程
    public  void  procedureSelectById(int hId) throws  Exception{

        /*
        -- 在MySQL中每行命令都是用“；”结尾，回车后自动执行，在存储过程中“；”往往不代表指令结束，马上运行，
        -- 而DELIMITER原本就是“；”的意思，
        -- 因此用这个命令转换一下“；”为“//”，这样只有收到“//”才认为指令结束可以执行

        //创建一个存储过程
        delimiter //
        drop procedure proc_student_findById//
        create procedure proc_student_findById(
                in n int)
        begin
        SELECT * FROM student where id=n;
        end//
        */
        String findSql = "call proc_student_findById(?) ";
        // CallableStatement 来执行存储过程
        CallableStatement callableStatement = mysqlcon.prepareCall(findSql);
        callableStatement.setInt(1,1);

        ResultSet resultSet =  callableStatement.executeQuery();

        while (resultSet.next()){

            System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2));
        }

    }

}
