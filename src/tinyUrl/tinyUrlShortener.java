package tinyUrl;

import java.util.HashMap;
import java.util.Random;

public class tinyUrlShortener {
	
	private HashMap<String,String> tiny2Long;
	private HashMap<String,String> long2Tiny;
	
	private int urlLength;
	private char charList[];
	private Random rand;
	private String fixedDomain;
	
	public tinyUrlShortener() {
		// TODO Auto-generated constructor stub
		
		tiny2Long = new HashMap<String, String>();
		long2Tiny = new HashMap<String, String>();
		rand = new Random();
		urlLength = 8;
		fixedDomain = "http://palashZ.in";
		
		charList="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		
	}
	
	String refineUrl(String u){
		
		if (u.substring(0, 8).equals("https://"))
			u = u.substring(7);

		if (u.substring(0, 7).equals("http://"))
			u = u.substring(7);

		if (u.charAt(u.length() - 1) == '/')
			u = u.substring(0, u.length() - 1);
	
		return u;
	}
	
	private String generateShort(String u){
		
		String shortUrl="";
		
		boolean exists=false;
		
		while(!exists){
			shortUrl="";
			
			for(int i=0;i<urlLength;i++){
				shortUrl+=charList[rand.nextInt(62)];
			}
			
			if(!tiny2Long.containsKey(shortUrl)){
				exists=true;
				break;
			}
				
		}
		
		tiny2Long.put(shortUrl, u);
		long2Tiny.put(u,shortUrl);
		
		return shortUrl;
			
	}
	
	public String makeUrlShort(String longu){
		
		String shortu="";
		
		longu=refineUrl(longu);
		
		if(long2Tiny.containsKey(longu)){
			shortu=fixedDomain + "/" + long2Tiny.get(longu);
		}
		else{
			shortu=fixedDomain +"/" + generateShort(longu);
		}
		
		return shortu;
		
	}
	
	
	
	
	
	public static void main(String args[]){
		
		tinyUrlShortener url=new tinyUrlShortener(); 
		
		String testUrls[] = { "www.google.com", "www.google.com/",
				"http://www.facebook.com", "www.facebook.com/", "www.flipkart.com",
				"www.amazon.in/test.php", "www.amazon.in/test2.php",
				"www.nsit.ac.in", "www.jacdelhi.com",
				"www.zomato.com", "www.grofers.com", "www.iplt20.com" };
		
		for(int i=0;i<testUrls.length;i++){
			System.out.println("TEST URL: " + testUrls[i] + "\t\t\tTinyURL: " + 
		                        url.makeUrlShort(testUrls[i]));
		}
		
		
		
		
	}
	
}
