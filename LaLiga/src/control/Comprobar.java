package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;

public class Comprobar {
	
	//13 de marzo de 2019
	
	//crear una lista de jugadores, vacia.
	//Recorrer secuencialmente el fichero
	//crear el objeto jugador por cada registro del fichero
	//añadir jugador a la lista.
	//al terminar el fichero devolver la lista
	//Devolver null, si hay cualquier excepción
	
	public ArrayList<Jugador> creaListaJugadores (String rutaJugadores)
	{
		ArrayList<Jugador> lista = new ArrayList<Jugador>();
		
			try {
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaJugadores));
				String registro;
			
					while ((registro = fichero.readLine()) != null) {
						String[] campos = registro.split("#");
						Jugador e = new Jugador();
						e.setId(Integer.parseInt(campos[0]));
						e.setNombre(campos[2]);
						e.setDorsal(Integer.parseInt(campos[6]));
						e.setIdEquipo(Integer.parseInt(campos[7]));
						lista.add(e);
					}
					fichero.close();
					System.out.println("Fin lectura fichero");
			
					System.out.println(lista);
					
					
			} catch (NumberFormatException e) {
				System.out.println("Excepción Formato");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException");
				e.printStackTrace();
			}		

				
		return lista;
	}
	
	
	
	
	// Clave-> valor K->V
	// La clave es el nif.
	//crear un HashMap, vacia.
		//recorre secuencialmente el fichero
		//crear el objeto jugador por cada registro del fichero
		//añadir jugador al mapa, usando el nif como clave.
		//al terminar el fichero devolver el mapa
		//Devolver null, si hay cualquier excepción
	
	public HashMap<String, Jugador> creaMapaJugadores (String rutaJugadores){
		HashMap<String, Jugador> mapa = new HashMap<String, Jugador>();
		
		
		
		
		
		return mapa;
	}
	
	
	
	
	
		
		// equipo es el nombre corto XYZ, RMA
		// Recorrer la LISTA de equipos.txt, preguntando si el nombre corto coincide con alguno...
		// ArrayList <Equipo>
		
	
	/*public Equipo buscarEquipo (String equipo, ArrayList<Equipo> equipos) {
		Equipo resultado;
		for (Equipo equipo : equipos) {
			if (equipo.getNombreCorto().equals(equipo))
				return equipo;
		}
		System.out.println("Ooops.. algo falla");
		return null;
	}		
		*/

		
		//12 de marzo de 2019	
	
		// Modificar crearMapaEquipos para que devuelva una lista
		// ArrayList<equipo>
		// El fichero a leer se llama equipos.txt
	
	public ArrayList<Equipo> crearListaEquipos(String rutaEquipos) {
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
			try {
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaEquipos));
					String registro;
					
						while ((registro = fichero.readLine()) != null) {
							String[] campos = registro.split("#");
							Equipo e = new Equipo();
							e.setId(Integer.parseInt(campos[0]));
							e.setNombreCorto(campos[1]);
							e.setNombre(campos[2]);
							e.setGc(0);
							e.setGf(0);
							e.setPe(0);
							e.setPg(0);
							e.setPp(0);
							equipos.add(e);
						}
						fichero.close();
						System.out.println("Fin lectura fichero");		
						return equipos;
				} catch (FileNotFoundException e) {
					System.out.println(" fichero no encontrado");	
			} catch (IOException e) {
				System.out.println(" IOException");	
			}
			return null;

	}
	
	public Partido creaPartido(String linea) {
		Partido partido = new Partido();
		String[] campos = linea.split("#");
		partido.setId(Integer.parseInt(campos[0]));
		partido.setJornada(Integer.parseInt(campos[1]));
		partido.seteL(campos[2]);		
		partido.seteV(campos[4]);

		return partido;
	}
	
	
	
	// 12 de marzo de 2019
	// Prueba de límites. Crear una busqueda binaria de un fichero previamente ordenado.
	
	// Utilizamos la fórmula ((valorDerecho-valorIzquierdo)/2)+valorDercho es igual al valor medio
	// así vamos buscando en la midad de la mitad
	
	
	/*public int busquedaBinaria(int aguja, int [] pajar) {
		int izq = 0;
		int der = pajar.length -1 ;	
		
		while (izq <= der) {
			int med= (der - izq)/2 + izq;
			if (pajar [med] == aguja)  // encontrado
				}
			if (pajar [med] == aguja) {
				
			}
		System.out.println(" ENCONTRADO" + AGUJA + "EN POSICION" + MED);
				return med;
		
		
	}
	*/
	

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
//	ejercicios.leerFichero("ficheros/equipos.txt");
		// ArrayList<Persona> listaPersonas =
		// ejercicios.creaListaPersonas("ficheros/personas.txt", "##");
		// HashMap<String, Integer> equipos =
		// HashMap<String, Integer> x =
		// HashMap<String, Integer> x =
//	ejercicios.comprobarPartidos("ficheros/partidos.txt");
		// ArrayList<Equipo> equipos =
		// ArrayList<Equipo> x = ejercicios.crearListaEquipos("ficheros/equipos.txt");
		// HashMap<String, Equipo> equipos =
		// ejercicios.crearMapaEquipos("ficheros/equipos.txt");
		// ejercicios.mostrarNumeroPartidosJugadosTry("ficheros/partidos.txt");

/*	int [] pajar= {12,45,46,57,78,90,99,120};
	int aguja=12;
	ejercicios.busquedaBinaria(99, pajar);*/
		
	// ejercicios.crearListaEquipos("ficheros/equipos.txt");
		
		
		ejercicios.creaListaJugadores("ficheros/Jugadores.txt");
	

	}

}
