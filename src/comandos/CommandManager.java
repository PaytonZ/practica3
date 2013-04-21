﻿package comandos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import persona.Ciclista;
import principal.CiclistaManager;

import vista.Ventana;
import entradaDeDatos.SuperLectura;

/**
 * 
 * Esta clase convierte los strings que se le introduce como mensaje en órdenes
 * que el sistema pueda entender.
 * 
 * @author Juan Luis Perez
 * @author Juan Carlos Marco
 * @author Emilio Alvarez
 * 
 */

public class CommandManager {

    ArrayList<InterfazCommand> array;
    private String num_ciclista;
    private InterfazCommand instruccion;
    String cadena;
    StringTokenizer comandosYatributos;
    String comandoElegido;
    Vector<Object> vector;

    /**
     * clase capaz de dado un string, formatearlo de tal forma, para ejecutar un
     * comando.
     * 
     * @param manager
     */
    public CommandManager(Vector<Object> mivector) {
	instruccion = new ComandoNinguno();
	vector = mivector;
	array = new ArrayList<InterfazCommand>();
	// añadimos los objetos de cada tipo de comando que tenemos

	array.add(new ComandoAsignaCadencia(null, 0));
	array.add(new ComandoAyuda());
	array.add(new ComandoBajaPinon(null));
	array.add(new ComandoBajaPlato(null));
	array.add(new ComandoDesconocido());
	array.add(new ComandoFrenar(null, 0, 0));
	array.add(new ComandoIncompleto());
	array.add(new ComandoNinguno());
	array.add(new ComandoSubePinon(null));
	array.add(new ComandoSubePlato(null));

    }

    /**
     * Este metodo, formatea una entrada en ordenes capaces de ser entendidas El
     * formato de los comandos es el siguiente: comando parametro donde comando
     * es una instruccion creada en el sistema y el parametro se refiere al
     * numero de ciclista al que va destinada esa orden
     * 
     * @param comando
     * @return
     */
    public InterfazCommand compruebaComando(String cadena) {

	comandosYatributos = new StringTokenizer(cadena, "\n\r ");

	// comprobamos si existe el comando
	if (existeCommand()) {

	    asignaAtributos(comandoElegido, comandosYatributos);
	} else {
	    asignaAtributos("desconocido", comandosYatributos);
	}

	return null;

    }

    /**
     * se comprueba si existe el comando , en el array de objetos de comando que
     * tenemos
     * 
     * @return true si existe, false en caso contrario
     */
    public boolean existeCommand() {

	Iterator<InterfazCommand> it = array.iterator();
	String comando = "";
	boolean encontrado = false;
	// cogemos el primer token que deberia corresponder al comando
	if (comandosYatributos.hasMoreTokens()) {
	    comando = comandosYatributos.nextToken();
	}
	// recorremos el arraylist de objetos comando, y comprobamos que existe
	// de ser asi, le asignamos a la variable comandoElegido, el comando que
	// queremos asignar
	while (it.hasNext() && !encontrado) {
	    InterfazCommand comand = (InterfazCommand) it.next();

	    if (comand.parse(comando)) {
		comandoElegido = comando;
		encontrado = true;
	    }

	}
	return encontrado;
    }

    /**
     * dado un comando, se le asignan los atributos necesarios para poder
     * ejecutarlo, si no se le pueden asignar, el comando resultante sera,
     * comando incompleto.
     * 
     * @param comando
     * @param args
     */
    public void asignaAtributos(String comando, StringTokenizer args) {
	instruccion = new ComandoIncompleto();

	switch (comando) {
	case "asignacadencia": {
	    if (args.countTokens() == 2) {
		int numciclista = Integer.parseInt(args.nextToken());
		String auxpar1 = args.nextToken();

		double param1 = 0;

		if (auxpar1.equalsIgnoreCase("+")) {
		    auxpar1 = ((Ciclista) vector.elementAt(numciclista))
			    .getCadencia();
		    param1 = Double.parseDouble(auxpar1) + 1;
		} else if (auxpar1.equalsIgnoreCase("-")) {
		    auxpar1 = ((Ciclista) vector.elementAt(numciclista))
			    .getCadencia();
		    param1 = Double.parseDouble(auxpar1) - 1;

		} else {
		    param1 = Double.parseDouble(auxpar1);
		}
		instruccion = new ComandoAsignaCadencia(
			(Ciclista) vector.elementAt(numciclista), (int) param1);

	    }
	    break;
	}
	case "ayuda": {
	    if (args.countTokens() == 0) {

		instruccion = new ComandoAyuda();
	    }

	    break;
	}
	case "bajapinon": {
	    if (args.countTokens() == 1) {

		int numciclista = Integer.parseInt(args.nextToken());
		instruccion = new ComandoBajaPinon(
			(Ciclista) vector.elementAt(numciclista));

	    }

	    break;
	}
	case "bajaplato": {
	    if (args.countTokens() == 1) {

		int numciclista = Integer.parseInt(args.nextToken());
		instruccion = new ComandoBajaPlato(
			(Ciclista) vector.elementAt(numciclista));

	    }

	    break;
	}
	case "desconocido": {
	    instruccion = new ComandoDesconocido();

	    break;
	}
	case "frenar": {
	    if (args.countTokens() == 3) {

		int numciclista = Integer.parseInt(args.nextToken());
		int par1 = Integer.parseInt(args.nextToken());
		int par2 = Integer.parseInt(args.nextToken());
		instruccion = new ComandoFrenar(
			(Ciclista) vector.elementAt(numciclista), par1, par2);

	    }

	    break;
	}
	case "incompleto": {
	    instruccion = new ComandoIncompleto();

	    break;
	}
	case "ninguno": {
	    instruccion = new ComandoNinguno();

	    break;
	}
	case "subepinon": {
	    if (args.countTokens() == 1) {
		int numciclista = Integer.parseInt(args.nextToken());
		instruccion = new ComandoSubePinon(
			(Ciclista) vector.elementAt(numciclista));

	    }

	    break;
	}
	case "subeplato": {
	    if (args.countTokens() == 1) {

		int numciclista = Integer.parseInt(args.nextToken());
		instruccion = new ComandoSubePlato(
			(Ciclista) vector.elementAt(numciclista));

	    }

	    break;
	}
	}
	instruccion.execute();
    }

    /**
     * retorna la informacion del comando dado.
     * 
     * @return
     */
    public String getInstruccion() {

	return instruccion.getInformacionInstruccion();

    }
}

// public class CommandManager {
//
// CiclistaManager cm;
// ArrayList<InterfazCommand> lista_de_comandos;
//
// public CommandManager(CiclistaManager new_cm)
// {
// cm=new_cm;
// lista_de_comandos.add(new ComandoAsignaCadencia(null, 0));
// lista_de_comandos.add(new ComandoAyuda());
// lista_de_comandos.add(new ComandoBajaPinon(null));
// lista_de_comandos.add(new ComandoBajaPlato(null));
// lista_de_comandos.add(new ComandoDesconocido());
// lista_de_comandos.add(new ComandoFrenar(null, 0, 0));
// lista_de_comandos.add(new ComandoIncompleto());
// lista_de_comandos.add(new ComandoNinguno());
// lista_de_comandos.add(new ComandoSubePinon(null));
// lista_de_comandos.add(new ComandoSubePlato(null));
//
// }
// public InterfazCommand dameComand(String comando)
// {
//
// for(InterfazCommand i: lista_de_comandos)
// {
// if(i.parse(comando)){
//
// return i;
// }
// }
// return null;
//
// }
//
//
