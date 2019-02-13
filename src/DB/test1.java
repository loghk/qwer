package DB;

import java.sql.*;

public class test1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/xupt?useSSL=false&serverTimezone=UTC";
        Connection con = DriverManager.getConnection(url, "root", "19970307sxz");
        select(con);
        transaction(con);
        System.out.println();
        select(con);
        System.out.println(con);
        Thread.sleep(20000);
        con.close();
    }
//查询
    public static void select(Connection con) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "select * from stu";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = rs.getMetaData().getColumnCount();
            while (rs.next()){
                for (int i = 0;i<count;i++){
                    System.out.print(rs.getString(i+1));
                    System.out.print("\t");
                }
                System.out.println();
            }
            /*ps.setString(1, "ccc");
            ps.setString(2, "f");
            ps.setInt(3, 52);*/
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//事务
    public static void transaction(Connection con){
        try {
            con.setAutoCommit(false);
            String sql = "update stu set age=age-10 where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 2);
            ps.executeUpdate();
            sql = "update stu set age=age+10 where id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

}
