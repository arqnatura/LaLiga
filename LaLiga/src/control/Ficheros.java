package control;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Ficheros {

	
	public void lecturaFicheros () {
		
	// Fichero del que queremos leer
	File fichero = new File("ficheros/equipos.txt");
	Scanner s = null;

	try {
		// Leemos el contenido del fichero
		System.out.println("... Leemos el contenido del fichero ...");
		s = new Scanner(fichero);

		// Leemos linea a linea el fichero
		while (s.hasNextLine()) {
			String linea = s.nextLine(); 	// Guardamos la linea en un String
			System.out.println(linea);      // Imprimimos la linea
		}

	} catch (Exception ex) {
		System.out.println("Mensaje: " + ex.getMessage());
	} finally {
		// Cerramos el fichero tanto si la lectura ha sido correcta o no
		try {
			if (s != null)
				s.close();
		} catch (Exception ex2) {
			System.out.println("Mensaje 2: " + ex2.getMessage());
		}
	}
}
	
	public void escrituraFicheros () {
	
	String[] lineas = { "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "..." };

	/** FORMA 1 DE ESCRITURA **/
	FileWriter fichero;
	try {

		fichero = new FileWriter("ficheros/fichero_escritura.txt");

		// Escribimos linea a linea en el fichero
		for (String linea : lineas) {
			fichero.write(linea + "\n");
		}

		fichero.close();

	} catch (Exception ex) {
		System.out.println("Mensaje de la excepción: " + ex.getMessage());
	}
}
	
	
		
	public static void main(String[] args) {
		Ficheros ejercicio = new Ficheros();
		
		// ejercicio.lecturaFicheros();

		ejercicio.escrituraFicheros();
	}

}
