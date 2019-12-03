import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Network {

	public  ArrayList<Var> Vars=new ArrayList<Var>();
	
	public Network() {}
	
	public boolean add(Var e) {
		return Vars.add(e);
	}
	
	public int findByName(String name) {
		for(int i=0 ; !Vars.isEmpty() ;i++) {
			if (Vars.get(0).name==name) {
				return i;
			}
		}
		return -1;
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



}
