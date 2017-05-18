package model;

public class ListMonitor {

	private int[] list;
	private int cantInt;

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
	
	public synchronized int peek(){
		while(cantInt == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list[0];
	}
	
	public synchronized int pop(){
		while(cantInt == 0){
			try{
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int first = list[0];
		int[] temp = list;
		
		for(int i = 0; i < temp.length-1; i++){
			list[i] = temp[i+1];
		}
		cantInt--;
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
		notify(); //o notifyAll?
	}
	
    public void sort() {
        mergesort(0, cantInt - 1);
    }

	private void mergesort(int low, int high) {
        if (low < high) {
        	int middle = low + (high - low) / 2;
            mergesort(low, middle);
            mergesort(middle + 1, high);
            merge(low, middle, high);
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

}