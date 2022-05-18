/**
 * @author Manuel Alejandro Martínez Flores
 * 
 * SqMatrix.
 * 
 * Matriz cuadrada para implementar el grafo.
 */

import java.util.ArrayList;

public class SqMatrix {

	private ArrayList<ArrayList<Float>> data;
	final float INF = Float.POSITIVE_INFINITY;
	final float nINF = Float.NEGATIVE_INFINITY;
	private int size = 0;
	
	/**
	 * Inicializa matriz vacia
	 */
	SqMatrix(){
		data = new ArrayList<ArrayList<Float>>();
	}
	
	/**
	 * Inicializa matriz de cierta dimensión
	 * @param size
	 */
	SqMatrix(int size){
		this(size, false);
	}
	
	/**
	 * Inicializa matriz de cierta dimensión con opcion a zeros
	 * @param size
	 * @param zero
	 */
	SqMatrix(int size, boolean zero) {
		this();
		for (int i = 0; i < size; i++) {
			ArrayList<Float> temp = new ArrayList<Float>();
			for (int j = 0; j < size; j++) {
				if (zero) {
					temp.add(0f);
				}
				else {
					temp.add(INF);
				}
			}
			data.add(temp);
		}
		this.size = size;
	}
	
	/**
	 * Añade una columna y fila a la matriz
	 */
	public void scale_up() {
		size = size + 1;
		for (ArrayList<Float> arr : data) {
			arr.add(INF);
		}
		ArrayList<Float> temp = new ArrayList<Float>();
		for (int i = 0; i < size; i++) {
			temp.add(INF);
		}
		data.add(temp);
	}
	
	/**
	 * Establece un valor a algún espacio
	 * @param i fila
	 * @param j columna
	 * @param e valor
	 */
	public void set(int i, int j, float e) {
		i--;
		j--;
		ArrayList<Float> temp = data.get(i);
		temp.set(j, e);
		data.set(i, temp);
	}
	
	/**
	 * Obtiene el valor de algún espacio
	 * @param i fila
	 * @param j columna
	 * @return valor
	 */
	public Float get(int i, int j) {
		i--;
		j--;
		return data.get(i).get(j);
	}
	
	/**
	 * Genera un array de valores máximos por columna
	 * @return array
	 */
	private ArrayList<Float> maxArray() {
		ArrayList<Float> maxArr = new ArrayList<Float>();
		for (int i=0; i<size; i++) {
			float max = nINF;
			for (ArrayList<Float> arr : data) {
				if (max < arr.get(i)) {
					max = arr.get(i);
				}
			}
			maxArr.add(max);
		}
		return maxArr;
		
	}
	
	/**
	 * Genera el índice mínimo de el maxArray
	 * @return índice
	 */
	public int argmin() {
		ArrayList<Float> maxArr = maxArray();
		float min = INF;
		int id = 0;
		for (int i=0; i<maxArr.size(); i++) {
			if (maxArr.get(i) < min) {
				min = maxArr.get(i);
				id = i + 1;
			}
		}
		return id;
	}
	
	/**
	 * Genera una copia
	 * @return copia
	 */
	public SqMatrix copy() {
		SqMatrix temp = new SqMatrix(size, false);
		for (int i=1; i<=size; i++) {
			for (int j=1; j<=size; j++) {
				temp.set(i,  j, get(i, j));
			}
		}
		return temp;
	}
	

	/**
	 * Elimina una fila y columna
	 * @param i fila y columna
	 */
	public void deleteRowCol(int i) {
		data.remove(i);
		for (ArrayList<Float> arr : data) {
			arr.remove(i);
		}
	}
	
	/**
	 * Genera String con la matriz
	 */
	@Override
	public String toString() {
		String txt = "";
		for (ArrayList<Float> arr : data) {
			txt += "[";
			for (Float f : arr) {
				if (f == INF) {
					txt += "INF,\t";
				} else {
					txt += f.toString() + ",\t";
				}
			}
			txt = txt.substring(0, txt.length() - 2);
			txt += "]\n";
		}
		return txt;
	}
}
