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
	public void print() {
		for (int i=0;i<lines.size();i++) {
			lines.get(i).print();
			System.out.println("");
		}
	}
	

	



	public void combine2CPT ( CPT b) {

		for (int i=0;i<this.lines.get(0).parents.parents_names.size();i++) {
			for (int j=0;j<b.lines.get(0).parents.parents_names.size();j++) {
				if (!this.lines.get(0).parents.parents_names.get(i).equals(b.lines.get(0).parents.parents_names.get(j))) {
					boolean check=checkIfWeHve(this, b.lines.get(0).parents.parents_names.get(j));
					if (check) {
						for (int t=0;t<this.lines.size();t++) {
							this.lines.get(t).parents.parents_names.add(b.lines.get(0).parents.parents_names.get(j));
							this.lines.get(t).parents.parents_values.add(b.lines.get(0).parents.parents_values.get(j));	

						}
						
						j=0;
						i=0;
					}
				}
				
			}
		}

	}

	

	public static boolean checkIfWeHve (CPT a , String Name) {
		for (int i=0;i<a.lines.get(0).parents.parents_names.size();i++) {
			if (a.lines.get(0).parents.parents_names.get(i).contains(Name)) {
				return false;
			}
		}


		return true;
	}
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
	public void print() {
		parents.print();
		//System.out.print(",");
		System.out.print(prob);
	}


	public  void KillName (String tempKill) {
		for (int i=0;i<this.parents.parents_names.size();i++) {
			if (this.parents.parents_names.get(i).contains(tempKill)) {
				this.parents.parents_names.remove(i);
				this.parents.parents_values.remove(i);
				i=0;
			}
		}	
	}
}

class ParentsCPT{
	public ArrayList<String> parents_names = new ArrayList<String>();
	public ArrayList<String> parents_values = new ArrayList<String>();
	public ParentsCPT () {}
	public ParentsCPT(ParentsCPT other) {

		for (int i=0;i<other.parents_names.size();i++) {
			this.parents_names.add(other.parents_names.get(i));
		}
		for (int i=0;i<other.parents_values.size();i++) {
			this.parents_values.add(other.parents_values.get(i));
		}
	}
	public void print () {
		for (int i=0;i<this.parents_names.size();i++) {
			System.out.print(this.parents_names.get(i)+"=");
			System.out.print(this.parents_values.get(i)+",");
		}	
	}
}



