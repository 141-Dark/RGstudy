package demo;

import dao.UserDAO;
import model.User;
import org.junit.Test;
import utils.DBConMysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Method_Test {
    @Test
    public void test() throws SQLException, IOException, ClassNotFoundException {
        Connection con = DBConMysql.getConnection();
        System.out.println(con);
        con.close();
    }

    @Test
    public  void test1() throws SQLException {
        User user= new User("1234qw","123","diao");
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(user);

    }
}
