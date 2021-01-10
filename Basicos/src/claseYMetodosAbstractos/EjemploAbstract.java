package claseYMetodosAbstractos;




	// ABSTRACT
	// -------------------------------------------------------------------------------------
	// Es una palabra reservada, que puede usarse a nivel de método o de clase. Sirve para indicar 
	// la obligación de implementar un método o de extender una clase completa.

	// NIVEL CLASE
	// ----------------------------------------------------------------------------------------------
	// * Clase definida como abstract
	// * Clase que no se pueden crear instancias de la misma.
	// * Puede tener métodos con implementación y atributos, y también métodos abstractos.

	// NIVEL MÉTODO
	// ----------------------------------------------------------------------------------------------
	// * Deben estar en una clase definida como abstract.
	// * Son métodos declarados, pero sin implementación
	// * Sus subclases se comprometen a implementarlo. Si no lo hacen también deben ser abstractas.
	// * Pueden convivir con metodos normales.

	// CLASES ABSTRACT QUE IMPLEMENTAN UNA INTERFAZ
	// ---------------------------------------------------------------------------------------------
	// Una clase que implementa una interfaz tiene la obligación de implementar todos sus métodos, pero
	// una clase abstract puede dejar métodos sin implementación, obligando a quienes la extiendan a hacerlo.
	

interface Interfaz {

    public void rotar();
    public void voltearHorizontal();
    public void voltearVertical();
}

public abstract class EjemploAbstract implements Interfaz {

    protected int x, y;

    public void moverA(int nuevaX, int nuevaY) {
        this.x = nuevaX;
        this.y = nuevaY;
    }

    abstract public void dibujar();

    abstract public void cambiarTamanio(int factorAumento);

}