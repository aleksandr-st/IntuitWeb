package tamplater;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PageGenerator {
	private static final String HTM_DIR = "tml";
	private static final Configuration CFG = new Configuration(); 
		
	public static String getTime(){
		Date date = new Date();
		date.getTime();
		DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
		return formatter.format(date);
	}
	
	public static String getPage(String fileName, Map<String,Object> data){
		Writer stream = new StringWriter();
		try{
			Template template = CFG.getTemplate(HTM_DIR + File.separator + fileName);
			template.process(data, stream);
		}catch(IOException | TemplateException e){
			e.printStackTrace();
		}
		
		return stream.toString();
	}
}
