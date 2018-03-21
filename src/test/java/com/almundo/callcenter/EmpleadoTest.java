package com.almundo.callcenter;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Evillalba
 *
 */
public class EmpleadoTest {

	private Empleado empleado;

	/**
	 * Simula la atencion de una llamada exitosa
	 */
	@Test(timeout = 3000)
	public void atenderllamada_atenderUnaLlamadaConExito() {
		empleado = new Empleado(TipoEmpleado.OPERADOR, 1);
		LllamadaTelefonica llamada = new LllamadaTelefonica(1234, 1, 1);
		empleado.atenderllamada(llamada);
		Assert.assertTrue("La llamada fue atendida con éxito ", llamada.isAsignada());
	}
	
   /**
    * 
    */
	@Test
	public void testEqualsTrue() {
		Empleado emp1= new Empleado(TipoEmpleado.OPERADOR, 1);
		
		Empleado emp2= new Empleado(TipoEmpleado.OPERADOR, 1);

		Assert.assertEquals(emp1, emp2);
	}

	/**
	 * 
	 */
	@Test
	public void testEquals_diferenteTipo() {
		Empleado emp1= new Empleado(TipoEmpleado.SUPERVISOR, 1);
		
		Empleado emp2= new Empleado(TipoEmpleado.OPERADOR, 1);

		Assert.assertNotEquals(emp1, emp2);
	}
	
	/**
	 * 
	 */
	@Test
	public void testEquals_diferenteID() {
		Empleado emp1= new Empleado(TipoEmpleado.SUPERVISOR, 1);
		
		Empleado emp2= new Empleado(TipoEmpleado.SUPERVISOR, 2);

		Assert.assertNotEquals(emp1, emp2);
	}
	
	/**
	 * 
	 */
	@Test
	public void testEquals_diferente_Tipo_ID() {
		Empleado emp1= new Empleado(TipoEmpleado.SUPERVISOR, 1);
		
		Empleado emp2= new Empleado(TipoEmpleado.DIRECTOR, 2);

		Assert.assertNotEquals(emp1, emp2);
	}
	
	/**
	 * 
	 */
	@Test
	public void testEquals_diferente_Object() {
		Empleado emp1= new Empleado(TipoEmpleado.SUPERVISOR, 1);
		Assert.assertNotEquals(emp1, new Object());
	}
	
	/**
	 * 
	 */
	@Test
	public void testEquals_objeto_Null() {
		Empleado emp1= new Empleado(TipoEmpleado.SUPERVISOR, 1);
		Assert.assertNotEquals(emp1, null);
	}
	
	/**
	 * 
	 */
	@Test
	public void testEquals_mismaReferencia() {
		Empleado emp1= new Empleado(TipoEmpleado.SUPERVISOR, 1);
		Assert.assertEquals(emp1, emp1);
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testHashCode_objetosIguales() {
		Empleado emp1= new Empleado(TipoEmpleado.SUPERVISOR, 1);
		Empleado emp2= new Empleado(TipoEmpleado.SUPERVISOR, 1);
		Assert.assertTrue(emp1.hashCode() == emp2.hashCode());
	}
}
