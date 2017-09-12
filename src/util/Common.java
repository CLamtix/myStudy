package util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 通用方法类
 * @author gaobo
 *
 */
public class Common {
	
	//时间格式
	public static DateFormat df1 = new SimpleDateFormat("yyyyMMdd HHmmss");
	public static DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat df3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public static DateFormat df4 = new SimpleDateFormat("HH:mm:ss");
	public static DateFormat df5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static DateFormat df6 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static DateFormat df7 = new SimpleDateFormat("yyyyMMdd");
	public static DateFormat df8 = new SimpleDateFormat("HHmmss");
	public static DateFormat df9 = new SimpleDateFormat("yyyyMd");
	public static DateFormat df10 = new SimpleDateFormat("H:m:s");
	/**
	 * 取当前时间
	 * @author gaobo
	 * @return
	 */
	public static Date getDate(){
		Calendar ca = Calendar.getInstance();
		Date date = ca.getTime();
		return date;		
	}
	
	/**
	 * 将字符串首字母转为小写形式
	 */
	public static String toLowerCaseStart(String str){
		String startStr = str.substring(0,1);		
		String _str = str.replaceFirst(startStr,startStr.toLowerCase());
		return _str;
	}
	
    public static Date getLastDay(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
    public static Date getNestDay(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		return date;
	}
    /**
     * 
     * <b>方法说明：</b>
     * <ul>
     * 判断传入的日期是否是当天
     * </ul>
     * @param date
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date,Date date2)
	{
    	if(date==null){
    		return false;
    	}
		if(df7.format(date).equals(df7.format(date2))){
			return true;
		}
		return false;
	}
}
