package com.almundo.callcenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Evillalba
 *
 */
public class DispatcherTest {

	private static final int TIEMPO_MINIMO_LLAMADA = 5;
	private static final int TIEMPO_MAXIMO_LLAMADA = 10;
	private static final int TIEMPO_MAXIMO_ESPERA = 15;
	private static final int THREADS = 3;

	/**
	 * En esta prueba se espera que las tres llamadas sean atendida por los tres
	 * empelados según su prioridad (OPERADOR,SUPERVISOR,DIRECTOR). De forma
	 * concurrente
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void dispatchCall_NumeroDeThreadsIgualaNumeroDeEmpleados() throws InterruptedException {
		Dispatcher dispatcher;
		LllamadaTelefonica llamada1 = new LllamadaTelefonica(12, TIEMPO_MINIMO_LLAMADA, TIEMPO_MAXIMO_LLAMADA);
		LllamadaTelefonica llamada2 = new LllamadaTelefonica(33, TIEMPO_MINIMO_LLAMADA, TIEMPO_MAXIMO_LLAMADA);
		LllamadaTelefonica llamada3 = new LllamadaTelefonica(55, TIEMPO_MINIMO_LLAMADA, TIEMPO_MAXIMO_LLAMADA);

		Empleado supervisor1 = new Empleado(TipoEmpleado.SUPERVISOR, 3);
		Empleado operator2 = new Empleado(TipoEmpleado.OPERADOR, 2);
		Empleado operator1 = new Empleado(TipoEmpleado.DIRECTOR, 1);

		dispatcher = new Dispatcher(Arrays.asList(operator1, operator2, supervisor1), THREADS);

		dispatcher.dispatchCall(llamada1);
		dispatcher.dispatchCall(llamada2);
		dispatcher.dispatchCall(llamada3);

		dispatcher.getExecutorService().awaitTermination(TIEMPO_MAXIMO_ESPERA, TimeUnit.SECONDS);

		Assert.assertEquals(llamada1.getEmpleado().getTipo(), TipoEmpleado.OPERADOR);
		Assert.assertEquals(llamada2.getEmpleado().getTipo(), TipoEmpleado.SUPERVISOR);
		Assert.assertEquals(llamada3.getEmpleado().getTipo(), TipoEmpleado.DIRECTOR);

	}

	/**
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void dispatchCall_GeneraUnaInterruptedExceptionEnAsignarLlamada() throws InterruptedException {
		Dispatcher dispatcher;
		LllamadaTelefonica llamada1 = new LllamadaTelefonica(12, TIEMPO_MINIMO_LLAMADA, TIEMPO_MAXIMO_LLAMADA);
		BlockingQueue<Empleado> empleados = mock(PriorityBlockingQueue.class);
		when(empleados.take()).thenThrow(new InterruptedException());
		Empleado operator1 = new Empleado(TipoEmpleado.OPERADOR, 2);

		dispatcher = new Dispatcher(Arrays.asList(operator1), THREADS);
		dispatcher.setEmpleados(empleados);
		dispatcher.dispatchCall(llamada1);
		dispatcher.getExecutorService().awaitTermination(1, TimeUnit.SECONDS);

		Assert.assertFalse("La llamada no fue asignada", llamada1.isAsignada());

	}

}