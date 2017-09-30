package pojo.cs106x.cs.iastate.edu;

public class UnitRubric {
	private String pattern;
	private int credit;
	private String comment;
	
	public UnitRubric(){
		
	}
	public UnitRubric(String pattern, int credit){
		this.pattern = pattern;
		this.credit = credit;
	}

	public UnitRubric(String pattern, int credit, String comment){
		this.pattern = pattern;
		this.credit = credit;
		this.comment = comment;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString(){
		String result = "Pattern:"+this.pattern+"\n";
		return result;
	}
}
