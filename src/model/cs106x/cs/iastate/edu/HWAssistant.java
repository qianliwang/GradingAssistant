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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pojo.cs106x.cs.iastate.edu.Student;
import conf.cs106x.cs.iastate.edu.GlobalVar;

public class HWAssistant {

//	protected TreeSet<Student> stuTreeSet;
	protected HashSet<Student> stuTreeSet;
	protected String week;
	
//	private static final int K_SHINGLE = 2;
	
	public HWAssistant(String classListPath,String week){
		this.week = week;
//		this.stuTreeSet = new TreeSet<Student>();
		this.stuTreeSet = new HashSet<Student>();
		if(classListPath == null || classListPath.equals("")){
			loadStudentFromClassList(GlobalVar.CLASS_LIST_PATH);
		}else{
			loadStudentFromClassList(classListPath);
		}
	}

	public void sendEmailsToStudent(int purpose, String tempHWPath,boolean isDebug) {

//		preprocessEmail(purpose,tempHWPath);
		
		String senderAccount = null;
		String senderPass = null;
//		ArrayList<String> recipients = null;
		ArrayList<String> ccList = null;
		ArrayList<String> bccList = null;
		
		String subject = null;
		String emailBody = null;
		
//		ArrayList<String> attachments = null;
		
		ArrayList<String> accountInfo = GlobalFunction.readFromFile(GlobalVar.EMAIL_ACCOUNT_PATH);
		senderAccount = accountInfo.get(0);
		senderPass = accountInfo.get(1);
		
//		recipients = GlobalFunction.readFromFile(GlobalVar.EMAIL_RECIPIENTLIST_PATH);
		ccList = GlobalFunction.readFromFile(GlobalVar.EMAIL_CCLIST_PATH);
		bccList = GlobalFunction.readFromFile(GlobalVar.EMAIL_BCCLIST_PATH);
		
		Set<Student> stdSet = getStudent(tempHWPath);
		Set<Student> emailSet = null;
		String tempSubjectPath = null;
		String tempEmailBodyPath = null;
		
		switch(purpose){
		
		case GlobalVar.EMAIL_REQUEST_LATE_HW:
			emailSet = new HashSet<Student>(this.stuTreeSet);
			emailSet.removeAll(stdSet);

			tempSubjectPath = GlobalVar.EMAIL_SUBJECT_LATEHW_PATH;
			tempEmailBodyPath = GlobalVar.EMAIL_BODY_LATEHW_PATH;
			break;
		case GlobalVar.EMAIL_RETURN_HW:
			emailSet = stdSet;

			tempSubjectPath = GlobalVar.EMAIL_SUBJECT_RETURNHW_PATH;
			tempEmailBodyPath = GlobalVar.EMAIL_BODY_RETURNHW_PATH;
			break;
		default:
			tempSubjectPath = GlobalVar.EMAIL_SUBJECT_DEFAULT_PATH;
			tempEmailBodyPath = GlobalVar.EMAIL_BODY_DEFAULT_PATH;
				break;
		}
		
		ArrayList<String> subjectList = GlobalFunction.readFromFile(tempSubjectPath);
		subject = subjectList.get(0);
		
		subject = subject.replace("$$", this.week);
		
		ArrayList<String> emailBodyList = GlobalFunction.readFromFile(tempEmailBodyPath);
		StringBuilder strBuilder = new StringBuilder();
		for(String str:emailBodyList){
			strBuilder.append(str);
			strBuilder.append("\n");
		}
		emailBody = strBuilder.toString();
		
		EmailSender emailSender = new EmailSender(senderAccount,senderPass);
		String emailBody_backup = emailBody;
		for(Student stu:emailSet){
			System.out.println("**************\t"+stu.getIsuEmail()+"\t**************");
			System.out.println("Subject:\t"+subject);
			emailBody = emailBody.replace("#####", stu.getFirstName());
			emailBody = emailBody.replace("$$", this.week);
			System.out.println(emailBody);
			if(!isDebug){
				emailSender.send(senderAccount, stu.getIsuEmail(),ccList, bccList, subject, emailBody, stu.getHWPath());
			}
			emailBody = emailBody_backup;
		}
		System.out.println(emailSet.size() + " emails have been sent!");
	}
	
	/*	
	private void preprocessEmail(int purpose, String tempHWPath){
		
		Set<Student> stdSet = getStudent(tempHWPath);
		Set<Student> emailSet = null;
		
		String tempSubject = null;
		String emailBody = null;
		String tempSubjectPath = null;
		String tempEmailBodyPath = null;
		
		ArrayList<String> subjectList;
		ArrayList<String> emailBodyList;
		ArrayList<String> recipientList = new ArrayList<String>();
		ArrayList<String> attachmentList = new ArrayList<String>();
		
		switch(purpose){
		
		case GlobalVar.EMAIL_REQUEST_LATE_HW:
			emailSet = new HashSet<Student>(this.stuTreeSet);
			emailSet.removeAll(stdSet);
			for(Student stu:emailSet){
				recipientList.add(stu.getIsuEmail());
			}
			tempSubjectPath = GlobalVar.EMAIL_SUBJECT_LATEHW_PATH;
			tempEmailBodyPath = GlobalVar.EMAIL_BODY_LATEHW_PATH;
			break;
		case GlobalVar.EMAIL_RETURN_HW:
			emailSet = stdSet;
			for(Student stu:emailSet){
				recipientList.add(stu.getIsuEmail());
				attachmentList.add(stu.getHWPath());
			}
			tempSubjectPath = GlobalVar.EMAIL_SUBJECT_RETURNHW_PATH;
			tempEmailBodyPath = GlobalVar.EMAIL_BODY_RETURNHW_PATH;
			break;
		default:
			tempSubjectPath = GlobalVar.EMAIL_SUBJECT_DEFAULT_PATH;
			tempEmailBodyPath = GlobalVar.EMAIL_BODY_DEFAULT_PATH;
				break;
		}
		
		GlobalFunction.printOutFile(GlobalVar.EMAIL_RECIPIENTLIST_PATH, recipientList, false);
		GlobalFunction.printOutFile(GlobalVar.EMAIL_ATTACHMENT_PATH, attachmentList, false);
		
		subjectList = GlobalFunction.readFromFile(tempSubjectPath);
		tempSubject = subjectList.get(0);
		tempSubject = tempSubject.replace("$$", this.week);
		GlobalFunction.printOutFile(GlobalVar.EMAIL_SUBJECT_PATH, tempSubject, false);
		
		emailBodyList = GlobalFunction.readFromFile(tempEmailBodyPath);
		emailBody = GlobalFunction.listToString(emailBodyList);
		GlobalFunction.printOutFile(GlobalVar.EMAIL_BODY_PATH, emailBody, false);

	}
	
	
	public void sendEmailsToStudent(int purpose, String tempHWPath,
			boolean isDebug) {

		Set<Student> stdSet = getStudent(tempHWPath);
		Set<Student> emailSet = null;
		
		ArrayList<String> accountInfo = GlobalFunction.readFromFile(GlobalVar.EMAIL_ACCOUNT_PATH);
		EmailSender emailSender = new EmailSender(accountInfo.get(0),accountInfo.get(1));
		
//		EmailSender emailSender = new EmailSender(GlobalVar.TAEmail,GlobalVar.TAEmailPass);
		String subject = null;
		String emailBody = null;
		String attachment = null;
		ArrayList<String> ccList = new ArrayList<String>();

		ccList.add("shchang@iastate.edu");

		switch(purpose){
		
		case GlobalVar.EMAIL_REQUEST_LATE_HW:
			emailSet = new HashSet<Student>(this.stuTreeSet);
			emailSet.removeAll(stdSet);
			break;
		case GlobalVar.EMAIL_RETURN_HW:
			emailSet = stdSet;
			break;
		}
		
		if (this.week != "") {
			// System.out.println(tempHWPath);
			
			for (Student std : emailSet) {
			
				switch (purpose) {
				case GlobalVar.EMAIL_REQUEST_LATE_HW:
					subject = "COM S 106x Week " + this.week + " Late Homework";
					emailBody = "Hi,"+std.getFirstName()+":\n\n"+GlobalVar.LateHWEmailBody;
					break;
				case GlobalVar.EMAIL_RETURN_HW:
					subject = "COM S 106X Week " + this.week + " Returned Homework";
					emailBody = "Dear "+ std.getFirstName()+ ":\n\nHere is your week "+ this.week+ " graded homework. Please see the detailed comments and total points in the attachment.\n\nPlease do not respond to this email. If you have any questions, please send an email via blackboard. Please understand that we need to keep all of records within blackboard virtual classroom.\n\nBest Regards\nSection "+ std.getSection() + " TA Sen\n";
					attachment = std.getHWPath();
					break;
				}
				if (!isDebug) {
					emailSender.send(GlobalVar.TAEmail, std.getIsuEmail(),
							ccList, null, subject, emailBody, attachment);
					try {
						System.err.println("Sleep for 20 seconds!!");
						Thread.sleep(20000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		} else {
			System.err.println("Please specify which week!");
		}

	}
	
	private void sendOutEmail(String senderFilePath,String recipientsFilePath,String ccFilePath,String bccFilePath,String subjectFilePath,String emailBodyFilePath,String attachmentFilePath){
		
		String senderAccount = null;
		String senderPass = null;
		ArrayList<String> recipients = null;
		ArrayList<String> ccList = null;
		ArrayList<String> bccList = null;
		
		String subject = null;
		String emailBody = null;
		
		ArrayList<String> attachments = null;
		
		if(senderFilePath!=null){
			ArrayList<String> accountInfo = GlobalFunction.readFromFile(senderFilePath);
			senderAccount = accountInfo.get(0);
			senderPass = accountInfo.get(1);
		}
		if(recipientsFilePath!=null){
			recipients = GlobalFunction.readFromFile(recipientsFilePath);
		}
		if(ccFilePath!=null){
			ccList = GlobalFunction.readFromFile(ccFilePath);
		}
		if(bccFilePath!=null){
			bccList = GlobalFunction.readFromFile(bccFilePath);
		}
		if(subjectFilePath!=null){
			ArrayList<String> subjectList = GlobalFunction.readFromFile(subjectFilePath);
			subject = subjectList.get(0);
		}
		if(emailBodyFilePath!=null){
			ArrayList<String> emailBodyList = GlobalFunction.readFromFile(emailBodyFilePath);
			StringBuilder strBuilder = new StringBuilder();
			for(String str:emailBodyList){
				strBuilder.append(str);
				strBuilder.append("\n");
			}
			emailBody = strBuilder.toString();
		}
		
		EmailSender emailSender = new EmailSender(senderAccount,senderPass);
		
		if(attachmentFilePath!=null){
			attachments = GlobalFunction.readFromFile(attachmentFilePath);
			if(attachments.size()!=recipients.size()){
				System.err.println("The recipients and attachments are not matched!!!\nDouble Check!!!\n");
			}else{
				int count = 0;
				for(String recipient:recipients){
					emailSender.send(senderAccount, recipient,ccList, bccList, subject, emailBody, attachments.get(count));
					count++;
				}
			}
		}else{
			for(String recipient:recipients){
				emailSender.send(senderAccount, recipient,ccList, bccList, subject, emailBody, null);
			}
		}
	}
	
*/	
	
	public void renameHWs(String inputFolderPath){
		
		File folder = new File(inputFolderPath);	
		File[] listOfFile = folder.listFiles();
		
		File tempNewFile;
		String tempNewFileName;
		String tempFileName[];
		Student stu;
		for(File file:listOfFile){
			
			tempFileName = file.getName().split("\\.(?=[^\\.]+$)");
			if(tempFileName != null && tempFileName[0] != ""){
//				stu = searchStudent(hashKey,tempFileName[0]);
				stu = searchStudent(tempFileName[0]);
				if(stu != null){
					tempNewFileName = stu.getHWName(this.week)+"."+tempFileName[1];
					tempNewFile = new File(inputFolderPath+File.separator+tempNewFileName);
					file.renameTo(tempNewFile);
					System.out.println(tempFileName[0]+"\t\t"+tempNewFileName);
				}else{
					System.err.println("Can't find corresponding Student with homework: "+ file.getName()+"\n");
				}
			}else{
				System.err.println("Invalid File Name: "+ file.getName()+"\n");
			}	
		}
	}
	/*
	public void renameHWs(String hashKey,String inputFolderPath){
		
		File folder = new File(inputFolderPath);	
		String[] listOfFileName = folder.list();
		
		File tempFile,tempNewFile;
		String tempNewFileName;
		String tempFileName[];
		Student stu;
		for(String fileName:listOfFileName){
			
			tempFileName = fileName.split("\\.(?=[^\\.]+$)");
			if(tempFileName != null && tempFileName[0] != ""){
				stu = searchStudent(hashKey,tempFileName[0]);
//				stu = searchStudent(tempFileName[0]);
				if(stu != null){
					tempNewFileName = stu.getHWName(this.week)+"."+tempFileName[1];
					tempFile = new File(inputFolderPath+File.separator+fileName);
					tempNewFile = new File(inputFolderPath+File.separator+tempNewFileName);
					tempFile.renameTo(tempNewFile);
					System.out.println(fileName+"\t\t"+tempNewFileName);
				}else{
					System.err.println("Can't find corresponding Student with homework: "+ fileName+"\n");
				}
			}else{
				System.err.println("Invalid File Name: "+ fileName+"\n");
			}	
		}
	}
	*/
	private void loadStudentFromClassList(String classListPath){
		InputStreamReader is;
		FileInputStream fis;
		BufferedReader bf;
		String temp = null;
		
		try {
			fis = new FileInputStream(classListPath);
			is = new InputStreamReader(fis);
			bf = new BufferedReader(is);

			temp = bf.readLine();
			String clsfnYear,firstName,lastName,section,netID,isuEmail;
			StringTokenizer strToken;
			String email[];
			Student stu;
			while(bf.ready()) {

				temp = bf.readLine();

				strToken = new StringTokenizer(temp, ",");
				
				section = strToken.nextToken();
				firstName = strToken.nextToken();
				lastName = strToken.nextToken();
				clsfnYear = strToken.nextToken();
				isuEmail = strToken.nextToken();
					
				email = isuEmail.split("@");
				if(email != null && email[0] != ""){
					netID = email[0];
					stu = new Student(firstName,lastName,netID,isuEmail,section);
					this.stuTreeSet.add(stu);
				}else{
					System.err.println("Can't find NetID in "+ isuEmail+"\n");
				}	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Student searchStudent(String key, String query){
		Iterator<Student> iterator = this.stuTreeSet.iterator();
		String tempValue = null;
		Student s;
        while(iterator.hasNext()) {
            s = iterator.next();
            switch(key){
			case GlobalVar.LASTNAME:
				tempValue = s.getLastName().toLowerCase();
				break;
			case GlobalVar.NETID:
				tempValue = s.getNetID().toLowerCase();
				
				break;
			case GlobalVar.FIRSTNAME:
				tempValue = s.getFirstName().toLowerCase();
				break;				
				default:
					break;
			}
            if(tempValue.equals(query.toLowerCase())){
				return s;
			}      
        }

        return null;                
    }
	
	private Student searchStudent(String fileName){
		TreeSet<String> fileKShingleSet = GlobalFunction.getKShingle(fileName, GlobalVar.K_SHINGLE);
		TreeSet<String> stuNameKShingleSet;
		Iterator<Student> iterator = this.stuTreeSet.iterator();
		Student tempStu;
		Student stu = null;
		double jacSim = 0;
		double maxJacSim = 0;
		while(iterator.hasNext()) {
			tempStu = iterator.next();
			stuNameKShingleSet = GlobalFunction.getKShingle(tempStu.getFullName(), GlobalVar.K_SHINGLE);
			jacSim = GlobalFunction.JaccardSimilarity((TreeSet<String>)fileKShingleSet.clone(), (TreeSet<String>)stuNameKShingleSet.clone());
			if(jacSim>maxJacSim){
				stu = tempStu;
				maxJacSim = jacSim;
			}
			
		}
		stuNameKShingleSet = null;
		return stu;
		
	}
	public void generateFollowUpLog(String inputFolderPath){
		
		int tempWeekNum = Integer.parseInt(GlobalFunction.getWeekNum(inputFolderPath));
		Pattern p = Pattern.compile("WK([0-9]+)HW");
		Matcher m = p.matcher(inputFolderPath);
		
		if(m.find()){
			
			String tempFolderPath = null;
			String tempWeek;
			File folder;	
			String[] listOfFileName;
			String tempNetID;
			
			for(int i=1;i<=tempWeekNum;i++){
				tempWeek = "WK"+String.valueOf(i)+"HW";
				tempFolderPath = m.replaceFirst(tempWeek);
				folder = new File(inputFolderPath);
				listOfFileName = folder.list();
				for(String fileName:listOfFileName){
					tempNetID = getNetID(fileName);
					for(Student std:this.stuTreeSet){
						if(tempNetID.equals(std.getNetID())){
							if(std.getIsHWTurnedin()[i] == 1){
								System.err.println("Week "+i+" homework has been set to 1"+fileName+"\n");
							}else{
								std.getIsHWTurnedin()[i] = 1;
							}
							break;
						}
					}
				}
			}

			String tempResult;
			String output = "";
			String tempRow = "";
			
			for(Student stu:this.stuTreeSet){
				
				tempRow = stu.getFirstName()+","+stu.getLastName()+","+stu.getSection()+","+stu.getIsuEmail()+",";				
				tempResult = "";
				for(int i=1;i<=tempWeekNum;i++){
					
					if(stu.getIsHWTurnedin()[i]==0){
						tempResult = tempResult + "WK"+i+"P;";
					}
					
				}
				if(tempResult != ""){
					tempRow = tempRow + tempResult +"\n";
					output = output + tempRow;
				}
				
			}
			
			System.out.println(output);
			GlobalFunction.printOutFile(inputFolderPath+File.separator+"followUpLog.csv",output,false);		
			
		}else{
			System.err.println("Can't find pattern WK([0-9]+)HW in the path: "+inputFolderPath+"\n");
		}
		
	}
	
	public void addFinalGrade(String commentsFile){
		
		BufferedReader br;
		String line;
		String columns[];
		String tempResult;
		String titleColumn[];
		String tempFileName;
		File tempFile;
		try {
			br = new BufferedReader(new FileReader(commentsFile));
			line = br.readLine();
			titleColumn = line.split(",");
			while (((line = br.readLine()) != null) && (line != "")) {
				columns = line.split(",");
				tempResult = formatFinalGrade(columns,titleColumn);
//				System.out.println(tempResult);
				
				for(int i=1;i<titleColumn.length;i++){
					tempFileName = columns[0]+File.separator+titleColumn[i];
//					System.out.println(tempFileName);
					tempFile = new File(tempFileName);
					if(tempFile.exists()){
						GlobalFunction.printOutFile(tempFileName,tempResult,true);	
					}	
				}
				
			}
			br.close();
			
			System.out.println("Final grade has been add to each student's homework!!\n\n");
			
			printFinalGrade();
			
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}
	
/*	
 * public void printFinalGrade(String commentsFile){
		
		BufferedReader br;
		String line;
		String columns[];

		String tempStuNetID;
		int totalPoint = 0;
		int singleDeductPoint;
		int totalDeductPoint;

		HashMap<String,Student> tempStu = buildHashMap(GlobalVar.NETID,this.studentList);
		Student tempStudent;		
		try {
			br = new BufferedReader(new FileReader(commentsFile));
			line = br.readLine();

			while (((line = br.readLine()) != null) && (!line.equals(""))) {
				columns = line.split(",");
				
				totalDeductPoint = 0;

				for(int i=1;i<columns.length;i++){
					singleDeductPoint = Integer.parseInt(columns[i]);
					totalDeductPoint = totalDeductPoint + singleDeductPoint;
				}
				tempStuNetID = getNetID(columns[0]);
				totalPoint = 50 - totalDeductPoint;
				tempStudent = (Student)tempStu.get(tempStuNetID);
				tempStudent.getIsHWTurnedin()[Integer.parseInt(this.week)] = totalPoint;
			}
			br.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		
		for(Student stu:this.studentList){
			System.out.println(stu.getFirstName()+"\t\t\t"+stu.getLastName()+"\t\tweek"+this.week+"\t"+stu.getIsHWTurnedin()[Integer.parseInt(this.week)]);
		}
		
	}
	*/
	
	public void printFinalGrade(){
		for(Student std:this.stuTreeSet){
			System.out.println(std.getFirstName()+","+std.getLastName()+",week"+this.week+","+std.getIsHWTurnedin()[Integer.parseInt(this.week)]);
		}
	}
	
	private String formatFinalGrade(String columns[],String titleColumns[]){
		
		int totalDeductPoint = 0;
		int singleDeductPoint;
		int totalPoint;
		int intColumns[] = new int[columns.length];
		for(int i=1;i<columns.length;i++){
			singleDeductPoint = Integer.parseInt(columns[i]);
			intColumns[i] = singleDeductPoint;
			totalDeductPoint = totalDeductPoint + singleDeductPoint;
		}
		totalPoint = 80-totalDeductPoint;
		
		String tempNetID = getNetID(columns[0]);
		int tempWeek = Integer.parseInt(this.week);
		for(Student std:this.stuTreeSet){
			if(std.getNetID().equals(tempNetID)){
				std.getIsHWTurnedin()[tempWeek] = totalPoint;
				break;
			}
		}
		
		String comment;
		comment = "\n<!---------------------- The final grade for the week " + this.week +" program: Total: "+totalPoint+"/80 POINTS.---------\n";
		int count = 65;
		if(totalDeductPoint != 0){	
			for(int i=1;i<intColumns.length;i++){
				if(intColumns[i] != 0){
					comment = comment + "\t(" + (char)(count) + ") DEDUCT " + intColumns[i] +" POINTS from the '"+ titleColumns[i] + "' file.\n";
					count++;
				}
			}
		} 
		comment = comment + "---------------------------->\n";
		return comment;
	}

	private Set<Student> getStudent(String inputFolderPath) {
		
		Set<Student> stuSet = new TreeSet<Student>();
		Student tempStu;
		
		File folder = new File(inputFolderPath);
		File[] listOfFiles = folder.listFiles();
		String fileName = "";
		String netID;
		int tempWeekNum;
		for (File f:listOfFiles) {
			if (f.isFile()&&!f.isHidden()) {
				fileName = f.getName();
				// System.out.println(files);
				
				netID = getNetID(fileName);
				tempStu = searchStudent(GlobalVar.NETID,netID);
				if(tempStu!=null){
					tempStu.setHWPath(f.getAbsolutePath());
// The reason that this.week is not used here is because later I may invoke this method to generate FollowUpLog.  
					
					tempWeekNum = Integer.parseInt(GlobalFunction.getWeekNum(inputFolderPath));
					tempStu.getIsHWTurnedin()[tempWeekNum] = 1;
					stuSet.add(tempStu);	
				}
			}
		}	
		
		return stuSet;

	}
	
	private String getNetID(String fileName) {
		
		String temp;
		String result = null;
		int period = fileName.indexOf(".");
		
		if(period == -1){
			temp = fileName;
		}else{
			temp = fileName.substring(0, period);	
		}

		String[] netID = temp.split("WK[0-9]+P-");

		if(netID.length>1){
			result = netID[1];
		}
		return result;
	}

}
