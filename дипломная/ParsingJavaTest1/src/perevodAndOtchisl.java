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

public class perevodAndOtchisl {

	public String raspFile;
	public perevodAndOtchisl(String raspolog) throws IOException {
		
		Document documentPer = Jsoup.connect("https://nngasu.ru/sveden/education/Prikaz_otch_perevod_vosstan.php"+ "?User-Agent=Chrome/81.0.4044.148").get();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("perevod And Otchislenia");
        
        Elements tableMain = documentPer.getElementsByAttributeValue("class", "table-small");
        
        Node secondStr = tableMain.get(0).childNode(1).childNode(1);
        List<String> titleList = new ArrayList<String>();
        for (int i = 1;i<16;i=i+2) {
        	titleList.add(secondStr.childNode(i).childNode(1).childNode(0).toString());
        }
        
        List<List<String>> resultList = new ArrayList<List<String>>();
		int rownum = 0; 
		Row row;
		Cell cell;
		
		row = sheet.createRow(rownum);
		for(int k = 0;k<titleList.size();k++) {
			cell = row.createCell(k, CellType.STRING);
	    	cell.setCellValue(titleList.get(k));
		}
		
		Elements rowsAlld = tableMain.get(0).getElementsByAttributeValue("itemprop", "eduPerevod");;
		
		for (int num = 1;num<rowsAlld.size();num++) {
			Element strTb = rowsAlld.get(num);
			
			List<String> listZnacheni = new ArrayList<String>();
			listZnacheni.add(strTb.getElementsByAttributeValue("itemprop", "eduCode").text());
			listZnacheni.add(strTb.getElementsByAttributeValue("itemprop", "eduName").text());
			listZnacheni.add(strTb.getElementsByAttributeValue("itemprop", "eduLevel").text());
			listZnacheni.add(strTb.getElementsByAttributeValue("itemprop", "eduForm").text());
			listZnacheni.add(strTb.getElementsByAttributeValue("itemprop", "numberOut").text());
			listZnacheni.add(strTb.getElementsByAttributeValue("itemprop", "numberTo").text());
			listZnacheni.add(strTb.getElementsByAttributeValue("itemprop", "numberRes").text());
			listZnacheni.add(strTb.getElementsByAttributeValue("itemprop", "numberExp").text());
			
			resultList.add(listZnacheni);
		}
		
		
		for(var elem:resultList) {
			rownum++;
			row = sheet.createRow(rownum);
			for(int i = 0;i<titleList.size();i++) {
				if(i<4) 
				{
					cell = row.createCell(i, CellType.STRING);
			    	cell.setCellValue(elem.get(i));
				}
				else
				{
					if(elem.get(i).equals("-")) 
					{
						cell = row.createCell(i, CellType.STRING);
				    	cell.setCellValue(elem.get(i));
					}
					else
					{
						cell = row.createCell(i, CellType.NUMERIC);
				    	cell.setCellValue(Integer.parseInt(elem.get(i)));
					}
				}
			}
				
		}		
			
		
		
		File file = new File(raspolog+"infPerevodAndOtchisl.xls");
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        raspFile = "Created file: " + file.getAbsolutePath();   	
		

	}

}
