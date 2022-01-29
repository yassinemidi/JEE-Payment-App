package myapp.internationalisation;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;



public class International {

	public static ResourceBundle r=ResourceBundle.getBundle("myapp.messages/MessagesBundle",new Locale("fr",""));	
		

	
	public static void setLangue(String s) {
		r=ResourceBundle.getBundle("myapp.messages/MessagesBundle",new Locale(s,""));
	}



	
	
	public static Hashtable<String, String> toHashtable(){
		
		Hashtable<String, String> map=new Hashtable<String, String>();
		Enumeration<String> keys=r.getKeys();
		while (keys.hasMoreElements()) {
			String string = (String) keys.nextElement();
			map.put(string, r.getString(string));
			
		}
		return map;
	}
}
