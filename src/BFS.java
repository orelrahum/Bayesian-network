//THIS CODE ITS FROM Algorithms 2 course
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {
	private  int size;//number of vertexes
	private  Queue<Integer> q;
	private  int dist[], pred[], color[], partition[];
	private  final int WHITE=1, GRAY=2,  BLACK=3, NIL = -1;
	private  ArrayList<Integer> graph[];
	private  int numComps, source;
	private  int components[];

	public BFS(ArrayList<Integer> g[]){
		size = g.length;
		q = new ArrayBlockingQueue<Integer>(size);
		dist = new int[size];
		pred = new int[size];
		color = new int[size];
		partition = new int[size];
		graph = new ArrayList[size];
		components = new int[size];
		for (int i = 0; i < size; i++) {
			graph[i] = new ArrayList<Integer>(g[i]);
		}
		source = 0;
		numComps = 0;
	}
	public void bfs(int s){
		for (int i = 0; i < size; i++) {
			dist[i] = NIL;
			pred[i] = NIL;
			color[i] = WHITE;
		}
		source = s;
		dist[source] = 0;
		color[source] = GRAY;
		q.add(source);
		while(!q.isEmpty()){
			int u = q.poll();//Retrieves and removes the head of this queue, or returns null if this queue is empty
			for(int v : graph[u]){
				if (color[v] == WHITE){
					dist[v] = dist[u]+1;
					pred[v] = u;
					color[v] = GRAY;
					q.add(v);
				}
			}
			color[u] = BLACK;
		}
	}
	public String getPath(int s,int v){
		bfs(s);
		//print();
		if (dist[v]==NIL) return null;
		String path = new String();
		if (v==s) path = path + s;
		else{
			path = path + v;
			int t = pred[v];
			while (t != NIL){
				path = t + "->" + path;
				t = pred[t];
			}
		}
		return path;
	}
	public boolean isBipartite (){
		boolean  bipartite = true;
		int partitions[] = new int[size];
		for (int i = 0; i < partitions.length; i++) {
			partitions[i] = 0;
		}
		int s = 0;//source vertex
		dist[s] = 0;
		color[s] = GRAY;
		partitions[s] = 1;
		q.add(s);
		while(bipartite && !q.isEmpty()){
			int u = q.poll();//Retrieves and removes the head of this queue, or returns null if this queue is empty
			for(int v : graph[u]){
				if (partition [u] == partition [v])
					bipartite = false;
				else if (color[v] == WHITE){
					dist[v] = dist[u]+1;
					pred[v] = u;
					color[v] = GRAY;
					partition[v] = 3 - partition[u];
					q.add(v);
				}
			}
			color[u] = BLACK;
		}
		return bipartite;
	}
	/**
	 * @return true if the graph is connected,
	 * otherwise return false
	 */
	public boolean isConnected(){
		boolean ans = true;
		bfs(0);
		for (int i=0; ans && i<size; i++){
			if  (dist[i] == NIL) ans = false;
		}
		return ans;
		
	}
	// components[i] - component's number of vertex i 
	private void connectedComponents(){
		while (hasNextComponent()){
			numComps++;
			bfs(source);
			for (int i = 0; i < components.length; i++) {
				if (dist[i]!=NIL) components[i] = numComps;
			}
		}
	}
	private boolean hasNextComponent(){
		boolean ans = false;
		for (int i = 0; !ans && i < components.length; i++) {
			if(components[i] == 0) {
				ans = true;
				source = i;
			}
		}
		return ans;
	}
	public String getAllComponents(){
		connectedComponents();
		ArrayList<Integer>[] compsList = new ArrayList[numComps];
		for (int i = 0; i < compsList.length; i++) {
			compsList[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < components.length; i++) {
			int n = components[i];
			compsList[n-1].add(i);
		}
		String ans = new String();
		for (int i = 0; i < compsList.length; i++) {
			ans = ans + compsList[i] + "\n";
		}
		return ans;
	}
	
	public void print(){
		System.out.println("pred: " + Arrays.toString(pred));
		System.out.println("dist: " + Arrays.toString(dist));
		System.out.println("color: " + Arrays.toString(color));
	}
	
}
