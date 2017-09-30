package pojo.cs106x.cs.iastate.edu;

public class CPTagRubric {

	private String tag;
	private int creditPerTag;
	private int amountOfTag;
	
	public CPTagRubric(String item,int credit,int amount){
		this.tag = item;
		this.creditPerTag = credit;
		this.amountOfTag= amount;
	}

	public String getTag() {
		return tag;
	}

	public int getCreditPerTag() {
		return creditPerTag;
	}

	public int getAmountOfTag() {
		return amountOfTag;
	}

}
