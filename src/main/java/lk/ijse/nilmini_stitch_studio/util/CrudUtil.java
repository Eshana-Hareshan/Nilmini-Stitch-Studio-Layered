/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.nilmini_stitch_studio.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.ijse.nilmini_stitch_studio.db.DBConnection;

/**
 *
 * @author Admin
 */
public class CrudUtil {
    
    public static <T> T execute(String sql, Object... obj) throws SQLException {
                
        Connection conn = DBConnection.getInstance().getConnection();
        
        PreparedStatement ptsm = conn.prepareStatement(sql);
        
        for(int i=0; i<obj.length; i++) {
            ptsm.setObject(i+1, obj[i]);
        }
        
        if(sql.startsWith("select") || sql.startsWith("SELECT")) {
        
            ResultSet rs = ptsm.executeQuery();
            return (T)rs;
            
        } else {
        
            int result = ptsm.executeUpdate();
            boolean rs = result>0;
            return (T)(Boolean)rs;
            
        }
        
    }
    
}
