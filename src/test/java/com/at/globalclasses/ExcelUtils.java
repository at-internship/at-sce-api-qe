package com.at.globalclasses;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Properties;

public class ExcelUtils {

    public static Workbook workbook = new HSSFWorkbook();
    public static Sheet sheet = workbook.createSheet("Reports");
    public static JSONObject reportJson = new JSONObject();
    public static JSONArray arrayJson = new JSONArray();
    public static int counter = 0;


    public ExcelUtils() throws Exception {
    }


    public static void generateExcelReports() {

        int aux;
        aux = (arrayJson.length());
        Row row;
        JSONObject arrayObject = new JSONObject(arrayJson.getJSONObject(aux-1).toString());

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        if(reportJson.get("Scenario Status").toString().equals("FAILED")){
            style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());}
        if(reportJson.get("Scenario Status").toString().equals("PASSED")){
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());}
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        if (aux == 1) {
            row = sheet.createRow(0);
            if(counter==43){
                System.out.println("");
            }
            CellStyle style2 = workbook.createCellStyle();
            style2.setWrapText(true);
            style2.setAlignment(HorizontalAlignment.LEFT);
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            style2.setBorderBottom(BorderStyle.THIN);
            style2.setBorderTop(BorderStyle.THIN);
            style2.setBorderRight(BorderStyle.THIN);
            style2.setBorderLeft(BorderStyle.THIN);
            style2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Cell cell = row.createCell(1);
            cell.setCellValue("Scenario Name");
            cell.setCellStyle(style2);
            Cell cell2 = row.createCell(2);
            try {cell2.setCellValue(("Request Body"));
            } catch (Exception e) {
                cell2.setCellValue("              There is not an available Request Body.              ");
            }
            cell2.setCellStyle(style2);
            Cell cell3 = row.createCell(4);
            cell3.setCellValue(("Response Body"));
            cell3.setCellStyle(style2);
            Cell cell4 = row.createCell(5);
            cell4.setCellValue(("Expected Status"));
            cell4.setCellStyle(style2);
            Cell cell5 = row.createCell(6);
            cell5.setCellValue(("Current Status"));
            cell5.setCellStyle(style2);
            Cell cell6 = row.createCell(7);
            cell6.setCellValue(("Scenario Status"));
            cell6.setCellStyle(style2);
            Cell cell7 = row.createCell(0);
            cell7.setCellValue(("Tags"));
            cell7.setCellStyle(style2);
            Cell cell8 = row.createCell(3);
            cell8.setCellValue(("Path"));
            cell8.setCellStyle(style2);
        }

        row = sheet.createRow(aux);

        Cell cell = row.createCell(1);
        cell.setCellValue(arrayObject.get("Scenario Name").toString());
        cell.setCellStyle(style);
        Cell cell2 = row.createCell(2);
        try {cell2.setCellValue(arrayObject.getString("Request Body"));
        } catch (Exception e) {
            cell2.setCellValue("There is not an available Request Body.                                ");
        }
        cell2.setCellStyle(style);
        Cell cell3 = row.createCell(4);
        try {cell3.setCellValue(arrayObject.getString("Response Body"));
        } catch (Exception e) {
            cell3.setCellValue("There is not an available Response Body.                                ");
        }
        cell3.setCellStyle(style);
        Cell cell4 = row.createCell(5);
        cell4.setCellValue(arrayObject.getString("Expected Status"));
        cell4.setCellStyle(style);
        Cell cell5 = row.createCell(6);
        cell5.setCellValue(arrayObject.getInt("Current Status"));
        cell5.setCellStyle(style);
        Cell cell6 = row.createCell(7);
        cell6.setCellValue(arrayObject.getString("Scenario Status"));
        cell6.setCellStyle(style);
        Cell cell7 = row.createCell(0);
        cell7.setCellValue(arrayObject.get("Tags").toString());
        cell7.setCellStyle(style);
        Cell cell8 = row.createCell(3);
        cell8.setCellValue(arrayObject.getString("Path"));
        cell8.setCellStyle(style);
        for(int i=0;i<8;i++){
            sheet.autoSizeColumn(i);}



        try {
            FileOutputStream out = new FileOutputStream(new File(getFromProperties("config.properties","QA.excel-path")));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateArrayJson(JSONObject individualJson) {
        arrayJson.put(counter, individualJson);
        counter = counter + 1;
    }

    public static String getFromProperties(String fileName, String property ) throws Exception{
        Properties prop = new Properties();
        String propFileName = fileName;
        InputStream inputStream = ExcelUtils.class.getClassLoader().getResourceAsStream(propFileName);
        try{
            if(inputStream==null){
                throw new FileNotFoundException("Property file :"+fileName+ "not found");
            }
            prop.load(inputStream);

        } catch (Exception exc){
            exc.printStackTrace();
        }
        return prop.getProperty(property);


    }


}
