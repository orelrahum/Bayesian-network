import java.util.ArrayList;


public class Var {
	public String name;
	public String[] values;
	public ArrayList<Var> parents = new ArrayList<Var>();
	public ArrayList<Var> children = new ArrayList<Var>();
	
	public Var(String name) {
		this.name=name;
	}
	public Var() {}
	

	public void print() {
		System.out.println("Name: " + name);
		System.out.print("values: ");
//		for (String v : values) System.out.print(v + " "); System.out.print("\n");
//		System.out.print("parents: ");
		for (var v : parents) System.out.print(v.name + " "); System.out.print("\n");
		System.out.print("children: ");
		for (var v : children) System.out.print(v.name + " "); System.out.print("\n");
	}
}
