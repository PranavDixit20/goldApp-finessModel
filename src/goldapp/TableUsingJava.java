/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldapp;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akshay
 */
public class TableUsingJava {

    Connection c = null;

    public void Con() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:goldAppDB.db");

            System.out.println("Opened database successfully");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }

    public void createTable() {
        Statement stmt = null;
        Con();
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS report(id INTEGER,sr_no TEXT,date TEXT,time TEXT,cname TEXT,cphone TEXT,full_gold_weight TEXT,gold_weight TEXT,"
                    + "lab_weight TEXT,sample_weight TEXT,pure_weight TEXT,balance_weight TEXT,gold_finess TEXT,gold_carat TEXT,bill_url TEXT,PRIMARY KEY(id))";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            System.out.println("Opened database successfully");
            System.out.println("Table created successfully");
        } catch (SQLException ex) {
            Logger.getLogger(TableUsingJava.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void updateValue(String url,int id){
        Con();
         PreparedStatement s;
            try {

                PreparedStatement p = c.prepareStatement("update report set bill_url = ? where id = ?");
                p.setString(1, url);
                p.setInt(2, id);

                p.executeUpdate();
                c.close();
                System.out.println("url saved");
            } catch (SQLException ex) {
                System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
                System.exit(0);
            }
    }
}
