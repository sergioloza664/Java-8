package sobrescribirMetodosObject;

	// HERENCIA DE OBJECT
	// -----------------------------------------------------------------------------------------------
	// * Todo objeto, de forma directa o indirecta hereda de Object.
	// * Es la clase base de cualquier otra en Java
	// * Tiene algunos metodos:
	//   	* equals: nos permite indicar cuando dos objetos son iguales
	//   	* hashCode: nos devuelve un número “único” asociado a una instancia de una clase
	//   	* toString: nos devuelve una reperesentación del objeto como una cadena de caracteres.

	// COMPARACION DE OBJETOS
	// --------------------------------------------------------------------------------------------------
	// * Con tipos primitivos, hemos usado el opera
	// * Con los objetos primero tenemos que definir cuando dos instancias de un objeto son iguales o 
	//   diferentes.

	// EQUALS
	// ---------------------------------------------------------------------------------------------------
	// * El método equals nos permite devolver un boolean indicando si un objeto es igual a otro.
	// * Nuestro IDE lo autogenera, junto con hashCode.

	// HASHCODE
	// -----------------------------------------------------------------------------------------------
	// * Devuelve un número asociado a la clase.
	// * Sirve como posición de memoria en hexadecimal. 
	// * Por definición, si dos objetos son iguales (equals), su hash code también debe serlo.
	// * Si sobrescribimos el método equals, también tenemos que sobrescribir hashCode para que se cumpla 
	//   esa propiedad.

	// TOSTRING
	// ----------------------------------------------------------------------------------------------------
	// * Devuelve una representación en String del objeto.
	// * Por defecto, devuelve el tipo (la clase) y su hashCode.
	// * Lo podemos sobrescribir para que represente los valores.
	// * Dos objetos iguales deben tener la misma representación.

