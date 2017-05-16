package test;

import static org.junit.Assert.*;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import model.ListMonitor;

public class ListMonitorTest {
	
	private ListMonitor lista; 

	@Before
	public void setUp() throws Exception {
		lista = new ListMonitor();
	}

	@Test
	public void testAlIniciarUnaListaMonitorElTamanioDebeSerCero() {
		assertEquals(0, lista.size());
	}

	@Test
	public void testAlIniciarUnaListaMonitorSeEncuentraVacia() {
		assertTrue(lista.isEmpty());
	}
	
	@Test
	public void testSiAUnaListaMonitorSinElementosSeLePreguntaSiContieneXDevuelveFalso(){
		assertFalse(lista.contains(100));
	}
	
	@Test
	public void testElPeekAUnaListaMonitorConUnEnteroDevuelveEseEntero(){
		lista.add(10);
		assertEquals(10, lista.peek());
	}
	
	@Test
	public void testPopAUnaListaMonitorConUnEnteroDevuelveEseEntero(){
		lista.add(10);
		
		assertTrue(10 == lista.pop());
	}
	
	@Test
	public void testPopAUnaListaMonitorConUnEnteroDejaLaListaVacia(){
		lista.add(10);
		lista.pop();
		
		assertTrue(lista.isEmpty());
	}
}
