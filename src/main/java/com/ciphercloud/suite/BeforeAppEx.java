package com.ciphercloud.suite;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import com.ciphercloud.autoUtil.Atom;
import com.ciphercloud.util.DataSource;
//import com.ciphercloud.util.ExecLinuxScripts;
import com.ciphercloud.util.SelectingWebDriver;



public class BeforeAppEx {
    private static final Logger log = LoggerFactory.getLogger(BeforeAppEx.class);

	DataSource dd = null;
	@Parameters({"localhost","browser"})
	@BeforeSuite
	public   void beforeSuite(String localhost,String browser  ) throws Exception{
		dd = new DataSource(localhost,browser);
		dd.loadProperties();
		DataSource.buffer();
		//DeleteFileFolder("./src/test/resources/Screenshot/");
		log.info(localhost +","+ browser );
		//dd.createXmlFile();
		/*DataSource.map.get("cleandb");
		if(DataSource.map.get("cleandb").contains("true")){
			//ExecLinuxScripts.excLinuxScriptsToCleanDB();
		}*/
	}


	
	//please enable beforTest4Suite,beforeSuite4Class and afterTest4Suite  
	@AfterSuite(groups = { "smokeTest" })
	public void afterSuite(){
		try{
			log.info("afterSuite");
			DataSource.map.clear();
			DataSource.buffer.clear();
			if(SelectingWebDriver.driver!=null){
				log.info("i am in afterSuite");
				//SelectingWebDriver.driver.close();
				SelectingWebDriver.driver.quit();
				SelectingWebDriver.driver= null;
			}
			

			//cleaning 
		}catch(Exception e){
	log.info("error occured while closing the browser ,Error message:"+e.getMessage());
		}
	}
	
	

public void DeleteFileFolder(String path) {

    File file = new File(path);
    if(file.exists())
    {
        do{
            delete(file);
        }while(file.exists());
    }else
    {
        log.info("File or Folder not found : "+path);
    }

}
private void delete(File file)
{
    if(file.isDirectory())
    {
        String fileList[] = file.list();
        if(fileList.length == 0)
        {
            log.info("Deleting Directory : "+file.getPath());
            file.delete();
        }else
        {
            int size = fileList.length;
            for(int i = 0 ; i < size ; i++)
            {
                String fileName = fileList[i];
                log.info("File path : "+file.getPath()+" and name :"+fileName);
                String fullPath = file.getPath()+"/"+fileName;
                File fileOrFolder = new File(fullPath);
                log.info("Full Path :"+fileOrFolder.getPath());
                delete(fileOrFolder);
            }
        }
    }else
    {
        log.info("Deleting file : "+file.getPath());
        file.delete();
    }
	
	
	
}
	
	
	
	
	
	
	
}
