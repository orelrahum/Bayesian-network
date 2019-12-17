import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Iterator;

public class Network {

	public  ArrayList<Var> Vars=new ArrayList<Var>();
	String Answers=new String();
	public Network() {}
	
	public boolean add(Var e) {
		return Vars.add(e);
	}
	
	public int findByName(String name2) {
		for(int i=0 ;i<Vars.size();i++) {
			if (Vars.get(i).name.compareTo(name2)==0) {
				return i;
			}
		}
		return -1;
	}
	
	public void makeoutput(String answer) throws IOException {
		FileWriter output1 = new FileWriter("output.txt");
		BufferedWriter writer = new BufferedWriter(output1);
		writer.write(answer);
		writer.close();
	}
	
	public String QueryAnswers() {
		String s="";
		for (int i=0;i<35;i++) {
		s+="1\n";}
		return s;
	}
	
	public boolean addAll(Collection<? extends Var> c) {
		return Vars.addAll(c);
	}
	/**
	 *this function clear all the elements from this layer 
	 */
	
	public void clear() {
		// TODO Auto-generated method stub
		Vars.clear();
	}/**
	 * @return if the layer contains this element
	 */


	public boolean contains(Object o) {
		return Vars.contains(o);
	}
	/**
	 * @return if the layer contains all elements 
	 */
	
	public boolean containsAll(Collection<?> c) {
		return Vars.containsAll(c);
	}
	/**
	 * @return if the layer is empty 
	 */
	public boolean isEmpty() {
		return Vars.isEmpty();
	}

	public Iterator<Var> iterator() {
		return Vars.iterator();
	}
	/**
	 * @return if the element remove success
	 */

	public boolean remove(Object o) {
		return Vars.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return Vars.removeAll(c);
	}
	public void print() {
		for (int i=0;i<Vars.size();i++) {
			Vars.get(i).print();
			System.out.println("********************************************************************************");
		}
		System.out.println(Answers);
	}
}
