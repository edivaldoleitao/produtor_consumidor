package produtorConsumidor.monitor;

import java.util.Stack;

public class Consumidor extends Thread {
	
	private Silo silo;
	
	public Consumidor(Silo silo) {
		this.silo = silo;
	}
	
	
	@Override
	public void run() {
		while (true) {
			try {
				int item = silo.consumir();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
