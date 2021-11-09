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

public class personnelPars {

	public String raspFile;
	public personnelPars(String raspolog) throws IOException {
		
		Document documentPers = Jsoup.connect("https://nngasu.ru/sveden/employees/Prof_pedagog_sostav.php"+ "?User-Agent=Chrome/81.0.4044.148").get();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("pedagog structure");
		
        Elements tablesAll = documentPers.getElementsByAttributeValue("class", "table-small");
		Element titleTb = tablesAll.get(0);
		
		
		Node titleRow = titleTb.childNode(1).childNode(1);
		
		List<List<String>> resultList = new ArrayList<List<String>>();
		int rownum = 0; 
		Row row;
		Cell cell;
		
		row = sheet.createRow(rownum);
		for (int i = 1,k = 0;i<titleRow.childNodeSize();i = i+2,k++) {
			cell = row.createCell(k, CellType.STRING);
			cell.setCellValue(titleRow.childNode(i).childNode(0).toString());
			
		}
		
		Elements dataPedagog = documentPers.getElementsByAttributeValue("itemprop", "teachingStaff");
		dataPedagog.forEach(itemstr -> {
			List<String> listZnch = new ArrayList<String>();
			
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "fio").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "post").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "teachingDiscipline").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "teachingLevel").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "teachingQual").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "degree").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "academStat").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "employeeQualification").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "profDevelopment").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "genExperience").text());
			listZnch.add(itemstr.getElementsByAttributeValue("itemprop", "specExperience").text());
			
			resultList.add(listZnch);
		});
		
		for(var elem:resultList) {
			rownum++;
			row = sheet.createRow(rownum);
			for(int i = 0;i<11;i++) {
				if(i<9) {
					cell = row.createCell(i, CellType.STRING);
					cell.setCellValue(elem.get(i).toString());
				}
				else
				{
					cell = row.createCell(i, CellType.NUMERIC);
					if(elem.get(i).equals("#Í/Ä")) {
						cell.setCellValue(elem.get(i).toString());
					}
					else
					{
						cell.setCellValue(Double.parseDouble(elem.get(i).replaceAll("\\s+","").replace(",", ".")));
					}
				}
			}
		}
		
		File file = new File(raspolog+"structurePedagogs.xls");
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        raspFile = "Created file: " + file.getAbsolutePath();   	
		

	}

}
