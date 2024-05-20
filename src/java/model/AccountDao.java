/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbconnext.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class AccountDao {

    public static ArrayList<Account> getAllAccounts() {
        ArrayList<Account> AccountList = new ArrayList<>();
        int account_id;
        String username;
        String password;
        String name;
        String email;
        String phoneNumber;
        String address;
        String date_of_birth;
        boolean active_status = true;
        String profile_picture;
        String role;
        ConnectDB db = ConnectDB.getInstance();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = db.openConnection();
            String query = "SELECT * FROM Account";
            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                account_id = rs.getInt("account_id");
                username = rs.getString("username");
                password = rs.getString("password");
                name = rs.getString("name");
                email = rs.getString("email");
                phoneNumber = rs.getString("PhoneNumber");
                address = rs.getString("address");
                role = rs.getString("role");
                date_of_birth = rs.getString("date_of_birth");
                active_status = rs.getBoolean("active_status");
                profile_picture = rs.getString("profile_picture");
                Account Acc = new Account(account_id, username, password, name, email, phoneNumber, address, date_of_birth, profile_picture, role, active_status);
                AccountList.add(Acc);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return AccountList;
    }
    public static void main(String[] args) {
        ArrayList<Account> test = AccountDao.getAllAccounts();
        for(Account ac : test)
        {
            System.out.println(ac);
        }
    }
}