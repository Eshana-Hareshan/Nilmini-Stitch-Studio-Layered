package lk.ijse.nilmini_stitch_studio.bo.custom;

import lk.ijse.nilmini_stitch_studio.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean checkCredentials(String username, String password) throws SQLException;
}
