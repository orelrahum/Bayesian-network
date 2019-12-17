import java.io.IOException;

public class ex1 {

	public static void main(String[] args) throws IOException {
		readfile f=new readfile("input.txt");
		Network Net=f.read();
		Net.print();
		Net.makeoutput(Net.Answers);
	}

}
