package controller;

import java.util.concurrent.Semaphore;

public class Jogador extends Thread {
	
	Semaphore semaforoCozinheiro;
	Semaphore semaforoEntrega;
	
	public Jogador(Semaphore semaforoCozinheiro, Semaphore semaforoEntrega) {
		this.semaforoCozinheiro = semaforoCozinheiro;
		this.semaforoEntrega = semaforoEntrega;
	}
	
	@Override
	public void run() {
		setup();
	}
	
	private void setup() {
		long id = getId();
		
		try {
			semaforoCozinheiro.acquire();
			
			if(id%2 == 0) {
				cebola(id);
			}
			if(id%2 == 1) {
				lasanha(id);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			entrega(id);
			semaforoCozinheiro.release();
		}
	}
	
	private void lasanha(long id) {
		int preparo = (int) ((Math.random() * 8) + 6);
		int tempoGasto = 0;
		System.out.println("#"+id+" - Lasanha a Bolonhesa");
		
		while(preparo > tempoGasto) {
			System.out.println("#"+id+" - progresso em: "+((tempoGasto*100)/preparo)+"%");
			
			pausa(1000);
			tempoGasto++;
		}
		System.out.println("#"+id+" - Finalizado");
		
	}
	
	private void cebola(long id) {
		int preparo = (int) ((Math.random() * 4) + 5);
		int tempoGasto = 0;
		
		System.out.println("#"+id+" - Sopa de Cebola");
		
		while(preparo > tempoGasto) {
			System.out.println("#"+id+" - progresso em: "+((tempoGasto*100)/preparo)+"%");
			
			pausa(1000);
			tempoGasto++;
		}
		System.out.println("#"+id+" - Finalizado");
	}
	
	private void entrega(long id) {
		try {
			semaforoEntrega.acquire();
			
			int tempoEntrega = 5;
			int tempoGasto = 0;
			
			while(tempoGasto > tempoEntrega) {			
				pausa(1000);
				tempoGasto++;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("#"+id+" - Entrega Finalizada");
			semaforoEntrega.release();
		}
	}
	
	private void pausa(int time) {
		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
