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

public class studentChislenst {

	public String raspFile;
	public studentChislenst(String raspolog) throws IOException {
		
		Document documentPer = Jsoup.connect("https://nngasu.ru/sveden/education/Chislen.php"+ "?User-Agent=Chrome/81.0.4044.148").get();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("chislennost stydentov");
		
		
		Elements smlTable = documentPer.getElementsByAttributeValue("class", "table-small");
		Node titleRow1 = smlTable.get(0).childNode(1).childNode(0);
		Node titleRow2 = smlTable.get(0).childNode(1).childNode(2);
		
		List<String> titlListR1 = new ArrayList<String>();
		List<String> titlListR2 = new ArrayList<String>();
		List<String> titlListAll = new ArrayList<String>();
		
		for(int item = 1;item<12;item+=2) {
			titlListR1.add(titleRow1.childNode(item).childNode(1).childNode(0).toString());
		}
		for(int item = 1;item<8;item+=2) {
			titlListR2.add(titleRow2.childNode(item).childNode(1).childNode(0).toString());
		}
		
		for(int k = 0;k<titlListR1.size();k++) {
			if(k !=4) 
			{
			titlListAll.add(titlListR1.get(k));
			}
			else
			{
				for(int i = 0;i<titlListR2.size();i++) {
					String str = titlListR1.get(k) + " " + titlListR2.get(i);
					titlListAll.add(str);
				}
			}
		}
		
		List<List<String>> resultList = new ArrayList<List<String>>();
		int rownum = 0; 
		Row row;
		Cell cell;
		
		row = sheet.createRow(rownum);
		for(int k = 0;k<titlListAll.size();k++) {
			cell = row.createCell(k, CellType.STRING);
	    	cell.setCellValue(titlListAll.get(k));
		}
		
		
		Elements rowsMainTb = documentPer.getElementsByAttributeValue("itemprop", "eduChislen");
		rowsMainTb.forEach(nrow -> {
			
			List<String> listZnach = new ArrayList<String>();
			
			listZnach.add(nrow.getElementsByAttributeValue("itemprop", "eduCode").text());
			listZnach.add(nrow.getElementsByAttributeValue("itemprop", "eduName").text());
			listZnach.add(nrow.getElementsByAttributeValue("itemprop", "eduLevel").text());
			listZnach.add(nrow.getElementsByAttributeValue("itemprop", "eduForm").text());
			listZnach.add(nrow.getElementsByAttributeValue("itemprop", "numberBF").text());
			listZnach.add(nrow.getElementsByAttributeValue("itemprop", "numberBR").text());
			listZnach.add(nrow.getElementsByAttributeValue("itemprop", "numberBM").text());
			listZnach.add(nrow.getElementsByAttributeValue("itemprop", "numberP").text());
			listZnach.add(nrow.getElementsByAttributeValue("itemprop", "numberF").text());
			
			resultList.add(listZnach);
			
		});
		
		for(var elem:resultList) {
			rownum++;
			row = sheet.createRow(rownum);
			for(int i = 0;i<titlListAll.size();i++) {
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
		
		File file = new File(raspolog+"chislennostStyd.xls");
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        raspFile = "Created file: " + file.getAbsolutePath();   	
		

	}

}
