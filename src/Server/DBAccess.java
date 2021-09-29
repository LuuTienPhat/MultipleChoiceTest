package Server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Phat
 */
public class DBAccess {

    private Connection con;
    private Statement stmt;

    public DBAccess() {
        try {
            // connect with SQL Server
            MyConnection myCon = new MyConnection();
            con = myCon.getConnection();
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public int Update(String str) {
        try {
            System.out.println(str);
            int i = stmt.executeUpdate(str);
            return i;
        } catch (SQLException e) {
            return -1;
        }
    }

    public ResultSet Query(String str) {
        try {
            ResultSet rs = stmt.executeQuery(str);
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }
}
