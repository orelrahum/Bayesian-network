import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Given{
	public Map<String, String> Given_the = new HashMap<>();
}

class BayesBallQuery{
	public ArrayList<Given> Given_the_BB = new ArrayList<Given>();
	String start;
	String end=new String();
	
}
class VarElimQuery{}
public class Query {
	public ArrayList<BayesBallQuery> BayesBall = new ArrayList<BayesBallQuery>();
	public ArrayList<VarElimQuery> VarElim = new ArrayList<VarElimQuery>();
}

