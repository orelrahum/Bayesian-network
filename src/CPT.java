import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CTPParents{
	public ArrayList<String> parents_names = new ArrayList<String>();
	public ArrayList<String> parents_values = new ArrayList<String>();
	//public Map<String, String> parents_value = new HashMap<>();
	public CTPParents () {}
	public CTPParents(CTPParents other) {

		for (int i=0;i<this.parents_names.size();i++) {
			other.parents_names.add(this.parents_names.get(i));
		}
		for (int i=0;i<this.parents_values.size();i++) {
			other.parents_values.add(this.parents_values.get(i));
		}
	}
}


class CTPValues{
	public ArrayList<String> value = new ArrayList<String>();
	public ArrayList<Double> prob = new ArrayList<Double>();
	//public Map<String, Double> value_prob = new HashMap<>();
	public CTPValues() {}
	public CTPValues(CTPValues other) {
		for (int i=0;i<this.value.size();i++) {
			other.value.add(this.value.get(i));
		}
		for (int i=0;i<this.prob.size();i++) {
			other.prob.add(this.prob.get(i));
		}	
	}
}

public class CPT {
	String Name=new String();
	public ArrayList<CTPParents> parents_values = new ArrayList<CTPParents>();
	public ArrayList<CTPValues> values_prob = new ArrayList<CTPValues>();


	public CPT() {}

	public CPT(CPT other) {
		this.Name+=other.Name;
		for (int i=0;i<this.parents_values.size();i++) {
			CTPParents temp=new CTPParents(this.parents_values.get(i));
			other.parents_values.add(temp);
		}
		for (int i=0;i<this.values_prob.size();i++) {
			CTPValues temp=new CTPValues(this.values_prob.get(i));
			other.values_prob.add(temp);	
		}

	}

	public  boolean parentsValueEqual(CPT other) {
		if (this.parents_values.get(0).parents_names.size()!=other.parents_values.get(0).parents_names.size()) {return false;}
		for (int i=0;i<this.parents_values.get(0).parents_names.size();i++) {
			if (!this.parents_values.get(0).parents_names.get(i).equals(other.parents_values.get(0).parents_names.get(i))){return false;}
		}
		return true;	
	}
	

	public void print() {
		//		for(int i=0;i<this.values_prob.size();i++) {
		//			System.out.print(this.parents_values.get(i).parents_value);
		//			System.out.println(this.values_prob.get(i).value_prob);
		//		}

	}
}
