package conf.cs106x.cs.iastate.edu;

import java.io.File;

public class GlobalVar {
	
	public static final String CLASS_LIST_PATH = "."+File.separator+"conf"+File.separator+"classList"+File.separator+"classList.csv";
	public static final String GRADING_SCALE_PATH = "C:\\Users\\watson\\workspace\\COMS106_v2\\criteria.txt";
	
//	public static final String EMAIL_BASE_PATH = "D:\\programmer\\workspace\\coms106"+File.separator+"conf"+File.separator+"email"+File.separator;
	public static final String EMAIL_BASE_PATH = "."+File.separator+"conf"+File.separator+"email"+File.separator;
	public static final String EMAIL_TAMPLATE_PATH  =  EMAIL_BASE_PATH + "template"+ File.separator;
	public static final String EMAIL_ACCOUNT_PATH = EMAIL_BASE_PATH+"account.txt";
	public static final String EMAIL_RECIPIENTLIST_PATH = EMAIL_BASE_PATH+"recipient.txt";
	public static final String EMAIL_CCLIST_PATH = EMAIL_BASE_PATH+"cc.txt";
	public static final String EMAIL_BCCLIST_PATH = EMAIL_BASE_PATH+"bcc.txt";
	public static final String EMAIL_BODY_PATH = EMAIL_BASE_PATH+"body.txt";
	public static final String EMAIL_SUBJECT_PATH = EMAIL_BASE_PATH+"subject.txt";
	public static final String EMAIL_ATTACHMENT_PATH = EMAIL_BASE_PATH+"attachment.txt";
	
	
	public static final String EMAIL_SUBJECT_LATEHW_PATH = EMAIL_TAMPLATE_PATH+"subject_lateHW.txt";
	public static final String EMAIL_SUBJECT_RETURNHW_PATH = EMAIL_TAMPLATE_PATH+"subject_returnHW.txt";
	public static final String EMAIL_SUBJECT_DEFAULT_PATH = EMAIL_TAMPLATE_PATH+"subject_default.txt";
	public static final String EMAIL_BODY_DEFAULT_PATH = EMAIL_TAMPLATE_PATH+"body_default.txt";
	public static final String EMAIL_BODY_LATEHW_PATH = EMAIL_TAMPLATE_PATH+"body_lateHW.txt";
	public static final String EMAIL_BODY_RETURNHW_PATH = EMAIL_TAMPLATE_PATH+"body_returnHW.txt";
	
	public static final String LASTNAME = "lastName";
	public static final String NETID = "netID";
	public static final String FIRSTNAME = "firstName";
	
	public static final int EMAIL_REQUEST_LATE_HW = 1000;
	public static final int EMAIL_RETURN_HW = 1001;
	public static final int EMAIL_EXTRA = 1002;
	
	public static final int TASK_ASKLATEHW = 2000;
	public static final int TASK_RENAMEHW = 2001;
	public static final int TASK_UNZIPHW = 2002;
	public static final int TASK_ZIPHW = 2003;
	public static final int TASK_RETURNHW= 2004;
	public static final int TASK_GRADEHW = 2005;
	public static final int TASK_GENFOLLOWUPLOG = 2006;
	public static final int TASK_ADDFINALGRADE = 2007;
	public static final int TASK_GRADECP1 = 2008;
	public static final int TASK_GRADECP2 = 2009;
	
	public static final int K_SHINGLE = 2;
}
