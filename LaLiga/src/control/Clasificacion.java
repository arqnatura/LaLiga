package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Equipo;
import modelo.Partido;

public class Clasificacion {
	
	//Dado un equipo mostrar sus jugadores
	
	public void leerObjetosEquipos() {
		ObjectInputStream objetos = null;
		try {
			objetos = new ObjectInputStream(new FileInputStream("ficheros/equipos.obj"));

			while (true) {
				 Equipo equipo = (Equipo) objetos.readObject();		
				 System.out.println(equipo.getNombre());
			}

		} catch (FileNotFoundException e) {
			System.out.println("error1");
		} catch (IOException e) {
			System.out.println("Fin de la lectura");
			try {
				objetos.close();
			} catch (IOException e1) {

			}
		} catch (ClassNotFoundException e) {
			System.out.println("clase no encontrada");
		} catch (java.lang.ClassCastException e) {
			System.out.println("Casting imposible");
		}

	}	
		
	
	public void crearFicheroObjetoEquipos (String rutaEquipos) {
		
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader("ficheros/equipos.txt"));	
			FileOutputStream salida = new FileOutputStream ("ficheros/equipos.obj");
			ObjectOutputStream objetos = new ObjectOutputStream(salida);

			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				Equipo equipo = new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]);
				equipo.setPuntos(0);
				equipo.setGc(0);
				equipo.setGf(0);
				equipo.setPe(0);
				equipo.setPg(0);
				equipo.setPp(0);

			}
			fichero.close();
			System.out.println("Fin de la lectura del fichero");

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		
	}

	/*
	 * public void grabarTiradasDado (int cuantas) { //"TiradasDado.txt" //abrir
	 * FICHERO DE SALIDA try { BufferedWriter fichero; fichero = new
	 * BufferedWriter(new FileWriter("ficheros/TiradasDado.txt")); int acum=0; for
	 * (int i=0; i<cuantas; i++ ) { int numero=1 + rnd.nextInt(6); acum+=numero;
	 * fichero.write(numero+"\n");
	 * 
	 * System.out.printf("media = %.2f", (floatargs)acum/cuantas);
	 * System.out.println("Proceso terminado."); } } catch (IOException ex) {
	 * System.out.println("Error I/O " + ex.getMessage()); }
	 * System.out.println("Fin de entrada de datos."); }
	 */
	
	
	public void entradaTecladoAFichero(String rutaFichero) {
		
		try {
			BufferedWriter fichero;
			fichero = new BufferedWriter(new FileWriter(rutaFichero));
			Scanner teclado = new Scanner(System.in);
			
			String tecleado;
			System.out.println("Teclee sus datos...(x|X) para terminar");
			while ((tecleado = teclado.nextLine()).compareToIgnoreCase("x")!=0) {
			System.out.println("Teclee sus datos...(x|X) para terminar");
			fichero.write(tecleado+"\n");	
			}
			fichero.close();
			
		} catch (IOException ex) {
			System.out.println("Error I/O " + ex.getMessage());
		}
		System.out.println("Fin de entrada de datos.");
		
	}
	
	
	
	public void muestraClasificacion() {
		JFrame ventana;
		ventana = new JFrame("Clasificacion");

		JPanel panel = new JPanel();
		ventana.add(panel);

		ArrayList<Equipo> equipos = this.generaClasificacion("ficheros/partidos.txt", "ficheros/equipos.txt");
		
		String[] columnas= {"EQUIPO","PUNTOS","PJ","PG","PE","PP","GF","GC"};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

		modelo.addRow(columnas);
		for (Equipo equipo : equipos) {
			Object[] vector = { equipo.getNombre(), 
					equipo.getPuntos(),
					equipo.getPj(), equipo.getPg(), equipo.getPe(),
					equipo.getPp(), equipo.getGf(), equipo.getGc() };
			modelo.addRow(vector);
		}
		JTable tabla = new JTable(modelo);
		panel.add(tabla);
		ventana.pack();
		ventana.setVisible(true);
	}
	
				//Se puede representar en SCENE BUILDER para presentaciones en XML de los resultados Clasificacion
	
	/*
	 * public Equipo buscarEquipoEnLista (String nombreCorto, ArrayList<Equipo>
	 * equipos) { Equipo resultado; for (Equipo equipo : equipos) { if
	 * (equipo.getNombreCorto().equals(nombreCorto)); return equipo; }
	 * System.out.println("Oooops algo falla"); return null; }
	 */
	
	public Equipo buscarEquipoEnLista(String nombreCorto, ArrayList<Equipo> equipos) {
		Equipo resultado;
		for (Equipo equipo : equipos) {
			if (equipo.getNombreCorto().equals(nombreCorto))
				return equipo;
		}
		System.out.println("Ooops.. algo falla");
		return null;
	}
	
	public void actualizaEquipos(Partido partido, ArrayList<Equipo> equipos) {
		String nCortoL=partido.geteL();
		String nCortoV=partido.geteV();
		Equipo eL = buscarEquipoEnLista(nCortoL, equipos);
		Equipo eV = buscarEquipoEnLista(nCortoV, equipos);
		
					// logica del resultado del partido, se suman puntos y goles
		
		if (partido.getgL() > partido.getgV())
		{
			eL.setPuntos(eL.getPuntos()+3);
			eL.setPg(eL.getPg()+1);
			eV.setPp(eV.getPp()+1);
		}
		else
			if (partido.getgL() < partido.getgV())
			{
				eV.setPuntos(eV.getPuntos()+3);
				eV.setPg(eV.getPg()+1);
				eL.setPp(eL.getPp()+1);
			}
			else
			{
				eV.setPuntos(eV.getPuntos()+1);
				eL.setPuntos(eL.getPuntos()+1);
				eV.setPe(eV.getPe()+1);
				eL.setPe(eL.getPe()+1);
			}
						// los goles se suman siempre independiente de quie haya ganado o perdido
		eL.setGf(eL.getGf() + partido.getgL());
		eL.setGc(eL.getGc() + partido.getgV());
		
		eV.setGf(eV.getGf() + partido.getgV());
		eV.setGc(eV.getGc() + partido.getgL());
		
		eL.setPj(eL.getPj()+1);
		eV.setPj(eV.getPj()+1);
	}

	public Partido creaPartido(String linea) {
		Partido partido = new Partido();
		String[] campos = linea.split("#");
		partido.setId(Integer.parseInt(campos[0]));
		partido.setJornada(Integer.parseInt(campos[1]));
		partido.seteL(campos[2]);		
		partido.seteV(campos[4]);

		try {
			partido.setgL(Integer.parseInt(campos[3]));
			partido.setgV(Integer.parseInt(campos[5]));
		} catch (NumberFormatException e) {
			return null;
		}

		return partido;
	}
	
	public ArrayList<Equipo> generaClasificacion(String rutaPartidos, String rutaEquipos) {
		ArrayList<Equipo> resultado;
		try {
														// crear lista equipos desde fichero equipos.txt
			resultado = crearListaEquipos(rutaEquipos);
														//
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaPartidos));
			String registro;
			Partido partido;
			while ((registro = fichero.readLine()) != null) {
				partido = creaPartido(registro);
				if (partido == null) 					// ultimo partido jugado..
					break;
														// actualiza lista Equipos
				actualizaEquipos(partido, resultado);
			}
			Collections.sort(resultado, null);
			fichero.close();
			return resultado;
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
				equipo.setGc(0);
				equipo.setGf(0);
				equipo.setPe(0);
				equipo.setPg(0);
				equipo.setPp(0);
				equipo.setPuntos(0);
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
		Clasificacion ejercicios = new Clasificacion();
		
		// ArrayList<Equipo> eqOrdenados = ejercicios.crearListaEquipos("ficheros/equipos.txt");
		// System.out.println("\n" + eqOrdenados);
		
		//ArrayList<Equipo> clasificacion = ejercicios.generaClasificacion("ficheros/partidos.txt", "ficheros/equipos.txt");
		//System.out.println(clasificacion);
		
		// ArrayList <Equipo> clasificacion = ejercicios.generaClasificacion("ficheros/partidos.txt", "ficheros/equipos.txt");
		
		// ejercicios.muestraClasificacion();

		// ejercicios.entradaTecladoAFichero("ficheros/teclado.txt");
		
		// ejercicios.grabarTiradasDado(10);
		
		// ejercicios.leerObjetosEquipos();
		// ejercicios.muestraClasificacion();
		ejercicios.crearFicheroObjetoEquipos("ficheros/equipos.obj");
		System.out.println("Fin del programa");
	}

}
