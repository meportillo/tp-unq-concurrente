package model;

public class ListMonitor {

	private int[] list;
	private int cantInt;
	private int cantidadThreads;

	public ListMonitor() {
		this.cantInt = 0;
		this.list = new int[3];
	}

	public synchronized int size() {
		return cantInt;
	}

	public synchronized boolean isEmpty() {
		return cantInt == 0;
	}

	public synchronized boolean contains(int elem) {
		boolean contain = false;

		for (int i = 0; i < this.size() && !contain; i++) {
			contain = contain || (list[i] == elem);
		}
		return contain;
	}

	public synchronized int peek() {
		while (cantInt == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return list[0];
	}

	public synchronized int pop() {
		while (cantInt == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int first = list[0];
		int[] temp = list;

		for (int i = 0; i < temp.length - 1; i++) {
			list[i] = temp[i + 1];
		}
		cantInt--;
		return first;
	}

	public synchronized void add(int itgr) {
		if (hayEspacio()) {
			list[cantInt] = itgr;
			cantInt++;
			notifyAll();
		} else {
			int[] temp = list;
			list = new int[temp.length * 2];
			for (int i = 0; i < temp.length; i++) {
				list[i] = temp[i];
			}
			list[cantInt] = itgr;
			cantInt++;
		}
		notifyAll();
	}

	public synchronized ListMonitor sublist(int inicio, int fin) {

		ListMonitor ret = new ListMonitor();
		for (int i = inicio; i < fin; i++) {
			ret.add(this.list[i]);
		}

		return ret;
	}

	public synchronized void addAll(ListMonitor lista) {

		for (int i = 0; i < lista.size(); i++) {
			this.add(lista.pop());// preguntar Si esta vacia que pasa
		}

	}

	public void sort(int n) {
		this.cantidadThreads = n;
		mergesort(0, cantInt - 1);
	}

	private synchronized void mergesort(int low, int high) {
		if (low < high) {

			int middle = low + (high - low) / 2;
			mergesort(low, middle);
			mergesort(middle + 1, high);
			/// ver de levantar el thread aca???
			// if cant de thead < param new thread----- >
			// Array de Threads????
			while(this.cantidadThreads == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
			this.cantidadThreads--;
			
			Thread t = new Thread(new Runnable() {
				public void run()
				{
					System.out.println("thread");
					merge(low, middle, high);
				}
			}, "");
			t.start();
			this.cantidadThreads++;
			notify();

		}
	}

	private void merge(int low, int middle, int high) {
		int[] aux = new int[cantInt];
		for (int i = low; i <= high; i++) {
			aux[i] = list[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;

		while (i <= middle && j <= high) {
			if (aux[i] <= aux[j]) {
				list[k] = aux[i];
				i++;
			} else {
				list[k] = aux[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			list[k] = aux[i];
			k++;
			i++;
		}
	}

	private boolean hayEspacio() {
		return cantInt < list.length;
	}

	public String toString() {
		String ret = "";
		ret += "[";
		for (int i = 0; i < this.size(); i++) {

			ret += list[i] + ",";

		}
		ret += "]";
		ret.replaceAll("]", ",]");
		return ret;
	}
	/// merge concurrentemente
	///
}