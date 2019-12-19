
public class General {
	public static double round(double x) {
		x=x*100000;
		int y=(int)x;
		x=(double)y;
		x=x/100000;
		return x;
	}
}
