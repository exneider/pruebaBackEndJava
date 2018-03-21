package com.almundo.callcenter;

/**
 * @author Evillalba
 *
 */
public class LllamadaTelefonica {

	private int numeroTelefono;

	private long duracion;

	private boolean asignada;

	private long duracionMinima;

	private long duracionMaxima;
	
	private Empleado empleado;

	/**
	 * @param numeroTelefono
	 * @param duracionMinima
	 * @param duracionMaxima
	 */
	public LllamadaTelefonica(int numeroTelefono, long duracionMinima, long duracionMaxima) {
		this.numeroTelefono = numeroTelefono;
		this.duracionMinima = duracionMinima;
		this.duracionMaxima = duracionMaxima;
	}

	/**
	 * @return numeroTelefono
	 */
	public int getNumeroTelefono() {
		return numeroTelefono;
	}


	/**
	 * @return duracion
	 */
	public long getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion
	 */
	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return asignada
	 */
	public boolean isAsignada() {
		return asignada;
	}

	/**
	 * @param asignada
	 */
	public void setAsignada(boolean asignada) {
		this.asignada = asignada;
	}

	/**
	 * @return duracionMinima
	 */
	public long getDuracionMinima() {
		return duracionMinima;
	}


	/**
	 * @return duracionMaxima
	 */
	public long getDuracionMaxima() {
		return duracionMaxima;
	}

	/**
	 * 
	 * @return empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * 
	 * @param empleado
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
