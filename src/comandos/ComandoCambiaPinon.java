package comandos;

import java.util.StringTokenizer;

import persona.Ciclista;
import principal.Presentador;
import constantes.Constantes;

public class ComandoCambiaPinon implements InterfazCommand {
    private Ciclista ciclista;
    private int identificador_ciclista;
    private int pinon;

    public ComandoCambiaPinon(int nuevo_identificador_ciclista, int nuevo_pinon) {

	identificador_ciclista = nuevo_identificador_ciclista;
	pinon = nuevo_pinon;
    }

    @Override
    // Bicicleta n cambia piñon x
    public InterfazCommand parse(String nombre) {

	int id_ciclista;
	int nuevo_pinon;
	InterfazCommand c = null;

	String[] atributos = nombre.split("\\s");
	if (atributos.length >= 4) {
	    if (atributos[0].equalsIgnoreCase("bicicleta")) {
		id_ciclista = Integer.parseInt(atributos[1]);
		if (atributos[2].equalsIgnoreCase("cambia")
			&& atributos[3].equalsIgnoreCase("pinon")) {
		    nuevo_pinon = Integer.parseInt(atributos[4]);
		    if (nuevo_pinon >= 0
			    && nuevo_pinon < Constantes.NUM_PINONES) {
			c = new ComandoCambiaPinon(id_ciclista, nuevo_pinon);
		    }
		}
	    }
	}
	return c;

    }

    @Override
    public void execute() {

	ciclista.cambiaPinon(pinon);

    }

    @Override
    public void configurarContexto(Presentador presentador) {
	ciclista = presentador.getCiclista(identificador_ciclista);

    }

    @Override
    public String obtenerAyuda() {
	return "bajapinon <num_ciclista>";
	// TODO Auto-generated method stub

    }

    @Override
    public String getInformacionInstruccion() {
	// TODO Auto-generated method stub
	return "Pinon cambiado en el ciclista "
		+ ciclista.getIdentificador_ciclista() + "\n pinon actual :"
		+ ciclista.getPinonActualBici();
    }

}
