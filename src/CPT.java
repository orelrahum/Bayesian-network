import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CTPParents{
	public Map<String, String> parents_value = new HashMap<>();
}
class CTPValues{
	public Map<String, Double> value_prob = new HashMap<>();
}

public class CPT {	
	public ArrayList<CTPParents> parents_values = new ArrayList<CTPParents>();
	public ArrayList<CTPValues> values_prob = new ArrayList<CTPValues>();

	public CPT() {}

	public void print() {
		for (CTPParents v : parents_values) {}
		for (CTPValues v : values_prob) {}

	}
}
