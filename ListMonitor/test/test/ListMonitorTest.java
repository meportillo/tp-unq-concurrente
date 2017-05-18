package test;

import static org.junit.Assert.*;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import model.ListMonitor;

public class ListMonitorTest {
	
	private ListMonitor lista; 

	@Before
	public void setUp() throws Exception {
		lista = new ListMonitor();
	}

	@Test
	public void testAlIniciarUnaListMonitorElTamanioDebeSerCero() {
		assertEquals(0, lista.size());
	}

	@Test
	public void testAlIniciarUnaListMonitorDebeEncontrarseVacia() {
		assertTrue(lista.isEmpty());
	}
	
	@Test
	public void testSiAUnaListMonitorVaciaSeLePreguntaSiContieneXDevuelveFalso(){
		assertFalse(lista.contains(100));
	}
	
	@Ignore
	@Test
	public void testDelMetodoPeekAUnaListMonitorVacia(){
		
	}
	
	@Ignore
	@Test
	public void testDelMetodoPopAUnaListMonitorVacia(){
		
	}
	
	@Test
	public void testPeekAUnaListMonitorConUnEnteroDevuelveEseEntero(){
		lista.add(10);
		assertEquals(10, lista.peek());
	}
	
	@Test
	public void testPopAUnaListMonitorConUnEnteroDevuelveEseEntero(){
		lista.add(10);
		
		assertEquals(10, lista.pop());
	}
	
	@Test
	public void testPopAUnaListMonitorConUnEnteroDejaLaListaVacia(){
		lista.add(10);
		lista.pop();
		
		assertTrue(lista.isEmpty());
	}
	
	@Test
	public void testAgregarUnElementoAUnaListMonitorAumentaElTamanio(){
		lista.add(10);
		
		assertEquals(1, lista.size());
	}
	
	@Test
	public void testSiSeAgregaUnElementoAUnaListMonitorYSeLePreguntaSiLoContieneEsTrue(){
		lista.add(10);
		
		assertTrue(lista.contains(10));
	}
	
	@Test
	public void testMergeSort(){
		lista.add(10);
		lista.add(1);
		lista.add(110);
		lista.add(0);
		lista.add(16);
		lista.add(20);
		lista.add(3);
		lista.add(7);
		lista.add(2);
		
		lista.sort();
		
		int n = lista.size();
		int mayor = lista.peek();
		boolean ordenada = true;
		
		for(int i= 0; i < n; i++){
			if(lista.peek() >= mayor && ordenada){
				mayor = lista.peek();
				ordenada = ordenada && (lista.peek() >= mayor);
				lista.pop();
			}
		}
		assertTrue(ordenada);
	}
}
