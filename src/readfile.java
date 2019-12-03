import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;



public class readfile {
	Network Net=new Network();
	String Network_name;
	Var var;
	public readfile(String FileInput) {
		Network_name=FileInput;
	}

	public void read() {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(Network_name))) 
		{	
			line = br.readLine();
			line = br.readLine();
			line = br.readLine();
			while ((line = br.readLine()) != null) 
			{
				if (line.contains("Var")) {
					var=new Var();
					var.name=line.substring(4);
					line = br.readLine();
					line=line.substring(7);
					var.values=line.split(",");
					line = br.readLine();
					if (line.contains("None")) {
						line = br.readLine();
					} else {
						System.out.println("we are in else!!!");
						for(int i=0;i<Net.Vars.size();i++) {
							if (Net.Vars.get(i).name.compareTo(line.substring(8))==1)
							{var.parents.add(Net.Vars.get(i));
							}
						}
					}
					var.print();
					System.out.println("***********************************************");
					Net.Vars.add(var);
				}
			}

		} catch (IOException e) {e.printStackTrace();}
	}
}
