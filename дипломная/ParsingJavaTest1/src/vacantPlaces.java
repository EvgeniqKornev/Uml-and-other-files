import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class vacantPlaces {
	String rasplFile;

	public vacantPlaces(String raspDirect) throws IOException {
		// TODO Auto-generated method stub

		Document document = Jsoup.connect("https://nngasu.ru/sveden/vacant/").get();
		 //+ "?User-Agent=Chrome/81.0.4044.138"
		
		
		List<List<String>> myList = new ArrayList<List<String>>();
				
		Elements row_tb = document.getElementsByAttributeValue("itemprop", "vacant");
		row_tb.forEach(row_tb_num -> {
			List<String> list_one = new ArrayList<String>();
 			Elements eduCode = row_tb_num.getElementsByAttributeValue("itemprop", "eduCode");
			Element eduCode_value = eduCode.get(0);
			//System.out.println(eduCode_value.text());
			list_one.add(eduCode_value.text());
			
			
			  Elements eduName = row_tb_num.getElementsByAttributeValue("itemprop",
			  "eduName"); Element eduName_value = eduName.get(0);
			  list_one.add(eduName_value.text());
			  
			  Elements eduLevel = row_tb_num.getElementsByAttributeValue("itemprop",
			  "eduLevel"); Element eduLevel_value = eduLevel.get(0);
			  list_one.add(eduLevel_value.text());
			  
			  Elements eduCourse = row_tb_num.getElementsByAttributeValue("itemprop",
			  "eduCourse"); Element eduCourse_value = eduCourse.get(0);
			  list_one.add(eduCourse_value.text());
			  
			  Elements eduForm = row_tb_num.getElementsByAttributeValue("itemprop",
			  "eduForm"); Element eduForm_value = eduForm.get(0);
			  list_one.add(eduForm_value.text()); 
			  //--------- 
			  Elements numberBFVacant = row_tb_num.getElementsByAttributeValue("itemprop", "numberBFVacant"); Element
			  numberBFVacant_value = numberBFVacant.get(0);
			  list_one.add(numberBFVacant_value.text());
			  
			  Elements numberBRVacant = row_tb_num.getElementsByAttributeValue("itemprop",
			  "numberBRVacant"); Element numberBRVacant_value = numberBRVacant.get(0);
			  list_one.add(numberBRVacant_value.text());
			  
			  Elements numberBMVacant = row_tb_num.getElementsByAttributeValue("itemprop",
			  "numberBMVacant"); Element numberBMVacant_value = numberBMVacant.get(0);
			  list_one.add(numberBMVacant_value.text());
			  
			  Elements numberPVacant = row_tb_num.getElementsByAttributeValue("itemprop",
			  "numberPVacant"); Element numberPVacant_value = numberPVacant.get(0);
			  list_one.add(numberPVacant_value.text());
			 
			
			myList.add(list_one);


		});
		
			/*
			 * for (var item:myList) { System.out.println(item.toString()); }
			 */
		
		writeInExcel wie = new writeInExcel(myList,raspDirect );
		rasplFile = wie.raspolgFile;
	}

}
