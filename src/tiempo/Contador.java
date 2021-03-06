package tiempo;

/**
 * Esta clase representa un contador estandar de un intervalo de tiempo
 * variable.
 * 
 * @author Juan Carlos Marco y Juan Luis Pérez
 * 
 */
public class Contador

{

    /**
     * @uml.property name="unidad"
     */
    private int unidad = 0;

    /**
     * Este metodo devuelve el valor de la unidad
     * 
     * @return unidad
     * @uml.property name="unidad"
     */
    public int getUnidad() {
	return unidad;
    }

    /**
     * Este metodo incrementa la variable unidad + el parametro que se le
     * introduzca
     * 
     * @param miunidad
     */
    public void incremento(int miunidad) {
	unidad = unidad + miunidad;
    }

    /**
     * este metodo devuelve 1 si la unidad es igual al limite y 0 si no lo es
     * 
     * @param limite
     *            que se quiere establecer
     * @return 1 cuando se alcanza el limite
     */
    public int pasoUnidad(int limite) {
	int carry = 0;
	if (unidad == limite) {
	    unidad = 0;
	    carry = 1;
	} else {
	    carry = 0;
	}
	return carry;
    }

}