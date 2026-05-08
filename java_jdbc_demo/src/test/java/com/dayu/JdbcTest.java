package com.dayu;

import com.dayu.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {

    /**
     * 测试JDBC入门
     * @throws Exception
     */
    @Test
    public void testUpData() throws Exception {
        // 1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.获取数据库连接
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);
        // 3.获取SQL语句执行对象
        Statement statement = connection.createStatement();
        // 4.执行SQL语句
        int i = statement.executeUpdate("update user set age = 25 where id = 1");// DML语句
        System.out.println("SQL执行完毕影响的记录数为： " +  i);

        // 5.释放资源
        statement.close();
        connection.close();
    }

    @Test
    public void testQuery() {
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null; // 封装查询返回的结果

        try{
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.打开链接
            connection = DriverManager.getConnection(url, username, password);
            // 3.执行查询
            String sql = "Select id, username, password, name, age From user Where username = ? And password = ? "; // 预编译sql
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "daqiao");
            preparedStatement.setString(2, "123456");

            rs = preparedStatement.executeQuery();

            // 4.处理结果
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getInt("age")
                );
                System.out.println(user); // Lombok @Data 自动生成toString()方法
            }
        }catch (SQLException  se){
            se.printStackTrace();
        }catch (Exception  e) {
            e.printStackTrace();
        }finally {
            // 5.释放资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException  se) {
                se.printStackTrace();
            }
        }
    }
}
