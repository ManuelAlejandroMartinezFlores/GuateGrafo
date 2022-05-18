/**
 * @author Manuel Alejandro Martínez Flores
 * 
 * App.
 * 
 * Clase controladora que ejecuta todo el programa.
 */

public class App {

	
	public static void main(String[] args) {
		Graph guate = new Graph();
		Vista vista = new Vista();
		while (true) {
			try {
				guate = Archivos.leer(vista.getPath());
				break;
			} catch (Exception e) {
				vista.mostrarMsg("\nIngrese path valido\n");
			}
		}
		vista.mostrarMsg(guate.toString());
		
		int opcion = 0;
		
		while (opcion != 6) {
			opcion = vista.getOpcion();
				
			switch (opcion) {
				case 1:
					try {
						String from = vista.getCiudadOrigen();
						String to = vista.getCiudadDest();
						vista.mostrarMsg("\n" + guate.shortestPath(from, to));
					} catch (Exception e) {
						vista.mostrarMsg("\nNo se encontraron las ciudades\n");
					}
					break;
					
				case 2:
					vista.mostrarMsg(guate.centre());
					break;
					
				case 3:
					vista.mostrarMsg(guate.toString());
					break;
					
				case 4:
					try {
						String from = vista.getCiudadOrigen();
						String to = vista.getCiudadDest();
						guate.deleteEdge(from, to);
						vista.mostrarMsg(guate.centre());
					} catch (Exception e) {
						vista.mostrarMsg("\nNo se encontraron las ciudades");
					}
					break;
				
				case 5:
					try {
						String from = vista.getCiudadOrigen();
						String to = vista.getCiudadDest();
						float dist = vista.getDistancia();
						guate.addEdge(from, to, dist);
						vista.mostrarMsg(guate.centre());
					} catch (Exception e) {
						vista.mostrarMsg("\nNo se encontraron las ciudades");
					}
					break;
					
				case 6:
					vista.mostrarMsg("\nCerrando");
			}
		}
		
		
	}
}
