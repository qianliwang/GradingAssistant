package main.cs106x.cs.iastate.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conf.cs106x.cs.iastate.edu.GlobalVar;
import pojo.cs106x.cs.iastate.edu.Student;
import model.cs106x.cs.iastate.edu.HWGrader;
import model.cs106x.cs.iastate.edu.HWAssistant;
import model.cs106x.cs.iastate.edu.Zipper;

public class Main {

	public static void main(String args[]){
		
		
		System.out.println("Welcome to 106 assistant!!\nCommand Line Help:\n--week: required\n--renameHW (inputFolderPath)\n--unzipHW (inputFolderPath,outputFolderPath)\n--zipHW (inputFile)\n--askLateHW (inputFolderPath)\n--returnHW (inputFolderPath)\n--gradeHW (inputFolderPath,taHWFolderPath)\n--gradeCP1 (inputFolderPath)\n--genFollowUpLog (inputFolderPath)\n--addFinalGrade (inputFile)\n--setClassList(classListFile)\n");
		int i = 0;
		String arg = "";
		String week = "";
		String inputFolderPath = "";
		String inputFile = "";
		String outputFolderPath = "";
		String classListPath = "";
		String taHWFolderPath = "";
		HWAssistant hwAt;
		HWGrader hwGrader;
		int taskID = 0;
		boolean isDebug = true;
        while (i < args.length && args[i].startsWith("--")) {
            arg = args[i++];

            // use this type of check for "wordy" arguments
            
            switch(arg){
            case "--week":
            	if (i < args.length){
            		week = args[i++];
            		System.out.println("The week is "+ week);
            	}
                else{
                	System.err.println("--week requires a specific week!!");
                }
            	break;
            case "--renameHW":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_RENAMEHW;
            		inputFolderPath = args[i++];
            		week = getWeekNum(inputFolderPath);
            		System.out.println("The week is "+ week);
            		System.out.println("The input folder path is "+ inputFolderPath);
            		
            	}
                else{
                	System.err.println("--rename requires a specific folder path!!");
                }
            	break;
            case "--askLateHW":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_ASKLATEHW;
            		inputFolderPath = args[i++];
            		week = getWeekNum(inputFolderPath);
            		System.out.println("The week is "+ week);
            		System.out.println("The input folder path is "+ inputFolderPath);
            	}
                else{
                	System.err.println("--askLateHW requires a specific folder path!!");
                }
            	break;
            case "--returnHW":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_RETURNHW;
            		inputFolderPath = args[i++];
            		week = getWeekNum(inputFolderPath);
            		System.out.println("The week is "+ week);
            		System.out.println("The input folder path is "+ inputFolderPath);
            	}
                else{
                	System.err.println("--returnHW requires a specific folder path!!");
                }
            	break;
            case "--unzipHW":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_UNZIPHW;
            		inputFolderPath = args[i++];
            		outputFolderPath = args[i++];
            		week = getWeekNum(inputFolderPath);
            		System.out.println("The week is "+ week);
            		System.out.println("The folder path is "+ inputFolderPath);
            		
            	}
                else{
                	System.err.println("--unzipHW requires a specific folder path!!");
                }
            	break;
            case "--zipHW":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_ZIPHW;
            		inputFile = args[i++];
            		outputFolderPath = args[i++];
            		week = getWeekNum(inputFile);
            		System.out.println("The week is "+ week);
//            		System.out.println("The input folder path is "+ inputFolderPath);
            		System.out.println("The file path is "+ inputFile);
            		System.out.println("The output folder path is "+ outputFolderPath);
            	}
                else{
                	System.err.println("--zipHW requires a specific folder path!!");
                }
            	break;
            case "--gradeHW":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_GRADEHW;
            		inputFolderPath = args[i++];
//            		taHWFolderPath = args[i++];
            		week = getWeekNum(inputFolderPath);
            		System.out.println("The week is "+ week);
            		System.out.println("The input folder path is "+ inputFolderPath);
//            		System.out.println("The TA homework folder path is "+ taHWFolderPath);
            	}
                else{
                	System.err.println("--gradeHW requires a specific folder path!!");
                }
            	break;
            case "--genFollowUpLog":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_GENFOLLOWUPLOG;
            		inputFolderPath = args[i++];
//            		outputFolderPath = args[i++];
            		week = getWeekNum(inputFolderPath);
            		System.out.println("The week is "+ week);
            		System.out.println("The input file path is "+ inputFolderPath);
            		System.out.println();
            	}
                else{
                	System.err.println("--genFollowUpLog requires inputFolder!!");
                }
            	break;
            case "--gradeCP1":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_GRADECP1;
            		inputFolderPath = args[i++];
            		System.out.println("The input file is "+ inputFolderPath);
            		System.out.println();
            	}
                else{
                	System.err.println("--gradeSP1 requires inputFolder!!");
                }
            	break;
            case "--gradeCP2":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_GRADECP2;
            		inputFolderPath = args[i++];
            		System.out.println("The input file is "+ inputFolderPath);
            		System.out.println();
            	}
                else{
                	System.err.println("--gradeSP2 requires inputFolder!!");
                }
            	break;
            case "--addFinalGrade":
            	if (i < args.length){
            		taskID = GlobalVar.TASK_ADDFINALGRADE;
            		inputFile = args[i++];
            		week = getWeekNum(inputFile);
            		System.out.println("The week is "+ week);
            		System.out.println("The input file is "+ inputFile);
            		System.out.println();
            	}
                else{
                	System.err.println("--genFollowUpLog requires inputFolder and outputFolder!!");
                }
            	break;
            case "--setClassList":
            	if (i < args.length){
            		classListPath = args[i++];
            		System.out.println("The class list path is "+ classListPath);
            	}
                else{
                	System.err.println("--setClassList requires the csv file!!");
                }
            	break;
            case "--run":
            	if (i <= args.length){
            		isDebug = false;
            	}
                else{
                	System.err.println("--setClassList requires the csv file!!");
                }
            	break;
            	
            	default:
            		System.err.println("Not recognized command!!");
            	break;
            
            }
        }
        

        hwAt = new HWAssistant(classListPath,week);
        
        switch(taskID){
        case GlobalVar.TASK_RENAMEHW:
        	hwAt.renameHWs(inputFolderPath);
        	break;
        case GlobalVar.TASK_ASKLATEHW:
//        	hwAt.requestHW(inputFolderPath,isDebug);
        	hwAt.sendEmailsToStudent(GlobalVar.EMAIL_REQUEST_LATE_HW, inputFolderPath, isDebug);
        	break;
        case GlobalVar.TASK_RETURNHW:
//        	hwAt.returnHW(inputFolderPath,isDebug);
        	hwAt.sendEmailsToStudent(GlobalVar.EMAIL_RETURN_HW, inputFolderPath, isDebug);
        	break;
        case GlobalVar.TASK_UNZIPHW:
        	Zipper.unzipHWs(inputFolderPath, outputFolderPath);
        	break;
        case GlobalVar.TASK_ZIPHW:
//        	hwAt.zipHWs(inputFolderPath, outputFolderPath);
        	Zipper.zipHWByFile(inputFile,outputFolderPath);
        	break;
        case GlobalVar.TASK_GRADEHW:
        	hwGrader = new HWGrader(week);
        	hwGrader.grade(inputFolderPath,isDebug);
        	break;
        case GlobalVar.TASK_GENFOLLOWUPLOG:
        	hwAt.generateFollowUpLog(inputFolderPath);
        	break;
        case GlobalVar.TASK_ADDFINALGRADE:
        	hwAt.addFinalGrade(inputFile);
        	break;
        case GlobalVar.TASK_GRADECP1:
        	hwGrader = new HWGrader();
        	hwGrader.gradeSP1(inputFolderPath);
        	break;
        case GlobalVar.TASK_GRADECP2:
        	hwGrader = new HWGrader();
        	hwGrader.gradeSP2(inputFolderPath);
        	break;
        	default:
        	break;
        }
		
	}
	
	private static String getWeekNum(String argument){
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
