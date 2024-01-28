package produtorConsumidor.monitor;

public class Produtor extends Thread{
	private Silo silo;
	
	public Produtor(Silo silo) {
		this.silo = silo;
	}
	
	
	@Override
	public void run() {
		int item;
		while (true) {
			try {
				item = (int) (10*Math.random());
				silo.produzir(item);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
