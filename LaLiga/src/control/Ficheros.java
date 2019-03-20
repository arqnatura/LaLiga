package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;

public class Ficheros {

	// Este método recorre recursivamente directorios
	
	public static void traverse(File parentNode, String leftIndent) {
	    if (parentNode.isDirectory()) {
	        System.out.println(leftIndent + parentNode.getName());

	        leftIndent += "     ";

	        File childNodes[] = parentNode.listFiles();
	        for (File childNode : childNodes) {
	            traverse(childNode, leftIndent);
	        }
	    } else {

	        System.out.println(leftIndent +"|   --> "+ parentNode.getName());

	    }
	}
	
	
	
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
	
	public static void escrituraFicheros2 () {

		String[] lineas = { "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "..." };

		/** FORMA 2 DE ESCRITURA. Con el fichero codificado en UTF-8 **/
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("fichero_escritura2.txt"), "UTF-8"));
			
			// Escribimos linea a linea en el fichero
			for (String linea : lineas) {
				try {
					out.write(linea+"\n");
				} catch (IOException ex) {
					System.out.println("Mensaje excepcion escritura: " + ex.getMessage());
				}
			}

		} catch (UnsupportedEncodingException | FileNotFoundException ex2) {
			System.out.println("Mensaje error 2: " + ex2.getMessage());
		} finally {
			try {
				out.close();
			} catch (IOException ex3) {
				System.out.println("Mensaje error cierre fichero: " + ex3.getMessage());
			}
		}
	
	}
	
	
		
	public static void main(String[] args) {
		Ficheros ejercicio = new Ficheros();
		
		//Recorre el diectorio
	    //	File inputFolder = new File("c:/");
	    //	traverse(inputFolder, "");
		
		
		// ejercicio.lecturaFicheros2();
		// ejercicio.escrituraFicheros2();
	}

}
