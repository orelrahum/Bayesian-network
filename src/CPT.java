import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CTPParents{
	public ArrayList<String> parents_names = new ArrayList<String>();
	public ArrayList<String> parents_values = new ArrayList<String>();
	//public Map<String, String> parents_value = new HashMap<>();
}
class CTPValues{
	public ArrayList<String> value = new ArrayList<String>();
	public ArrayList<Double> prob = new ArrayList<Double>();
	//public Map<String, Double> value_prob = new HashMap<>();
}

public class CPT {	
	public ArrayList<CTPParents> parents_values = new ArrayList<CTPParents>();
	public ArrayList<CTPValues> values_prob = new ArrayList<CTPValues>();
	String Name;
	String given_the;
	
	public CPT() {}

	public void print() {
//		for(int i=0;i<this.values_prob.size();i++) {
//			System.out.print(this.parents_values.get(i).parents_value);
//			System.out.println(this.values_prob.get(i).value_prob);
//		}
	
	}
}
