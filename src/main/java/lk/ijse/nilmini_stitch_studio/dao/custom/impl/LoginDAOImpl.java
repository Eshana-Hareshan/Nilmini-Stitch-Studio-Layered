package lk.ijse.nilmini_stitch_studio.dao.custom.impl;

import lk.ijse.nilmini_stitch_studio.dao.custom.LoginDAO;
import lk.ijse.nilmini_stitch_studio.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean checkCredentials(String username, String password) throws SQLException {


        String sql = "SELECT password FROM logging WHERE user_name = ?";

        try (ResultSet resultSet = CrudUtil.execute(sql, username)) {

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return password.equals(storedPassword);
            } else {
                return false;
            }
        }
    }
}