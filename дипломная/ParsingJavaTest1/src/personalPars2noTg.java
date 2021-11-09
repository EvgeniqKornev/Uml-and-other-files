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

public class personalPars2noTg {
	
	public static String raspFile;

	public static void main(String[] args) throws IOException {
	
		
		String raspolog = "C:\\Евг_документы\\Диплом\\Данные\\Ексельки\\Данные таблиц\\";
		Document documentPers = Jsoup.connect("https://nngasu.ru/sveden/employees/Prof_pedagog_sostav.php"+ "?User-Agent=Chrome/81.0.4044.148").get();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("pedagog structure");
		
        Elements tablesAll = documentPers.getElementsByAttributeValue("class", "table-small");
		Element titleTb = tablesAll.get(0);
		Node dataTb = titleTb.childNode(1);
		Node titleStr = titleTb.childNode(1).childNode(0);
		List<String> titleList = new ArrayList<String>();
		List<List<String>> resultList = new ArrayList<List<String>>();
		
		for (int i = 1;i<titleStr.childNodeSize();i = i+2) {
			if(i==17)continue;
			titleList.add(titleStr.childNode(i).childNode(0).toString());
		}
		
		int rownum = 0; 
		Row row;
		Cell cell;
		
		row = sheet.createRow(rownum);
		int k = 0;
		for (var item:titleList) {
			cell = row.createCell(k, CellType.STRING);
			cell.setCellValue(item);
			k++;
			
		}
	    int r = dataTb.childNodeSize();
		for (int i = 2;i<dataTb.childNodeSize();i=i+2) {
			List<String> promResult = new ArrayList<String>();
			for(int j = 1;j<22;j+=2) {
				if(j==17)continue;
				promResult.add(dataTb.childNode(i).childNode(j).childNodes().get(0).toString());
			}
			resultList.add(promResult);
		}
		
		for(var rowVal:resultList) {
			rownum++;
			row = sheet.createRow(rownum);
			for(int i = 0;i<10;i++) {
				if(i<8) {
					cell = row.createCell(i, CellType.STRING);
					cell.setCellValue(rowVal.get(i).toString());
				}
				else
				{
					cell = row.createCell(i, CellType.NUMERIC);
					if(rowVal.get(i).equals("#Н/Д")) {
						cell.setCellValue(rowVal.get(i).toString());
					}
					else
					{
						cell.setCellValue(Double.parseDouble(rowVal.get(i).replaceAll("\\s+","").replace(",", ".")));
					}
				}
			}
		}
		
		
		File file = new File(raspolog+"structurePedagogs.xls");
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        raspFile = "Created file: " + file.getAbsolutePath();   
        System.out.println(raspFile);
	}

}
