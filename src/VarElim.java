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
			Temp2=Temp2.replace(")", "");
			String SecondAplit2[]=Temp2.split("=|,");

			for (int i=0;i<SecondAplit2.length-1;i=i+2) {
				given_the_name.add(SecondAplit2[i]);
				given_the_value.add(SecondAplit2[i+1]);
			}
			String Temp3=new String (SecondAplit2[SecondAplit2.length-1]);
			String ThirdSplit[]=Temp3.split("-");
			for (int i=0;i<ThirdSplit.length;i++) {
				WhatToKill.add(ThirdSplit[i]);
			}
		}

		for (int i=0;i<Net.Vars.size();i++) {
			CPT temp= new CPT (Net.Vars.get(i).cpt);
			CPT_vec.add(temp);

		}
		for (int i=0;i<given_the_name.size();i++) {
			for (int j=0;j<CPT_vec.size();j++) {
				if (given_the_name.contains(CPT_vec.get(j).Name)){
					for (int k=0;k<CPT_vec.get(j).lines.size();k++) {
						if (!CPT_vec.get(j).lines.get(k).Value.contains(given_the_value.get(i))) {
							CPT_vec.get(j).lines.remove(k);
							k--;

						}
					}
				}
			}

		}

		while(WhatToKill.size()>0) {
			ArrayList<CPT> CPT_vec_temp = new ArrayList<CPT>();
			String KillNow=WhatToKill.get(0);
			for (int i=0;i<CPT_vec.size();i++) {
				if (!CPT_vec.get(i).lines.isEmpty() || CPT_vec.get(i).lines.size()>1){
					for (int j=0;j<CPT_vec.get(i).lines.get(0).parents.parents_names.size();j++) {
						if (CPT_vec.get(i).lines.get(0).parents.parents_names.get(j).contains(KillNow)) {
							CPT_vec_temp.add(CPT_vec.get(i));
							CPT_vec.remove(i);
							i--;
						}
					}
				}
			}
			int killIndex=Net.findByName(KillNow);
			CPT tempKill=new CPT(Net.Vars.get(killIndex).cpt);
			boolean haveParent=false;
			boolean haveChild=false;
			if (Net.Vars.get(killIndex).parents.size()>0) {
				haveParent=true;

			}
			if (Net.Vars.get(killIndex).children.size()>0) {
				haveChild=true;
			}
			CPT AfterJoin=Join(CPT_vec_temp , tempKill , haveParent ,haveChild );
			for (int i=0;i<CPT_vec.size();i++) {
				if (CPT_vec.get(i).Name.contains(tempKill.Name)) {
					CPT_vec.remove(i);
					i--;
				}
			}
			System.out.println("its after the join :");
			System.out.println();
			AfterJoin.print();
			CPT afterEliminate=Eliminate(AfterJoin ,KillNow);
			System.out.println();
			System.out.println("its after elim :");
			afterEliminate.print();
			CPT_vec.add(afterEliminate);


			WhatToKill.remove(0);
			CPT_vec_temp.clear();
		}
		int NameIndex=Net.findByName(Name);
		CPT CTPName=Net.Vars.get(NameIndex).cpt;
		boolean haveParent=false;
		boolean haveChild=false;
		if (Net.Vars.get(NameIndex).parents.size()>0) {
			haveParent=true;

		}
		if (Net.Vars.get(NameIndex).children.size()>0) {
			haveChild=true;
		}
		CPT AfterJoin=Join(CPT_vec , CTPName , haveParent ,haveChild );
		System.out.println("its after the join :");
		System.out.println();
		AfterJoin.print();
		Normalize(AfterJoin);
		System.out.println("after normalize :");
		AfterJoin.print();
		System.out.println("we used "+JoinNum+" Joins");
		System.out.println("we used "+EliminateNum+" Eliminate");
		if (AfterJoin.Name.contains(Name)){
			for(int i=0;i<AfterJoin.lines.size();i++) {
				if (AfterJoin.lines.get(i).Value.contains(Value)) {
					AfterJoin.lines.get(i).prob=General.round(AfterJoin.lines.get(i).prob);
					lastProb=AfterJoin.lines.get(i).prob;
				}
			}
		}
		if (!AfterJoin.Name.contains(Name)){
			for(int i=0;i<AfterJoin.lines.size();i++) {
				for(int j=0;j<AfterJoin.lines.get(i).parents.parents_names.size();j++) {
					if (AfterJoin.lines.get(i).parents.parents_names.get(j).contains(Name)) {
						if (AfterJoin.lines.get(i).parents.parents_values.get(j).contains(Value)) {
							AfterJoin.lines.get(i).prob=General.round(AfterJoin.lines.get(i).prob);
							lastProb=AfterJoin.lines.get(i).prob;
						}
					}
				}
			}
		}
		Answer=lastProb+","+EliminateNum+","+JoinNum;


		return Answer;
	}


	public static CPT Join(ArrayList <CPT> vec , CPT tempKill,boolean haveParent ,boolean haveChild ) {
		ifChange=false;
		CPT newTemp=new CPT();
		if ( haveChild) {
			if (vec.size()>1) {
				for (int i=0;i<vec.size();i++) {
					for (int j=i+1;j<vec.size();j++) {
						ifChange=false;
						newTemp=Join2Tables(vec.get(i), vec.get(j), tempKill.Name ,ifChange);
						if (ifChange) {
							vec.remove(i);
							vec.remove(j);
							j--;
							i--;
						}
						ifChange=false;
					}
				}
			}
			else {
				newTemp=new CPT (vec.get(0));

			}
			newTemp=JoinKillCPT(tempKill, newTemp);


		}
		return newTemp;
	}


	public static CPT Join2Tables(CPT A, CPT B ,String NameToKill , boolean ifChange) {
		if (A.lines.get(0).parents.parents_names.size()>B.lines.get(0).parents.parents_names.size()) {

			for (int i=0;i<A.lines.size();i++) {
				for (int j=0;j<B.lines.size();j++) {
					for (int k=0;k<A.lines.get(i).parents.parents_names.size();k++) {
						for (int z=0;z<B.lines.get(j).parents.parents_names.size();z++) {
							if (A.lines.get(i).parents.parents_names.get(k).contains(NameToKill) && B.lines.get(j).parents.parents_names.get(z).contains(NameToKill)) {
								if (A.lines.get(i).parents.parents_values.get(k).contains(B.lines.get(j).parents.parents_values.get(z))){
									A.lines.get(i).prob*=B.lines.get(j).prob;
									ifChange=true;
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
			for (int i=0;i<A.lines.size();i++) {
				for (int j=0;j<B.lines.size();j++) {
					for (int k=0;k<A.lines.get(i).parents.parents_names.size();k++) {
						for (int z=0;z<B.lines.get(j).parents.parents_names.size();z++) {
							if (A.lines.get(i).parents.parents_names.get(k).contains(NameToKill) && B.lines.get(j).parents.parents_names.get(z).contains(NameToKill)) {
								if (A.lines.get(i).parents.parents_values.get(k).contains(B.lines.get(j).parents.parents_values.get(z))){
									B.lines.get(j).prob*=A.lines.get(i).prob;
									ifChange=true;
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

	public static CPT JoinKillCPT(CPT tempKill,CPT table) {
		if (tempKill.lines.get(0).parents.parents_names.size()>table.lines.get(0).parents.parents_names.size()) {
			for(int j=0;j<table.lines.size();j++) {
				for (int z=0;z<table.lines.get(j).parents.parents_names.size();z++) {
					if (tempKill.Name.contains(table.lines.get(j).parents.parents_names.get(z))) {
						for (int k=0;k<tempKill.lines.size();k++) {
							if (tempKill.lines.get(k).Value.contains(table.lines.get(j).parents.parents_values.get(z))){
								tempKill.lines.get(k).prob*=table.lines.get(j).prob;
								JoinNum++;}
						}
					}
				}
			}
			return tempKill;
		}
		else {
			for(int j=0;j<table.lines.size();j++) {
				for (int z=0;z<table.lines.get(j).parents.parents_names.size();z++) {
					if (tempKill.Name.contains(table.lines.get(j).parents.parents_names.get(z))) {
						for (int k=0;k<tempKill.lines.size();k++) {
							if (tempKill.lines.get(k).Value.contains(table.lines.get(j).parents.parents_values.get(z))){
								table.lines.get(j).prob*=tempKill.lines.get(k).prob;
								JoinNum++;}
						}
					}
				}
			}

		}
		return table;
	}




	public static CPT Eliminate(CPT AfterJoin ,String tempKill) {

		if (AfterJoin.Name.contains(tempKill)) {


			for (int i=0;i<AfterJoin.lines.size();i++) {
				for (int j=i+1;j<AfterJoin.lines.size();j++) {
					boolean compare=CompareLine1(AfterJoin.lines.get(i),AfterJoin.lines.get(j));
					if (compare) {
						AfterJoin.lines.get(i).prob+=AfterJoin.lines.get(j).prob;
						EliminateNum++;
						AfterJoin.lines.get(i).Value="";
						AfterJoin.lines.remove(j);
						j--;

					}		
				}
			}
			AfterJoin.Name="";
		}
		else {
			for (int i=0;i<AfterJoin.lines.size();i++) {
				for (int j=i+1;j<AfterJoin.lines.size();j++) {
					boolean compare=CompareLine2(AfterJoin.lines.get(i),AfterJoin.lines.get(j),tempKill);
					if (compare) {
						AfterJoin.lines.get(i).prob+=AfterJoin.lines.get(j).prob;
						AfterJoin.lines.get(i).Value="";
						AfterJoin.lines.remove(j);
						EliminateNum++;
						j--;

					}		
				}
			}
			AfterJoin.Name="";
		}

		return AfterJoin;}

	public static boolean CompareLine1 (LineCPT a ,LineCPT b) {
		for (int i=0;i<a.parents.parents_values.size();i++) {
			if (!a.parents.parents_values.get(i).contains(b.parents.parents_values.get(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean CompareLine2 (LineCPT a ,LineCPT b ,String KillName) {
		for (int i=0;i<a.parents.parents_values.size();i++) {
			if (!a.parents.parents_names.get(i).contains(KillName)) {
				if (!a.parents.parents_values.get(i).contains(b.parents.parents_values.get(i))) {
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
		System.out.println(x);
		if (x!=0) {
			x=1/x;
		}
		for (int i=0;i<last.lines.size();i++) {
			last.lines.get(i).prob*=x;
		}

	}

}
