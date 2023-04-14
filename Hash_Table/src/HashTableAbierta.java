import java.util.ArrayList;

/* Implementación de una Hash Table Abierta. Utiliza la clase Entrada para almacenar la llave-valor.
* La clase nativa ArrayList de Java nos sirve como una 'cadena' en cada entrada de la Hash Table para almacenar estas entradas.
* Contendrá los mismos métodos que en la Tabla Cerrada: insertar, recuperar, borrar, modificar y una función de hasheo.
* */
public class HashTableAbierta {
    private int tamaño;
    private ArrayList[] espacios;
    private int numElementos;

    public HashTableAbierta(int tamaño) {
        this.tamaño = tamaño;
        this.espacios = new ArrayList[tamaño];

        // Cada entrada de nuestra tabla Hash será un array del tamaño indicado por el usuario.
        for (int i = 0; i < tamaño; i++) {
            espacios[i] = new ArrayList<>();
        }
    }

    /* El método hashFunction nos permite transformar una entrada (la llave) en un hashValue(entero).
     *  Es utilizando este hashValue que accedemos al índice (al valor) del elemento que buscamos
     * @params:
     *   String s: La llave 'string' que vamos a convertir en un hashValue.
     * @returns:
     *   int hv: El entero hashValue que se produce con la función. Se utilizará para acceder al valor de la HashTable.
     *  */
    private int hashFunction(String llave) {
        // Utilizamos un multiplicador para cada posición de la cadena, el cual se incrementará conforme avanzamos en la cadena
        int mult = 1;
        // Inicializamos el valor del hashValue en 0
        int hashValue = 0;
        // Utilizamos un for loop para recorrer la cadena string, convertir cada letra en un entero(ord - ASCII), multiplicar
        // este valor por el mult y sumarlo al hashValue
        for (int i = 0; i < llave.length(); i++){
            // Tomamos la letra en la posición i
            char ch = llave.charAt(i);
            // Transformamos la letra en un entero
            hashValue += mult * (int) ch;
            mult += 1;
        }
        return hashValue % this.tamaño;
    }


    /* El método insertar() nos sirve para colocar una nueva entrada en la HashTable.
     * Al insertar un elemento en el array, utilizamos una función nativa array.add(elemento)
     * @params:
     *   String llave: La llave que vamos a utilizar para localizar al elemento.
     *   int valor: El valor asociado con la llave.
     * @returns:
     *   void
     * */
    public void insertar(String llave, long valor){
        // Generamos una nueva entrada con llave, valor
        Entrada nuevoElemento = new Entrada(llave, valor);
        // Obtenemos el HashValue llamando a la función hashFunction
        int hashValue = hashFunction(llave);

        // Agregamos un elemento al array correspondiente a la posición hashValue con la función add()
        this.espacios[hashValue].add(nuevoElemento);
        // INcrementamos el número de elementos
        this.numElementos++;
    }

    /* La función recuperar() nos regresa el valor almacenado que corresponde a la llave indicada por el usuario.
     * Para hacerlo, se debe llamar a la función hashFunction que nos otorgará el valor del hash.
     * Posteriormente, atravesamos el array en búsqueda del elemento que contenga la combinación llave-valor que solicitó el usuario
     * @params:
     *   String llave: La cadena con la que almacenamos el valor dentro de la hash table.
     * @returns:
     *   long: El valor almacenado en esa llave
     * */
    public java.lang.Long recuperar(String llave){
        // Primero calculamos un hashValue
        int hashValue = hashFunction(llave);

        // Declaramos nuestro valor, realizaremos una búsqueda a través del array
        long valor = -1;
        // Recorremos el array buscando la entrada que corresponde al valor del hash
        for (int i = 0; i < this.espacios[hashValue].size(); i++){
            Entrada temporal = (Entrada)this.espacios[hashValue].get(i);
            // Si encontramos la llave solicitada en alguna posición del array, lo devolvemos
            if (temporal.llave.equals(llave)){
                valor = temporal.valor;
                return valor;
            }
        }
        System.out.println("\nNo se encontró el valor correspondiente a '" + llave + "'.");
        return null;
    }

    /* La función de borrado utiliza la misma técnica que la función recuperar() para encontrar la llave-valor indicada por el usuario.
     * El efecto de borrar es que elemento que corresponde a la llave indicada por el usuario apuntará a null. Si no existe la llave, se imprimirá
     * un mensaje indicando que la llave no existe en la Hash Table.
     * @params:
     *   String llave: La llave indicada por el usuario, es con esta llave que se buscará el elemento a borrar.
     * @returns:
     *   Boolean true; si se eliminó correctamente el valor / false: si no se encontró la llave a eliminar.
     * */
    public Boolean borrar(String llave){
        // Primero calculamos un hashValue
        int hashValue = hashFunction(llave);

        // Inicializamos una variable que almacenará temporalmente el valor que nos regrese el hash
        long valor =-1;

        // Recorremos el array buscando la entrada que corresponde al valor del hash
        for (int i = 0; i < this.espacios[hashValue].size(); i++){
            Entrada temporal = (Entrada)this.espacios[hashValue].get(i);
            // Si encontramos la llave solicitada en alguna posición del array, lo eliminamos
            if (temporal.llave.equals(llave)){
                // Se elimina la entrada del array
                System.out.println("Se ha borrado la llave-valor '" + llave + " - " + temporal.valor + "'.");
                this.espacios[hashValue].remove(i);
                this.numElementos--;
                return true;
            }
        }
        System.out.println("\nNo se encontró el valor correspondiente a '" + llave + "'.");
        return false;
    }

    /* La función modificar() nos sirve para cambiar el valor almacenado en una llave determinada.
     * El usuario pasa la llave y el nuevo valor que deberá estar almacenado en esta llave y se cambia el valor.
     * @params:
     *   String llave: El valor de la llave con la cual se almacenó el valor originalmente.
     *   long nuevoValor: El nuevo valor que deberá almacenar.
     * @returns:
     *   Boolean: True si se ha podido modificar / False si no se pudo modificar el contenido.
     *     * */
    public Boolean modificar(String llave, long nuevoValor){
        // Primero calculamos un hashValue
        int hashValue = hashFunction(llave);

        // Inicializamos una variable que almacenará temporalmente el valor que nos regrese el hash
        long valor =-1;

        // Recorremos el array buscando la entrada que corresponde al valor del hash
        for (int i = 0; i < this.espacios[hashValue].size(); i++){
            Entrada temporal = (Entrada)this.espacios[hashValue].get(i);
            // Si encontramos la llave solicitada en alguna posición del array, modificamos su contenido
            if (temporal.llave.equals(llave)){
                valor = temporal.valor;
                // Aquí es donde se asigna el nuevo valor
                temporal.valor = nuevoValor;
                System.out.println("\nSe ha modificado el elemento contenido en '" + llave + "'.");
                System.out.println("El valor anterior era: " + valor + ". El nuevo valor es: " + nuevoValor);

                return true;
            }
        }

        System.out.println("\nNo se encontró el valor correspondiente a: '" + llave + "'.");
        return false;
    }

    /* ObtenerTamaño es una función auxiliar para obtener el tamaño de la HashTable
    @ params:
        null
    @ returns:
        int tamaño: El tamaño actual de la HashTable
        * */
    public int obtenerTamaño(){
        return this.tamaño;
    }

    /* obtenerElementos es una función auxiliar para obtener el número de elementos de la HashTable
     * */
    public Entrada obtenerElementos(int index){
        for (int i = 0; i < espacios[index].size(); i++) {
            return (Entrada) espacios[index].get(i);
            }
        return null;
    }

    /* El método main() nos sirve para probar los otros métodos de esta clase.
    * */
    public static void main(String args[]){
        // Probamos los métodos de la clase.
        System.out.println("Instanciando la Hash Abierta: ");
        HashTableAbierta myHashAbierta = new HashTableAbierta(5);
        // Insertamos dos valores que causarán colisión
        myHashAbierta.insertar("hello world", 28);
        myHashAbierta.insertar("world hello", 30);

        // Insertamos un valor más
        myHashAbierta.insertar("Víctor", 28);

        // Intentamos recuperar un valor que no existe
        System.out.println(myHashAbierta.recuperar("bleh"));

        // Comprobamos que funciona el método borrar
        myHashAbierta.borrar("Víctor");

        // Intentamos borrar algo que no existe en la Hash Table
        myHashAbierta.borrar("Víctor2");

        // Modificamos el valor en una entrada existente
        myHashAbierta.modificar("hello world", 29);


    }
}
