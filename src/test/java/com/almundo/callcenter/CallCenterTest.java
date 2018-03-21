package com.almundo.callcenter;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Evillalba
 *
 */
public class CallCenterTest {

	private static final int NUMERO_DE_LLAMADAS = 10;
	private static final int TIEMPO_MINIMO_LLAMADA = 5;
	private static final int TIEMPO_MAXIMO_LLAMADA = 10;
	private static final int TIEMPO_MAXIMO_ESPERA = 20;

	private static final int THREADS = 10;

	private Dispatcher dispatcher;

	/**
	 * Se preparan los datos para la prueba
	 */
	@Before
	public void setUp() {

		Empleado operator1 = new Empleado(TipoEmpleado.OPERADOR, 1);
		Empleado operator2 = new Empleado(TipoEmpleado.OPERADOR, 2);
		Empleado supervisor1 = new Empleado(TipoEmpleado.SUPERVISOR, 1);
		Empleado supervisor2 = new Empleado(TipoEmpleado.SUPERVISOR, 2);
		Empleado supervisor3 = new Empleado(TipoEmpleado.SUPERVISOR, 3);
		Empleado operator3 = new Empleado(TipoEmpleado.OPERADOR, 3);
		Empleado operator4 = new Empleado(TipoEmpleado.OPERADOR, 4);
		Empleado operator5 = new Empleado(TipoEmpleado.OPERADOR, 5);
		Empleado operator6 = new Empleado(TipoEmpleado.OPERADOR, 6);
		Empleado director = new Empleado(TipoEmpleado.DIRECTOR, 1);

		dispatcher = new Dispatcher(Arrays.asList(operator1, operator2, operator3, operator4, operator5, operator6,
				supervisor1, supervisor2, supervisor3, director), THREADS);

	}

	/**
	 * Test que simula las llamadas concurrentes
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void simularLlamada() throws InterruptedException {
		for (int i = 0; i < NUMERO_DE_LLAMADAS; i++) {
			dispatcher.dispatchCall(new LllamadaTelefonica(1 + i, TIEMPO_MINIMO_LLAMADA, TIEMPO_MAXIMO_LLAMADA));
		}

		dispatcher.getExecutorService().awaitTermination(TIEMPO_MAXIMO_ESPERA, TimeUnit.SECONDS);
	}
}
