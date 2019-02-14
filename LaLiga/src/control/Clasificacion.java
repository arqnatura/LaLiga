package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import modelo.Equipo;
import modelo.Partido;

public class Clasificacion {

	
	public Equipo buscarEquipoEnLista (String nombreCorto, ArrayList<Equipo> equipos)
	{
		Equipo resultado;
		for (Equipo equipo : equipos) {
			if (equipo.getNombreCorto().equals(nombreCorto));
					return equipo;
		}			
		System.out.println("Oooops algo falla");
		return null;
	}
	
	
	public void actualizaEquipos(Partido partido, ArrayList<Equipo> equipos) {
		String nCortoL=partido.geteL();
		String nCortoV=partido.geteV();
		Equipo eL = buscarEquipoEnLista(nCortoL, equipos);
		Equipo eV = buscarEquipoEnLista(nCortoV, equipos);
		
				
		/*
		 * if (gL.compareTo(gV) > 0) {// gana Local equipos.get(eL).set(0,
		 * equipos.get(eL).get(0) + 1); equipos.get(eV).set(2, equipos.get(eV).get(2) +
		 * 1);
		 * 
		 * } else if (gL.compareTo(gV) < 0) // gana Visitante {// gana Local
		 * equipos.get(eL).set(2, equipos.get(eL).get(2) + 1); equipos.get(eV).set(0,
		 * equipos.get(eV).get(0) + 1); } else { // empate
		 * 
		 * equipos.get(eL).set(1, equipos.get(eL).get(1) + 1); equipos.get(eV).set(1,
		 * equipos.get(eV).get(1) + 1); }
		 */
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
				if (partido == null) // ultimo partido jugado..
					break;
				// actualiza lista Equipos
				actualizaEquipos(partido, resultado);
			}
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
		
		ArrayList<Equipo> clasificacion = ejercicios.generaClasificacion("ficheros/partidos.txt", "ficheros/equipos.txt");
		System.out.println(clasificacion);

		System.out.println("Fin del programa");
	}

}
