package comandos;

import java.util.StringTokenizer;

import persona.Ciclista;
import principal.Presentador;

public class ComandoBajaPlato implements InterfazCommand {
    Ciclista ciclista;
    int identificador_ciclista;

    public ComandoBajaPlato(int nuevo_identificador_ciclista) {

	identificador_ciclista = nuevo_identificador_ciclista;
    }

    @Override
    public InterfazCommand parse(String nombre) {

	InterfazCommand c = null;
	StringTokenizer comandosYatributos = new StringTokenizer(nombre,
		"\n\r ");
	if (comandosYatributos.nextToken().equalsIgnoreCase("bajaplato")) {
	    if (comandosYatributos.countTokens() == 1) {

		int numciclista = Integer.parseInt(comandosYatributos
			.nextToken());

		c = new ComandoBajaPlato(numciclista);
	    } else {
		c = new ComandoIncompleto(this.obtenerAyuda());
	    }
	}
	return c;
    }

    @Override
    public void execute() {

	ciclista.disminuyePlato();

    }

    @Override
    public void configurarContexto(Presentador presentador) {
	ciclista = presentador.getCiclista(identificador_ciclista);

    }

    @Override
    public String obtenerAyuda() {
	return "bajaplato <num_ciclista>";
	// TODO Auto-generated method stub

    }

    @Override
    public String getInformacionInstruccion() {
	// TODO Auto-generated method stub
	return "plato bajado en el ciclista"
		+ ciclista.getIdentificador_ciclista() + "\n plato actual :"
		+ ciclista.getPlatoActualBici();
    }

}
