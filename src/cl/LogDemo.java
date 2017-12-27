package cl;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class LogDemo {
	private static Logger log = Logger.getLogger("cl");
	
	public static void main(String[] args) {
//		log.debug("this is debug message!");
//		
//		log.info("this is info message!");
		
		try {
			f();
		} catch (Exception e) {
			log.error(getExceptionTrace(e));
		}
	}
	public static void f(){
		String s = null;
		s.charAt(3);
	}
	
	public static String getExceptionTrace(Throwable e) {
		if (e != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return sw.toString();
		}
       return "No Exception";
    }
}

