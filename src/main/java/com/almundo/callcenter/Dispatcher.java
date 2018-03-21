package com.almundo.callcenter;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase encargada de orquestar las llamadas entrantes.
 * 
 * @author Evillalba
 */
public class Dispatcher {

	private final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

	private ExecutorService executorService;
	private BlockingQueue<Empleado> empleados = new PriorityBlockingQueue<>();

	/**
	 * Recibe la lista de empleados y los ingresa a una cola de prioridad.
	 * 
	 * @param empleados
	 * @param nThreads
	 */
	public Dispatcher(List<Empleado> empleados, int nThreads) {
		this.executorService = Executors.newFixedThreadPool(nThreads);
		this.empleados.addAll(empleados);
	}

	/**
	 * Recibe y asigna la llamada telefónica de acuerdo a la disponibilidad de
	 * empleados
	 *
	 * @param llamadaTelefonica
	 * 
	 */
	public void dispatchCall(LllamadaTelefonica llamadaTelefonica) {
		executorService.submit(() -> asignarLlamada(llamadaTelefonica));
	}

	/**
	 * Asigna la llamada a un empleado disponible segun su prioridad
	 * 
	 * @param llamadaTelefonica
	 * @throws InterruptedException
	 */
	private void asignarLlamada(LllamadaTelefonica llamadaTelefonica) {
		Empleado empleado = null;
		try {
			empleado = empleados.take();
			empleado.atenderllamada(llamadaTelefonica);

		} catch (InterruptedException  e) {
			logger.error("Error al asignar la llamada", e);
		    Thread.currentThread().interrupt();
		} finally {
			liberarEmpleado(empleado);
		}
	}

	/**
	 * Agrega un empleado disponible a la lista de empleados
	 * 
	 * @param empleado
	 */
	private void liberarEmpleado(Empleado empleado) {
		if (empleado != null) {
			empleados.add(empleado);
		}
	}

	/**
	 * @return executorService
	 */
	public ExecutorService getExecutorService() {
		return executorService;
	}

	/**
	 * 
	 * @param empleados
	 */
	public void setEmpleados(BlockingQueue<Empleado> empleados) {
		this.empleados = empleados;
	}

}
