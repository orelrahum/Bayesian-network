
public class ex1 {

	public static void main(String[] args) {
		readfile f=new readfile("input.txt");
		Network Net=f.read();
		Net.print();
	}

}
