package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.Equipo;

public class LaLiga {
	
	// 7 de febrero 2019
	
	// Obtener datos para la CLASIFICACION con el método generaPuntosEquipos ya creado;
	// con el Nombre Largo con el método crearMapaEquipos ya creado
	
	
	// public HashMap<String, arrayList<Integer> crearMapaEquipos2(String rutaFichero) {

		
	
		
		
	//}

	
	
	// 6 de febrero 2019
	
	public ArrayList<Equipo> equiposListaOrdenadaId (String rutafichero)
	
	{
		ArrayList<Equipo> lista;
		lista = crearListaEquipos("ficheros/equipos.txt");
		lista.sort(new Comparator<Equipo>() {

			@Override
			public int compare(Equipo eq1, Equipo eq2) {
				if (eq1.getId() > eq2.getId())
				return 1;
				else if (eq1.getId() < eq2.getId())
				return -1;
				else;
				return 0;
			}
		});
		// System.out.println(" \n"+ lista);		
		return lista;
	}
	
	
	
	// 5 de febrero 2019
	
		// Obtener un ArrayList ORDENADA por nombre LAARgo del equipo
		// a partir de la lista obtenida en el método
		//  crearListaEquipos
		
	public ArrayList<Equipo> equiposListaOrdenadaNombre(ArrayList<Equipo> equipos)
		
	{
		ArrayList<Equipo> lista;
		lista = crearListaEquipos("ficheros/equipos.txt");
		lista.sort(new Comparator<Equipo>() {

			@Override
			public int compare(Equipo eq1, Equipo eq2) {

				return eq1.getNombre().compareTo(eq2.getNombre());
			}
		});
		return lista;
	}
		
	public void ordenarMapaPuntosEquipos (HashMap<String,Integer> puntosEquipos)
	{
		Set<Entry<String, Integer>> set = puntosEquipos.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        for(Map.Entry<String, Integer> entry:list){
            System.out.println(entry.getKey()+" ==== "+entry.getValue());
        }
	}

	// 31 enero 2019
	// Muestra la clasificación por puntos de los equipos en un archivo *.txt

	public void muestraClasificacionPuntosEquipos(HashMap<String, ArrayList<Integer>> resultados) {
		// recorrer el HashMap...
		// obtenemos la lista de claves (K)
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream ( new FileOutputStream ("ficheros/puntos.txt"));

			for (String clave : resultados.keySet()) {
				ArrayList<Integer> datos = resultados.get(clave);
				int puntos = datos.get(0) * 3 + datos.get(1);
				out.writeObject(clave + " => " + puntos + "\n" );
				}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("/n"+ resultados);
	}
	


	// 30 enero 2019

	public HashMap<String, Integer> generaPuntosEquipos(HashMap<String, ArrayList<Integer>> partidos_GEP) {
		HashMap<String, Integer> resultado = new HashMap<String, Integer>();
		for (String clave : partidos_GEP.keySet()) {
			ArrayList<Integer> datos = partidos_GEP.get(clave);
			int puntos = datos.get(0) * 3 + datos.get(1);
			resultado.put(clave, puntos);
		}
		return resultado;
	}
	
	// pruebita de SWING (MVC)

	public void pruebaSWING() {
		JFrame ventana;
		ventana = new JFrame("Mi primer SWING");
		JButton boton = new JButton("pulsaMe!");
		JPanel panel = new JPanel();
		ventana.add(panel);
		
		ArrayList<Equipo> equipos = this.crearListaEquipos("ficheros/equipos.txt");
		
		Equipo[] arrayEquipos = equipos.toArray(new Equipo[equipos.size()]);

		JComboBox lista = new JComboBox(arrayEquipos);
		JComboBox resultado = new JComboBox(arrayEquipos);
		panel.add(lista);
		panel.add(resultado);
		panel.add(boton);
		ventana.pack();
		ventana.setVisible(true);
	}

	
		
		// 29 enero 2019
		// Calcula los puntos por equipos...

		public void muestraPuntosEquipos(HashMap<String, ArrayList<Integer>> resultados) {
			
			// recorrer el HashMap...
			// obtenemos la lista de claves (K)
			
			for (String clave : resultados.keySet()) {
				ArrayList<Integer> datos = resultados.get(clave);
				int puntos = datos.get(0) * 3 + datos.get(1);
				System.out.println(clave + " => " + puntos);
				}
		}
		
		
		public HashMap<String, ArrayList<Integer>> resultadosEquipos(String rutaPartidos){
			
		// devuelve un mapa de equipos
		// por cada equipo hay una lista de contadores
		// que representan VICTORIAS, EMPATES Y DERROTAS			
			
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaPartidos));
			String registro;
			HashMap<String, ArrayList<Integer>> equipos = new HashMap<String, ArrayList<Integer>>();
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				if (campos[3].equals("")) 			// ultimo partido jugado..
					break;
				String eL = campos[2];
				String gL = campos[3];
				String eV = campos[4];
				String gV = campos[5];

				// gracias Byron..!!
				// si no existe eL, eV lo añadimos al mapa..

				if (!equipos.containsKey(eL))
					equipos.put(eL, new ArrayList<Integer>(Arrays.asList(0, 0, 0)));

				if (!equipos.containsKey(eV))
					equipos.put(eV, new ArrayList<Integer>(Arrays.asList(0, 0, 0)));

				// cual fue el resultado ..?

				if (gL.compareTo(gV) > 0) {// gana Local
					equipos.get(eL).set(0, equipos.get(eL).get(0) + 1);
					equipos.get(eV).set(2, equipos.get(eV).get(2) + 1);

				} else if (gL.compareTo(gV) < 0) // gana Visitante
				{// gana Local
					equipos.get(eL).set(2, equipos.get(eL).get(2) + 1);
					equipos.get(eV).set(0, equipos.get(eV).get(0) + 1);
				} else { // empate

					equipos.get(eL).set(1, equipos.get(eL).get(1) + 1);
					equipos.get(eV).set(1, equipos.get(eV).get(1) + 1);
				}

			}
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
			return equipos;

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;

	}
	
		// 23 enero 2019

		public void mostrarNumeroPartidosJugados(String rutaPartidos) {

			try {
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaPartidos));
				String registro;
				int contador = 0;
				while ((registro = fichero.readLine()) != null) {
					String[] campos = registro.split("#");
					if (!campos[3].equals("")) {
						Integer.parseInt(campos[3]);
						contador++;

					} else
						break;
				}
				fichero.close();
				System.out.println(contador);
				System.out.println("Fin de la lectura del fichero");

			} catch (FileNotFoundException excepcion) {
				System.out.println("fichero no encontrado");

			} catch (IOException e) {
				System.out.println("IO Excepcion");
			}
		}

		// Mapa de equipos

		public HashMap<String, Equipo> crearMapaEquipos(String rutaFichero) {
			try {
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaFichero));
				String registro;
				Equipo equipo = null;
				HashMap<String, Equipo> equipos = new HashMap<String, Equipo>();
				while ((registro = fichero.readLine()) != null) {
					String[] campos = registro.split("#");
					equipo = new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]);
					equipos.put(campos[1], equipo);
				}
				fichero.close();
				System.out.println("Fin de la lectura del fichero");
				return equipos;

			} catch (FileNotFoundException excepcion) {
				System.out.println("fichero no encontrado");

			} catch (IOException e) {
				System.out.println("IO Excepcion");
			}
			return null;
		}
		// lista de equipos

		public ArrayList<Equipo> crearListaEquipos(String rutaFichero) {
			try {
				BufferedReader fichero;
				fichero = new BufferedReader(new FileReader(rutaFichero));
				String registro;
				Equipo equipo = null;
				ArrayList<Equipo> equipos = new ArrayList<Equipo>();
				while ((registro = fichero.readLine()) != null) {
					String[] campos = registro.split("#");
					equipo = new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]);
					equipos.add(equipo);
				}
				fichero.close();
				System.out.println("Fin de la lectura del fichero");
				return equipos;

			} catch (FileNotFoundException excepcion) {
				System.out.println("fichero no encontrado");

			} catch (IOException e) {
				System.out.println("IO Excepcion");
			}
			return null;
		}
	
	
	public static void main(String[] args) {
		LaLiga ejercicios = new LaLiga();

		
//ArrayList<Equipo> ejecucion = ejercicios.equiposListaOrdenadaNombre (Equipo<nombre>);
		// ArrayList<Equipo> ejecucion = ejercicios.equiposListaOrdenadaId ("ficheros/equipos.txt");
		

		
		//ejercicios.crearMapaEquipos("ficheros/equipos.txt");
		
		ejercicios.pruebaSWING(); 									//30 de enero
		// HashMap<String, ArrayList<Integer>> x = ejercicios.resultadosEquipos("ficheros/partidos.txt");
		// ejercicios.muestraPuntosEquipos(x);
//ejercicios.ordenarMapaPuntosEquipos (x);
//ejercicios.mostrarNumeroPartidosJugadosTry("ficheros/partidos.txt");
		// ejercicios.muestraClasificacionPuntosEquipos(x);  			//29 de enero
		// ArrayList<Equipo> equipos =
		// ArrayList<Equipo> x = ejercicios.crearListaEquipos("ficheros/equipos.txt");
		//HashMap<String, Equipo> equipos =.................
//ejercicios.crearMapaEquipos("ficheros/equipos.txt");
		// ejercicios.mostrarNumeroPartidosJugadosTry("ficheros/partidos.txt");
		 ejercicios.crearListaEquipos("ficheros/equipos.txt");
//	System.out.println("mostrar por pantalla");		
	}

}
