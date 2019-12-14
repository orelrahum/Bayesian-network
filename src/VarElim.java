import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VarElim {

	public static String VarElimAnswer(Network Net,String Query) {
		ArrayList<String> given_the_name = new ArrayList<String>();
		ArrayList<String> given_the_value = new ArrayList<String>();
		String firstSplit[]=Query.split("\\|");
		String Temp= new String (firstSplit[0]);
		Temp=Temp.substring(2);
		String secondSplit[]=Temp.split("=");
		String Name=new String(secondSplit[0]);
		String Value=new String (secondSplit[1]);
		//		if (firstSplit.length>1) {
		//			String Temp2=new String (firstSplit[1]);
		//			String SecondAplit2[]=Temp2.split(")");
		//			if (SecondAplit2.length>1) {
		//				String Temp3=new String(SecondAplit2[0]);
		//				String ThirdSplit3[]=Temp3.split(",");
		//				for (int i=0;i<ThirdSplit3.length-1;i++) {
		//					System.out.println(SecondAplit2[i]);
		//					System.out.println(SecondAplit2[i+1]);
		//					given_the_name.add(SecondAplit2[i]);
		//					given_the_value.add(SecondAplit2[i+1]);
		//				}
		//			}
		//		}

		String Answer=new String("varelim");
		ArrayList<CPT> CPT_vec = new ArrayList<CPT>();
		for (int i=0;i<Net.Vars.size();i++) {
			CPT temp= new CPT (Net.Vars.get(i).cpt);
			CPT_vec.add(temp);
		}
		ArrayList<String> WhatToKill = new ArrayList<String>();




		return Answer;
	}
}
