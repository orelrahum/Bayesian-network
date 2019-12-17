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
				if (!CPT_vec.get(i).parents.isEmpty()) {
					System.out.println("1");
					for (int j=0;i<CPT_vec.get(i).parents.size();j++) {
						System.out.println("2");

						for (int k=0;k<CPT_vec.get(i).parents.get(j).parents_names.size();k++) {
							System.out.println("3");

							if (CPT_vec.get(i).parents.get(j).parents_names.get(k).equals(KillNow)) {
								System.out.println("kakaakakak");
								CPT_vec_temp.add(CPT_vec.get(i));
								CPT_vec.remove(i);}
						}
					}
				}
			}
		}
		System.out.println(CPT_vec.size());
		//		CPT AfterJoin=Join(CPT_vec_temp , tempKill);
		//		CPT afterEliminate=Eliminate(AfterJoin);
		//		//CPT_vec.add(afterEliminate);
		//
		//		WhatToKill.remove(0);
		//		CPT_vec_temp.clear();


		return Answer;
	}


	public static CPT Join(ArrayList <CPT> vec , CPT tempKill ) {
		double temp=0;
		for (int i=0;i<vec.size();i++) {
			System.out.println("orelarrea");
			for (int i2=0;i2<vec.get(i).parents.size();i2++) {
				for (int i3=0;i3<tempKill.values_prob.size();i3++) {
					if (vec.get(i).parents.get(i2).parents_values.equals(tempKill.values_prob.get(i3).value)){
						System.out.println("lalalal");
						temp=tempKill.values_prob.get(i3).prob.get(0)*vec.get(i).values_prob.get(i2).prob.get(0);
					}
				}
			}
		}
		System.out.println(temp);
		CPT a = null;
		return a;}



	public static CPT Eliminate(CPT AfterJoin) {
		CPT a = null;
		return a;}
}
