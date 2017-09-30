package test.cs106x.cs.iastate.edu;

import conf.cs106x.cs.iastate.edu.GlobalVar;
import model.cs106x.cs.iastate.edu.GlobalFunction;
import model.cs106x.cs.iastate.edu.HWAssistant;
import model.cs106x.cs.iastate.edu.HWGrader;
import model.cs106x.cs.iastate.edu.Zipper;

public class Test {

	public static void main(String args[]){
		String inputFolderPath = "D:\\programmer\\workspace\\coms106\\test\\WK1HW\\pending\\";
		String classListPath = "D:\\programmer\\workspace\\coms106\\conf\\classList\\classList.csv";
		String week = GlobalFunction.getWeekNum(inputFolderPath);
		HWAssistant hwAt = new HWAssistant(classListPath,week);
		hwAt.renameHWs(inputFolderPath);
//		String unZipFolder = "D:\\programmer\\workspace\\coms106\\test\\WK2HW\\graded\\";
//		Zipper.unzipHWs(inputFolderPath, unZipFolder);
//		hwAt.sendEmailsToStudent(GlobalVar.EMAIL_REQUEST_LATE_HW, inputFolderPath, true);
//		hwAt.sendEmailsToStudent(GlobalVar.EMAIL_RETURN_HW, inputFolderPath, true);
		
		String toGradePath = "D:\\programmer\\workspace\\coms106\\test\\WK2HW\\graded\\S1\\";
		week = GlobalFunction.getWeekNum(toGradePath);
		HWGrader hwGrader = new HWGrader(week);
//		hwGrader.grade(toGradePath,false);
    	
//    	hwAt.addFinalGrade("D:\\programmer\\workspace\\coms106\\test\\WK2HW\\graded\\S1\\gradeComments.txt");
//    	Zipper.zipHWByFile("D:\\programmer\\workspace\\coms106\\test\\WK2HW\\graded\\S1\\gradeComments.txt","D:\\programmer\\workspace\\coms106\\test\\WK2HW\\graded\\toReturn\\" );
	}
}
