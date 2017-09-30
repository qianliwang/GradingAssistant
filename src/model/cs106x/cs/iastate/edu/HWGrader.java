package model.cs106x.cs.iastate.edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import pojo.cs106x.cs.iastate.edu.ChapterRubric;
import pojo.cs106x.cs.iastate.edu.FileRubric;
import pojo.cs106x.cs.iastate.edu.GradingComment;
import pojo.cs106x.cs.iastate.edu.Rubric;
import pojo.cs106x.cs.iastate.edu.UnitRubric;
import pojo.cs106x.cs.iastate.edu.CPTagRubric;
import pojo.cs106x.cs.iastate.edu.CPRubric;

public class HWGrader {
	
	protected Rubric rubric;
	protected ChapterRubric chapterRubric;
	
	public HWGrader(){
		
	}
	
	public HWGrader(String week){

		this.chapterRubric = loadGradingScale(week);
	}

	public void grade(String stuHWPath,boolean isDebug){

		if(this.chapterRubric != null){
			gradeHWs(stuHWPath,this.chapterRubric,isDebug);
		}else{
			System.err.println("Can't find grading scale!!");
		}
		
		
	}
	
	private void gradeHWs(String folderPath,ChapterRubric chapterRubric,boolean isDebug){
		
		File folder = new File(folderPath);
		
		File[] listOfSubFolders = folder.listFiles();
		String files = "";
		List<String> taHW;
		List<String> stuHW;
		
		String tempFileName = "";
		String tempFolderName = "";
		String singleStdGrade = "";
		String result = "Student,";
		
		for(FileRubric fr: chapterRubric.getFileRubricList()){
			result = result+fr.getFileName()+",";
		}
		result = result + "\n";
		for (int i = 0; i < listOfSubFolders.length; i++) {

			tempFolderName = "";
			
			if(listOfSubFolders[i].isDirectory()){
				
				singleStdGrade = gradeSingleStdHW(listOfSubFolders[i].getAbsolutePath(),chapterRubric,isDebug);
				System.out.println(singleStdGrade);
				
				result = result + singleStdGrade + "\n";		
			}
		}
		if(!isDebug){
			printOutFile(folderPath+"gradeComments.txt",result,false);
			System.out.println("\n\nGrade has been written to "+folderPath+"gradeComments.txt!!!");
//			setFinalGrade(folderPath+"gradeComments.txt");
		}
		
	}
	
	private String gradeSingleStdHW(String folderPath,ChapterRubric chapterRubric,boolean isDebug){
		
		String fileToGrade;

		GradingComment gc;
		String summary = folderPath+",";

		for(FileRubric fr: chapterRubric.getFileRubricList()){
			fileToGrade = folderPath + File.separator+ fr.getFileName();
			gc = regexUnitProcess(fileToGrade,fr);
			
			if(!isDebug){
				
				if(gc.getDeductPoint()!=0){
					printOutFile(fileToGrade,gc.getComments(),true);
					printOutFile(folderPath+File.separator+"Please_see_comments_in_"+fr.getFileName()+"_.txt","",false);
				}
			}
			summary = summary + gc.getDeductPoint()+",";
		}

		return summary;
	}
	
	private GradingComment regexUnitProcess(String inputFile,FileRubric fr){
		
		String temp = "";
		String result = "\n";
		int deductPoint = 0;
		GradingComment gc = new GradingComment();
		String TAComment = "";
		
		temp = readFile(inputFile, StandardCharsets.UTF_8);
		
		if(temp == null){
			temp = "";
		}
		
		for(UnitRubric ur: fr.getUnitRubricList()){
			
			Pattern pattern = Pattern.compile(ur.getPattern());
			Matcher matcher = pattern.matcher(temp);
			
			if (!matcher.find()){
				
				if(inputFile.endsWith("css")){		
					TAComment = "/*\n-------------------- TA GRADING COMMMENTS:   "+ur.getComment() +"   *************(DEDUCT "+ur.getCredit()+" POINTS)------------\n*/\n";
				}else if(inputFile.endsWith("html")){
					TAComment = "\n<!---------------------- TA GRADING COMMMENTS:   "+ur.getComment() +"   *************(DEDUCT "+ur.getCredit()+" POINTS)-------------->\n";	
				}else{
					
				}
				
				result = result + TAComment;
				deductPoint = deductPoint + ur.getCredit();
			}
		
		}
		if(deductPoint != 0){
			if(inputFile.endsWith("css")){		
				result = result + "/*---------------------- TA GRADING COMMENTS: Total DEDUCT POINTS FOR the '"+fr.getFileName()+"' program is "+deductPoint+" points. Please see the comments above.-----------*/\n";	
			}else if(inputFile.endsWith("html")){
				result = result + "<!---------------------- TA GRADING COMMENTS: Total DEDUCT POINTS FOR the '"+fr.getFileName()+"' program is "+deductPoint+" points. Please see the comments above.----------->\n";	
			}else{
				
			}
		}
		gc.setDeductPoint(deductPoint);
		gc.setComments(result);
		
		return gc;
		
	}
	
	private ChapterRubric loadGradingScale(String week) {
		
		Rubric r = new Rubric();
		return r.getChapterRubric(week);
		
	}
	
	
	private String readFile(String path, Charset encoding){
		byte[] encoded = null;
		String result = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
			result = encoding.decode(ByteBuffer.wrap(encoded)).toString();
		} catch (IOException e) {
			System.err.println("Can't find "+ path);
		}
		return result;
	}
	
	private String getNetID(String fileName) {
		
		int period = fileName.indexOf(".");
		
		String temp = fileName.substring(0, period);

		String[] netID = temp.split("P-");

		return netID[1];
	}
	private List<String> fileToLines(String filename) {
        List<String> lines = new LinkedList<String>();
        String line = "";
        try {
                BufferedReader in = new BufferedReader(new FileReader(filename));
                while ((line = in.readLine()) != null) {
                        lines.add(line);
                }
        } catch (IOException e) {
//                e.printStackTrace();
        		System.err.println("Can't find "+ filename);
        }
        return lines;
	}
	
	private void printOutFile(String fileName,String content,boolean isAppend){
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
	
	public void gradeCP(String inputPath,String rubricFile){
		File folder = new File(inputPath);
		File[] listOfSubFolders = folder.listFiles();
		
		CPRubric cpr = new CPRubric(rubricFile);
		ArrayList<CPTagRubric> tagRubrics = cpr.getRubricList();
		
		HashMap<String,Integer> result;
		String tempComment = "";
		
		for(int i=0;i<listOfSubFolders.length;i++){
			if(listOfSubFolders[i].isDirectory()){
				tempComment = "<!------------------------- TA Grading Comments------------------------\nThe final Score of the Favorite Vacation Project is ";
				result = gradeSingleStdCP(listOfSubFolders[i].getAbsolutePath()+File.separator,tagRubrics);
				String finalScore = printSP1Score(result);
				tempComment = tempComment + finalScore + "\n-->";
				System.out.println(listOfSubFolders[i].getAbsolutePath() + "\n"+ tempComment);		
			}
		}
		
		
	}
	private HashMap<String,Integer> gradeSingleStdCP(String folderPath,ArrayList<CPTagRubric> tagRubrics){
		
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		HashMap<String,Integer> tempGS;
		HashMap<String,Integer> result = getSP1GradingScale();
		int tempValue;
		for(int i=0;i<listOfFiles.length;i++){
			if(listOfFiles[i].getName().endsWith(".html")){
				tempGS = parseHTML(listOfFiles[i].getAbsolutePath(),tagRubrics);
				for(String tagName: result.keySet()){
					tempValue = result.get(tagName) + tempGS.get(tagName);
					result.put(tagName,tempValue);
				}
			}
		}
		
		return result;
	}
	
	private String printCPScore(HashMap<String,Integer> result,ArrayList<CPTagRubric> tagRubrics){
		
		String finalScore;
		int baseScore = 76;
		String comment = "";
		String tempTagName;
		int amountOfTags;
		for(CPTagRubric tagRubric:tagRubrics){
			tempTagName = tagRubric.getTag();
			amountOfTags = result.get(tempTagName);
			if(amountOfTags < tagRubric.getAmountOfTag()){
				baseScore = baseScore + tagRubric.getCreditPerTag()*amountOfTags;
				comment = comment + "\n"+ amountOfTags +" <"+tempTagName+">\n";
			}else{
				baseScore = baseScore + tagRubric.getCreditPerTag()*tagRubric.getAmountOfTag();
			}
		}
		
		finalScore = String.valueOf(baseScore)+";\t"+ comment + "\n";
		return finalScore;
	}
	public void gradeSP1(String inputPath){
		File folder = new File(inputPath);
		File[] listOfFiles = folder.listFiles();
		
		HashMap<String,Integer> result;
		
		String tempComment = "";
		
		for(int i=0;i<listOfFiles.length;i++){
			if(listOfFiles[i].isDirectory()){
				tempComment = "<!------------------------- TA Grading Comments------------------------\nThe final Score of the Favorite Vacation Project is ";
				result = gradeSingleSP1(listOfFiles[i].getAbsolutePath()+File.separator);
				String finalScore = printSP1Score(result);
				tempComment = tempComment + finalScore + "\n-->";
				System.out.println(listOfFiles[i].getAbsolutePath() + "\n"+ tempComment);		
			}
		}
		
		
	}
	
	private HashMap<String,Integer> parseHTML(String filePath,ArrayList<CPTagRubric> itemRubrics){
		
		File input = new File(filePath);
		Document doc;
		Elements links;
		HashMap<String,Integer> gs = new HashMap<String,Integer>();
		try {
			doc = Jsoup.parse(input, "UTF-8", "");
			for(CPTagRubric ir:itemRubrics){
				links = doc.getElementsByTag(ir.getTag());
				gs.put(ir.getTag(),links.size());
			}
		} catch (IOException e) {
			System.err.println("Can't find "+ filePath);
		}
		
		return gs;
	}
	
	private HashMap<String,Integer> gradeSingleSP1(String folderPath){
		
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		HashMap<String,Integer> tempGS;
		HashMap<String,Integer> result = getSP1GradingScale();
		int tempValue;
		for(int i=0;i<listOfFiles.length;i++){
			if(listOfFiles[i].getName().endsWith(".html")){
				tempGS = parseSP1HTML(listOfFiles[i].getAbsolutePath());
				for(String tagName: result.keySet()){
					tempValue = result.get(tagName) + tempGS.get(tagName);
					result.put(tagName,tempValue);
				}
			}
		}
		
		return result;
	}
	
	private HashMap<String,Integer> parseSP1HTML(String filePath){
		
		HashMap<String,Integer> gs = getSP1GradingScale();
		File input = new File(filePath);
		Document doc;
		Elements links;
		try {
			doc = Jsoup.parse(input, "UTF-8", "");
			for(String tagName: gs.keySet()){
				links = doc.getElementsByTag(tagName);
				gs.put(tagName,links.size());
			}
		} catch (IOException e) {
			System.err.println("Can't find "+ filePath);
		}
		
		return gs;
	}
	
	private HashMap<String,Integer> parseSP2HTML(String filePath){
		
		HashMap<String,Integer> gs = getSP2GradingScale();
		File input = new File(filePath);
		Document doc;
		Elements links;
		try {
			doc = Jsoup.parse(input, "UTF-8", "");
			for(String tagName: gs.keySet()){
				links = doc.getElementsByTag(tagName);
				gs.put(tagName,links.size());
			}
		} catch (IOException e) {
			System.err.println("Can't find "+ filePath);
		}
		
		return gs;
	}
	
	private HashMap<String,Integer> getSP1GradingScale(){
		
		HashMap<String,Integer> gradingScale = new HashMap<String,Integer>();
		
		gradingScale.put("html", 0);
		gradingScale.put("a", 0);
		gradingScale.put("img", 0);
		gradingScale.put("li", 0);
		gradingScale.put("table", 0);
		gradingScale.put("p", 0);
		gradingScale.put("h1", 0);
		gradingScale.put("h2", 0);
		gradingScale.put("h3", 0);
		gradingScale.put("h4", 0);
		gradingScale.put("h5", 0);
		gradingScale.put("h6", 0);
		gradingScale.put("map", 0);
		
		return gradingScale;
	} 
	
	private String printSP1Score(HashMap<String,Integer> result){
		
		String finalScore;
		int tempScore = 76;
		String comment = "";
		for(String key:result.keySet()){
			switch(key){
			case "html":
				if(result.get(key) <= 3){
					tempScore = tempScore + 2*result.get(key);
					comment = comment + result.get(key)+" webpages\n";
				}else{
					tempScore = tempScore + 8;
				}
				break;
			case "table":
				if(result.get(key) >=1 ){
					tempScore = tempScore + 2;
				}else{
					comment = comment + "No table\n";
				}
				break;
			case "img":
				if(result.get(key) >=2 ){
					tempScore = tempScore + 2;
				}else{
					comment = comment + "Less than 2 images\n";
				}
				break;
			case "a":
				if(result.get(key) >=8 ){
					tempScore = tempScore + 4;
				}else{
					tempScore = tempScore + 2;
					comment = comment + "Less than 8 hyperlinks\n";
				}
				break;
			case "p":
				if(result.get(key) >=2 ){
					tempScore = tempScore + 2;
				}else{
					comment = comment + "Less than 2 paragraphs\n";
				}
				break;
			case "map":
				if(result.get(key) >=1 ){
					tempScore = tempScore + 2;
				}else{
					comment = comment + "No ImageMap\n";
				}
				break;
			case "li":
				if(result.get(key) >=1 ){
					tempScore = tempScore + 2;
				}else{
					comment = comment + "No list\n";
				}
				break;
				default:
					break;
			}
		}
		
		if(result.get("h1")>=1||result.get("h2")>=1||result.get("h3")>=1||result.get("h4")>=1||result.get("h5")>=1||result.get("h6")>=1){
			tempScore = tempScore + 2;
		}else{
			comment = comment + "No headings\n";
		}
		finalScore = String.valueOf(tempScore)+";\t"+ comment + "\n";
		return finalScore;
	}
	
	public void gradeSP2(String inputPath){
		File folder = new File(inputPath);
		File[] listOfFiles = folder.listFiles();
		
		HashMap<String,Integer> result;
		
		String tempComment = "";
		
		for(int i=0;i<listOfFiles.length;i++){
			if(listOfFiles[i].isDirectory()){
				tempComment = "<!------------------------- TA Grading Comments------------------------\nThe final Score of the Homepage Design Project is ";
				result = gradeSingleSP2(listOfFiles[i].getAbsolutePath()+File.separator);
				String finalScore = printSP2Score(result);
				tempComment = tempComment + finalScore + "\n-->";
				System.out.println(listOfFiles[i].getAbsolutePath() + "\n"+ tempComment);		
			}
		}
		
		
	}
	
	private HashMap<String,Integer> gradeSingleSP2(String folderPath){
		
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		HashMap<String,Integer> tempGS;
		HashMap<String,Integer> result = getSP2GradingScale();
		int tempValue;
		for(int i=0;i<listOfFiles.length;i++){
			if(listOfFiles[i].getName().endsWith(".html")){
				tempGS = parseSP2HTML(listOfFiles[i].getAbsolutePath());
				for(String tagName: result.keySet()){
					tempValue = result.get(tagName) + tempGS.get(tagName);
					result.put(tagName,tempValue);
				}
			}
		}
		
		return result;
	}
	

	
	private HashMap<String,Integer> getSP2GradingScale(){
		
		HashMap<String,Integer> gradingScale = new HashMap<String,Integer>();
		
		gradingScale.put("html", 0);
		gradingScale.put("a", 0);
		gradingScale.put("img", 0);
		gradingScale.put("table", 0);
		gradingScale.put("audio", 0);
		gradingScale.put("video", 0);
		gradingScale.put("script", 0);
		
		return gradingScale;
	} 
	
	private String printSP2Score(HashMap<String,Integer> result){
		
		String finalScore;
		int tempScore = 45;
		String comment = "";
		for(String key:result.keySet()){
			switch(key){
			case "html":
				if(result.get(key) <= 3){
					tempScore = tempScore + 5*result.get(key);
					comment = comment + result.get(key)+" webpages\n";
				}else{
					tempScore = tempScore + 15;
				}
				break;
			case "table":
				if(result.get(key) >=1 ){
					tempScore = tempScore + 5;
				}else{
					comment = comment + "No table\n";
				}
				break;
			case "img":
				if(result.get(key) >=5 ){
					tempScore = tempScore + 5;
				}else{
					comment = comment + "Less than 5 images\n";
				}
				break;
			case "a":
				if(result.get(key) >=2 ){
					tempScore = tempScore + 5;
				}else{
					comment = comment + "Less than 2 hyperlinks\n";
				}
				break;
			case "audio":
				if(result.get(key) >=1 ){
					tempScore = tempScore + 5;
				}else{
					comment = comment + "No audio\n";
				}
				break;
			case "video":
				if(result.get(key) >=1 ){
					tempScore = tempScore + 5;
				}else{
					comment = comment + "No video\n";
				}
				break;
			case "script":
				if(result.get(key) >=1 ){
					tempScore = tempScore + 15;
				}else{
					comment = comment + "No Javascript Function\n";
				}
				break;
				default:
					break;
			}
		}
		
		finalScore = String.valueOf(tempScore)+";\n"+ comment + "\n";
		return finalScore;
	}
}
