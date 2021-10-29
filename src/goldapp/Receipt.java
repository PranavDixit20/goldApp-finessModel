/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldapp;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

/**
 *
 * @author akshay
 */
public class Receipt extends javax.swing.JFrame {
    
    Connection c = null;
    int id;
    String name, dat, time, gdwt, lbwt, smp, fine, prwt, blwt, fullwt, krt;
    String words;
    
    String givenStr;
    String finalStr = "";
    String check;

    /**
     * Creates new form Receipt
     */
    
    //finess percentage split by point
    static String[] separate_num_from_decimal(String decimal_value){
		
		return decimal_value.split("\\.");
		
	}
    
    //duplicat Bill genrator
    public void duplicatBill(String sr_no,String wt)
    {
        
        if (wt.contains("true")) {
            
            Con();
            PreparedStatement s;
            try {
                
                s = c.prepareStatement("select * from report where id = ?");
                s.setInt(1, Integer.parseInt(sr_no));
                ResultSet rs = s.executeQuery();
                
                if (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("cname");
                    dat = rs.getString("date");
                    time = rs.getString("time");
                    gdwt = rs.getString("gold_weight");
                    lbwt = rs.getString("lab_weight");
                    prwt = rs.getString("pure_weight");
                    blwt = rs.getString("balance_weight");
                    smp = rs.getString("sample_weight");
                    fullwt = rs.getString("full_gold_weight");
                    krt = rs.getString("gold_carat");
                    fine = rs.getString("gold_finess");
                    
                }
                c.close();
                System.out.println("id = " + id + "name" + name);
                srno.setText(String.valueOf(String.format("%05d", id)));
                nm.setText(name);
                tim.setText(time);
                dte.setText(dat);
                gwt.setText(gdwt);
                pwt2.setText(prwt);
                lwt2.setText(lbwt);
                fgwt.setText(fullwt);
                karat.setText(krt);
                bwt2.setText(blwt);
                smpl.setText(smp);
                fin.setText(fine);
            } catch (SQLException ex) {
                Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
           
            Con();
            PreparedStatement s;
            try {
                
                s = c.prepareStatement("select * from report where id = ?");
                s.setInt(1, Integer.parseInt(sr_no));
                ResultSet rs = s.executeQuery();
                
                if (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("cname");
                    dat = rs.getString("date");
                    time = rs.getString("time");
                    gdwt = rs.getString("gold_weight");
                    smp = rs.getString("sample_weight");
                    fine = rs.getString("gold_finess");
                    fullwt = rs.getString("full_gold_weight");
                    krt = rs.getString("gold_carat");
                    
                }
                c.close();
                System.out.println("id = " + id + "name" + name);
                srno.setText(String.valueOf(String.format("%05d", id)));
                nm.setText(name);
                tim.setText(time);
                dte.setText(dat);
                smpl.setText(smp);
                gwt.setText(gdwt);
                fin.setText(fine);
                fgwt.setText(fullwt);
                karat.setText(krt);
                
                //set visibility
         
                
                pwt1.setVisible(false);
                pwt2.setVisible(false);
                lwt1.setVisible(false);
                lwt2.setVisible(false);
                bwt1.setVisible(false);
                bwt2.setVisible(false);
                
            } catch (SQLException ex) {
                Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //finess in word and set in word lable
        givenStr = fine;
        CFG cfg = new CFG();
        String[] arrOfNumbers = separate_num_from_decimal(givenStr);
        for(int i = 0; i < arrOfNumbers.length; i++){
			if(finalStr.isEmpty()){
				finalStr = finalStr + (cfg.convert_to_words(arrOfNumbers[i].toCharArray())) + " point ";
                                Words.setText(finalStr);
			}else{
				finalStr = finalStr + (cfg.convert_to_words(arrOfNumbers[i].toCharArray()));
                                Words.setText(finalStr);
			}        
		}
            if(finalStr.endsWith("point")){
            finalStr=finalStr+ "zero";
            }
            Words.setText(finalStr);
                            System.out.println("Given number: " + givenStr);
                    System.out.println("Number to word: " + finalStr);
        
    }
    
    //set all values in jprint
    public void set(boolean x) {
        
        check = String.valueOf(x);
        System.out.println("Check Box" + check);
        
        
        //if visibility checkbox is checked
        if (x) {
            
            Con();
            PreparedStatement s;
            try {
                
                s = c.prepareStatement("SELECT * FROM report ORDER BY id DESC LIMIT 1;");
                
                ResultSet rs = s.executeQuery();
                
                if (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("cname");
                    dat = rs.getString("date");
                    time = rs.getString("time");
                    gdwt = rs.getString("gold_weight");
                    lbwt = rs.getString("lab_weight");
                    prwt = rs.getString("pure_weight");
                    blwt = rs.getString("balance_weight");
                    smp = rs.getString("sample_weight");
                    fullwt = rs.getString("full_gold_weight");
                    krt = rs.getString("gold_carat");
                    fine = rs.getString("gold_finess");
                    
                }
                c.close();
                System.out.println("id = " + id + "name" + name);
                srno.setText(String.valueOf(String.format("%05d", id)));
                nm.setText(name);
                tim.setText(time);
                dte.setText(dat);
                gwt.setText(gdwt);
                pwt2.setText(prwt);
                lwt2.setText(lbwt);
                fgwt.setText(fullwt);
                karat.setText(krt);
                bwt2.setText(blwt);
                smpl.setText(smp);
                fin.setText(fine);
            } catch (SQLException ex) {
                Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
           
            Con();
            PreparedStatement s;
            try {
                
                s = c.prepareStatement("SELECT * FROM report ORDER BY id DESC LIMIT 1;");
                
                ResultSet rs = s.executeQuery();
                
                if (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("cname");
                    dat = rs.getString("date");
                    time = rs.getString("time");
                    gdwt = rs.getString("gold_weight");
                    smp = rs.getString("sample_weight");
                    fine = rs.getString("gold_finess");
                    fullwt = rs.getString("full_gold_weight");
                    krt = rs.getString("gold_carat");
                    
                }
                c.close();
                System.out.println("id = " + id + "name" + name);
                srno.setText(String.valueOf(String.format("%05d", id)));
                nm.setText(name);
                tim.setText(time);
                dte.setText(dat);
                smpl.setText(smp);
                gwt.setText(gdwt);
                fin.setText(fine);
                fgwt.setText(fullwt);
                karat.setText(krt);
                
                //set visibility
         
                
                pwt1.setVisible(x);
                pwt2.setVisible(x);
                lwt1.setVisible(x);
                lwt2.setVisible(x);
                bwt1.setVisible(x);
                bwt2.setVisible(x);
                
            } catch (SQLException ex) {
                Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //finess in word and set in word lable
        givenStr = fine;
        CFG cfg = new CFG();
        String[] arrOfNumbers = separate_num_from_decimal(givenStr);
        for(int i = 0; i < arrOfNumbers.length; i++){
			if(finalStr.isEmpty()){
				finalStr = finalStr + (cfg.convert_to_words(arrOfNumbers[i].toCharArray())) + " point ";
                                Words.setText(finalStr);
			}else{
				finalStr = finalStr + (cfg.convert_to_words(arrOfNumbers[i].toCharArray()));
                                Words.setText(finalStr);
			}        
		}
            if(finalStr.endsWith("point")){
            finalStr=finalStr+ "zero";
            }
            Words.setText(finalStr);
            System.out.println("Given number: " + givenStr);
            System.out.println("Number to word: " + finalStr);
            
            //Bills Folder create
            String path = System.getProperty("user.home") + File.separatorChar + "Documents";
            System.out.println("File Path: "+path);
            String fullpath = path+"/GoldApp/bills";
            boolean fe = new File(fullpath).mkdirs();
            System.out.println("File create: "+fe);
            
            BufferedImage img = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_RGB);
            jPanel1.print(img.getGraphics()); // or: panel.printAll(...);
            String bill_url = fullpath+"/"+id+".jpg";
        try {
            ImageIO.write(img, "jpg", new File(bill_url));
        } catch (IOException ex) {
            Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            new TableUsingJava().updateValue(check,id);
                    
        
    }
    
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
    
    public Receipt() {
        initComponents();
        LineBorder l = new LineBorder(Color.decode("#0476D0"), 2, true);
        jPanel2.setBorder(l);
        jPanel3.setBorder(l);
        jPanel4.setBorder(l);
        jPanel5.setBorder(l);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lwt1 = new javax.swing.JLabel();
        gwt = new javax.swing.JLabel();
        lwt2 = new javax.swing.JLabel();
        smpl = new javax.swing.JLabel();
        fin = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dte = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tim = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        srno = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nm = new javax.swing.JLabel();
        pwt1 = new javax.swing.JLabel();
        pwt2 = new javax.swing.JLabel();
        bwt1 = new javax.swing.JLabel();
        bwt2 = new javax.swing.JLabel();
        fgwtlabel = new javax.swing.JLabel();
        fgwt = new javax.swing.JLabel();
        krtlabel = new javax.swing.JLabel();
        karat = new javax.swing.JLabel();
        Words = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        printButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 1, true));
        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Abyssinica SIL", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel1.setText("ROYAL GOLD AND SILVER TESTING");

        jLabel2.setFont(new java.awt.Font("Malayalam MN", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(4, 10, 86));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("245 - Gani building ( near pillaiyar kovil) Muppudathi amman kovil Street ,");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/goldapp/final_logo.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Malayalam MN", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(4, 10, 86));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Kadayanallur 627751 , Tenkasi - dist. Tamil Nadu");

        jLabel16.setFont(new java.awt.Font("Malayalam MN", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(4, 10, 86));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Cell :9359929217,9146705670");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel15)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel16)
                        .addGap(2, 2, 2))))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 1, true));

        jLabel11.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(4, 10, 86));
        jLabel11.setText("Sample  :");

        jLabel13.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(4, 10, 86));
        jLabel13.setText("Finess :");

        jLabel9.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(4, 10, 86));
        jLabel9.setText("Gold Wt  :");

        lwt1.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        lwt1.setForeground(new java.awt.Color(4, 10, 86));
        lwt1.setText("Lab Wt  :");

        gwt.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        gwt.setText("N/A");

        lwt2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        lwt2.setText("N/A");

        smpl.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        smpl.setText("N/A");

        fin.setFont(new java.awt.Font("Abyssinica SIL", 1, 36)); // NOI18N
        fin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fin.setText("N/A");

        jLabel5.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(4, 10, 86));
        jLabel5.setText("Date  :");

        dte.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        dte.setText("N/A");

        jLabel8.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(4, 10, 86));
        jLabel8.setText("Time  :");

        tim.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        tim.setText("N/A");

        jLabel6.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(4, 10, 86));
        jLabel6.setText("Sr No  :");

        srno.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        srno.setText("N/A");

        jLabel7.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(4, 10, 86));
        jLabel7.setText("Name   :");

        nm.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        nm.setText("N/A");

        pwt1.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        pwt1.setForeground(new java.awt.Color(4, 10, 86));
        pwt1.setText("Pure Wt  :");

        pwt2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        pwt2.setText("N/A");

        bwt1.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        bwt1.setForeground(new java.awt.Color(4, 10, 86));
        bwt1.setText("Balance Wt :");

        bwt2.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        bwt2.setText("N/A");

        fgwtlabel.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        fgwtlabel.setForeground(new java.awt.Color(4, 10, 86));
        fgwtlabel.setText("Full Gold Wt :");

        fgwt.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        fgwt.setText("N/A");

        krtlabel.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        krtlabel.setForeground(new java.awt.Color(4, 10, 86));
        krtlabel.setText("Gold Karat :");

        karat.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        karat.setText("N/A");

        Words.setFont(new java.awt.Font("Abyssinica SIL", 0, 18)); // NOI18N
        Words.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fgwtlabel))
                                .addGap(11, 11, 11)
                                .addComponent(fgwt, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(smpl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nm, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(srno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(91, 91, 91))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(pwt1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pwt2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(gwt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addComponent(Words, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lwt1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lwt2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(krtlabel)
                                .addGap(14, 14, 14)
                                .addComponent(karat, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(bwt1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bwt2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dte, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(tim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dte)
                            .addComponent(jLabel5))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tim))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(krtlabel)
                            .addComponent(karat))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lwt1)
                            .addComponent(lwt2))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bwt1)
                            .addComponent(bwt2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fin, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Words, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(srno)
                    .addComponent(jLabel6))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nm)
                    .addComponent(jLabel7))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(smpl)
                    .addComponent(jLabel11))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fgwtlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fgwt))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(gwt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pwt1)
                    .addComponent(pwt2)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 1, true));
        jPanel5.setAlignmentX(0.0F);
        jPanel5.setAlignmentY(0.0F);

        jLabel3.setFont(new java.awt.Font("Abyssinica SIL", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 51, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ASSAYING REPORT");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));

        jLabel14.setFont(new java.awt.Font("Malayalam MN", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 51, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Note: We are not responsible for any error in assaying result");
        jLabel14.setToolTipText("");
        jLabel14.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        printButton.setBackground(new java.awt.Color(0, 51, 153));
        printButton.setForeground(new java.awt.Color(255, 255, 255));
        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(343, 343, 343))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(printButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        printButton.getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        
            
            PrinterJob pjob = PrinterJob.getPrinterJob();
            PageFormat preformat = pjob.defaultPage();
            preformat.setOrientation(PageFormat.LANDSCAPE);
            PageFormat postformat = pjob.pageDialog(preformat);
            //If user does not hit cancel then print.
            if (preformat != postformat) {
                //Set print component
                pjob.setPrintable(new Printer(jPanel1), postformat);
                if (pjob.printDialog()) {
                    try {
                        pjob.print();
                    } catch (PrinterException ex) {
                        Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            new goldApp().setVisible(true);
            dispose();
            
        
    }//GEN-LAST:event_printButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Receipt r = new Receipt();
            r.setVisible(true);
            r.set(false);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Words;
    private javax.swing.JLabel bwt1;
    private javax.swing.JLabel bwt2;
    private javax.swing.JLabel dte;
    private javax.swing.JLabel fgwt;
    private javax.swing.JLabel fgwtlabel;
    private javax.swing.JLabel fin;
    private javax.swing.JLabel gwt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel karat;
    private javax.swing.JLabel krtlabel;
    private javax.swing.JLabel lwt1;
    private javax.swing.JLabel lwt2;
    private javax.swing.JLabel nm;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel pwt1;
    private javax.swing.JLabel pwt2;
    private javax.swing.JLabel smpl;
    private javax.swing.JLabel srno;
    private javax.swing.JLabel tim;
    // End of variables declaration//GEN-END:variables
}
