/* Implementación de una Hash Table simple. Se hace uso de la clase Entrada.
* La clase HashTable implementa hasta el momento las funciones: insertar() y revisarCarga() y agrandar(), posteriormente se implementarán
* otros métodos para obtener valores.
* */

public class HashTable {
    private int tamaño;
    private Entrada[] espacios;
    private int numElementos;
    private double FACTORCARGA;

    public HashTable(int tamaño) {
        this.tamaño = tamaño;
        this.espacios = new Entrada[this.tamaño];
        this.numElementos = 0;
        this.FACTORCARGA = 0.65;

    }

    /* El método hashFunction nos permite transformar una entrada (la llave) en un hashValue(entero).
    *  Es utilizando este hashValue que accedemos al índice (al valor) del elemento que buscamos
    * @params:
    *   String s: La llave 'string' que vamos a convertir en un hashValue.
    * @returns:
    *   int hv: El entero hashValue que se produce con la función. Se utilizará para acceder al valor de la HashTable.
    *  */
    private int hashFunction(String s) {
        // Utilizamos un multiplicador para cada posición de la cadena, el cual se incrementará conforme avanzamos en la cadena
        int mult = 1;
        // Inicializamos el valor del hashValue en 0
        int hashValue = 0;
        // Utilizamos un for loop para recorrer la cadena string, convertir cada letra en un entero(ord - ASCII), multiplicar
        // este valor por el mult y sumarlo al hashValue
        for (int i = 0; i < s.length(); i++){
            // Tomamos la letra en la posición i
            char ch = s.charAt(i);
            // Transformamos la letra en un entero
            hashValue += mult * (int) ch;
            mult += 1;
        }
        return hashValue % this.tamaño;
    }

    /* El método insertar() nos sirve para colocar una nueva entrada en la HashTable.
    * Al insertar un elemento, se hace un llamado a la función revisarCarga() que se encarga de verificar que aún
    * queden espacios disponibles en la hash table.
    * Utiliza el método de linear probing para evitar colisiones.
    * @params:
    *   String llave: La llave que vamos a utilizar para localizar al elemento.
    *   int valor: El valor asociado con la llave.
    * @returns:
    *   void (También podría regresar un Booleano para comprobar si todo salió en orden).
    * */
    public void insertar(String llave, int valor){
        // Generamos una nueva entrada con llave, valor
        Entrada nuevoElemento = new Entrada(llave, valor);
        // Obtenemos el HashValue llamando a la función hashFunction
        int hashValue = hashFunction(llave);

        // Atravesamos los espacios en la HashTable hasta encontrar un espacio vacío o uno que ya esté ocupado por la misma llave
        while (this.espacios[hashValue] != null){
            // Si encontramos la llave en algún espacio, salimos del bucle
            if (this.espacios[hashValue].llave.equals(llave)){
                break;
            }
            // Vamos al siguiente espacio de forma circular
            hashValue = (hashValue + 1) % this.tamaño;
        }
        // Si el espacio estaba disponible, lo insertamos ahí e incrementamos la variable numElementos
        if (this.espacios[hashValue] == null){
            this.numElementos++;
        }
        this.espacios[hashValue] = nuevoElemento;
        revisarCarga();
    }

    /* La función revisarCarga() tiene el propósito de asegurarse que la Hash Table aún tenga suficientes espacios vacíos.
    * En la definición de la HashTable se colocó una constante llamada FACTORCARGA, que nos servirá para verificar que
    * el número de elementos/número total de espacios no supere un cierto rango (FACTORCARGA = 0.65). Si se supera este
    * valor, se llamará a la función: agrandar() que se encarga de duplicar el tamaño de la Hash Table.
    * @params:
    *   null
    * @returns:
    *   null: La función también podría regresar True o False, para fines de debuggeo. <<<<<<- NOTA ->>>>>>
    * */
    public void revisarCarga(){
        // Calculamos el factor de carga actual
        double factorActual = (double)this.numElementos/this.tamaño;
        if (factorActual > this.FACTORCARGA){
            agrandar();
            System.out.printf("Se rebasó el factor de llenado. Por ello, se ha incrementado el tamaño de la Hash Table.\nEl factor de carga antes de incrementar" +
                    "el tamaño era de %f", factorActual);
            System.out.printf("\nEl factor de llenado después de agrandar la Hash Table es: %f", (double)this.numElementos/this.tamaño);

        }
    }

    public void agrandar(){
        // Se genera una nueva HashTable con el doble de tamaño
        HashTable nuevaHashTable = new HashTable(this.tamaño * 2);

        // Atravesamos la Hash Table actual y tomamos los espacios que ya contienen entradas. Estos serán insertados en la nueva
        // Hash table
        for (int i = 0; i < this.tamaño; i++){
            // Si el espacio no está vacío
            if (this.espacios[i] != null){
                nuevaHashTable.insertar(this.espacios[i].llave, this.espacios[i].valor);
            }

        }
        // Actualizamos el tamaño y el número de espacios de la nueva HashTable
        this.tamaño = nuevaHashTable.tamaño;
        this.espacios = nuevaHashTable.espacios;

    }

    /* La función recuperar() nos regresa el valor almacenado que corresponde a la llave indicada por el usuario.
    * Para hacerlo, se debe llamar a la función hashFunction que nos otorgará el valor del hash. Si no se encuentra el
    * elemento en esa celda, se intentará con la siguiente (o hasta haber recorrido la hashTable).
    * @params:
    *   String llave: La cadena con la que almacenamos el valor dentro de la hash table.
    * @returns:
    *   Entrada
    * */
    public java.lang.Integer recuperar(String llave){
        // Primero calculamos un hashValue
        int hashValue = hashFunction(llave);

        // Atravesamos la Hash Table buscando el hashValue que obtuvimos y comparando el valor obtenido
        while (this.espacios[hashValue] != null){
            if (this.espacios[hashValue].llave.equals(llave)){
                return this.espacios[hashValue].valor;
            }
            // Si no se encuentra en la primera celda, buscamos en la siguiente de forma cíclica.
            hashValue = (hashValue + 1) % this.tamaño;
        }
        System.out.println("\nNo se encontró el valor correspondiente a: " + llave);
        return null;
    }

    /* El método insertarCuadratica() nos sirve para colocar una nueva entrada en la HashTable.
     * Al insertar un elemento, se hace un llamado a la función revisarCarga() que se encarga de verificar que aún
     * queden espacios disponibles en la hash table.
     * Utiliza el método de quadratic probing para evitar colisiones.
     * @params:
     *   String llave: La llave que vamos a utilizar para localizar al elemento.
     *   int valor: El valor asociado con la llave.
     * @returns:
     *   void (También podría regresar un Booleano para comprobar si todo salió en orden).
     * */
    public void insertarCuadratica(String llave, int valor){
        // Generamos una nueva entrada con llave, valor
        Entrada nuevoElemento = new Entrada(llave, valor);
        // Obtenemos el HashValue llamando a la función hashFunction
        int hashValue = hashFunction(llave);
        // Para implementar quadratic probing, necesitamos inicializar el primer término a la potencia 1
        int termino = 1;
        // Atravesamos los espacios en la HashTable hasta encontrar un espacio vacío o uno que ya esté ocupado por la misma llave
        while (this.espacios[hashValue] != null){
            // Si encontramos la llave en algún espacio, salimos del bucle
            if (this.espacios[hashValue].llave.equals(llave)){
                break;
            }
            // Vamos al siguiente espacio de forma circular
            hashValue = (hashValue + termino*termino) % this.tamaño;
            termino +=1;
        }
        // Si el espacio estaba disponible, lo insertamos ahí e incrementamos la variable numElementos
        if (this.espacios[hashValue] == null){
            this.numElementos++;
        }
        this.espacios[hashValue] = nuevoElemento;
        revisarCarga();
    }

    /* La función recuperarCuadrada() es el complemento de la función insertarCuadrada().
     * Nos regresa el valor almacenado que corresponde a la llave indicada por el usuario.
     * Para hacerlo, se debe llamar a la función hashFunction que nos otorgará el valor del hash. Si no se encuentra el
     * elemento en esa celda, se intentará con la siguiente (o hasta haber recorrido la hashTable).
     * @params:
     *   String llave: La cadena con la que almacenamos el valor dentro de la hash table.
     * @returns:
     *   Entrada
     * */
    public java.lang.Integer recuperarCuadrada(String llave){
        // Primero calculamos un hashValue
        int hashValue = hashFunction(llave);
        // Puesto que esta función utiliza quadratic probing, se requiere el término a la potencia n
        int termino = 1;
        // Atravesamos la Hash Table buscando el hashValue que obtuvimos y comparando el valor obtenido
        while (this.espacios[hashValue] != null){
            if (this.espacios[hashValue].llave.equals(llave)){
                return this.espacios[hashValue].valor;
            }
            // Si no se encuentra en la primera celda, buscamos en la siguiente de forma cíclica.
            hashValue = (hashValue + termino * termino) % this.tamaño;
            termino += 1;
        }
        System.out.println("\nNo se encontró el valor correspondiente a: " + llave);
        return null;
    }
}