package factoresExternos;

import interfaceMain.InterfaceEjecuta;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import persona.Ciclista;
import constantes.Constantes;

/**
 * 
 * @author Juan Carlos Marco
 * @author Juan Luis Perez
 * @author Emilio Alvarez Pinerio
 * 
 */
public class Pendiente implements InterfaceEjecuta {

    private TreeMap<Integer, Integer> arbol;
    private ArrayList<Ciclista> lista_de_ciclistas;

    /**
     * se recibe el vector de bicis
     * 
     * @param bicis
     * @param rel
     */

    public Pendiente(ArrayList<Ciclista> nueva_lista_de_ciclistas) {
	arbol = new TreeMap<Integer, Integer>();
	lista_de_ciclistas = nueva_lista_de_ciclistas;
    }

    /**
     * añade la curva a su mapaDeCurvas con su PK y su velocidad maxima
     * 
     * @param hora
     * @param tipo
     * @param velocidad
     */
    public void setPendiente(int punto_kilometrico, int pendiente) {
	arbol.put(punto_kilometrico, pendiente);

    }

    public void setArbol(TreeMap<Integer,Integer> ar)
    {
	arbol = ar;
    }
    
    public TreeMap<Integer,Integer> getArbol()
    {
	return arbol;
    }
    @Override
    public void ejecuta() {

	/**
	 * si la hora actual, esta en nuestro mapa de vientos, asignaremos el
	 * viento a las bicicletas
	 */
	int i = 0;
	for (Ciclista c : lista_de_ciclistas)
	{
	  if(c.getIdentificador_ciclista() == 0)
	  {
	        Iterator<Entry<Integer, Integer>> it = arbol.entrySet().iterator();
		Iterator<Entry<Integer, Integer>> itaux = arbol.entrySet().iterator();
		if (it.hasNext()) {
		    itaux.next();
		}
		while (itaux.hasNext()) 
		{

		    Entry<Integer, Integer> elemento = it.next();
		    double espacio_recorrido = c.getBici().getEspacioRecorrido();

		    if (it.hasNext()) 
		    {

			Entry<Integer, Integer> elemento2 = itaux.next();

			
			if (espacio_recorrido >= elemento.getKey()
				    && espacio_recorrido < elemento2.getKey()) {
				// si la pendiente es positiva, nos afectara
				// negativamente
				if (elemento.getValue() > 0) {
				    c.getBici().setFactorPendiente(
					    Constantes.FACTORPENDIENTE
						    * elemento.getValue());
				    System.out.println("sube");
				}
				// si la pendiente es negativa, nos afectara
				// positivamente
				else if (elemento.getValue() < 0) {
				    c.getBici().setFactorPendiente(
					    Constantes.FACTORPENDIENTE
						    * elemento.getValue());
				    System.out.println("baja");
				}
				// si la pendiente es 0, no afecta
				else {
				    c.getBici().setFactorPendiente(0);
				    System.out.println("nada");
				}
			    }
			} else {
			    // comprobamos en que metro nos encontramos del recorrido,
			    // si hemos pasado el punto donde empienza la pendiente,
			    // esta nos empezara a afectar positiva o negativamente
			    if (espacio_recorrido >= elemento.getKey()) {
				// si la pendiente es positiva, nos afectara
				// negativamente
				if (elemento.getValue() > 0) {
				    c.getBici().setFactorPendiente(
					    Constantes.FACTORPENDIENTE
						    * elemento.getValue());
				}
				// si la pendiente es negativa, nos afectara
				// positivamente
				else if (elemento.getValue() < 0) {
				    c.getBici().setFactorPendiente(
					    Constantes.FACTORPENDIENTE
						    * elemento.getValue());

				}
				// si la pendiente es 0, no afecta
				else {
				    c.getBici().setFactorPendiente(0);

				}
			    }

			
		    }

		}	
	}
	}

    }
}
