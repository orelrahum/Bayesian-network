import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VarElim {
	public static int JoinNum=0;
	public static int EliminateNum=0;
	public static boolean ifChange=false;
	public static String VarElimAnswer(Network Net,String Query) {
		String Answer=new String("varelim");
		double lastProb=0;
		ArrayList<String> given_the_name = new ArrayList<String>();
		ArrayList<String> given_the_value = new ArrayList<String>();
		ArrayList<String> WhatToKill = new ArrayList<String>();
		ArrayList<CPT> CPT_vec = new ArrayList<CPT>();
		JoinNum=0;
		EliminateNum=0;	
		CPT_vec.clear();
		WhatToKill.clear();
		given_the_name.clear();
		given_the_value.clear();
		String firstSplit[]=Query.split("\\|");
		String Temp= new String (firstSplit[0]);
		Temp=Temp.substring(2);
		String secondSplit[]=Temp.split("=");
		String Name=new String(secondSplit[0]);
		String Value=new String (secondSplit[1]);
		if (firstSplit.length>1) {
			String Temp2=new String (firstSplit[1]);
			String SecondAplit2[]=Temp2.split("\\)");
			String Temp3=new String (SecondAplit2[0]);
			String giventhe[]=Temp3.split(",|=");
			for (int i=0;i<giventhe.length-1;i=i+2) {
				given_the_name.add(giventhe[i]);
				given_the_value.add(giventhe[i+1]);
			}
			if (SecondAplit2.length>1) {
				if (SecondAplit2[1].length()>1) {
					String Temp4=new String (SecondAplit2[1]);
					Temp4=Temp4.substring(1);
					String ThirdSplit[]=Temp4.split("-");
					for (int i=0;i<ThirdSplit.length;i++) {
						WhatToKill.add(ThirdSplit[i]);
					}
				}
			}
		}

		for (int i=0;i<Net.Vars.size();i++) {
			CPT temp= new CPT (Net.Vars.get(i).cpt);
			CPT_vec.add(temp);

		}
		for (int i=0;i<given_the_name.size();i++) {
			for (int j=0;j<CPT_vec.size();j++) {
				for (int z=0;z<CPT_vec.get(j).lines.size();z++) {
					for (int g=0;g<CPT_vec.get(j).lines.get(z).Var_name.size();g++) {
						if (CPT_vec.get(j).lines.get(z).Var_name.get(g).equals(given_the_name.get(i))) {
							if (!CPT_vec.get(j).lines.get(z).Var_value.get(g).equals(given_the_value.get(i))) {
								CPT_vec.get(j).lines.remove(z);
								z=0;
								g=0;
								j=0;

							}
							else if (CPT_vec.get(j).lines.get(z).Var_value.get(g).equals(given_the_value.get(i))) {
								CPT_vec.get(j).lines.get(z).KillName(given_the_name.get(i));
								z=0;
								g=0;
								j=0;

							}
						}
					}
				}
			}

		}
//		for (int i=0;i<WhatToKill.size();i++) {
//			for (int j=0;j<CPT_vec.size();j++) {
//			boolean find=checkIfweHave(CPT_vec.get(j).lines.get(0), WhatToKill.get(i));
//					
//					
//			}
//			
//		}

		while(WhatToKill.size()>0) {
			ifChange=true;
			ArrayList<CPT> CPT_vec_temp = new ArrayList<CPT>();
			String KillNow=WhatToKill.get(0);
			for (int i=0;i<CPT_vec.size();i++) {
				if (!CPT_vec.get(i).lines.isEmpty() && !CPT_vec.get(i).lines.get(0).Var_name.isEmpty() && CPT_vec.get(i).lines.get(0).Var_name.size()>0  ) {
					for (int j=0;j<CPT_vec.get(i).lines.get(0).Var_name.size();j++) {
						if (CPT_vec.get(i).lines.get(0).Var_name.get(j).equals(KillNow)) {
							CPT_vec_temp.add(CPT_vec.get(i));
							CPT_vec.remove(i);
							i--;
						}
					}
				}
			}
			int killIndex=Net.findByName(KillNow);
			boolean haveParent=false;
			boolean haveChild=false;
			if (Net.Vars.get(killIndex).parents.size()>0) {
				haveParent=true;

			}
			if (Net.Vars.get(killIndex).children.size()>0) {
				haveChild=true;
			}
			CPT AfterJoin=Join(CPT_vec_temp , KillNow , haveParent ,haveChild );

			System.out.println("its after the join :");
			System.out.println();
			AfterJoin.print();
			CPT afterEliminate=Eliminate(AfterJoin ,KillNow);

			System.out.println("its after the +++++ :");
			afterEliminate.print();
			CPT_vec.add(afterEliminate);

			WhatToKill.remove(0);
			CPT_vec_temp.clear();
		}


		int NameIndex=Net.findByName(Name);
		boolean haveParent=false;
		boolean haveChild=false;
		if (Net.Vars.get(NameIndex).parents.size()>0) {
			haveParent=true;

		}
		if (Net.Vars.get(NameIndex).children.size()>0) {
			haveChild=true;
		}
		if (ifChange) {
			ArrayList<CPT> CPT_vec_temp = new ArrayList<CPT>();
			for (int i=0;i<CPT_vec.size();i++) {
				if (!CPT_vec.get(i).lines.isEmpty() && !CPT_vec.get(i).lines.get(0).Var_name.isEmpty() && CPT_vec.get(i).lines.get(0).Var_name.size()>0  ) {
					for (int j=0;j<CPT_vec.get(i).lines.get(0).Var_name.size();j++) {
						if (CPT_vec.get(i).lines.get(0).Var_name.get(j).equals(Name)) {
							CPT_vec_temp.add(CPT_vec.get(i));

						}
					}
				}
			}
			CPT AfterJoin=Join(CPT_vec_temp , Name , haveParent ,haveChild );
			System.out.println("its after the join :");
			System.out.println();
			AfterJoin.print();
			Normalize(AfterJoin);
			System.out.println("after normalize :");
			AfterJoin.print();
			System.out.println("we used "+JoinNum+" Joins");
			System.out.println("we used "+EliminateNum+" Eliminate");
			for(int i=0;i<AfterJoin.lines.size();i++) {
				for(int j=0;j<AfterJoin.lines.get(i).Var_name.size();j++) {
					if (AfterJoin.lines.get(i).Var_name.get(j).equals(Name)) {
						if (AfterJoin.lines.get(i).Var_value.get(j).equals(Value)) {
							AfterJoin.lines.get(i).prob=General.round(AfterJoin.lines.get(i).prob);
							lastProb=AfterJoin.lines.get(i).prob;
						}
					}
				}
			}
		}
		else {
			int x= findCPTindex(CPT_vec, Name);
			System.out.println("we have wonnnnnnnnnnn the match");
			CPT_vec.get(x).print();;
			for(int i=0;i<CPT_vec.get(x).lines.size();i++) {
				for(int j=0;j<CPT_vec.get(x).lines.get(i).Var_name.size();j++) {
					if (CPT_vec.get(x).lines.get(i).Var_name.get(j).equals(Name)) {
						if (CPT_vec.get(x).lines.get(i).Var_value.get(j).equals(Value)) {
							CPT_vec.get(x).lines.get(i).prob=General.round(CPT_vec.get(x).lines.get(i).prob);
							lastProb=CPT_vec.get(x).lines.get(i).prob;
						}
					}
				}
			}
		}

		Answer=lastProb+","+EliminateNum+","+JoinNum;
		System.out.println("final :"+ Answer);
		System.out.println("*************************************************************************************************************");
		return Answer;
	}


	public static CPT Join(ArrayList <CPT> vec , String tempKill,boolean haveParent ,boolean haveChild ) {
		vec=sortByLine(vec);
		CPT newTemp=new CPT();
		if (vec.size()>1) {
			for (int i=0;i<vec.size();i++) {
				for (int j=i+1;j<vec.size();j++) {
					vec.get(i).sortByValName();
					vec.get(j).sortByValName();
					System.out.println("\nits the first table!!!!");
					vec.get(i).print();
					System.out.println("\nits the second table!!!!");
					vec.get(j).print();
					
					
					newTemp=Join2Tables(vec.get(i), vec.get(j), tempKill);
					System.out.println("\nits the result table!!!!");
					newTemp.print();
					if (vec.get(i).lines.size()>vec.get(j).lines.size()) {

						vec.remove(j);
						j--;
					}
					else {
						vec.remove(i);	
						i--;
					}
				}
				vec=sortByLine(vec);
			}
		}
		else if (vec.size()>0) {
			newTemp=new CPT (vec.get(0));

		}
		return newTemp;
	}


	public static CPT Join2Tables(CPT A, CPT B ,String NameToKill ) {
		CPT temp=new CPT ();{
			
		}

		if (A.lines.size()>B.lines.size()) {

			for (int i=0;i<A.lines.size();i++) {
				for (int j=0;j<B.lines.size();j++) {
					for (int k=0;k<A.lines.get(i).Var_name.size();k++) {
						for (int z=0;z<B.lines.get(j).Var_name.size();z++) {
							if (A.lines.get(i).Var_name.get(k).equals(NameToKill) && B.lines.get(j).Var_name.get(z).equals(NameToKill)) {
								if (A.lines.get(i).Var_value.get(k).equals(B.lines.get(j).Var_value.get(z))){
									
									A.lines.get(i).prob*=B.lines.get(j).prob;
									JoinNum++;
								}
							}
						}
					}
				}
			}
			return A;
		}

		else {
			for (int i=0;i<B.lines.size();i++) {
				for (int j=0;j<A.lines.size();j++) {
					for (int k=0;k<B.lines.get(i).Var_name.size();k++) {
						for (int z=0;z<A.lines.get(j).Var_name.size();z++) {
							if (A.lines.get(j).Var_name.get(z).equals(NameToKill) && B.lines.get(i).Var_name.get(k).equals(NameToKill)) {
								if (A.lines.get(j).Var_value.get(z).equals(B.lines.get(i).Var_value.get(k))){
									//System.out.println("prob 1 "+B.lines.get(i).prob);
									//System.out.println("prob 2 " +A.lines.get(j).prob);
									double prob=A.lines.get(j).prob*B.lines.get(i).prob;
									//System.out.println("after join prob is :"+prob);
									B.lines.get(i).prob*=A.lines.get(j).prob;
									JoinNum++;
								}
							}
						}
					}
				}
			}	
		}
		return B;
	}




	public static CPT Eliminate(CPT AfterJoin ,String tempKill) {

		for (int i=0;i<AfterJoin.lines.size();i++) {
			for (int j=i+1;j<AfterJoin.lines.size();j++) {
				boolean compare=CompareLine2(AfterJoin.lines.get(i),AfterJoin.lines.get(j),tempKill );
				if (compare) {
					AfterJoin.lines.get(i).prob+=AfterJoin.lines.get(j).prob;
					EliminateNum++;
					AfterJoin.lines.remove(j);
					j--;
				}
			}
		}
		
		for (int i=0;i<AfterJoin.lines.size();i++) {
			for (int j=0;j<AfterJoin.lines.get(i).Var_name.size();j++) {
				AfterJoin.lines.get(i).KillName(tempKill);
			}
		}
		return AfterJoin;
	}






	public static boolean CompareLine2 (LineCPT a ,LineCPT b ,String KillName) {
		for (int i=0;i<a.Var_value.size();i++) {
			if (!a.Var_name.get(i).equals(KillName)) {
				if (!a.Var_value.get(i).equals(b.Var_value.get(i))) {
					return false;
				}
			}
		}
		return true;
	}


	public static void Normalize(CPT last) {
		double x=0;
		EliminateNum--;
		for (int i=0;i<last.lines.size();i++) {
			x+=last.lines.get(i).prob;
			EliminateNum++;
		}
		if (x!=0) {
			x=1/x;
		}
		for (int i=0;i<last.lines.size();i++) {
			last.lines.get(i).prob*=x;
		}

	}

	public static ArrayList <CPT> sortByLine    (ArrayList <CPT> vec){
		for (int i=0;i<vec.size();i++) {
			for (int j=i+1;j<vec.size();j++) {
				boolean first=compareLineSize(vec.get(i),vec.get(j));
				if (!first) {
					CPT temp =vec.get(i);
					vec.remove(i);
					vec.add(temp);
					i=0;
					j=0;
				}


			}
		}



		return vec;
	}


	public static boolean compareLineSize(CPT a , CPT b) {
		if (a.lines.size()>b.lines.size()) {	return false;}
		return true;	
	}

	public static int findCPTindex (ArrayList <CPT> vec , String Name) {
		int x=-1;
		for (int i=0;i<vec.size();i++) {
			if (vec.get(i).Name.equals(Name)) {
				x=i;
			}
		}
		return x;
	}
	
	
	
	public static void checkMissName(CPT a ,CPT b) {
		ArrayList<String> name_to_add = new ArrayList<String>();
		ArrayList<String> value_to_add = new ArrayList<String>();


		for (int k=0;k<b.lines.get(0).Var_name.size();k++) {
			boolean check=checkIfweHave(a.lines.get(0), b.lines.get(0).Var_name.get(k));
			if (check) {
				name_to_add.add(b.lines.get(0).Var_name.get(k));
			}
		}
	}
	
	
	public static boolean checkIfweHave(LineCPT a,String Parent) {
		for (int i=0;i<a.Var_name.size();i++){
			if (a.Var_name.get(i).equals(Parent)) {
				return false;	
			}
		}
		return true;
	}

}