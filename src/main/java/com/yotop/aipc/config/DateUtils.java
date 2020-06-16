package com.yotop.aipc.config;

import com.yotop.aipc.core.ServiceException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;



public class DateUtils {
	/**
	 *根据参数返回
	 *当前年份+月份
	 *当前年份+周别
	 *当前年份+月+日
	 **/
	public static String getDateName(String dayflag)
	{

		String str = "";
		if (dayflag.equals("month")) {
			//默认是返回当前月份
			str = "yyyy-MM";

		} else if(dayflag.equals("week")){
			//默认是返回当前周
			str = "yyyy-ww";
		}else if (dayflag.equals("year")){
			//默认是返回当前年份
			str = "yyyy";
		}else {
			//默认是返回当前日期
			str = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		String date = sdf.format(new Date());
		return date;
	}
	/**
	 * 根据rs.getDate() 获取字符串 2009-10-12
	 *
	 * */
	public static String getUtilDate(java.sql.Date date)
	{
		Date d=new Date (date.getTime());
		String str = getDateFormat(d);
		return str;
	}

	/**
	 * 根据2001-06-07这样的字符串返回对应2010年06月07日 日期
	 *
	 * */
	public static String convertDateToChineseDate(String str){
		Date date = new Date();
		String chistr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(str);
			SimpleDateFormat chisdf = new SimpleDateFormat("yyyy年MM月dd日");
			chistr = chisdf.format(d);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return chistr;
	}

	/**
	 * 根据2001-06-07这样的字符串返回对应Date日期
	 *
	 * */
	public static Date createDate(String str){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getDate()
	{
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return sdf.format(date);
	}
	/**
	 *
	 * 将date 转换成yyyy-MM-dd HH:mm:ss
	 *
	 * */
	public static String getToday(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}


	public static String getToday()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	public static String getThisYear()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}
	public static String getThisDateHMSS()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(date);
	}
	public static String getThisDateHMS()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	public static String getThisTime()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	/**
	 *
	 * 将date 转换成yyyy-MM-dd HH:mm:ss
	 *
	 * */
	public static String getDateFormat(Date date)
	{
		if(date==null)
		{
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 *
	 * 将date 转换成yyyy-MM-dd
	 *
	 * */
	public static String getDate(Date date)
	{
		if(date==null)
		{
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	/**
	 *
	 * 将date 转换成yyyy年MM月dd日 HH时mm分ss日
	 *
	 * */
	public static String getDateFormatToChinese(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss日");
		return sdf.format(date);
	}

	/**
	 * 获取某一日期的后几天
	 * @param date
	 * @param day
	 * @return
	 */
	  public static Date getDateBefore(Date date,int day){
	   Calendar now =Calendar.getInstance();
	   now.setTime(date);
	   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);


	   return now.getTime();
	  }
	  /**
	   * 获取某一日期的后几天
	   * @param startdate
	   * @param day
	   * @return
	   */
	  public static String getDateByadd(String startdate,int day){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Date date;
		try {
			date = sdf.parse(startdate);
			Calendar now =Calendar.getInstance();
			now.setTime(date);
			now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
			date = now.getTime();
			String enddate = sdf.format(date);
			return enddate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("日期转换失败！");
		}
	  }
	  /**
	   * 获取某一日期几天后的时间
	   * @param date
	   * @param day
	   * @return
	   */
	  public static Date getDateAfter(Date date,int day){
	   Calendar now =Calendar.getInstance();
	   now.setTime(date);
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
	   return now.getTime();
	  }

	  /**
	   * 获取当前日期 后几年的日期 比如 获取2014-06-22 明年今天的日期 为 2015-06-22
	   * @param n
	   * @return
	   */
	  public static String getCurentNextNyearDate(int n){
		  Calendar now = Calendar.getInstance();
		  now.setTime(new Date());
		  now.set(Calendar.YEAR,now.get(Calendar.YEAR)+n);
		  Date date = now.getTime();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      return sdf.format(date);
	  }
	  /**
	   * 获取固定日期 后几年的日期 比如 获取2014-06-22 明年今天的日期 为 2015-06-22
	   * @param n
	   * @return
	   */
	  public static String getNextNyearDate(String startdate,int n){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Date date;
		try {
		  date = sdf.parse(startdate);
		  Calendar now = Calendar.getInstance();
		  now.setTime(date);
		  now.set(Calendar.YEAR,now.get(Calendar.YEAR)+n);
		  Date date2 = now.getTime();
		  SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		  return sdf2.format(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("日期转换失败！");
		}
	  }


	  public static long compare2DateDays(Date d1,Date d2){
		  long end = d1.getTime();
		  long now = d2.getTime();
		  long day = 0L;
		  if(!d1.before(d2)){
			  day = (end - now) / (1000 * 60 * 60 * 24) + 1;
		  }else{
			  day = (end - now) / (1000 * 60 * 60 * 24);
		  }
		  return day;
	  }

	  public static String getDateByAddMonth(String date,int month){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Date now = null;
		  try {
			now = sdf .parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Calendar ca = Calendar.getInstance();
		  ca.setTime(now);
		  ca.add(Calendar.MONTH, month);

		  return sdf.format(ca.getTime());
	  }

	  public static boolean getDateFlag(String startdate,String dqrq, int month){
		  String cd = getDateByAddMonth(startdate,month);
		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
			  if(df.parse(cd).getTime()>= df.parse(dqrq).getTime()){
				  return true;
			  }
			} catch (ParseException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	  }

	  /**
	   * 功能描述: 获取当前时间毫秒数
	   * @Param  []
	   * @Return  java.lang.String
	   * @Author  wangxin
	   * @Date   2019/11/4 15:28
	   **/
	  public static String getDateTimemilli(){
		  long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
		  String s = String.valueOf(l);
		  return s;
	  }

    /**
     * 计算两个日期之间的天数
     * @param startdate 开始日期
     * @param lastdate 结束日期
     * @return 天数
     */
	  public static long getBetweenDay(String startdate, String lastdate){
	  	if(startdate.length()>10){
		    startdate = startdate.substring(0,10);
		}else{
			String[] split = startdate.split("-");
			if(split.length == 3){
				String y = split[0];
				String m = split[1];
				String d = split[2];
				if(m.length()==1){
					m = "0" + m;
				}
				if(d.length()==1){
					d = "0" + d;
				}
				startdate = y + "-" + m + "-" + d;
			}
		}
	  	if(lastdate.length()>10){
	  		lastdate = lastdate.substring(0,10);
		}else{
			String[] split = lastdate.split("-");
			if(split.length == 3){
				String y = split[0];
				String m = split[1];
				String d = split[2];
				if(m.length()==1){
					m = "0" + m;
				}
				if(d.length()==1){
					d = "0" + d;
				}
				lastdate = y + "-" + m + "-" + d;
			}
		}
          LocalDate _startdate = LocalDate.parse(startdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          LocalDate _lastdate = LocalDate.parse(lastdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          return  _startdate.toEpochDay() - _lastdate.toEpochDay() ;
      }

      /**
		 * 计算两个日期之间的天数 +1
		 * @param startdate 开始日期
		 * @param lastdate 结束日期
		 * @return 天数
		 */
	  public static long getBetweenDay2(String startdate, String lastdate){
		  startdate = startdate.substring(0,10);
		  lastdate = lastdate.substring(0,10);
          LocalDate _startdate = LocalDate.parse(startdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          LocalDate _lastdate = LocalDate.parse(lastdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          return  _startdate.toEpochDay() - _lastdate.toEpochDay() + 1;
      }


      public  static  String checkisDate(String datevalue){
		  try {
			  SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			  Date dd = fmt.parse(datevalue);
			  if (datevalue.equals(fmt.format(dd))) {
				  return datevalue;
			  } else {
				  return "";
			  }
		  } catch (Exception e) {
			  return "";
		  }

	  }
}
