import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VarElim {

	public static String VarElimAnswer(Network Net,String Query) {
		String Answer=new String("varelim");
		ArrayList<String> given_the_name = new ArrayList<String>();
		ArrayList<String> given_the_value = new ArrayList<String>();
		ArrayList<String> WhatToKill = new ArrayList<String>();
		int JoinNum=0;
		int EliminateNum=0;
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
		ArrayList<CPT> CPT_vec = new ArrayList<CPT>();
		for (int i=0;i<Net.Vars.size();i++) {
			CPT temp= new CPT (Net.Vars.get(i).cpt);
			CPT_vec.add(temp);
		}

		while(WhatToKill.size()>0) {
			// send to kill this var
			CPT tempKill=new CPT();
			ArrayList<CPT> CPT_vec_temp = new ArrayList<CPT>();
			String KillNow=WhatToKill.get(0);
			for (int i=0;i<CPT_vec.size();i++) {
				if (CPT_vec.get(i).Name.contains(KillNow)) {
					System.out.println("dsadsad");
					tempKill=new CPT(CPT_vec.get(i));
					CPT_vec.remove(i);
				}
				for (int j=0;j<CPT_vec.get(i).lines.get(0).parents.parents_names.size();j++) {
					if (CPT_vec.get(i).lines.get(0).parents.parents_names.get(j).contains(KillNow)) {
						CPT_vec_temp.add(CPT_vec.get(i));
						CPT_vec.remove(i);}
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

			CPT AfterJoin=Join(CPT_vec_temp , tempKill , haveParent ,haveChild );
			CPT afterEliminate=Eliminate(AfterJoin);
			CPT_vec.add(afterEliminate);

			WhatToKill.remove(0);
			CPT_vec_temp.clear();
		}


		return Answer;
	}


	public static CPT Join(ArrayList <CPT> vec , CPT tempKill,boolean haveParent ,boolean haveChild ) {
		if (haveParent && haveChild) {
			for (int k=0;k<tempKill.lines.size();k++) {
				for (int i=0;i<vec.size();i++) {
					for(int j=0;j<vec.get(i).lines.size();j++) {
						for (int z=0;z<vec.get(i).lines.get(j).parents.parents_names.size();z++) {
							if (tempKill.Name.contains(vec.get(i).lines.get(j).parents.parents_names.get(z))) {
								if (tempKill.lines.get(k).Value.contains(vec.get(i).lines.get(j).parents.parents_values.get(z))) {
									tempKill.lines.get(k).prob*=vec.get(i).lines.get(j).prob;
									System.out.println("dsadsadasda");
								}
							}
						}

					}

				}
			}

		}
		if (haveParent && !haveChild) {

		}
		if (!haveParent && haveChild) {

		}


		CPT a = null;
		return a;}



	public static CPT Eliminate(CPT AfterJoin) {
		CPT a = null;
		return a;}
}
