package pojo.cs106x.cs.iastate.edu;

import java.util.ArrayList;

public class FileRubric {
	
	private String fileName;
	private ArrayList<UnitRubric> unitRubricList;
	
	
	public FileRubric(){
		unitRubricList = new ArrayList<UnitRubric>();
	}
	
	public FileRubric(String fileName){
		this.fileName = fileName;
		unitRubricList = new ArrayList<UnitRubric>();
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public ArrayList<UnitRubric> getUnitRubricList() {
		return unitRubricList;
	}
	public void setUnitRubricList(ArrayList<UnitRubric> unitRubricList) {
		this.unitRubricList = unitRubricList;
	}
	
	public void addUnitRubric(UnitRubric ur){
		this.unitRubricList.add(ur);
	}
	
	public void addUnitRubric(String patternStr, int credit, String comment){
		UnitRubric ur= new UnitRubric(patternStr, credit, comment);
		this.unitRubricList.add(ur);
	}
	
	@Override
	public String toString(){
		String result = "FileName:"+this.fileName+"\nNumber of UnitRubric:"+this.unitRubricList.size()+"\n";
		return result;
	}
}
