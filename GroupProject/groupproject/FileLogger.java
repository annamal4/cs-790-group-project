package groupproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FileLogger {
	protected String logText;
	protected static String logFileName = "LogFile.txt";
	protected static FileWriter fw;
	
	public FileLogger() { 
		logText = "";
		try {
            File log = new File(logFileName);
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.ns");
            String var = "";
            if (log.createNewFile()) {
              var  = java.time.LocalDateTime.now().format(myFormatObj)+": File created: " + log.getName();
              System.out.println(var);
              write(var);
            } else {
            	var = java.time.LocalDateTime.now().format(myFormatObj)+": File already exists. Deleted and created again.";
              System.out.println(var);
              write(var);
              log.delete();
              log.createNewFile();
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        try {
			fw = new FileWriter(logFileName);
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	public void write(String toAdd) {
		logText+=toAdd+"\n";
	}
	public void writeToFile() {
		try {
	    	fw = new FileWriter(logFileName);
	    	fw.write(logText);
	    	fw.close();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	}

}
