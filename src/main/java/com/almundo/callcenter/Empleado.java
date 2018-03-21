package com.almundo.callcenter;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representa el empleado, el cual es responsable de atender la llamada.
 * 
 * @author Evillalba
 * 
 */
public class Empleado implements Comparable<Empleado> {

	private final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

	private final long id;

	private TipoEmpleado tipo;

	/**
	 * @param tipo
	 * @param id
	 */
	public Empleado(TipoEmpleado tipo, long id) {
		this.tipo = tipo;
		this.id = id;
	}

	/**
	 * @return tipo
	 */
	public TipoEmpleado getTipo() {
		return tipo;
	}

	/**
	 * Prioridad de acuerdo al tipo de empleado
	 * 
	 * @return Prioridad
	 */
	private int getPrioridad() {
		return this.tipo.getPrioridad();
	}

	/**
	 * Este metodo atiende la llamada.
	 * 
	 * @param llamada
	 */
	public void atenderllamada(LllamadaTelefonica llamada) {
		try {
			llamada.setAsignada(true);
			llamada.setEmpleado(this);
			logger.info("llamada #TEL: {} : Asiganada al {} # {}", llamada.getNumeroTelefono(), tipo, id);
			llamada.setDuracion(obtenerRandomDuracionllamada(llamada.getDuracionMinima(), llamada.getDuracionMaxima()));
			TimeUnit.SECONDS.sleep(llamada.getDuracion());
			logger.info("llamada #TEL: {} Finalizada con Duracion de: {} seg : Atendida por el {} #{}",
					llamada.getNumeroTelefono(), llamada.getDuracion(), tipo, id);
		} catch (Exception e) {
			logger.error("Error al atenter la llamada", e);
		}
	}

	/**
	 * Calcula aleatoriamente la duracion de la llamada
	 * 
	 * @param minDuracion
	 * @param maxDuracion
	 * @return
	 */
	private long obtenerRandomDuracionllamada(long minDuracion, long maxDuracion) {
		return ThreadLocalRandom.current().nextLong(minDuracion, maxDuracion + 1);
	}

	@Override
	public int compareTo(Empleado empleado) {
		if (this.getPrioridad() < empleado.getPrioridad()) {
			return -1;
		}
		if (this.getPrioridad() > empleado.getPrioridad()) {
			return 1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Empleado other = (Empleado) obj;
 		return id == other.id && tipo == other.tipo;

	}

}
