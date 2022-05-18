
import java.util.ArrayList;

public class SqMatrix {

	ArrayList<ArrayList<Float>> data;
	final float INF = Float.POSITIVE_INFINITY;
	final float nINF = Float.NEGATIVE_INFINITY;
	int size = 0;
	
	SqMatrix(){
		data = new ArrayList<ArrayList<Float>>();
	}
	
	SqMatrix(int size){
		this(size, false);
	}
	
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
	
	public void set(int i, int j, float e) {
		i--;
		j--;
		ArrayList<Float> temp = data.get(i);
		temp.set(j, e);
		data.set(i, temp);
	}
	
	public Float get(int i, int j) {
		i--;
		j--;
		return data.get(i).get(j);
	}
	
	
	public ArrayList<Float> maxArray() {
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
	
	public SqMatrix copy() {
		SqMatrix temp = new SqMatrix(size, false);
		for (int i=1; i<=size; i++) {
			for (int j=1; j<=size; j++) {
				temp.set(i,  j, get(i, j));
			}
		}
		return temp;
	}
	
	
	private void deleteRow(int i) {
		data.remove(i);
	}
	
	private void deleteColumn(int i) {
		for (ArrayList<Float> arr : data) {
			arr.remove(i);
		}
	}
	
	public void deleteRowCol(int i) {
		deleteRow(i);
		deleteColumn(i);
	}
	
	
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
