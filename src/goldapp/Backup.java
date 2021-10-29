/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goldapp;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

public class Backup {
    
    //client record backup
    public void backupRecord(){
        
        DateFormat d=new SimpleDateFormat("dd-MM-yyyy");
        Date dat=new Date();
        String p = d.format(dat);
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:goldAppDB.db");

            System.out.println("Opened database successfully");
            HSSFWorkbook wb;
             try (PreparedStatement s = c.prepareStatement("select * from report"); 
                    ResultSet rs = s.executeQuery(  )) {
                 
                wb = new HSSFWorkbook();
                HSSFSheet spreadsheet = wb.createSheet("Client Records");
                HSSFCellStyle style = wb.createCellStyle();
                style.setBorderBottom(CellStyle.BORDER_THIN);
                style.setBorderTop(CellStyle.BORDER_THIN);
                style.setBorderRight(CellStyle.BORDER_THIN);
                style.setBorderLeft(CellStyle.BORDER_THIN);
                
                HSSFCellStyle dataStyle = wb.createCellStyle();
                dataStyle.cloneStyleFrom(style);
                 
                 HSSFRow row=spreadsheet.createRow(1);
                 HSSFCell cell;
                 
                 cell=row.createCell(0);
                 cell.setCellValue("Sr.no");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 0);
                 
                 cell=row.createCell(1);
                 cell.setCellValue("Date");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 1);
                 
                 cell=row.createCell(2);
                 cell.setCellValue("Time");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 2);
                 
                 cell=row.createCell(3);
                 cell.setCellValue("Client Name");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 3);
                 
                 cell=row.createCell(4);
                 cell.setCellValue("Phone no");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 4);
                 
                 cell=row.createCell(5);
                 cell.setCellValue("Full gold weight");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 5);
                 
                 cell=row.createCell(6);
                 cell.setCellValue("Gold weight");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 6);
                 
                 cell=row.createCell(7);
                 cell.setCellValue("Lab weight");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 7);
                 
                 cell=row.createCell(8);
                 cell.setCellValue("Sample");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 8);
                 
                 cell=row.createCell(9);
                 cell.setCellValue("Pure weight");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 9);
                 
                 cell=row.createCell(10);
                 cell.setCellValue("Balance weight");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 10);
                 
                 cell=row.createCell(11);
                 cell.setCellValue("Gold Finess");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 11);
                 
                 cell=row.createCell(12);
                 cell.setCellValue("Gold carat");
                 cell.setCellStyle(style);
                 spreadsheet.autoSizeColumn((short) 12);
                 
                 int i=2;
                 while(rs.next()){
                     row=spreadsheet.createRow(i);
                     cell=row.createCell(0);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getInt("sr_no"));
                     
                     cell=row.createCell(1);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("date"));
                     
                     cell=row.createCell(2);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("time"));
                     
                     cell=row.createCell(3);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("cname"));
                     
                     cell=row.createCell(4);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("cphone"));
                     
                     cell=row.createCell(5);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("full_gold_weight"));
                     
                     cell=row.createCell(6);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("gold_weight"));
                     
                     cell=row.createCell(7);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("lab_weight"));
                     
                     cell=row.createCell(8);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("sample_weight"));
                     
                     cell=row.createCell(9);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("pure_weight"));
                     
                     cell=row.createCell(10);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("balance_weight"));
                     
                     cell=row.createCell(11);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("gold_finess"));
                     
                     cell=row.createCell(12);
                     cell.setCellStyle(dataStyle);
                     cell.setCellValue(rs.getString("gold_carat"));
                     
                     i++;
                }                
            }
            c.close();
            
            String path = System.getProperty("user.home") + File.separatorChar + "Documents";
            boolean fe = new File(path+"/GoldApp/backup").mkdirs();
            
             FileOutputStream out = new FileOutputStream(
            new File(path+"/GoldApp/backup/"+"ClientRecords"+p+".xls"));
            wb.write(out);
            out.close();
            System.out.println(
            "exceldatabase.xlsx written successfully");
            JOptionPane.showMessageDialog(null,path+"/GoldApp/backup/"+"ClientRecords"+p+".xls written successfully" ,null,JOptionPane.DEFAULT_OPTION);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            JOptionPane.showMessageDialog(null,e.getMessage() ,null,JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Operation done successfully");
        
    }
    
}
