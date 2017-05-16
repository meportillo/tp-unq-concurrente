package model;

import javax.print.attribute.standard.PrinterLocation;

public class ListMonitor {

	private int[] list;
	private int cantInt;

	public ListMonitor() {
		this.cantInt = 0;
		// TODO - Inicializar el array
		 list = new int[3];
	}

	public synchronized int size() {
		return cantInt;
	}

	public synchronized boolean isEmpty() {
		return cantInt == 0;
	}

	public synchronized boolean contains(int elem) {
		boolean contain = false;

		for (int i = 0; i < list.length && !contain; i++) {
			contain = contain || (list[i] == elem);
		}
		return contain;
	}
	
	public synchronized int peek(){
		//falta bloquear si no hay elementos
		return list[0];
	}
	
	public synchronized int pop(){
		//falta bloquear si no hay elementos
		int first = list[0];
		int[] temp = list;
		
		for(int i = 0; i < temp.length - 1; i++){
			list[i] = temp[i+1];
		}
		cantInt-=1;
		return first;
	}

	public synchronized void add(int itgr) {
		if (hayEspacio()) {
			list[cantInt] = itgr;
			cantInt++;
		} else {
			int[] temp = list;
			list = new int[temp.length * 2];
			for (int i = 0; i < temp.length; i++) {
				list[i] = temp[i];
			}
			list[cantInt] = itgr;
			cantInt++;
		}
	}
	
	private boolean hayEspacio() {
		return cantInt < list.length;
	}
}