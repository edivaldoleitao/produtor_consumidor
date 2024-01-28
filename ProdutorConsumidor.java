package produtorConsumidor;

import java.util.Stack;
import java.util.concurrent.Semaphore;

public class ProdutorConsumidor {

	// Silo
	private static Stack<Integer> silo = new Stack<Integer>();

	// semCon inicializado com 0 para permitir que put() executa primeiro
	static Semaphore semCon = new Semaphore(0);

	static Semaphore semProd = new Semaphore(5);
	
	static Semaphore compt = new Semaphore(1);

	// remover um item do silo
	static void consumidor() throws InterruptedException {
		while (true) {
			Thread.sleep((long)(Math.random()*500));
			try {
				// antes de consumir um item deve conseguir permissao do semaforo
				semCon.acquire();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}

			compt.acquire();
			// consumidor consumindo um item
			System.out.println("Consumidor consumiu item : " + silo.pop() + " - Tamanho do silo: " + silo.size());
			compt.release();
			
			// Após consumir o item ele libera o semProd para notificar que pode produzir
			semProd.release();
		}

	}

	// colocar um item no silo
	static void produtor() throws InterruptedException {
		int item = 0;
		while (true) {
			Thread.sleep((long)(Math.random()*500));
			item = (int) (10*Math.random());

			try {
				// Antes do produtor produzir um item ele deve pegar uma permissao do semProd
				semProd.acquire();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}

			compt.acquire();
			silo.add(item);
			compt.release();
			// produtor produzindo um item
			System.out.println("Produtor produziu um item: " + item + " - Tamanho do silo: " + silo.size());

			// Apos produzir um item ele libera semCon para notificar o consumidor
			semCon.release();
		}

	}

	public static void main(String args[]) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					consumidor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					produtor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		
	}
}
