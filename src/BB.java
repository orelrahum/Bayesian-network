
public class BB {
	public void Bayes_ball(String s) throws Exception {	
		boolean ans=false;
		BayesianNetwork.Var source;
		BayesianNetwork.Var dest;
		direction direc=direction.up;
		ArrayList<BayesianNetwork.Var>ind=new ArrayList<>();
		if(s.charAt(s.length()-1)=='|') {
			String[]str=s.split("-|\\|");
			source=net.GetVarByName(str[0]);
			dest=net.GetVarByName(str[1]);
		}
		else {
			String[]str=s.split("-|=|\\|");
			source=net.GetVarByName(str[0]);
			dest=net.GetVarByName(str[1]);
			ind.add(net.GetVarByName(str[2]));
		}
		ans=Bayes_Ball_Alg(null,source,dest,ind,direc);
		System.out.println(ans);
		if(ans)output+="yes"+"\n";
		else output+="no"+"\n";
	}
	
	private boolean Bayes_Ball_Alg(BayesianNetwork.Var last,BayesianNetwork.Var source,BayesianNetwork.Var dest,ArrayList<BayesianNetwork.Var>ind,direction dir) {
		if(source==dest) return false;
		if(ind.contains(source)) {
			if(dir==direction.up) {
				return true;
			}
			else {
				for(BayesianNetwork.Var parent : source.parents) {
					if(!Bayes_Ball_Alg(source,parent,dest,ind,direction.up))
						return false;
				}
				
				return true;
			}
		}
		else {
			if(dir==direction.down) {
				for(BayesianNetwork.Var child : source.childrens) {
					if(child!=last&&!Bayes_Ball_Alg(source,child,dest,ind,direction.down))
						return false;
				}
				return true;
			}
			else {
				for(BayesianNetwork.Var parent : source.parents) {
					if(parent!=last&&!Bayes_Ball_Alg(source,parent,dest,ind,direction.up))
						return false;
				}
				for(BayesianNetwork.Var child : source.childrens) {
					if(child!=last&&!Bayes_Ball_Alg(source,child,dest,ind,direction.down))
						return false;
				}
				return true;
			}
		}
	}
}







public static boolean Bayesball(Vertex A,Vertex B) {
	//int arr[]=new int[network.getArray().size()];
	boolean ans=false;
	if(A.getName().equals(B.getName())) {
		return true;
	}
	for(int i=0; i<A.getChildren().size()&&!ans;i++) {
		if(A.getChildren().get(i).getName().contains(B.getName())) {	
			return true;
		}
		ans=Bayesball(A.getChildren().get(i),B);
	}		
	return ans;
}
public static boolean Bayesball(Vertex A,Vertex B,Vertex C,boolean Observed) {
	//int arr[]=new int[network.getArray().size()];
	boolean ans=false;
	//boolean Observed=false;
	if(A.getName().equals(B.getName())) {
		return true;
	}
	int i;
	for( i=0; i<A.getChildren().size()&&!ans&&!Observed;i++) {
		if(A.getChildren().get(i).getName().contains(B.getName())) {	
			return true;
		}
		if(A.getChildren().get(i).getName().contains(C.getName())) {	
			Observed=true;
			//System.out.println(A.getChildren().get(i).getName());
		}
		//System.out.println(Observed);
		ans=Bayesball(A.getChildren().get(i),B,C,Observed);
	}
	for(i=0;i<A.getParents().size()&&!ans&&Observed;i++) {
		//System.out.println(Observed);
//		System.out.println("1 "+A.getParents().get(i).getName());
//		System.out.println("2 "+B.getName());
		//System.out.println(A.getParents().get(i).getName().equals(B.getName()));
		if(A.getParents().get(i).getName().equals(B.getName())) {	
			return true;
		}
	}
	return ans;
}






























