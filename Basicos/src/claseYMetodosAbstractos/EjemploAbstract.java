package claseYMetodosAbstractos;




	// ABSTRACT
	// -------------------------------------------------------------------------------------
	// Es una palabra reservada, que puede usarse a nivel de m�todo o de clase. Sirve para indicar 
	// la obligaci�n de implementar un m�todo o de extender una clase completa.

	// NIVEL CLASE
	// ----------------------------------------------------------------------------------------------
	// * Clase definida como abstract
	// * Clase que no se pueden crear instancias de la misma.
	// * Puede tener m�todos con implementaci�n y atributos, y tambi�n m�todos abstractos.

	// NIVEL M�TODO
	// ----------------------------------------------------------------------------------------------
	// * Deben estar en una clase definida como abstract.
	// * Son m�todos declarados, pero sin implementaci�n
	// * Sus subclases se comprometen a implementarlo. Si no lo hacen tambi�n deben ser abstractas.
	// * Pueden convivir con metodos normales.

	// CLASES ABSTRACT QUE IMPLEMENTAN UNA INTERFAZ
	// ---------------------------------------------------------------------------------------------
	// Una clase que implementa una interfaz tiene la obligaci�n de implementar todos sus m�todos, pero
	// una clase abstract puede dejar m�todos sin implementaci�n, obligando a quienes la extiendan a hacerlo.
	

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