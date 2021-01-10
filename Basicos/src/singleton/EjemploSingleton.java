package singleton;

public class EjemploSingleton {
	
	// SINGLETON
	// -------------------------------------------------------------------------
	// sirve para poder tener una clase de la cual solamente querremos tener una instancia (manejadores, servicios, …).
	// Para implementarla, podemos seguir los siguientes pasos:
	// 1 Definir un único constructor, como privado, para evitar instanciaciones innecesarias.
	// 2 Obtener siempre la instancia a través de un método estático.
	
public static void main(String[] args) {
		
		//No podemos instanciar directamente
		//MiServicioSingleton ser1 = new MiServicioSingleton();
		
		//Creamos dos referencias, pero una sola instancia
		MiServicioSingleton ser2 = MiServicioSingleton.getInstance();
		MiServicioSingleton ser3 = MiServicioSingleton.getInstance();
		
		//Podemos comprobar que la dirección de memoria es la misma
		System.out.println(ser2);
		System.out.println(ser3);
		
		//Si cambiamos un atributo a través de una referencia
		ser2.setUnAtributo("Hola");
		//Podemos visualizarlo desde la otra.
		System.out.println(ser3.getUnAtributo());
		

	}

}
