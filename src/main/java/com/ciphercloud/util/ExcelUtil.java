package com.ciphercloud.util;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import com.ciphercloud.util.ExcelUtil;

public class ExcelUtil {
	public String id = null;
	public String module = null;
	public String name = null;
	public String description = null;

	public String tcParameters = null;
	public String tcFields = null;
	public String tcDropDown = null;
	public String tcDate = null;
	public String tcSelect = null;
	public String tcButtons = null;
	public String tcLabel = null;
	public String tcFileUpload = null;
	public String tcpath = null;
	public String tctabs = null;
	public String tchyperlink = null;
	public String tcCheckbox = null;
	public String tcToolbar = null;

	public String page2tcParameters = null;
	public String page2tcFields = null;
	public String page2tcDropDown = null;
	public String page2tcDate = null;
	public String page2tcSelect = null;
	public String page2tcButtons = null;
	public String page2tcLabel = null;
	public String page2tcFileUpload = null;
	public String page2tcpath = null;
	public String page2tctabs = null;
	public String page2tchyperlink = null;
	public String page2tcCheckbox = null;
	public String page2tcToolbar = null;

	public String parameters13 = null;
	public String parameters14 = null;
	public String parameters15 = null;
	public String parameters16 = null;
	public String parameters17 = null;
	public String parameters18 = null;
	public String parameters19 = null;
	public String parameters20 = null;
	public String parameters21 = null;

	public ExcelUtil(String id, String module, String name, String desc,
			String params, String params1, String params2, String params3,
			String params4, String params5, String params6, String params7,
			String params8, String params9, String params10, String params11,
			String params12, String page2params, String page2params1,
			String page2params2, String page2params3, String page2params4,
			String page2params5, String page2params6, String page2params7,
			String page2params8, String page2params9, String page2params10,
			String page2params11, String page2params12, String params30,
			String params31, String params32, String params33, String params34,
			String params35, String params36, String params37, String params38) {
		this.id = id;
		this.module = module;
		this.name = name;
		this.description = desc;

		this.tcParameters = params;
		this.tcFields = params1;
		this.tcDropDown = params2;
		this.tcDate = params3;
		this.tcSelect = params4;
		this.tcButtons = params5;
		this.tcLabel = params6;
		this.tcFileUpload = params7;
		this.tcpath = params8;
		this.tctabs = params9;
		this.tchyperlink = params10;
		this.tcCheckbox = params11;
		this.tcToolbar = params12;

		this.page2tcParameters = page2params;
		this.page2tcFields = page2params1;
		this.page2tcDropDown = page2params2;
		this.page2tcDate = page2params3;
		this.page2tcSelect = page2params4;
		this.page2tcButtons = page2params5;
		this.page2tcLabel = page2params6;
		this.page2tcFileUpload = page2params7;
		this.page2tcpath = page2params8;
		this.page2tctabs = page2params9;
		this.page2tchyperlink = page2params10;
		this.page2tcCheckbox = page2params11;
		this.page2tcToolbar = page2params12;

		this.parameters13 = params30;
		this.parameters14 = params31;
		this.parameters15 = params32;
		this.parameters16 = params33;
		this.parameters17 = params34;
		this.parameters18 = params35;
		this.parameters19 = params36;
		this.parameters20 = params37;
		this.parameters21 = params38;
	}

	public static LinkedHashMap<String, LinkedHashMap<String, String>> hash(
			List<ExcelUtil> excelTCObj, int x) {
		LinkedHashMap<String,LinkedHashMap<String,String>> hash2 = new LinkedHashMap<String,LinkedHashMap<String,String>>();
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcParameters)
				.isEmpty()))
			hash2.put(
					"parameters",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcParameters));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcFields)
				.isEmpty()))
			hash2.put("field",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcFields));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcDropDown)
				.isEmpty()))
			hash2.put("dropdown",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcDropDown));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcDate).isEmpty()))
			hash2.put("date",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcDate));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcSelect)
				.isEmpty()))
			hash2.put("select",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcSelect));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcButtons)
				.isEmpty()))
			hash2.put("button",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcButtons));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcLabel).isEmpty()))
			hash2.put("label",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcLabel));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcFileUpload)
				.isEmpty()))
			hash2.put(
					"file",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcFileUpload));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcpath).isEmpty()))
			hash2.put("url",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcpath));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tctabs).isEmpty()))
			hash2.put("tabs",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tctabs));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tchyperlink)
				.isEmpty()))
			hash2.put("hyperlink",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tchyperlink));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcCheckbox)
				.isEmpty()))
			hash2.put("checkbox",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcCheckbox));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).tcToolbar)
				.isEmpty())) {
			hash2.put("toolbar",
					findParameters(((ExcelUtil) excelTCObj.get(x)).tcToolbar));
		}
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcParameters)
				.isEmpty()))
			hash2.put(
					"span",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcParameters));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcFields)
				.isEmpty()))
			hash2.put(
					"title",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcFields));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcDropDown)
				.isEmpty()))
			hash2.put(
					"select2",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcDropDown));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcDate)
				.isEmpty()))
			hash2.put("label2",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcDate));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcSelect)
				.isEmpty()))
			hash2.put(
					"dropdown2",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcSelect));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcButtons)
				.isEmpty()))
			hash2.put(
					"field2",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcButtons));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcLabel)
				.isEmpty()))
			hash2.put(
					"dropdown3",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcLabel));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcFileUpload)
				.isEmpty()))
			hash2.put(
					"checkbox3",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcFileUpload));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcpath)
				.isEmpty()))
			hash2.put("page2path",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcpath));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tctabs)
				.isEmpty()))
			hash2.put("page2tabs",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tctabs));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tchyperlink)
				.isEmpty()))
			hash2.put(
					"page2hyperlink",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tchyperlink));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcCheckbox)
				.isEmpty()))
			hash2.put(
					"page2checkbox",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcCheckbox));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcToolbar)
				.isEmpty())) {
			hash2.put(
					"page2toolbar",
					findParameters(((ExcelUtil) excelTCObj.get(x)).page2tcToolbar));
		}

		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).parameters13)
				.isEmpty())) {
			hash2.put(
					"special1",
					findParameters(((ExcelUtil) excelTCObj.get(x)).parameters13));
		}
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).parameters14)
				.isEmpty())) {
			hash2.put(
					"special2",
					findParameters(((ExcelUtil) excelTCObj.get(x)).parameters14));
		}
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).parameters15)
				.isEmpty())) {
			hash2.put(
					"special3",
					findParameters(((ExcelUtil) excelTCObj.get(x)).parameters15));
		}
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).parameters16)
				.isEmpty())) {
			hash2.put(
					"special4",
					findParameters(((ExcelUtil) excelTCObj.get(x)).parameters16));
		}
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).parameters17)
				.isEmpty())) {
			hash2.put(
					"special5",
					findParameters(((ExcelUtil) excelTCObj.get(x)).parameters17));
		}
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).parameters18)
				.isEmpty()))
			hash2.put(
					"special6",
					findParameters(((ExcelUtil) excelTCObj.get(x)).parameters18));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).parameters19)
				.isEmpty()))
			hash2.put(
					"special7",
					findParameters(((ExcelUtil) excelTCObj.get(x)).parameters19));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).parameters20)
				.isEmpty()))
			hash2.put(
					"special8",
					findParameters(((ExcelUtil) excelTCObj.get(x)).parameters20));
		if (!(findParameters(((ExcelUtil) excelTCObj.get(x)).parameters21)
				.isEmpty())) {
			hash2.put(
					"special9",
					findParameters(((ExcelUtil) excelTCObj.get(x)).parameters21));
		}
		return hash2;
	}

	public static LinkedHashMap<String, String> findParameters(
			String parameters2) {
		LinkedHashMap<String,String> hash = new LinkedHashMap<String,String>();
		StringTokenizer tokenizer = new StringTokenizer(parameters2.trim(),
				"\n");
		int count = tokenizer.countTokens();
		try {
			for (int i = 0; i < count; ++i) {
				String x = tokenizer.nextToken();
				if (x.contains("=")) {
					String test = x.substring(0, x.indexOf("="));
					String test2 = x.substring(x.indexOf("="), x.length())
							.replace("=", "");
					if (!(test.startsWith("#"))) {
						if (test2.trim().equals("$SPACE")) {
							hash.put(test.trim(), "    ");
						} else {
							hash.put(test.trim(), test2.trim());
						}
						
						if(test2.trim().contains("$RANDOM")){
							hash.put(test.trim(), test2.trim().replace("$RANDOM", randomString1()));
					}else if(test2.trim().contains("$DYNAMIC_INT")){
						hash.put(test.trim(), test2.trim().replace("$DYNAMIC_INT", randomString()));

					}else if(test2.trim().contains("$Date_Day")){
						hash.put(test.trim(), test2.trim().replace("$Date_Day", Integer.toString(new Random().nextInt((31 - 1) + 1) + 1)));
					}else if(test2.trim().contains("$Date_Year")){
						hash.put(test.trim(), test2.trim().replace("$Date_Year", Integer.toString(new Random().nextInt((1996 - 1900) + 1) + 1900)));
					}else if(test2.trim().contains("$equal")){
						hash.put(test.trim(), test2.trim().replace("$equal","="));
					}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Exception occured while tokenizing the parameters in respected cells ");
		}

		return hash;
	}
	
	
	  public static SecureRandom random = new SecureRandom();

	  final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	  final static Random rand = new Random();

	  final static Set<String> identifiers = new HashSet<String>();
	 public static String randomString1()
	  {
	    StringBuilder builder = new StringBuilder();
	    while (builder.toString().length() == 0) {
	      int length = rand.nextInt(4) + 4;
	      for (int i = 0; i < length; ++i)
	        builder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(rand.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
	      if (identifiers.contains(builder.toString()))
	        builder = new StringBuilder();
	    }
	    return builder.toString();
	  }

	 
	 public static String randomString()
		{
			int length=9;
			Random rng = new Random();
			String characters="12345678901234567890";
			char[] text = new char[length];
			for (int i = 0; i < length; i++)
			{
				text[i] = characters.charAt(rng.nextInt(characters.length()));
			}
			return new String(text);
		}
	 
	 
	 
}