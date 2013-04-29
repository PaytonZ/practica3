package principal;

import factoresExternos.Curva;
import factoresExternos.Pendiente;
import factoresExternos.Viento;
import interfaceMain.InterfaceEjecuta;
import interfaceMain.InterfaceSalida;

import java.util.ArrayList;

import persona.Ciclista;
import tiempo.Reloj;
import vista.Lienzo;
import vista.SalidaDeDatosPorSwing;
import vista.Ventana;
import bicicleta.Bicicleta;

import comandos.Comandero;
import comandos.Parser;

import constantes.Constantes;

/**
 * Clase Principal del programa
 * 
 * @author Juan Carlos Marco
 * @author Juan Luis Perez
 * @author Emilio Alvarez Pinerio
 * 
 */

public class CiclistaManager {

    private ArrayList<InterfaceEjecuta> listaejecuta;
    private ArrayList<InterfaceSalida> listasalida;
    private Comandero comandero;

    private SalidaDeDatosPorSwing output;
    private Curva curva;
    private Viento viento;
    private Pendiente pendiente;
    private Reloj reloj;

    /*
     * java CiclistaManager.java numero_de_ciclistas chero_de_comandos
     * medida_del_tiempo cambio_de_plato cambio_de_piñon radio_rueda
     */
    public static void main(String args[]) {

	CiclistaManager manager = new CiclistaManager();

	manager.inicia();
	manager.ejecuta();
	manager.finaliza();

    }

    private void inicia() {
	listaejecuta = new ArrayList<InterfaceEjecuta>();
	listasalida = new ArrayList<InterfaceSalida>();

	int dientesporpinon[] = { Constantes.NUM_PIN_0, Constantes.NUM_PIN_1,
		Constantes.NUM_PIN_2, Constantes.NUM_PIN_3,
		Constantes.NUM_PIN_4, Constantes.NUM_PIN_5 };
	int dientesporplato[] = { Constantes.NUM_PLA_0, Constantes.NUM_PLA_1,
		Constantes.NUM_PLA_2 };
	Bicicleta bici0 = new Bicicleta(dientesporpinon.length,
		dientesporplato.length, 1, dientesporpinon, dientesporplato,
		0.6858, Constantes.MASA_BICICLETA_ESTANDAR);
	Bicicleta bici1 = new Bicicleta(dientesporpinon.length,
		dientesporplato.length, 1, dientesporpinon, dientesporplato,
		0.6858, Constantes.MASA_BICICLETA_ESTANDAR);
	Bicicleta bici2 = new Bicicleta(dientesporpinon.length,
		dientesporplato.length, 1, dientesporpinon, dientesporplato,
		0.6858, Constantes.MASA_BICICLETA_ESTANDAR);
	Bicicleta bici3 = new Bicicleta(dientesporpinon.length,
		dientesporplato.length, 1, dientesporpinon, dientesporplato,
		0.6858, Constantes.MASA_BICICLETA_ESTANDAR);
	Bicicleta bici4 = new Bicicleta(dientesporpinon.length,
		dientesporplato.length, 1, dientesporpinon, dientesporplato,
		0.6858, Constantes.MASA_BICICLETA_ESTANDAR);
	Bicicleta bici5 = new Bicicleta(dientesporpinon.length,
		dientesporplato.length, 1, dientesporpinon, dientesporplato,
		0.6858, Constantes.MASA_BICICLETA_ESTANDAR);
	reloj = new Reloj();
	Ciclista ciclista0 = new Ciclista(bici0, 0);
	Ciclista ciclista1 = new Ciclista(bici1, 1);
	Ciclista ciclista2 = new Ciclista(bici2, 2);
	Ciclista ciclista3 = new Ciclista(bici3, 3);
	Ciclista ciclista4 = new Ciclista(bici4, 4);
	Ciclista ciclista5 = new Ciclista(bici5, 5);

	ArrayList<Ciclista> lista_de_ciclistas = new ArrayList<Ciclista>();

	lista_de_ciclistas.add(ciclista0);
	lista_de_ciclistas.add(ciclista1);
	lista_de_ciclistas.add(ciclista2);
	lista_de_ciclistas.add(ciclista3);
	lista_de_ciclistas.add(ciclista4);
	lista_de_ciclistas.add(ciclista5);

	viento = new Viento(lista_de_ciclistas, reloj);
	curva = new Curva(lista_de_ciclistas);
	pendiente = new Pendiente(lista_de_ciclistas);

	Presentador presentador = new Presentador(lista_de_ciclistas, reloj,
		curva, viento, pendiente);

	comandero = new Comandero(presentador, new Parser());
	Lienzo lienzo = new Lienzo(lista_de_ciclistas);
	Ventana ventana = new Ventana(comandero, lienzo);

	listaejecuta.add(reloj);
	listaejecuta.add(ciclista0);
	listaejecuta.add(ciclista1);
	listaejecuta.add(ciclista2);
	listaejecuta.add(ciclista3);
	listaejecuta.add(ciclista4);
	listaejecuta.add(ciclista5);
	listaejecuta.add(lienzo);
	listaejecuta.add(comandero);
	listaejecuta.add(ventana);
	listaejecuta.add(viento);
	listaejecuta.add(curva);
	listaejecuta.add(pendiente);

	listasalida.add(reloj);
	listasalida.add(ciclista0);
	listasalida.add(ciclista1);
	listasalida.add(ciclista2);
	listasalida.add(ciclista3);
	listasalida.add(ciclista4);
	listasalida.add(ciclista5);
	listasalida.add(comandero);

	output = new SalidaDeDatosPorSwing(ventana, listasalida);

    }

    private void ejecuta() {
	int contador = 0; // Contara los segundos de ejecucion del programa
	int limite = 300; // Se establecera el limite en SEGUNDOS de la
			  // ejecucion del programa

	while (contador < limite) {

	    for (InterfaceEjecuta c : listaejecuta) {
		c.ejecuta();
	    }

	    output.mostrarObjetos();

	    contador++;
	}
    }

    private void finaliza() {

    }

    public Ciclista getCiclista(int identificador) {
	Ciclista ciclista_a_devolver = null;
	for (InterfaceEjecuta c : listaejecuta)

	{
	    if (c instanceof Ciclista) {
		if (identificador == ((Ciclista) c).getIdentificador_ciclista()) {
		    ciclista_a_devolver = (Ciclista) c;
		}
	    }

	}
	return ciclista_a_devolver;

    }

    public Curva getCurva() {

	return curva;

    }

    public Pendiente getPendiente() {

	return pendiente;

    }

    public Reloj getReloj() {

	return reloj;

    }

    public Viento getViento() {

	return viento;

    }

}
