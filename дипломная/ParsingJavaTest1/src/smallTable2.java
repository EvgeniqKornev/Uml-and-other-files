import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class smallTable2 {
	/*
	Bizagi Modeler
	camunda-modeler
	*/
	public String raspFile1;
	public String raspFile2;
	public smallTable2(String rasplog) throws IOException {
		Document documentST = Jsoup.connect("https://nngasu.ru/sveden/budget/"+ "?User-Agent=Chrome/81.0.4044.148").get();
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("budzhet obraz deiatelnosti");
        
		List<List<String>> resultList = new ArrayList<List<String>>();
		Elements table1and2 = documentST.getElementsByAttributeValue("class", "table-small");
		Element tb12get0 = table1and2.get(0);
		Node chNode = tb12get0.childNode(1).childNode(3);
		
		List<String> titleList = new ArrayList<String>();
		for(int i = 1;i<=7;i=i+2) {
			Node dvdf = chNode.childNode(i).childNode(1);
			titleList.add(dvdf.childNode(0).toString());
		}
		
		int rownum = 0;
        Cell cell;
        Row row1;
        
        row1 = sheet.createRow(rownum);
        cell = row1.createCell(0,CellType.STRING);
        cell.setCellValue("Год");
        int k = 1;
        for(var ttlCell:titleList) {
        	cell = row1.createCell(k, CellType.STRING);
        	cell.setCellValue(ttlCell);
        	k++;
        }
		
        List<String> listYearTb1 = new ArrayList<String>();
        listYearTb1.add("2017");
        listYearTb1.add("2018");
        listYearTb1.add("2019");
        listYearTb1.add("2020");
        listYearTb1.add("2021 план");
        
        
		List<Double> finBF = new ArrayList<Double>();
		List<Double> finBR = new ArrayList<Double>();
		List<Double> finBM = new ArrayList<Double>();
		List<Double> finPV = new ArrayList<Double>();
		Elements cell1_value_tb1 = documentST.getElementsByAttributeValue("itemprop", "finBFVolume"); //по столбцам работает , а другая таблица по строкам
		cell1_value_tb1.forEach(row -> {			
			double db = Double.parseDouble(row.text().replaceAll("\\s+","").replace(",", "."));
			finBF.add(db);		
			
		});
		
		Elements cell2_value_tb1 = documentST.getElementsByAttributeValue("itemprop", "finBRVolume");
		cell2_value_tb1.forEach(row -> {
			double db = Double.parseDouble(row.text().replaceAll("\\s+","").replace(",", "."));
			finBR.add(db);
		});
		
		Elements cell3_value_tb1 = documentST.getElementsByAttributeValue("itemprop", "finBMVolume");
		cell3_value_tb1.forEach(row -> {
			double db = Double.parseDouble(row.text().replaceAll("\\s+","").replace(",", "."));
			finBM.add(db);
		});
		
		Elements cell4_value_tb1 = documentST.getElementsByAttributeValue("itemprop", "finPVolume");
		cell4_value_tb1.forEach(row -> {
			double db = Double.parseDouble(row.text().replaceAll("\\s+","").replace(",", "."));
			finPV.add(db);
		});
		
		for(int i = 0;i<finBF.size();i++) {
			++rownum;
	        row1 = sheet.createRow(rownum);
	        
	        cell = row1.createCell(0, CellType.STRING);
        	cell.setCellValue(listYearTb1.get(i).toString());
	        
	        cell = row1.createCell(1, CellType.NUMERIC);
	        cell.setCellValue(finBF.get(i));
	        
	        cell = row1.createCell(2, CellType.NUMERIC);
	        cell.setCellValue(finBR.get(i));
	        
	        cell = row1.createCell(3, CellType.NUMERIC);
	        cell.setCellValue(finBM.get(i));
	        
	        cell = row1.createCell(4, CellType.NUMERIC);
	        cell.setCellValue(finPV.get(i));
		}
		
        File file = new File(rasplog+"smallTable1.xls");
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        raspFile1 = "Created file: " + file.getAbsolutePath();
	
		//------------------------
		
        HSSFWorkbook workbook2 = new HSSFWorkbook();
        HSSFSheet sheet2 = workbook2.createSheet("postyp and rasxod fin sredstv");
        Cell cell2;
        Row row2;
                
        
        Element tb12get1 = table1and2.get(1);
        Element dfvdf = tb12get1.selectFirst("tr"); // заголовки второй таблицы
        Node yaertb2 = dfvdf.child(0).childNode(0);
        Node postyp_zagolovok = dfvdf.child(1).childNode(0);
        Node rashod_zagolovok = dfvdf.child(2).childNode(0);
               
        rownum = 0;
        row2 = sheet2.createRow(rownum);
        cell2 = row2.createCell(0, CellType.STRING);
    	cell2.setCellValue(yaertb2.toString());
    	cell2 = row2.createCell(1, CellType.STRING);
    	cell2.setCellValue(postyp_zagolovok.toString());
    	cell2 = row2.createCell(2, CellType.STRING);
    	cell2.setCellValue(rashod_zagolovok.toString());
        
		Elements rows_value_tb2 = documentST.getElementsByAttributeValue("itemprop", "volume");
		rows_value_tb2.forEach(row -> {
			List<String> listtb2prom = new ArrayList<String>();
						
			Elements rowsAll = row.select("td");
			Element year = rowsAll.get(0);
			Node textyear2 = year.childNode(1).childNode(0);
			listtb2prom.add(textyear2.toString());
			
			
			Element postyp = rowsAll.get(1);
			Node postyptext = postyp.childNode(1).childNode(0);
			listtb2prom.add(postyptext.toString().replaceAll("\\s+","").replace(",", "."));
			
			
			Element rashod = rowsAll.get(2);
			Node rashodtext = rashod.childNode(1).childNode(0);
			listtb2prom.add(rashodtext.toString().replaceAll("\\s+","").replace(",", "."));
			resultList.add(listtb2prom);
			
		});
		
		for(var item:resultList) {
			++rownum;
			row2 = sheet2.createRow(rownum);
	        
	        cell2 = row2.createCell(0, CellType.STRING);
        	cell2.setCellValue(item.get(0));
	        
        	cell2 = row2.createCell(1, CellType.NUMERIC);
        	cell2.setCellValue(Double.parseDouble(item.get(1)));
        	
        	cell2 = row2.createCell(2, CellType.NUMERIC);
        	cell2.setCellValue(Double.parseDouble(item.get(2)));
		}
		
	
		
		File file2 = new File(rasplog+"smallTable2.xls");
        file2.getParentFile().mkdirs();
 
        FileOutputStream outFile2 = new FileOutputStream(file2);
        workbook2.write(outFile2);
        raspFile2 = "Created file: " + file2.getAbsolutePath();
		
	}
}
