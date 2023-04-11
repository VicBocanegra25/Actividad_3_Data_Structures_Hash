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
        myHash.insertar("Ely", 73);

        // En este momento, la Hash Table ha rebasado el límite crítico de espacios vacíos.
        myHash.insertar("Rafael", 63);
        myHash.insertar("Velkan", 13);



    }
}