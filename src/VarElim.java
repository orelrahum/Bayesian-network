import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VarElim {

	public static String VarElimAnswer(Network Net,String Query) {
		String Answer=new String("varelim");
		int JoinNum=0;
		int EliminateNum=0;
		ArrayList<String> given_the_name = new ArrayList<String>();
		ArrayList<String> given_the_value = new ArrayList<String>();
		ArrayList<String> WhatToKill = new ArrayList<String>();
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

		while(!WhatToKill.isEmpty()) {
			// send to kill this var
			ArrayList<CPT> CPT_vec_temp = new ArrayList<CPT>();
			String KillNow=WhatToKill.get(0);
			for (int i=0;i<CPT_vec.size();i++)
				if (CPT_vec.get(i).equals(KillNow)) {
					CPT_vec_temp.add(CPT_vec.get(i));
					CPT_vec.remove(i);
				}
				else {
					for (int j=0;i<CPT_vec.get(i).parents_values.size();j++) {
						for (int k=0;k<CPT_vec.get(i).parents_values.get(j).parents_names.size();k++) {
							if (CPT_vec.get(i).parents_values.get(j).parents_names.get(k).equals(KillNow)) {
								CPT_vec_temp.add(CPT_vec.get(i));
								CPT_vec.remove(i);}
						}
					}
				}
			
			WhatToKill.remove(0);
			CPT_vec_temp.removeAll(CPT_vec_temp);
		}
		return Answer;
	}



	public static void Join() {}



	public static void Eliminate() {}

}
