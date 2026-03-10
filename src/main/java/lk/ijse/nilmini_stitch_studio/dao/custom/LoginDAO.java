package lk.ijse.nilmini_stitch_studio.dao.custom;

import java.sql.SQLException;

public interface LoginDAO {
    boolean checkCredentials(String username, String password) throws SQLException;
}
