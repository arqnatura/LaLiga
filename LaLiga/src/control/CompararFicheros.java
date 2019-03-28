package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CompararFicheros {
	
	//27 marzo de 2019
	// Mezcla de dos ficheros de texto.  Ordenados previamente por un campo (CLAVE)
	
	
	public String leeRegistro (BufferedReader fichero) throws IOException {
		String registro = fichero.readLine();
		if(registro == null)					// se ha llegado al EOF fin del fichero
			return "ZZZZZZ";
		return registro;
	}
	
	
	private void mezclaFicherosOrdenados (String rutaF1, String rutaF2, String rutaF3) throws IOException {
		// abrir ficheros de entrada
		BufferedReader f1 = new BufferedReader(new FileReader(rutaF1));
		BufferedReader f2 = new BufferedReader(new FileReader(rutaF2));
		// abrir fichero de salida (escritura)
		BufferedWriter f3 = new BufferedWriter (new FileWriter(rutaF3));
		// Leemos el primer registro de cada fichero
		String registro1 = leeRegistro(f1);
		String registro2 = leeRegistro(f2);
		// abrimos el bucle que rastrea los archivos por las claves que están en la primera columna.
		// 
		while (true) {
			String k1 = registro1.split("#")[0];
			String k2 = registro2.split("#")[0];
			if  ((k1.compareTo(k2)) < 0) {
				f3.write(registro1);
				registro1 = leeRegistro(f1);
			} else {
				f3.write(registro2);
				registro2 = leeRegistro(f2);
			}
			
			
		}
		
		
		
		
	}
	
	
	
	public CompararFicheros() {

		
		
	}
	
	

}
