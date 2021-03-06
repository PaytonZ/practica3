package comandos;

import principal.Presentador;

/**
 * Siguiendo el patrón command, se define una interfaz para los comandos
 * 
 * @author Juan Carlos Marco
 * @author Juan Luis Perez
 * @author Emilio Alvarez Pinerio
 */
public interface InterfazCommand {

    /**
     * Este metodo configura el contexto del comando ,es decir , a quien va
     * dirigido dicho comando. Para ello debe recibir la informacion del
     * presentador.
     * 
     * @param presentador
     *            La referencia del presentador.
     */
    public void configurarContexto(Presentador presentador);

    /**
     * Se provoca la ejecucion del comando en cuestion
     */
    public void execute();

    /**
     * Devuelve el retorno despues de la ejecucion del comando
     * 
     * @return Un string con los datos de salida
     * @uml.property name="informacionInstruccion"
     */
    public String getInformacionInstruccion();

    /**
     * Este metodo devuelve un string con la 'sintaxis' del comando
     * 
     * @return Un string con la sintaxis del comando
     */
    public String obtenerAyuda();

    /**
     * Este metodo recibe un string y si es valido , devuelve un comando valido
     * correctamente configurado E.O.C Devuelve null.
     * 
     * @param comando
     * @return Comando Valido.
     */
    public InterfazCommand parse(String comando);

}
