package aleatorios;

import java.util.Random;

public class EjemploRandom {
	
	public static void main(String[] args) {
		
		// genera un número aleatorio entre 0 y 5
		Random r = new Random();
		int n1 = r.nextInt(5);
		
		// genera un número aleatorio entre 0 y 5
		int n2=new Random().ints(0, 5).findFirst().getAsInt();
		
		// genera 100 números aleatorio entre 0 y 5 con Stream (ints devuelve un InrStream)
		new Random().ints(100, 0, 5).forEach(System.out::println);
	}

}
