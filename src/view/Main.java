package view;

import java.util.concurrent.Semaphore;
import controller.Jogador;

public class Main {
	
	public static void main(String[] args) {
		Semaphore semaforoCozinheiro = new Semaphore(5);
		Semaphore semaforoEntrega = new Semaphore(1);
		
		for(int i = 0; i < 20; i++) {
			Jogador j = new Jogador(semaforoCozinheiro, semaforoEntrega);
			j.start();
		}
	}
}
