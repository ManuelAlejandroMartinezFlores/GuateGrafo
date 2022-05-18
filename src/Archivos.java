/**
 * @author Manuel Alejandro Martínez Flores
 * 
 * Archivos.
 * 
 * Clase encargada de persitencia de datos.
 */

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Archivos {

	/**
	 * Lee y evalua expresiones en un archivo
	 * @param data nombre de archivo
	 * @throws IOException
	 */
	public static Graph leer(String path) throws IOException {		
		BufferedReader reader = new BufferedReader(new FileReader(path));
        String row;
        Graph g = new Graph();
        while ((row = reader.readLine()) != null){
        	String[] data = row.split(" ");
        	g.addEdge(data[0], data[1], Float.valueOf(data[2]));
        }
        reader.close();
        return g;
	}
}
