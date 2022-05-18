
import java.util.HashMap;

public class GuateGraph {

	
	SqMatrix matrix;
	HashMap<String, Integer> ciudad_to_id = new HashMap<>();
	HashMap<Integer, String> id_to_ciudad = new HashMap<>();
	int size = 0;
	
	GuateGraph(){
		matrix = new SqMatrix();
	}
	
	public void addNode(String ciudad) {
		if (!ciudad_to_id.containsKey(ciudad)) {
			size++;
			ciudad_to_id.put(ciudad, size);
			id_to_ciudad.put(size, ciudad);
			matrix.scale_up();
			addEdge(ciudad, ciudad, 0);
		}
		
	}
	
	public void addEdge(String origen, String destino, float dist) {
		addNode(origen);
		addNode(destino);
		int from = ciudad_to_id.get(origen);
		int to = ciudad_to_id.get(destino);
		if (matrix.get(from, to) > dist) {
			matrix.set(from, to, dist);
		}
	}
	
	public float getEdge(String origen, String destino) {
		addNode(origen);
		addNode(destino);
		return matrix.get(ciudad_to_id.get(origen), ciudad_to_id.get(destino));
	}
	
	public SqMatrix[] Floyd() {
		SqMatrix[] ans = new SqMatrix[2];
		SqMatrix cost = matrix.copy();
		SqMatrix path = new SqMatrix(size, true);
		
		for (int k=1; k<=size; k++) {
			for (int i=1; i<=size; i++) {
				for (int j=1; j<=size; j++) {
					if (cost.get(i, j) > cost.get(i, k) + cost.get(k, j)) {
						cost.set(i, j, cost.get(i, k) + cost.get(k, j));
						if (i != j) {
							path.set(i, j, k);
						}
					}
				}
			}
		}
		ans[0] = cost;
		ans[1] = path;
		return ans;
	}
	
	public String centre() {
		SqMatrix cost = Floyd()[0];
		int id = cost.argmin();
		String ciudad = id_to_ciudad.get(id);
		if (ciudad == null) {
			return "No tiene centro";
		}
		return "Centro: " + ciudad;
	}
	
	
	public String shortestPath(String origen, String destino) {
		int from = ciudad_to_id.get(origen);
		int to = ciudad_to_id.get(destino);
		
		
		SqMatrix[] floyd = Floyd();
		SqMatrix cost = floyd[0];
		
		if (cost.get(from, to) == cost.INF) {
			return "No existe camino entre " + origen + " y " + destino;
		}
		
		String txt = "Distancia: " + cost.get(from, to).toString() + "\n";
		
		SqMatrix paths = floyd[1];
		return txt + path(from, to, paths, origen + "->") + destino;
		
		
	}
	
	private String path(int i, int j, SqMatrix paths, String txt) {
		if (paths.get(i, j) != 0) {
			txt = path(i, (int) paths.get(i, j).floatValue(), paths, txt);
			txt += id_to_ciudad.get((int) paths.get(i, j).floatValue()) + "->";
			txt = path((int) paths.get(i, j).floatValue(), j, paths, txt);
			return txt;
		}
		return txt;
	}
	
	public void deleteNode(String ciudad) {
		int id = ciudad_to_id.get(ciudad);
		matrix.deleteRowCol(id);
	}
	
	public void deleteEdge(String origen, String destino) {
		int from = ciudad_to_id.get(origen);
		int to = ciudad_to_id.get(destino);
		matrix.set(from, to, matrix.INF);
	}
	
	
	@Override
	public String toString() {
		String txt = "";
		for (int i=1; i<size+1; i++) {
			txt += id_to_ciudad.get(i) + ", ";
		}
		return txt.substring(0, txt.length() - 2) + "\n" + matrix.toString();
	}
}
