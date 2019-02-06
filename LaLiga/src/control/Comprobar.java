package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Comprobar {


		// 22 enero 2019
		// Comprueba fichero Partidos jugados

		public static HashMap<String, Integer> comprobarPartidos(String rutaFichero) {
			try {
				HashMap<String, Integer> mapaEquipos;
				mapaEquipos = new HashMap<String, Integer>();
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaFichero));
				String registro;
				while ((registro = fichero.readLine()) != null) {
					String[] campos = registro.split("#");
					for (int i = 2; i < campos.length; i += 2) {
						if (mapaEquipos.containsKey(campos[i])) {
							mapaEquipos.replace(campos[i], (mapaEquipos.get(campos[i]) + 1));
						} else {
							mapaEquipos.put(campos[i], 1);
						}
					}

				}
				fichero.close();
				return mapaEquipos;

			} catch (FileNotFoundException excepcion) {
				System.out.println("fichero no encontrado");

			} catch (IOException e) {
				System.out.println("IO Excepcion");
			}
			return null;
		}	
		
		// 10 enero 2019
		// Lee en pantalla el fichero seleccionado

		public void leerFichero(String rutaFichero) {
			try {
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaFichero));
				String registro;
				while ((registro = fichero.readLine()) != null) {
					System.out.println(registro);

					// ................

				}
				fichero.close();
				System.out.println("Fin de la lectura del fichero");

			} catch (FileNotFoundException excepcion) {
				System.out.println("fichero no encontrado");

			} catch (IOException e) {
				System.out.println("IO Excepcion");
			}
		}

		
		

	public static void main(String[] args) {
		Comprobar ejercicios = new Comprobar();

		// ejercicios.introListas();
		// ejercicios.introMapas();
	ejercicios.leerFichero("ficheros/equipos.txt");
		// ArrayList<Persona> listaPersonas =
		// ejercicios.creaListaPersonas("ficheros/personas.txt", "##");
		// HashMap<String, Integer> equipos =
		// HashMap<String, Integer> x =
		// HashMap<String, Integer> x =
	ejercicios.comprobarPartidos("ficheros/partidos.txt");
		// ArrayList<Equipo> equipos =
		// ArrayList<Equipo> x = ejercicios.crearListaEquipos("ficheros/equipos.txt");
		// HashMap<String, Equipo> equipos =
		// ejercicios.crearMapaEquipos("ficheros/equipos.txt");
		// ejercicios.mostrarNumeroPartidosJugadosTry("ficheros/partidos.txt");

		
		
		

	}

}
