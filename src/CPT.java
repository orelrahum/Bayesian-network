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




	public void sortByValName() {
		for (int i=0;i<this.lines.size();i++) {
			for (int j=0;j<this.lines.get(i).Var_name.size();j++) {
				for (int k=j+1;k<this.lines.get(i).Var_name.size();k++) {
					if (this.lines.get(i).Var_name.get(j).compareTo(this.lines.get(i).Var_name.get(k))>0) {
						String Name=this.lines.get(i).Var_name.get(j);
						String Value= this.lines.get(i).Var_value.get(j);
						this.lines.get(i).Var_name.remove(j);
						this.lines.get(i).Var_value.remove(j);
						this.lines.get(i).Var_name.add(Name);
						this.lines.get(i).Var_value.add(Value);
						j=0;
						k=0;


					}
				}

			}
		}
	}


	public void Combine2CPT( CPT b) {
		ArrayList<String> name_to_add = new ArrayList<String>();
		ArrayList<String> value_to_add = new ArrayList<String>();


		for (int k=0;k<b.lines.get(0).Var_name.size();k++) {
			boolean check=checkIfweHave(this.lines.get(0), b.lines.get(0).Var_name.get(k));
			if (check) {
				name_to_add.add(b.lines.get(0).Var_name.get(k));
			}
		}
//		for (int i=0;i<this.lines.size();i++) {
//			for (int j=0;j<b.lines.size();j++) {
//				for (int k=0;k<b.lines.get(j).Var_name.size();k++) {
//					boolean check2=compare2Lines(this.lines.get(i),b.lines.get(j), name_to_add );
//					if (check2){
//						this.lines.get(i).Var_name.add(e)
//					}
//				}
//			}
//		}


	}

	public static boolean checkIfweHave(LineCPT a,String Parent) {
		for (int i=0;i<a.Var_name.size();i++){
			if (a.Var_name.get(i).equals(Parent)) {
				return false;	
			}
		}
		return true;
	}


	public boolean compare2Lines(LineCPT a, LineCPT b ,ArrayList<String> name_to_add) {
		for (int i=0;i<b.Var_name.size();i++) {
			for (int j=0;j<a.Var_name.size();j++) {
				for (int t=0;t<name_to_add.size();t++) {
					if (!b.Var_name.get(i).equals(name_to_add.get(t))) {
						if (a.Var_name.get(j).equals(b.Var_name.get(i))) {
							if (!a.Var_value.get(i).equals(b.Var_name.get(j))) {
								return false;
							}
						}
					}
				}
			}
			return true;
		}



		return true;
	}


	public void cehckValue (CPT b) {
		for (int i=0;i<b.lines.size();i++) {

		}

	}







}










class LineCPT {
	public ArrayList<String> Var_name = new ArrayList<String>();
	public ArrayList<String> Var_value = new ArrayList<String>();
	double prob;

	public LineCPT() {}

	public LineCPT(ArrayList<String> Var_name ,ArrayList<String> Var_value , double probi) {
		prob=probi;
		for (int i=0;i<Var_name.size();i++) {
			this.Var_name.add(Var_name.get(i));
		}
		for (int i=0;i<Var_value.size();i++) {
			this.Var_value.add(Var_value.get(i));
		}
	} 

	public LineCPT(LineCPT other) {
		for (int i=0;i<other.Var_name.size();i++) {
			this.Var_name.add(other.Var_name.get(i));
		}
		for (int i=0;i<other.Var_value.size();i++) {
			this.Var_value.add(other.Var_value.get(i));
		}
		this.prob=other.prob;
	}
	public void print() {
		for (int i=0;i<this.Var_name.size();i++) {
			System.out.print(this.Var_name.get(i)+"=");
			System.out.print(this.Var_value.get(i)+",");
		}	
		System.out.print(prob);
	}


	public  void KillName (String tempKill) {
		for (int i=0;i<this.Var_name.size();i++) {
			if (this.Var_name.get(i).contains(tempKill)) {
				this.Var_name.remove(i);
				this.Var_value.remove(i);
				i=0;
			}
		}	
	}
}




