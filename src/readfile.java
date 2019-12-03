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
			if (line.contains("Variables")) {
				line=line.substring(10);
				String Variables [];
				Variables=line.split(",");
				for(int i=0; i<Variables.length ;i++) {
					var=new Var();
					var.name=Variables[i];
					Net.Vars.add(var);
				}
			}
			line = br.readLine();
			while ((line= br.readLine()) != null) 
			{
				if (line.contains("Var")) {
					String TempName=line.substring(4);
					int  index= Net.findByName(TempName);
					System.out.println("lfdsfdsf");
					if (index !=-1) {
						System.out.println("yeshhhh");
						line = br.readLine();
						line=line.substring(7);
						Net.Vars.get(index).values=line.split(",");
						line = br.readLine();
						line=line.substring(9);
						if (line.contains(",")){
							String ParentTemp[]=line.split(",");
							for (int i=0; i<ParentTemp.length;i++) {
								int ParentsIndex=Net.findByName(ParentTemp[i]);
								Net.Vars.get(index).parents.add(Net.Vars.get(ParentsIndex));
								Net.Vars.get(ParentsIndex).children.add(Net.Vars.get(index));
							}
						}
						else if (!line.contains("none")) {
							int ParentIndex=Net.findByName(line);
							Net.Vars.get(index).parents.add(Net.Vars.get(ParentIndex));
							Net.Vars.get(ParentIndex).children.add(Net.Vars.get(index));
						}
					}
					else {
						System.err.println("invaild input");
						System.exit(0);
					}

				}
				line=br.readLine();
			}
			for (int i=0;i<Net.Vars.size();i++) {
				Net.Vars.get(i).print();}
			System.out.println("***********************************************");
		}

		catch (IOException e) {e.printStackTrace();}
	}
}

