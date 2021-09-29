package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Phat
 */
public class Server {

    public static void main(String[] args) throws IOException, SQLException {
        int port = 1111;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server is running at port " + port);

        while (true) {
            Socket client = server.accept();
            System.out.println(client.getInetAddress().getCanonicalHostName() + " connected to Server");
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

            String option = dataInputStream.readUTF();
            String result = "";
            switch (option) {
                case "signin": {
                    String username = dataInputStream.readUTF();
                    String password = dataInputStream.readUTF();

                    System.out.println(username + " " + password);

                    try {
                        DBAccess db = new DBAccess();
                        String sql = "SELECT MASV FROM SINHVIEN WHERE Username = '" + username + "' AND Password = '" + password + "'";
                        System.out.println(sql);
                        ResultSet rs = db.Query(sql);
                        if (rs.next()) {
                            result += rs.getString("MASV") + "|" + rs.getString("HO") + "|" + rs.getString("TEN");
                        } else {
                            result = "fail";
                        }
                        dataOutputStream.writeUTF(result);

                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }

                    break;
                }

                case "signout": {
                    break;
                }
                default: {
                    break;
                }
            }

            client.close();
            System.out.println(client.getInetAddress().getHostName() + " disconnected");
        }
    }
}
