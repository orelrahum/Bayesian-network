import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CPT {
	String Name=new String();
	public ArrayList<LineCPT> lines = new ArrayList<LineCPT>();
	public CPT() {}
	public CPT(CPT other) {
		this.Name=other.Name;
		for (int i=0;i<other.lines.size();i++) {
			LineCPT temp =new LineCPT(other.lines.get(i));
			this.lines.add(temp);
		}
	}
	public void print() {}
}

class LineCPT {
	ParentsCPT parents=new ParentsCPT();
	String Value=new String();
	double prob;

	public LineCPT() {}

	public LineCPT(ParentsCPT Parents, String Val , double probi) {
		Value+=Val;
		prob=probi;
		parents=new ParentsCPT (Parents);
	} 

	public LineCPT(LineCPT other) {
		this.parents=new ParentsCPT(other.parents);
		this.Value+=other.Value;
		this.prob=other.prob;
	}
}

class ParentsCPT{
	public ArrayList<String> parents_names = new ArrayList<String>();
	public ArrayList<String> parents_values = new ArrayList<String>();
	public ParentsCPT () {}
	public ParentsCPT(ParentsCPT other) {

		for (int i=0;i<this.parents_names.size();i++) {
			other.parents_names.add(this.parents_names.get(i));
		}
		for (int i=0;i<this.parents_values.size();i++) {
			other.parents_values.add(this.parents_values.get(i));
		}
	}
}



