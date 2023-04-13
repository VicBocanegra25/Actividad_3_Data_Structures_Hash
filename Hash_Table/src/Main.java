/* En la clase Main se instanciará las clase HashTable para demostrar una aplicación real de esta estructura de datos.

* */

public class Main {
    public static void main(String[] args) {
        System.out.println("Implementación de una Hash Table! ");

        // Instanciamos una clase HashTable e insertamos 8 valores.
        HashTable myHash = new HashTable(10);
        myHash.insertar("Víctor", 28);
        myHash.insertar("Hugo", 28);
        myHash.insertar("Israel", 30);
        myHash.insertar("Rodrigo", 28);
        myHash.insertar("Cristina", 38);
         // En este momento, la Hash Table ha rebasado el límite crítico de espacios vacíos.
        myHash.insertar("Rafael", 63);


        // Los siguientes elementos proporcionarían una colisión:
        myHash.insertar("ad", 1);
        myHash.insertar("ga", 2);

        // Recuperando el valor con la llave "Hugo"
        System.out.println("\n\nRecuperamos el valor almacenado con la llave 'Hugo'");
        System.out.println(myHash.recuperar("Hugo"));

        // Intentamos recuperar un valor que no existe
        System.out.println("\nRecuperamos el valor almacenado con la llave 'Java'");
        System.out.println(myHash.recuperar("Java"));

        // Intentemos recuperar elementos que proporcionan colisiones
        System.out.println("\nRecuperamos el valor almacenado con la llave 'ad'");
        System.out.println(myHash.recuperar("ad"));

        System.out.println("\nRecuperamos el valor almacenado con la llave 'ga'");
        System.out.println(myHash.recuperar("ga"));

        // Intentando los métodos de insertar y recuperar con quadratic probing (método anti colisiones)
        myHash.insertarCuadratica("Cuadratico", 22);
        System.out.println("\nRecuperamos el valor almacenado con la llave 'Cuadratico'");
        System.out.println(myHash.recuperarCuadrada("Cuadratico"));

        // Si intentamos recuperar el elemento que fue insertado con Quadratic probing, utilizando una función que usa linear probing:
        System.out.println("\nRecuperamos el valor almacenado con la llave 'Cuadratico', pero utilizando linear probing");
        System.out.println(myHash.recuperar("Cuadratico"));

        // Probamos los métodos de doble hasheo (insertar y recuperar)
        myHash.insertarDobleHash("Doble Hasheo", 30);
        System.out.println("\nRecuperamos el valor almacenado con la llave 'Doble Hasheo'");
        System.out.println(myHash.recuperarDobleHash("Doble Hasheo"));

        // Probamos borrando uno de los primeros elementos y luego intentamos recuperarlo
        System.out.println("\nBorraremos la llave correspondiente a: 'Doble Hasheo'");
        myHash.borrar("Doble Hasheo");
        System.out.println(myHash.recuperarDobleHash("Doble Hasheo"));

        // Modificamos ahora el valor contenido en "Víctor".
        myHash.modificar("Víctor", 25);
        System.out.println("\nRecuperamos la llave correspondiente a: 'Víctor'");
        System.out.println(myHash.recuperarDobleHash("Víctor"));

        // Vamos a instanciar un objeto de la clase Contactos, que simula una agenda telefónica.
        Contactos miAgenda = new Contactos();
        miAgenda.inicializar();


    }
}