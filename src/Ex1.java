import java.io.IOException;

public class Ex1 {

	public static void main(String[] args) throws IOException {
		readfile f=new readfile("input3.txt");
		Network Net=f.read();
		Net.print();
		Net.makeoutput(Net.Answers);
	}

}
