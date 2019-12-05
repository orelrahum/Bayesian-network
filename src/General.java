
public class General {
	public static double round(double x) {
		x=x*1000;
		int y=(int)x;
		x=(double)y;
		x=x/1000;
		return x;
	}
}
