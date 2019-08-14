package DB;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.util.Properties;
import java.util.Scanner;

public class test2 {
    public static void main(String[] args) throws Exception{
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/xupt?useSSL=false&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("");
        ds.setMaxActive(1000);
        ds.setInitialSize(100);
        ds.setTestWhileIdle(false);
        Connection con = ds.getConnection();
        test1.select(con);
        Scanner scanner = new Scanner(System.in);
        scanner.next();
        ds.close();
        ClassLoader
    }
}
