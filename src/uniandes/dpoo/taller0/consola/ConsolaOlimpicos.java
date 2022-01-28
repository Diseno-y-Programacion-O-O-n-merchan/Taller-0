package uniandes.dpoo.taller0.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.taller0.modelo.Atleta;
import uniandes.dpoo.taller0.modelo.Genero;
import uniandes.dpoo.taller0.procesamiento.CalculadoraEstadisticas;
import uniandes.dpoo.taller0.procesamiento.LoaderOlimpicos;

public class ConsolaOlimpicos
{
	/**
	 * Esta es la calculadora de estadÃ­sticas que se usarÃ¡ para hacer todas las
	 * operaciones de la aplicaciÃ³n. Esta calculadora tambiÃ©n contiene toda la
	 * informaciÃ³n sobre los atletas despuÃ©s de que se cargue desde un archivo.
	 */
	private CalculadoraEstadisticas calculadora;

	/**
	 * Ejecuta la aplicaciÃ³n: le muestra el menÃº al usuario y la pide que ingrese
	 * una opciÃ³n, y ejecuta la opciÃ³n seleccionada por el usuario. Este proceso se
	 * repite hasta que el usuario seleccione la opciÃ³n de abandonar la aplicaciÃ³n.
	 */
	public void ejecutarAplicacion()
	{
		System.out.println("EstadÃ­sticas sobre los Juegos OlÃ­mpicos\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opciÃ³n"));
				if (opcion_seleccionada == 1)
					ejecutarCargarAtletas();
				else if (opcion_seleccionada == 2 && calculadora != null)
					ejecutarAtletasPorAnio();
				else if (opcion_seleccionada == 3 && calculadora != null)
					ejecutarMedallasEnRango();
				else if (opcion_seleccionada == 4 && calculadora != null)
					ejecutarAtletasPorPais();
				else if (opcion_seleccionada == 5 && calculadora != null)
					ejecutarPaisConMasMedallistas();
				else if (opcion_seleccionada == 6 && calculadora != null)
					ejecutarMedallistasPorEvento();
				else if (opcion_seleccionada == 7 && calculadora != null)
					ejecutarAtletasConMasMedallasQue();
				else if (opcion_seleccionada == 8 && calculadora != null)
					ejecutarAtletaEstrella();
				else if (opcion_seleccionada == 9 && calculadora != null)
					ejecutarMejorPaisEnUnEvento();
				else if (opcion_seleccionada == 10 && calculadora != null)
					ejecutarTodoterreno();
				else if (opcion_seleccionada == 11 && calculadora != null)
					ejecutarMedallistasPorNacionYGenero();
				else if (opcion_seleccionada == 12 && calculadora != null)
					ejecutarPorcentajeMedallistas();
				else if (opcion_seleccionada == 13 && calculadora != null)
					ejecutarPaisAtleta();
				else if (opcion_seleccionada == 14)
				{
					System.out.println("Saliendo de la aplicaciÃ³n ...");
					continuar = false;
				}
				else if (calculadora == null)
				{
					System.out.println("Para poder ejecutar esta opciÃ³n primero debe cargar un archivo de atletas.");
				}
				else
				{
					System.out.println("Por favor seleccione una opciÃ³n vÃ¡lida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los nÃºmeros de las opciones.");
			}
		}
	}

	/**
	 * Muestra al usuario el menÃº con las opciones para que escoja la siguiente
	 * acciÃ³n que quiere ejecutar.
	 */
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicaciÃ³n\n");
		System.out.println("1. Cargar un archivo de atletas");
		System.out.println("2. Consultar los atletas de un aÃ±o dado");
		System.out.println("3. Consultar las medallas de un atleta en un periodo");
		System.out.println("4. Consultar los atletas de un paÃ­s dado");
		System.out.println("5. Consultar el paÃ­s con mÃ¡s medallistas");
		System.out.println("6. Consultar todos los medallistas de un evento dado");
		System.out.println("7. Consultar los atletas con un mÃ­nimo de medallas");
		System.out.println("8. Consultar el atleta estrella de todos los tiempos");
		System.out.println("9. Consultar mejor paÃ­s en un evento");
		System.out.println("10. Consultar el atleta todoterreno");
		System.out.println("11. Consultar los medallistas por paÃ­s y gÃ©nero");
		System.out.println("12. Consultar el porcentaje de atletas que son medallistas");
		System.out.println("13. Consultar el país de un atleta");
		System.out.println("14. Salir de la aplicaciÃ³n\n");
	}
	/**
	 * Le muestra el usuario el pais que representa el atleta
	 */
	private void ejecutarPaisAtleta()
	{
		System.out.println("\n" + "Pais que representa un atleta" + "\n");

		String nombreAtleta = input("Por favor ingrese el nombre del atleta").toLowerCase();
		Atleta AtletaInfo = calculadora.BuscarInfoAtleta(nombreAtleta);

		if (AtletaInfo != null)
		{
			System.out.println("El atleta " + nombreAtleta + " representa a " + AtletaInfo.darPais().darNombre());
		}
		else
		{
			System.out.println("El atleta no fue encontrado");
		}
	}
	/**
	 * Le muestra el usuario el porcentaje de atletas que son medallistas
	 */
	private void ejecutarPorcentajeMedallistas()
	{
		System.out.println("\n" + "Porcentaje de atletas que son medallistas" + "\n");

		double porcentaje = calculadora.porcentajeMedallistas();
		double redondeado = (double) ((int) (porcentaje * 100.0)) / 100.0;
		System.out.println("El porcentaje de atletas que son medallistas es el " + redondeado + "%");
	}

	/**
	 * Le pide el usuario el nombre de un paÃ­s y un gÃ©nero, y luego le muestra la
	 * informaciÃ³n de los medallistas de ese gÃ©nero que han representado a ese paÃ­s.
	 */
	private void ejecutarMedallistasPorNacionYGenero()
	{
		System.out.println("\n" + "Medallistas por paÃ­s y gÃ©nero" + "\n");

		String pais = input("Por favor ingrese el nombre de un pais");
		String genero = input("Por favor ingrese M para consultar hombres y F para consultar mujeres").toLowerCase();

		if (!"m".equals(genero) && !"f".equals(genero))
		{
			System.out.println("SÃ³lo puede seleccionar M o F");
		}
		else
		{
			Genero elGenero = genero.equals("m") ? Genero.MASCULINO : Genero.FEMENINO;
			Map<String, List<Map<String, Object>>> medallistas = calculadora.medallistasPorNacionGenero(pais, elGenero);

			if (medallistas != null)
			{
				for (Map.Entry<String, List<Map<String, Object>>> entry : medallistas.entrySet())
				{
					String nombre_atleta = entry.getKey();
					List<Map<String, Object>> medallas = entry.getValue();

					System.out.println("El atleta " + nombre_atleta + " ha ganado " + medallas.size() + " medallas.");
					for (Map<String, Object> medalla : medallas)
					{
						System.out.println("Evento: " + medalla.get("evento") + " (" + medalla.get("anio") + ")");
						System.out.println("    Medalla: " + medalla.get("medalla"));
					}
				}
			}
			else
			{
				System.out.println("No se encontraron medallistas del paÃ­s ingresado.");
			}
		}
	}

	/**
	 * Le informa al usuario si hay un atleta todoterreno y la cantidad de deportes
	 * diferentes en los que ha participado
	 */
	private void ejecutarTodoterreno()
	{
		System.out.println("\n" + "Atleta todoterreno" + "\n");

		Atleta todoterreno = calculadora.buscarAtletaTodoterreno();
		System.out.println("El atleta todoterreno es: " + todoterreno.darNombre());
		System.out.println("Ha participado en " + todoterreno.contarDeportes() + " deportes diferentes");
	}

	/**
	 * Le pide al usuario el nombre de un evento y luego le informa cuÃ¡l es el mejor
	 * paÃ­s en ese evento.
	 */
	private void ejecutarMejorPaisEnUnEvento()
	{
		System.out.println("\n" + "Mejor paÃ­s en un evento" + "\n");

		String evento = input("Por favor ingrese el nombre de un evento");
		Map<String, int[]> mejores = calculadora.mejorPaisEvento(evento);
		if (mejores.size() == 0)
		{
			System.out.println("No se encontrÃ³ informaciÃ³n sobre el evento: " + evento);
		}
		else if (mejores.size() == 1)
		{
			String pais = mejores.keySet().iterator().next();
			System.out.println("El mejor paÃ­s en " + evento + " es " + pais + ":");
			int[] medallas = mejores.get(pais);
			System.out.println(
					"Ha gando: " + medallas[0] + " oros, " + medallas[1] + " platas, " + medallas[2] + " bronces.");
		}
		else
		{
			System.out.println("Hay un empate en " + evento + ":");
			for (Map.Entry<String, int[]> entry : mejores.entrySet())
			{
				int[] medallas = entry.getValue();
				System.out.println(entry.getKey() + "ha gando: " + medallas[0] + " oros, " + medallas[1] + " platas, "
						+ medallas[2] + " bronces.");
			}
		}
	}

	/**
	 * Muestra cuÃ¡les han sido los atletas (o el atleta) que mÃ¡s medallas ha ganado.
	 */
	private void ejecutarAtletaEstrella()
	{
		System.out.println("\n" + "Atleta estrella de todos los tiempos" + "\n");

		Map<String, Integer> estrellas = calculadora.atletaEstrella();
		for (Map.Entry<String, Integer> entry : estrellas.entrySet())
		{
			String nombre = entry.getKey();
			int medallas = entry.getValue();
			System.out.println(nombre + " ganÃ³ " + medallas + " medallas");
		}

	}

	/**
	 * Le pide al usuario una cantidad mÃ­nima de medallas y luego le muestra la
	 * informaciÃ³n de los atletas que han ganado mÃ¡s de esa cantidad de medallas.
	 */
	private void ejecutarAtletasConMasMedallasQue()
	{
		System.out.println("\n" + "Atletas con mÃ­nimo de medallas" + "\n");

		try
		{
			int cantidadMinima = Integer.parseInt(input("Ingrese la cantidad mÃ­nima de medallas"));
			Map<String, Integer> atletas = calculadora.atletasConMasMedallas(cantidadMinima);
			System.out.println(
					"Hay " + atletas.size() + " atletas que han ganado mÃ¡s de " + cantidadMinima + " medallas.");
			for (Map.Entry<String, Integer> entry : atletas.entrySet())
			{
				String nombre = entry.getKey();
				int medallas = entry.getValue();
				System.out.println(nombre + " ganÃ³ " + medallas + " medallas");
			}
		}
		catch (NumberFormatException nfe)
		{
			System.out.println("El nÃºmero ingresado no es vÃ¡lido. Por favor escriba un nÃºmero entero.");
		}
	}

	/**
	 * Le pide al usuario el nombre de un evento y muestra los atletas que han sido
	 * medallistas en ese envento.
	 */
	private void ejecutarMedallistasPorEvento()
	{
		System.out.println("\n" + "Medallistas de un evento" + "\n");

		String evento = input("Por favor ingrese el nombre de un evento");
		List<Atleta> medallistas = calculadora.medallistasPorEvento(evento);
		System.out.println("Los medallistas de " + evento + "son:");
		int num = 1;
		for (Atleta atleta : medallistas)
		{
			System.out.println("" + num + ". - " + atleta.darNombre());
			num++;
		}
	}

	/**
	 * Consulta el paÃ­s (o los paÃ­ses) con mÃ¡s medallistas
	 */
	private void ejecutarPaisConMasMedallistas()
	{
		System.out.println("\n" + "PaÃ­s con mÃ¡s medallistas" + "\n");

		Map<String, Integer> paises = calculadora.paisConMasMedallistas();
		if (paises.size() > 1)
		{
			System.out.println("Hay " + paises.size() + " paÃ­ses empatados en el primer lugar.");
		}
		for (String nombre : paises.keySet())
		{
			System.out.println(nombre + " ha tenido " + paises.get(nombre) + " medallistas");
		}
	}

	/**
	 * Le pide al usuario el nombre de un paÃ­s y luego le muestra la informaciÃ³n de
	 * todos los atletas de ese paÃ­s.
	 */
	private void ejecutarAtletasPorPais()
	{
		System.out.println("\n" + "Atletas de un paÃ­s" + "\n");

		String pais = input("Por favor ingrese el nombre de un pais");
		List<Map<String, Object>> atletas = calculadora.atletasPorPais(pais);
		if (atletas == null)
		{
			System.out.println("No existe un paÃ­s con ese nombre");
		}
		else
		{
			for (Map<String, Object> datos : atletas)
			{
				String nombre = (String) datos.get("nombre");
				String evento = (String) datos.get("evento");
				int anio = (int) datos.get("anio");
				System.out.println(" - " + evento + " en " + anio + " --> " + nombre);
			}
		}
	}

	/**
	 * Le pide al usuario un rango de aÃ±os y el nombre de un atleta. A continuaciÃ³n
	 * le muestra al usuario todas las medallas ganadas por el atleta en ese rango
	 * de aÃ±os.
	 */
	private void ejecutarMedallasEnRango()
	{
		System.out.println("\n" + "Medallas de un atleta en un periodo" + "\n");
		try
		{
			int anio_inicial = Integer.parseInt(input("Ingrese el aÃ±o inicial para el rango"));
			int anio_final = Integer.parseInt(input("Ingrese el aÃ±o final para el rango"));
			String nombre_atleta = input("Ingrese el nombre del atleta que le interesa");
			List<Map<String, Object>> medallas = calculadora.medallasEnRango(anio_inicial, anio_final, nombre_atleta);
			if (medallas == null)
			{
				System.out.println("No se encontrÃ³ un atleta llamado " + nombre_atleta);
			}
			else
			{
				System.out.println("El atleta " + nombre_atleta + " ha ganado " + medallas.size() + " medallas.");
				for (Map<String, Object> medalla : medallas)
				{
					System.out.println("Evento: " + medalla.get("evento") + " (" + medalla.get("anio") + ")");
					System.out.println("    Medalla: " + medalla.get("medalla"));
				}
			}
		}
		catch (NumberFormatException nfe)
		{
			System.out.println("El nÃºmero ingresado no es vÃ¡lido. Por favor escriba un nÃºmero entero.");
		}
	}

	/**
	 * Ejecuta la opciÃ³n para consultar los atletas de un aÃ±o.
	 */
	private void ejecutarAtletasPorAnio()
	{
		System.out.println("\n" + "Medallas de un atleta en un periodo" + "\n");

		int anio = Integer.parseInt(input("Ingrese el aÃ±o de su interÃ©s"));
		Map<String, List<Atleta>> atletas = calculadora.atletasPorAnio(anio);
		System.out.println("Se encontraron " + atletas.size() + " atletas");
		for (String deporte : atletas.keySet())
		{
			System.out.println(deporte + ": " + atletas.get(deporte).size() + " atletas");
		}
	}

	/**
	 * Este mÃ©todo le pide al usuario el nombre de un archivo con informaciÃ³n de los
	 * atletas, lo carga usando la clase LoaderOlimpicos y crea un objeto de tipo
	 * CalculadoraEstadisticas para que sea usado por las otras opciones de la
	 * consola.
	 */
	private void ejecutarCargarAtletas()
	{
		System.out.println("\n" + "Cargar un archivo de atletas" + "\n");

		String archivo = input("Por favor ingrese el nombre del archivo CSV con los atletas");
		try
		{
			calculadora = LoaderOlimpicos.cargarArchivo(archivo);
			System.out.println("Se cargÃ³ el archivo " + archivo + " con informaciÃ³n de los Juegos OlÃ­mpicos.");
			Collection<String> eventos = calculadora.darNombresDeportes();
			System.out.println("Los deportes para los que se tiene informaciÃ³n son:");
			for (String dep : eventos)
			{
				System.out.println(" - " + dep);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontrÃ³.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Este mÃ©todo sirve para imprimir un mensaje en la consola pidiÃ©ndole
	 * informaciÃ³n al usuario y luego leer lo que escriba el usuario.
	 * 
	 * @param mensaje El mensaje que se le mostrarÃ¡ al usuario
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Este es el mÃ©todo principal de la aplicaciÃ³n, con el que inicia la ejecuciÃ³n
	 * de la aplicaciÃ³n
	 * 
	 * @param args ParÃ¡metros introducidos en la lÃ­nea de comandos al invocar la
	 *             aplicaciÃ³n
	 */
	public static void main(String[] args)
	{
		ConsolaOlimpicos consola = new ConsolaOlimpicos();
		consola.ejecutarAplicacion();
	}

}
