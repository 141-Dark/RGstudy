package dao;

import model.User;
import utils.DBConMysql;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    Connection con = null;
    Statement sm = null;
    PreparedStatement pstmt = null;

    ResultSet rs = null;

    public int insertUser(User user) throws SQLException {
        try {
            con = DBConMysql.getConnection();
            String sql = "insert into user values(?,?,?)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getPwd());
            pstmt.setString(3,user.getUid());


        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pstmt.executeUpdate();

    }

    public User checkLogin(String username) throws SQLException, IOException, ClassNotFoundException {
        con = DBConMysql.getConnection();
        String sql = "select * form user where uname=?";

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1,username);

        rs = pstmt.executeQuery();
        //遍历所有结果
        if(rs.next()){
            User user = new User(rs.getString(1),rs.getString(2),rs.getString(3));
            return user;
        }else {
            return null;
        }

    }

    public List<User> queryAllUser() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        try {
            //获取stateMent
            sm = con.createStatement();
            //发送sql语句到Mysql服务器,返回值是ResultSet
            rs =  sm.executeQuery("select * from user");
            //使用迭代器来遍历查询出来的结果集
            while(rs.next()){
                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(sm!=null){
                con.close();
            }
        }
        return null;
    }
}
