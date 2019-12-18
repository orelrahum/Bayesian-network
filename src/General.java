
public class General {
	public static float round(float x) {
		x=x*1000;
		int y=(int)x;
		x=(float)y;
		x=x/1000;
		return x;
	}
}
