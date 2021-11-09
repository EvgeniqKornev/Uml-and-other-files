import java.io.File;
import java.io.FileNotFoundException;
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
import org.jsoup.select.Elements;
import org.apache.poi.ss.*;


public class writeInExcel {

	private List<List<String>> myList;
	public String raspolgFile;

	public writeInExcel(List<List<String>> myList1,String raspolzh) throws IOException{
		this.myList = myList1;
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("vacant places");
        
        Document document = Jsoup.connect("https://nngasu.ru/sveden/vacant/").get();
      //поля 
      		Elements code = document.select("body > main > div.page__content > div > div > div > div > table > tbody > tr:nth-child(1) > th:nth-child(1)");
      		Elements name_prof_spec = document.select("body > main > div.page__content > div > div > div > div > table > tbody > tr:nth-child(1) > th:nth-child(2)");
      		Elements level_obr = document.select("body > main > div.page__content > div > div > div > div > table > tbody > tr:nth-child(1) > th:nth-child(3)");
      		Elements kyrs = document.select("body > main > div.page__content > div > div > div > div > table > tbody > tr:nth-child(1) > th:nth-child(4)");
      		Elements forma_obr = document.select("body > main > div.page__content > div > div > div > div > table > tbody > tr:nth-child(1) > th:nth-child(5)");
      		Elements atr1 = document.select("body > main > div.page__content > div > div > div > div > table > tbody > tr:nth-child(2) > td:nth-child(1)");
      		Elements atr2 = document.select("body > main > div.page__content > div > div > div > div > table > tbody > tr:nth-child(2) > td:nth-child(2)");
      		Elements atr3 = document.select("body > main > div.page__content > div > div > div > div > table > tbody > tr:nth-child(2) > td:nth-child(3)");
      		Elements atr4 = document.select("body > main > div.page__content > div > div > div > div > table > tbody > tr:nth-child(2) > td:nth-child(4)");
      		
      		List<String> titleList = new ArrayList<String>();
      		titleList.add(code.text());
      		titleList.add(name_prof_spec.text());
      		titleList.add(level_obr.text());
      		titleList.add(kyrs.text());
      		titleList.add(forma_obr.text());
      		titleList.add(atr1.text());
      		titleList.add(atr2.text());
      		titleList.add(atr3.text());
      		titleList.add(atr4.text());
                
        int rownum = 0;
        Cell cell;
        Row row;
        
        row = sheet.createRow(rownum);
        int k = 0;
        for(var ttlCell:titleList) {
        	cell = row.createCell(k, CellType.STRING);
        	cell.setCellValue(ttlCell);
        	k++;
        }
        
        for(var list:myList) {
        rownum++;
        row = sheet.createRow(rownum);
		/*
		 * for(int i = 0;i<9;i++) { cell = row.createCell(i, CellType.STRING);  //для вывода всего в виде строк
		 * cell.setCellValue(list.get(i)); }
		 */
        
        
		
		  cell = row.createCell(0, CellType.STRING); cell.setCellValue(list.get(0));
		  
		  cell = row.createCell(1, CellType.STRING); cell.setCellValue(list.get(1));
		  
		  cell = row.createCell(2, CellType.STRING); cell.setCellValue(list.get(2));
		  
		  cell = row.createCell(3, CellType.NUMERIC); if(list.get(3)=="-")
		  {cell.setCellValue(0);} else
		  cell.setCellValue(Integer.parseInt(list.get(3)));
		  
		  cell = row.createCell(4, CellType.STRING); cell.setCellValue(list.get(4));
		  
			
			  cell = row.createCell(5, CellType.NUMERIC); if(list.get(5).toString().equals("-") || list.get(5).toString().equals(""))
			  {cell.setCellValue(0);} else
			  cell.setCellValue(Integer.parseInt(list.get(5)));
			 
			
			  cell = row.createCell(6, CellType.NUMERIC); if(list.get(6).toString().equals("-") || list.get(6).toString().equals(""))
			  {cell.setCellValue(0);} else
			  cell.setCellValue(Integer.parseInt(list.get(6)));
			  
			  cell = row.createCell(7, CellType.NUMERIC); if(list.get(7).toString().equals("-") || list.get(7).toString().equals(""))
			  {cell.setCellValue(0);} else
			  cell.setCellValue(Integer.parseInt(list.get(7)));
			  
			  cell = row.createCell(8, CellType.NUMERIC); if(list.get(8).toString().equals("-") || list.get(8).toString().equals(""))
			  {cell.setCellValue(0);} else
			  cell.setCellValue(Integer.parseInt(list.get(8)));
			 
			 
        
        }
        
        File file = new File(raspolzh+"vacant.xls");
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        raspolgFile = "Created file: " + file.getAbsolutePath();
 
	}
	
	
}
