import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CTPParents{
	public ArrayList<String> parents_values_name = new ArrayList<String>();
	public ArrayList<Var> parents_values_Var = new ArrayList<Var>();
	public Map<Var, String> parents_value = new HashMap<>();
}
class CTPValues{
	public Map<String, Double> value_prob = new HashMap<>();
}

public class CPT {	
	public ArrayList<CTPParents> parents_values = new ArrayList<CTPParents>();
	public ArrayList<CTPValues> values_prob = new ArrayList<CTPValues>();

	public CPT() {}

	public void print() {
		for(int i=0;i<this.values_prob.size();i++) {
			System.out.print(this.parents_values.get(i).parents_value);
			System.out.println(this.values_prob.get(i).value_prob);
		}
	
	}
}
