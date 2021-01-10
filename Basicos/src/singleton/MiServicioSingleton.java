package singleton;

public class MiServicioSingleton {
	
	private String unAtributo;
	
	//Una instancia del objeto que va a existir
	private static MiServicioSingleton instance = null;
	
	//Evitamos as� la instanciaci�n directa
	private MiServicioSingleton() {	}
	
	public static MiServicioSingleton getInstance() {
		if (instance == null)
			instance = new MiServicioSingleton();
		
		return instance;
	}

	public String getUnAtributo() {
		return unAtributo;
	}

	public void setUnAtributo(String unAtributo) {
		this.unAtributo = unAtributo;
	}

	
	

}
