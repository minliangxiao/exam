package prj.util;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

/**
 * CVS 文件助手
 * @author Rayman
 *
 */
public class CsvHelper {
	public static ArrayList<ArrayList<String>> export(String filePath) throws IOException {
		return export(filePath,"utf-8");
	}
	public static ArrayList<ArrayList<String>> export(String filePath,String encoding) throws IOException {
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
		ArrayList<String> row = null;
		File file =new File(filePath);
        InputStreamReader isr=new InputStreamReader(new FileInputStream(file),encoding);
        CsvReader reader = new CsvReader(isr);       //默认是逗号分隔符，UTF-8编码
        reader.readRecord();  // 空出表头的行
        while(reader.readRecord()){   
        	row = new ArrayList<String>();
            String[] values = reader.getValues();
            for (String v : values) {
				row.add(v);
			}
            table.add(row);
        }  
        reader.close();  
        
		return table;
	}
}
