package com.sc.aqjl.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Times {
	/*
	 * 取得当前时间
	 */
		public static String gettime(String fomateStr) { 
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat(fomateStr);
			String formateDate = sf.format(date);
			return formateDate;
			}
		public static String getyestodydate(String fomateStr) { 
			String yestody="";
			Calendar   cale   =   Calendar.getInstance();
			 cale.add(Calendar.DATE,-1);
             yestody=new   SimpleDateFormat(fomateStr).format(cale.getTime());   
		    return yestody;
		    
		}
		public static String gettodaydate(String fomateStr) { 
			String today="";
			Calendar   cale   =   Calendar.getInstance();
			 cale.add(Calendar.DATE,-0);
			 today=new   SimpleDateFormat(fomateStr).format(cale.getTime());   
		    return today;
		    
		}
		public static String getlastmonth(String fomateStr) { 
			String lastmonth="";
			  
			 Calendar   cale   =  Calendar.getInstance();
             cale.add(Calendar.MONTH,-1);
             lastmonth=new   SimpleDateFormat("yyyy-MM").format(cale.getTime());   
		    return lastmonth;
		    }
		public static String getwek(String fomateStr) { 
			SimpleDateFormat   dateFormat = new   SimpleDateFormat("yyyyMMdd");   
            Date date = null;   
            String tempdate = fomateStr; 
            try {
				date = dateFormat.parse(tempdate);
				System.out.println(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);   
            int mydate = cd.get(Calendar.DAY_OF_WEEK);   //获取指定日期转换成星期几
            String showDate = "";   
            switch(mydate){   
              case   1:   
                showDate = "星期日";   
                break;   
              case   2:   
                showDate = "星期一";   
                break;   
              case   3:   
                showDate = "星期二";   
                break;   
              case   4:   
                showDate = "星期三";   
                break;   
              case   5:   
                showDate = "星期四";   
                break;   
              case   6:   
                showDate = "星期五";   
                break;   
              default:   
                showDate = "星期六";   
                break;   
            }   
		    return showDate;
		    }
public static void main(String[] args) {
	System.out.println(Times.getyestodydate("yyyy-MM-dd"));
	System.out.println(Times.getlastmonth("yyyy-MM"));
	System.out.println(Times.gettodaydate("MM-dd"));
}

public static String addDay(String s,int n){
	try {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cd = Calendar.getInstance();
		cd.setTime(sdf.parse(s));
		cd.add(Calendar.DATE, n);
		return sdf.format(cd.getTime());
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	
}
}
