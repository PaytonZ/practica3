package comandos;

import persona.Ciclista;
import principal.Presentador;
import constantes.Constantes;

public class ComandoCambiaPinon implements InterfazCommand {
    /**
     * @uml.property name="ciclista"
     * @uml.associationEnd
     */
    private Ciclista ciclista;
    /**
     * @uml.property name="identificador_ciclista"
     */
    private int identificador_ciclista;
    /**
     * @uml.property name="pinon"
     */
    private int pinon;

    public ComandoCambiaPinon(int nuevo_identificador_ciclista, int nuevo_pinon) {

	identificador_ciclista = nuevo_identificador_ciclista;
	pinon = nuevo_pinon;
    }

    @Override
    public void configurarContexto(Presentador presentador) {

	ciclista = presentador.getCiclista(identificador_ciclista);

    }

    @Override
    public void execute() {

	if (pinon == Constantes.SUBIR) {
	    pinon = ciclista.getBici().getPinonAct();
	    if (pinon < Constantes.NUM_PINONES)
		ciclista.cambiaPinon(pinon + 1);
	} else if (pinon == Constantes.BAJAR) {
	    pinon = ciclista.getBici().getPinonAct();
	    if (pinon > 0)
		ciclista.cambiaPinon(pinon - 1);
	} else {
	    ciclista.cambiaPinon(pinon);
	}

    }

    @Override
    public String getInformacionInstruccion() {

	return "Pinon cambiado en el ciclista "
		+ ciclista.getIdentificador_ciclista() + "\n pinon actual :"
		+ ciclista.getPinonActualBici();
    }

    @Override
    public String obtenerAyuda() {
	return "bicicleta <num_bicicleta> cambia pinon <num_pinon>";

    }

    @Override
    // Bicicleta n cambia piñon x
    public InterfazCommand parse(String nombre) {

	int id_ciclista;
	int nuevo_pinon;
	InterfazCommand c = null;

	String[] atributos = nombre.split("\\s");
	if (atributos.length == 5
	&& atributos[0].equalsIgnoreCase("bicicleta")
	&& Integer.parseInt(atributos[1]) >= 0
	&& Integer.parseInt(atributos[1]) < Constantes.NUM_ACT_CICLISTAS
	&& atributos[2].equalsIgnoreCase("cambia")
	&& atributos[3].equalsIgnoreCase("pinon")) 
	{

		    nuevo_pinon = Integer.parseInt(atributos[4]);
		    id_ciclista = Integer.parseInt(atributos[1]);
		    if (nuevo_pinon >= 0
			    && nuevo_pinon < Constantes.NUM_PINONES) {
			c = new ComandoCambiaPinon(id_ciclista, nuevo_pinon);
		    }

		    if (nuevo_pinon == Constantes.SUBIR) {

			c = new ComandoCambiaPinon(id_ciclista,
				Constantes.SUBIR);
		    }

		    if (nuevo_pinon == Constantes.BAJAR) {

			c = new ComandoCambiaPinon(id_ciclista,
				Constantes.BAJAR);
		    }
	
	}
	return c;

    }

}
