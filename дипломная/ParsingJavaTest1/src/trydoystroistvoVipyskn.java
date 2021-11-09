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

public class trydoystroistvoVipyskn {

	public String rasplFile;
	public trydoystroistvoVipyskn(String derPath) throws IOException {
		
		
		Document documentStepn = Jsoup.connect("https://nngasu.ru/sveden/grants/"+ "?User-Agent=Chrome/81.0.4044.148").get();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Graduates placement");
		
		Elements tablesAll = documentStepn.getElementsByAttributeValue("class", "table-small");		
		Element bigTable = tablesAll.get(1);
		
		Node titlerow1 = bigTable.childNode(1).childNode(1);
		Node titlerow2 = bigTable.childNode(1).childNode(3);
		
		Node Code = titlerow1.childNode(1).childNode(0);
		Node nameProfes = titlerow1.childNode(3).childNode(0);
		Node year20 = titlerow1.childNode(5).childNode(0);
		Node year19 = titlerow1.childNode(7).childNode(0);
		Node year18 = titlerow1.childNode(9).childNode(0);
		
		List<String> year = new ArrayList<String>();
		year.add(year20.toString());
		year.add(year19.toString());
		year.add(year18.toString());
		
		Node kolvGraduate = titlerow2.childNode(1).childNode(0);
		Node kolvTrydoystrGraduate = titlerow2.childNode(3).childNode(0);
		
		
		int rownum = 0; 
		Row row;
		Cell cell;
		
		row = sheet.createRow(rownum);
		cell = row.createCell(0, CellType.STRING);
    	cell.setCellValue(Code.toString());
    	
    	cell = row.createCell(1, CellType.STRING);
    	cell.setCellValue(nameProfes.toString());
    	
    	
		 /*
    	sheet.addMergedRegion(new CellRangeAddress(0,1,0,0)); 
    	sheet.addMergedRegion(new CellRangeAddress(0,1,1,1)); 
    	sheet.addMergedRegion(new CellRangeAddress(0,0,2,3)); 
    	sheet.addMergedRegion(new CellRangeAddress(0,0,4,5)); 
    	sheet.addMergedRegion(new CellRangeAddress(0,0,6,7));
    	*/
    	    	
    	for(int i = 2;i<8;i++) {	
    		
    			
	    		if(i%2==0) {
			    	cell = row.createCell(i, CellType.STRING);
			    	if(i==2 || i==3)
			    	cell.setCellValue(kolvGraduate.toString() + " "+ year.get(0));
			    	if(i==4 || i==5)
			    		cell.setCellValue(kolvGraduate.toString() + " "+ year.get(1));
			    	if(i==6 || i==7)
			    		cell.setCellValue(kolvGraduate.toString() + " "+ year.get(2));
	    		}
	    		else {
	    			cell = row.createCell(i, CellType.STRING);
	    			if(i==2 || i==3)
			    	cell.setCellValue(kolvTrydoystrGraduate.toString() + " "+ year.get(0));
	    			if(i==4 || i==5)
				    	cell.setCellValue(kolvTrydoystrGraduate.toString() + " "+ year.get(1));
	    			if(i==6 || i==7)
				    	cell.setCellValue(kolvTrydoystrGraduate.toString() + " "+ year.get(2));
	    		}
    		
    	}
		
    	List<List<String>> resultListGr = new ArrayList<List<String>>();
    	
    	Elements dataGrJob = bigTable.getElementsByAttributeValue("itemprop", "graduateJob");
    	dataGrJob.forEach(strJob ->{
    		List<String> itemList = new ArrayList<String>();
    		
    		Elements eduCode = strJob.getElementsByAttributeValue("itemprop", "eduCode");
    		itemList.add(eduCode.text());
    		Elements eduName = strJob.getElementsByAttributeValue("itemprop", "eduName");
    		itemList.add(eduName.text());
    		
    		Elements v3 = strJob.getElementsByAttributeValue("itemprop", "v3");
    		itemList.add(v3.text());
    		Elements t3 = strJob.getElementsByAttributeValue("itemprop", "t3");
    		itemList.add(t3.text());
    		
    		Elements v2 = strJob.getElementsByAttributeValue("itemprop", "v2");
    		itemList.add(v2.text());
    		Elements t2 = strJob.getElementsByAttributeValue("itemprop", "t2");
    		itemList.add(t2.text());
    		
    		Elements v1 = strJob.getElementsByAttributeValue("itemprop", "v1");
    		itemList.add(v1.text());
    		Elements t1 = strJob.getElementsByAttributeValue("itemprop", "t1");
    		itemList.add(t1.text());
    		
    		
    		resultListGr.add(itemList);
    	});
    	
    	for(var item:resultListGr) {
    		rownum++;
    		row = sheet.createRow(rownum);
    		for(int k = 0;k<8;k++) {
    	    		
	    		if(k<2) 
	    		{
	    			cell = row.createCell(k, CellType.STRING);
			    	cell.setCellValue(item.get(k).toString());
			    			    	
	    		}
	    		else 
	    		{
	    			cell = row.createCell(k, CellType.NUMERIC);
			    	cell.setCellValue(Integer.parseInt(item.get(k)));
	    		}
    		}
    			
    	}
    	
    	
    	
    	
    	File file = new File(derPath+"/graduate.xls");
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        rasplFile = "Created file: " + file.getAbsolutePath();
        //System.out.println(rasplFile);
		
	}

}
