package model.cs106x.cs.iastate.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pojo.cs106x.cs.iastate.edu.Student;


public class GlobalFunction {
	
	public static ArrayList<String> readFromFile(String filePath){
		
		ArrayList<String> result = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null) {
				result.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String listToString(ArrayList<String> list){
		StringBuilder strBuilder = new StringBuilder();
		for(String str:list){
			strBuilder.append(str);
			strBuilder.append("\n");
		}
		return strBuilder.toString();
	}
	public static void printOutFile(String fileName,String content,boolean isAppend){
		PrintWriter writer;
		try {
			
			if(isAppend){
				writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			}else{
				writer = new PrintWriter(fileName, "UTF-8");
			}	
			writer.println(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void printOutFile(String fileName,ArrayList<String> contentList,boolean isAppend){
		PrintWriter writer;
		try {
			
			if(isAppend){
				writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			}else{
				writer = new PrintWriter(fileName, "UTF-8");
			}
			
			StringBuilder strBuilder = new StringBuilder();
			
			for(String str:contentList){
				strBuilder.append(str);
				strBuilder.append("\n");
			}
			writer.println(strBuilder.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void printOutFile(String fileName,Set contentSet,boolean isAppend){
		PrintWriter writer;
		try {
			
			if(isAppend){
				writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			}else{
				writer = new PrintWriter(fileName, "UTF-8");
			}
			
			Iterator iterator = contentSet.iterator();
			StringBuilder strBuilder = new StringBuilder();
			while(iterator.hasNext()){
				strBuilder.append(iterator.next());
			}
			
			writer.println(strBuilder.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getSection(String fileName){
		
		Pattern pattern = Pattern.compile("-(S[a-zA-Z0-9]+)-");
		Matcher matcher = pattern.matcher(fileName);
		String output = null;
		
		if(matcher.find()){
			output = matcher.group(1);	
		}
		
		return output;
	}
	
	public static String getEmail(String netID) {

		String email = netID + "@iastate.edu";
		return email;

	}
	
	public static TreeSet<String> getKShingle(String str,int k){
		
		TreeSet<String> result = new TreeSet<String>();
		for (int i = 0; i < str.length() - k; i++)
		{
		    result.add(str.substring(i, i + k));
		}
		return result;
	}
	
	public static double JaccardSimilarity(TreeSet<String> t1,TreeSet<String>t2){
		int t1Size = t1.size();
		int t2Size = t2.size();
		t1.retainAll(t2);
		double result = (double) 1.0;
		result = result*t1.size()/(t1Size+t2Size-t1.size());
		return result;
	}
	
	public static String getWeekNum(String argument){
		Pattern pattern = Pattern.compile("WK([0-9]+)HW");
		String output = "";
		
		if(argument != null && argument != ""){
			Matcher matcher = pattern.matcher(argument);
			if(matcher.find()){
				output = matcher.group(1);
			}
		}
		
		return output;
	}
}
