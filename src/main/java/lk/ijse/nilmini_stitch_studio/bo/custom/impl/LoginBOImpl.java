package lk.ijse.nilmini_stitch_studio.bo.custom.impl;

import lk.ijse.nilmini_stitch_studio.bo.custom.LoginBO;
import lk.ijse.nilmini_stitch_studio.dao.custom.LoginDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.LoginDAOImpl;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = new LoginDAOImpl();

    @Override
    public boolean checkCredentials(String username, String password) throws SQLException {
        return loginDAO.checkCredentials(username,password);
    }
}
