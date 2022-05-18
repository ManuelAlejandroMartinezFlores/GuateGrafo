import java.util.Scanner;


public class Vista {
	
	Scanner sc;
	
	Vista(){
		sc = new Scanner(System.in);
	}
	
	
	public String getPath() {
		mostrarMsg("\nIngrese path del archivo con el grafo\n");
		String s = sc.nextLine();
		return s;
	}
	
	
	public void mostrarMsg(String txt) {
		System.out.println(txt);
	}
	
	
	public int getOpcion() {
		mostrarMsg("\nIngrese el número acorde a la acción que desea realizar\n1 - Obtener ruta más corta\n2 - Obtener centro\n3 - Mostrar grafo\n4 - Indicar interrupción de tráfico\n5 - Indicar nueva conexión\n6 - Salir\n");
		int opcion = 0;
		while (opcion < 1 || opcion > 6) {
			try {
				opcion = sc.nextInt();
			} catch (Exception e) {
				sc.nextLine();
				mostrarMsg("\nIngrese un valor válido\n");
				opcion = 0;
			}
		}
		return opcion;
	}
	
	public String getCiudadOrigen() {
		mostrarMsg("\nIngrese ciudad de origen");
		sc.nextLine();
		return sc.nextLine();
	}
	
	public String getCiudadDest() {
		mostrarMsg("\nIngrese ciudad de destino");
		return sc.nextLine();
	}
	
	public float getDistancia() {
		mostrarMsg("\nIngrese distancia en KM");
		while (true) {
			try {
				return sc.nextFloat();
			} catch (Exception e) {
				mostrarMsg("\nIngrese dato válido");
				return getDistancia();
			}
		}
	}
}

