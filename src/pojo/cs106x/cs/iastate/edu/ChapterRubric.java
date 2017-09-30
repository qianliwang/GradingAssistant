package pojo.cs106x.cs.iastate.edu;

import java.util.ArrayList;

public class ChapterRubric {
	private String chapterName;
	private ArrayList<FileRubric> fileRubricList;

	public ChapterRubric(){
		this.fileRubricList = new ArrayList<FileRubric>();
	}
	
	public ChapterRubric(String chapterName){
		this.chapterName = chapterName;
		this.fileRubricList = new ArrayList<FileRubric>();
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public ArrayList<FileRubric> getFileRubricList() {
		return fileRubricList;
	}

	public void setFileRubricList(ArrayList<FileRubric> fileRubricList) {
		this.fileRubricList = fileRubricList;
	}
	
	public void addFileRubric(FileRubric fr){
		this.fileRubricList.add(fr);
	}
	
	@Override
	public String toString(){
		String result = "ChapterName:"+this.chapterName+"\nNumber of FileRubirc:"+this.fileRubricList.size()+"\n";
		return result;
	}
}
