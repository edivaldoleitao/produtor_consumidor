package produtorConsumidor.monitor;

import java.util.Stack;
import java.util.concurrent.Semaphore;

public class Silo {
	
	private Stack<Integer> silo;
	
	private Semaphore semCon;

	private Semaphore semProd;
	
	public Silo() {
		silo = new Stack<>();
		semCon= new Semaphore(0);
		semProd = new Semaphore(5);
	}
	
	public int consumir() throws InterruptedException {
		Thread.sleep((long)(Math.random()*500));
		try {
			// antes de consumir um item deve conseguir permissao do semaforo
			semCon.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		// consumidor consumindo um item
		int item;
		System.out.println("Consumidor consumiu item : " + (item = silo.pop()) + " - Tamanho do silo: " + silo.size());

		// Após consumir o item ele libera o semProd para notificar que pode produzir
		semProd.release();
		return item;
	}
	
	public void produzir(int item) throws InterruptedException {
		Thread.sleep((long)(Math.random()*500));

		try {
			// Antes do produtor produzir um item ele deve pegar uma permissao do semProd
			semProd.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		silo.add(item);
		// produtor produzindo um item
		System.out.println("Produtor produziu um item: " + item + " - Tamanho do silo: " + silo.size());

		// Apos produzir um item ele libera semCon para notificar o consumidor
		semCon.release();
	}

}
