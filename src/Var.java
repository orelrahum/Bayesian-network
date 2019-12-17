import java.util.ArrayList;


public class Var {
	public String name;
	public ArrayList<String> values = new ArrayList<String>();
	public ArrayList<Var> parents = new ArrayList<Var>();
	public ArrayList<Var> children = new ArrayList<Var>();
	int color=0; // 0=white, 1=gray
	public CPT cpt=new CPT();
	
	public Var(String name) {
		this.name=name;
	}
	public Var() {
	}
	public Var(Var other) {
		this.name=other.name;
		this.values=other.values;
		this.parents=other.parents;
		this.children=other.children;
	}
	public boolean CompareVar (Var V) {
		if (this.name==V.name) {return true;}
		else {return false;}
	}
	

	public void print() {
		System.out.println("Name: " + name);
		System.out.print("values: ");
		for (String v : values) System.out.print(v + ","); System.out.print("\n");
		System.out.print("parents: ");
		for (var v : parents) System.out.print(v.name + ","); System.out.print("\n");
		System.out.print("children: ");
		for (var v : children) System.out.print(v.name + ","); System.out.print("\n");
		cpt.print();
	}
	public String toString() {
		return this.name;
	}
}
