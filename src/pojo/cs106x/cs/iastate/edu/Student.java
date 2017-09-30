package pojo.cs106x.cs.iastate.edu;

public class Student implements Comparable<Student>{

	private String firstName;
	private String lastName;
	private String isuEmail;
	private String netID;
	private String section;
	private String hwPath;
	
	private final int COUNT = 15;
	private int isHWTurnedin[] = new int[COUNT];
	
	public Student(String firstName,String lastName,String netID,String isuEmail,String section,String hwPath){
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.netID = netID;
		this.section = section;
		this.isuEmail = isuEmail;
		this.hwPath = hwPath;
	}
	
	public Student(String firstName,String lastName,String netID,String isuEmail,String section){
		this.firstName = firstName;
		this.lastName = lastName;
		this.netID = netID;
		this.section = section;
		this.isuEmail = isuEmail;
	}
	
	public Student(){
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName(){
		return firstName+lastName;
	}
	
	public String getIsuEmail() {
		return isuEmail;
	}

	public void setIsuEmail(String isuEmail) {
		this.isuEmail = isuEmail;
	}

	public String getHWPath() {
		return hwPath;
	}

	public void setHWPath(String hwPath) {
		this.hwPath = hwPath;
	}

	public String getNetID() {
		return netID;
	}

	public void setNetID(String netID) {
		this.netID = netID;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int[] getIsHWTurnedin() {
		return isHWTurnedin;
	}

	public void setIsHWTurnedin(int[] isHWTurnedin) {
		this.isHWTurnedin = isHWTurnedin;
	}

	public String getHWName(String week){
		String result = this.firstName+this.lastName+"-G"+this.section+"-WK"+week+"P-"+this.netID;
		return result;
	}
	
	@Override
	public int compareTo(Student s) {
		
		if(this.netID.toLowerCase().equals(s.getNetID().toLowerCase())){
			return 0;
		}else{
			return this.lastName.toLowerCase().compareTo(s.getLastName().toLowerCase());
		}
	}
	
	@Override
	public String toString(){
		String result = "FirstName:"+this.firstName+"\nLastName:"+this.lastName+"\nNetID:"+this.netID+"\nSection:"+this.section+"\n";
		return result;
	}
	
}
