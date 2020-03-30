package service;

import dao.UserDAO;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    //调用dao层
    private UserDAO userDao = new UserDAO();

    public String checkLogin(String username, String password) throws SQLException, IOException, ClassNotFoundException {
        //调用dao层
        User user = userDao.checkLogin(username);
        if(user!=null){
            //说明账号存在,此时比对密码
            if(user.getPwd().equals(password)){
                //登录成功
                return "SUCCESS";
            }else {
                return "ERRORPASSWORD";
            }
        }else {
            return "NOUSER";
        }

    }

    /**
     * <p>
     * 获取用户列表
     * </p>
     * @author ss
     * @Date 2020年3月5日
     * @return
     */
    public List<User> getAllUsers() throws SQLException {

        return userDao.queryAllUser();
    }
}
