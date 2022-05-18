/**
 * @author Manuel Alejandro Martínez Flores
 * 
 * Graph.
 * 
 * Grafo utilizando la matriz de adyacencia.
 */


import java.util.HashMap;

public class Graph {

	
	private SqMatrix matrix;
	private HashMap<String, Integer> nombre_to_id = new HashMap<>();
	private HashMap<Integer, String> id_to_nombre = new HashMap<>();
	private SqMatrix cost;
	private SqMatrix paths;
	private int size = 0;
	private boolean modified = false;
	
	/**
	 * Inicializa grafo vacio
	 */
	Graph(){
		matrix = new SqMatrix();
	}
	
	/**
	 * Añade un nodo
	 * @param ciudad
	 */
	public void addNode(String ciudad) {
		if (!nombre_to_id.containsKey(ciudad)) {
			size++;
			nombre_to_id.put(ciudad, size);
			id_to_nombre.put(size, ciudad);
			matrix.scale_up();
			addEdge(ciudad, ciudad, 0);
			modified = true;
		}
		
		
	}
	
	/**
	 * Añade un arco o arista
	 * @param origen
	 * @param destino
	 * @param dist distancia
	 */
	public void addEdge(String origen, String destino, float dist) {
		addNode(origen);
		addNode(destino);
		int from = nombre_to_id.get(origen);
		int to = nombre_to_id.get(destino);
		if (matrix.get(from, to) > dist) {
			matrix.set(from, to, dist);
			modified = true;
		}
	}
	
	/**
	 * Indica el arco o arista entre dos nodos
	 * @param origen
	 * @param destino
	 * @return arco o arista
	 */
	public float getEdge(String origen, String destino) {
		addNode(origen);
		addNode(destino);
		return matrix.get(nombre_to_id.get(origen), nombre_to_id.get(destino));
	}
	
	/**
	 * Algoritmo de Floyd para rutas más cortas
	 * @return matriz de costo y matriz de rutas
	 */
	public void Floyd() {
		cost = matrix.copy();
		paths = new SqMatrix(size, true);
		
		for (int k=1; k<=size; k++) {
			for (int i=1; i<=size; i++) {
				for (int j=1; j<=size; j++) {
					if (cost.get(i, j) > cost.get(i, k) + cost.get(k, j)) {
						cost.set(i, j, cost.get(i, k) + cost.get(k, j));
						if (i != j) {
							paths.set(i, j, k);
						}
					}
				}
			}
		}
		modified = false;
	}
	
	/**
	 * Indica el centro del grafo
	 * @return nodo
	 */
	public String centre() {
		if (modified) {
			Floyd();
		}
		int id = cost.argmin();
		String ciudad = id_to_nombre.get(id);
		if (ciudad == null) {
			return "No tiene centro";
		}
		return "Centro: " + ciudad;
	}
	
	/**
	 * Indica rutas más cortas
	 * @param origen
	 * @param destino
	 * @return ruta más corta
	 */
	public String shortestPath(String origen, String destino) {
		int from = nombre_to_id.get(origen);
		int to = nombre_to_id.get(destino);
		
		if (modified) {
			Floyd();
		}
		
		if (cost.get(from, to) == cost.INF) {
			return "No existe camino entre " + origen + " y " + destino;
		}
		
		String txt = "Distancia: " + cost.get(from, to).toString() + "\n";
		
		return txt + path(from, to, origen + "->") + destino;
		
		
	}
	
	/**
	 * Reconstruye la ruta más corta
	 * @param i origen
	 * @param j destino
	 * @param txt
	 * @return ruta
	 */
	private String path(int i, int j, String txt) {
		if (paths.get(i, j) != 0) {
			txt = path(i, (int) paths.get(i, j).floatValue(), txt);
			txt += id_to_nombre.get((int) paths.get(i, j).floatValue()) + "->";
			txt = path((int) paths.get(i, j).floatValue(), j, txt);
			return txt;
		}
		return txt;
	}
	
	/**
	 * Elimina un nodo
	 * @param ciudad
	 */
	public void deleteNode(String ciudad) {
		int id = nombre_to_id.get(ciudad);
		matrix.deleteRowCol(id);
		size--;
	}
	
	/**
	 * Elimina un arco o arista
	 * @param origen
	 * @param destino
	 */
	public void deleteEdge(String origen, String destino) {
		int from = nombre_to_id.get(origen);
		int to = nombre_to_id.get(destino);
		matrix.set(from, to, matrix.INF);
	}
	
	/**
	 * Genera un texto con el grafo.
	 */
	@Override
	public String toString() {
		String txt = "";
		for (int i=1; i<size+1; i++) {
			txt += id_to_nombre.get(i) + ", ";
		}
		return txt.substring(0, txt.length() - 2) + "\n" + matrix.toString();
	}
	
	/**
	 * Indica la matriz de rutas
	 * @return matriz
	 */
	public SqMatrix getPaths() {
		return paths;
	}
	
	/**
	 * Indica la matriz de costo
	 * @return matriz
	 */
	public SqMatrix getCost() {
		return cost;
	}
}
