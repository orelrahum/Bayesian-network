public class BayesBall {

	public static String BayesAnswer(Network Net,String Query) {
		for (int i=0;i<Net.Vars.size();i++) {
			Net.Vars.get(i).color=0;
		}
		String answer="";
		String firstSplit[]=Query.split("\\|");
		String Temp=new String(firstSplit[0]);
		String SecondSplit[]=Temp.split("-");
		boolean up=true;
		int start_index=Net.findByName(SecondSplit[0]);
		int end_index=Net.findByName(SecondSplit[1]);
		Var Start=new Var(Net.Vars.get(start_index));
		Var End=new Var(Net.Vars.get(end_index));
		if (Start.color==1) {
			up=false;
		}
		if (firstSplit.length>1) {
			String Temp2=new String (firstSplit[1]);
			String SecondAplit2[]=Temp2.split(",");
			for (int i=0;i<SecondAplit2.length;i++) {
				String Temp3=new String (SecondAplit2[i]);
				String SecondAplit3[]=Temp3.split("=");
				int index =Net.findByName(SecondAplit3[0]);
				Net.Vars.get(index).color=1;
			}
		}
		boolean ans=isConnected(Net,null,Start,End,up);
		if (ans==false) {answer+="false";}
		else {answer+="true";}
		return answer;
	}
	// 	private boolean Bayes_Ball_Alg(BayesianNetwork.Var last,BayesianNetwork.Var source,BayesianNetwork.Var dest,ArrayList<BayesianNetwork.Var>ind,direction dir) {

	public static boolean isConnected(Network Net, Var last,Var Start, Var End , boolean up) {
		System.out.println("dsadsadsad");
		if(Start.name.contains(End.name)) return false;
		if(Start.color==1) {
			if(up) {
				return true;
			}
			else {
				for(int i=0;i<Start.parents.size();i++) {
					if(!isConnected(Net,Start,Start.parents.get(i),End,true))
						return false;
				}

				return true;
			}
		}
		else {
			if(!up) {
				for(int i=0; i<Start.children.size();i++) {
					if(Start.children.get(i)!=last&&!isConnected(Net,Start,Start.children.get(i),End,false))
						return false;
				}
				return true;
			}
			else {
				for(int i=0;i<Start.parents.size();i++) {
					if(Start.parents.get(i)!=last &&!isConnected(Net,Start,Start.parents.get(i),End,false))
						return false;
				}
				for(int i=0; i<Start.children.size();i++) {
					if(Start.children.get(i)!=last &&!isConnected(Net,Start,Start.children.get(i),End,false))
						return false;
				}
				return true;
			}
		}
	}

}
