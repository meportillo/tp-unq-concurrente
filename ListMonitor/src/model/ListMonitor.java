package model;

public class ListMonitor {

	private int[] list;
	private int cantInt;

	public ListMonitor() {

		this.cantInt = 0;
		// TODO - Inicializar el array
		// list = new int[3];
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
