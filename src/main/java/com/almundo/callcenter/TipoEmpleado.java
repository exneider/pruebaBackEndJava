package com.almundo.callcenter;

/**
 * 
 * @author Evillalba
 *
 */
public enum TipoEmpleado {
	/**
	 * Tipo empeado OPERADOR con prioridad 1
	 */
	OPERADOR(1),
	/**
	 * Tipo empeado SUPERVISOR con prioridad 2
	 */
	SUPERVISOR(2),
	/**
	 * Tipo empeado DIRECTOR con prioridad 3
	 */
	DIRECTOR(3);
	private int prioridad;

	TipoEmpleado(int prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * @return prioridad
	 */
	public int getPrioridad() {
		return prioridad;
	}
}
