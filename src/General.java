
public class General {
	public static float round(float x) {
		x=x*100000;
		int y=(int)x;
		x=(float)y;
		x=x/100000;
		return x;
	}
}
