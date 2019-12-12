import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class BayesBallQuery{
	public Map<String, String> Given_the1 = new HashMap<>();
	String start;
	String end;

}
class VarElimQuery{
	public ArrayList<String> WhatToKill = new ArrayList<String>();
	public Map<String, String> Given_the = new HashMap<>();
	String Name;
	String Value;
}
public class Query {
	public ArrayList<BayesBallQuery> BayesBall = new ArrayList<BayesBallQuery>();
	public ArrayList<VarElimQuery> VarElim = new ArrayList<VarElimQuery>();


	public void  print() {
		for(int i=0;i<this.BayesBall.size();i++) {
			System.out.println("fkjsdlkfnsdlkvnsd"+ this.BayesBall.size());
			System.out.print(this.BayesBall.get(i).start + "-"+this.BayesBall.get(i).end);
			if (!BayesBall.get(i).Given_the1.isEmpty()){
			System.out.print("|");	
			}
			for (int j=0;j<this.BayesBall.get(i).Given_the1.size();j++) {
				System.out.print(this.BayesBall.get(i).Given_the1);
			}

		}
	}
}

