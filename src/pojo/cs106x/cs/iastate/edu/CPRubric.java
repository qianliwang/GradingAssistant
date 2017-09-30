package pojo.cs106x.cs.iastate.edu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CPRubric {
	private String fileName;
	ArrayList<CPTagRubric> rubricList;
	
	public CPRubric(){
		
	}
	public CPRubric(String fileName){
		this.fileName = fileName;
		this.rubricList = new ArrayList<CPTagRubric>();
		ProcessRubricFile(fileName);
	}
	
	public String getFileName() {
		return fileName;
	}

	public ArrayList<CPTagRubric> getRubricList() {
		return rubricList;
	}

	private void ProcessRubricFile(String fileName){
		String line;
		String lineArray[];
		CPTagRubric tagRubric;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			while ((line = in.readLine()) != null) {
				lineArray = line.split(",");
				tagRubric = new CPTagRubric(lineArray[0],Integer.parseInt(lineArray[1]),Integer.parseInt(lineArray[2]));
				rubricList.add(tagRubric);
			}
			in.close();
		} catch (IOException e) {
			System.err.println("Can't find " + fileName);
		}
	}
	
	@Override
	public String toString(){
		System.out.println(this.fileName);
		return this.fileName;
	}
}
