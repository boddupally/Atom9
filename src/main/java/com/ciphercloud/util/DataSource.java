package com.ciphercloud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.ciphercloud.util.Helper;
import com.ciphercloud.util.DataSource;
import com.ciphercloud.util.ExcelUtil;

import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;



public class DataSource {
    private static final Logger log = LoggerFactory.getLogger(DataSource.class);

	public static String localhost = null;
	public static String screenshot = null;
	public static String browser = null;

	public static LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	Object[][] obj2 = null;
	Object[][] obj = null;
	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> buffer;
	public static LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>> bufferIteratons;
	static Helper helper = new Helper();
	static Helper helperIterations = new Helper();

	public DataSource() {
	}

	public DataSource(String args, String args2) {
		localhost = args;
		browser = args2;
	}

	public static void buffer() throws Exception {
		buffer 			= new LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String, String>>>();
		bufferIteratons = new LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String, String>>>>();

		FileInputStream fis = new FileInputStream(new File(	"src/main/resources/IterateData.xls"));
		FileInputStream fileInputStream4Iterations = new FileInputStream(new File("src/main/resources/IterateData.xls"));
		Workbook workbook4Iterations = WorkbookFactory.create(fileInputStream4Iterations);
		Workbook wb = WorkbookFactory.create(fis);

		for (int i = 0; i < workbook4Iterations.getNumberOfSheets(); ++i) {
			Sheet sheet = workbook4Iterations.getSheetAt(i);
			LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String, String>>> bufferIteratonsInternal = new LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String, String>>>();
			List<ExcelUtil> excelTCObj= helperIterations.parseExcelTestCases(sheet);
			int count= excelTCObj.size();
			
			for(int j=0;j<count;j++){
					bufferIteratonsInternal.put(excelTCObj.get(j).name.trim(), ExcelUtil.hash(excelTCObj,j)) ;				
			}
			
			bufferIteratons.put(sheet.getSheetName(), bufferIteratonsInternal);
		}

		for (int i = 0; i < wb.getNumberOfSheets(); ++i) {
			Sheet sheet = wb.getSheetAt(i);
			List<ExcelUtil> excelTCObj= helper.parseExcelTestCases(sheet);
			int count= excelTCObj.size();
			for(int j=0;j<count;j++){
				if(!excelTCObj.get(j).name.trim().startsWith("#")){
				buffer.put(excelTCObj.get(j).module.trim()+"_"+excelTCObj.get(j).name.trim(), ExcelUtil.hash(excelTCObj,j)) ;
				}
			}
		}
		fileInputStream4Iterations.close();
		fis.close();
	}

	public Object[][] dataDrive4Iteration(String methodName) {
 		LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String, String>>> bufferIteratonsInternal = new LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String, String>>>();
		bufferIteratonsInternal =
				bufferIteratons.get(extractSimpleClassName(Thread.currentThread().getStackTrace()[2].getClassName())+"_"+methodName);
		if(bufferIteratonsInternal!=null){
		ArrayList<String> arrayList =null;
		arrayList= new ArrayList<String>();
		for(Entry<String, LinkedHashMap<String, LinkedHashMap<String, String>>> entry:bufferIteratonsInternal.entrySet())
		{
			if(!entry.getKey().startsWith("#"))
			{
				arrayList.add(entry.getKey());
			}
		}
	int siz =arrayList.size();
	obj2 = new Object[siz][1];
	for(int i=0;i<siz;i++){
		obj2[i][0]=bufferIteratonsInternal.get(arrayList.get(i));
	}
	arrayList.clear();
	return obj2;
		}else {

			this.obj = new Object[1][1];
			try {
				this.obj[0][0] = buffer.get(extractSimpleClassName(java.lang.Thread.currentThread().getStackTrace()[2].getClassName())+ "_"+ methodName);
			} catch (Exception e) {
				System.out
						.println("please enable beforTest4Suite,beforeSuite4Class and afterTest4Suite  constants in Excuted class");
			}

			return this.obj;
		
			
			
		}
	}

	/*public Object[][] dataDrive(String methodName) {
		this.obj = new Object[1][1];
		try {
			this.obj[0][0] = buffer.get(extractSimpleClassName(java.lang.Thread.currentThread().getStackTrace()[2].getClassName())+ "_"+ methodName);
		} catch (Exception e) {
			System.out
					.println("please enable beforTest4Suite,beforeSuite4Class and afterTest4Suite  constants in Excuted class");
		}

		return this.obj;
	}
*/
	public static String extractSimpleClassName(String fullClassName) {
		if ((fullClassName == null) || ("".equals(fullClassName))) {
			return "";
		}
		int lastDot = fullClassName.lastIndexOf(46);
		if (lastDot < 0) {
			return fullClassName;
		}
		return fullClassName.substring(++lastDot);
	}

	private static String readFile(String path) throws IOException {
		  FileInputStream stream = new FileInputStream(new File(path));
		  try {
		    FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    /* Instead of using default, pass in a decoder. */
		    return Charset.defaultCharset().decode(bb).toString();
		  }
		  finally {
		    stream.close();
		  }
		}

	public void loadProperties() {
		Properties prop = new Properties();
		//localhost=projectPath;
		try {
			prop.load(new StringReader(readFile("src/main/resources/CipherCloud-APP-Util.properties").replace(" ", "~"))); } catch (Exception e) {e.printStackTrace();}
		for(Object str: prop.keySet()) {
			map.put(((String)str).trim().replace("~", " ").trim(),prop.getProperty((String) str).trim().replace("~", " ").trim() );
		}
		
	}

	public String propFile(String propertiesName) {
		return ((String) map.get(propertiesName.trim()));
	}
	
	
	
	
	/* @DataProvider(name = "create")
	  public static Object[][] createData() {
	    return new Object[][] {
	      new Object[] { new Integer(42) }
	    }
	  }
*/
	static DataSource dd = new DataSource();
	/* @DataProvider(parallel=true)
		public static Object[][] DataProvider(Method m){
			Object[][] object =null;
			object =dd.dataDrive(m.getName());
			return object;
		}
	*/
	

	/*	@DataProvider
		public Object[][] DataProvider4Iterations(Method m){
			Object[][] object =null;
			object =dd.dataDrive(m.getName());
			return object;
		}
		
		 @DataProvider()
			public static Object[][] DataProvider(Method m){
				Object[][] object =null;
				object =dd.dataDrive(m.getName());
				return object;
			}*/
	
	
	
	
}