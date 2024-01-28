package produtorConsumidor.monitor;

public class Principal {

	
	public static void main(String[] args) {
		Silo silo = new Silo();
		Produtor prod = new Produtor(silo);
		Consumidor cons = new Consumidor(silo);
		
		prod.start();
		cons.start();
	}
	
}
