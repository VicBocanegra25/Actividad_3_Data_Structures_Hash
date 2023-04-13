/* Implementación de una Hash Table simple. Se hace uso de la clase Entrada.
* La clase HashTable implementa hasta el momento las funciones: insertar() y revisarCarga() y agrandar(), posteriormente se implementarán
* otros métodos para obtener valores.
* */

public class HashTable {
    private int tamaño;
    private Entrada[] espacios;
    private int numElementos;
    private double FACTORCARGA;

    // Agregamos un número primo para la técnica de double-hashing
    private int PRIMO;

    public HashTable(int tamaño) {
        this.tamaño = tamaño;
        this.espacios = new Entrada[this.tamaño];
        this.numElementos = 0;
        this.FACTORCARGA = 0.65;
        this.PRIMO = 3;
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
    *   long valor: El valor asociado con la llave.
    * @returns:
    *   void (También podría regresar un Booleano para comprobar si todo salió en orden).
    * */
    public void insertar(String llave, long valor){
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

    /* La función agrandar() genera una nueva HashTable con el doble de tamaño.
    params:
        ninguno
    @returns:
        void
    * */
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
    *   long: El valor almacenado en esa llave
    * */
    public java.lang.Long recuperar(String llave){
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
     *   long valor: El valor asociado con la llave.
     * @returns:
     *   void (También podría regresar un Booleano para comprobar si todo salió en orden).
     * */
    public void insertarCuadratica(String llave, long valor){
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
    public java.lang.Long recuperarCuadrada(String llave){
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

    /* El método hashFunction2 es similar a hashFunction, pero sin realizar la operación módulo (%) entre el hash value y el tamaño de la Hash Table.
     * Nos permite transformar una entrada (la llave) en un hashValue(entero).
     * Esta función auxiliar es necesaria para implementar el método anti-colisión llamado double-hashing.
     * @params:
     *   String s: La llave 'string' que vamos a convertir en un hashValue.
     * @returns:
     *   int hv: El entero hashValue que se produce con la función. Se utilizará para acceder al valor de la HashTable.
     *  */
    private int hashFunction2(String s) {
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
        return hashValue;
    }

    /* El método insertarDobleHash() nos sirve para colocar una nueva entrada en la HashTable.
     * Es similar a las otras funciones para insertar, pero hace un doble hasheo para evitar clústers primarios (con linear probing)
     * y secundarios (con quadratic probing). Llama la función hashFunction y hashFunction2 en caso de que encuentre una colisión.
     * @params:
     *   String llave: La llave que vamos a utilizar para localizar al elemento.
     *   long valor: El valor asociado con la llave.
     * @returns:
     *   void (También podría regresar un Booleano para comprobar si todo salió en orden).
     * */
    public void insertarDobleHash(String llave, long valor){
        // Generamos una nueva entrada con llave, valor
        Entrada nuevoElemento = new Entrada(llave, valor);
        // Obtenemos el HashValue llamando a la función hashFunction
        int hashValue = hashFunction(llave);
        int factor = 1;

        // Atravesamos los espacios en la HashTable hasta encontrar un espacio vacío o uno que ya esté ocupado por la misma llave
        while (this.espacios[hashValue] != null){
            // Si encontramos la llave en algún espacio, salimos del bucle
            if (this.espacios[hashValue].llave.equals(llave)){
                break;
            }
            // Realizamos el doblehasheo llamadno a la función hashFunction2
            hashValue = (hashValue + factor * (this.PRIMO - hashFunction2(llave) % this.PRIMO)) % this.tamaño;
            factor++;
        }
        // Si el espacio estaba disponible, lo insertamos ahí e incrementamos la variable numElementos
        if (this.espacios[hashValue] == null){
            this.numElementos++;
        }
        this.espacios[hashValue] = nuevoElemento;
        revisarCarga();
    }

    /* La función recuperarDobleHash() es el complemento de la función insertarDobleHash().
     * Nos regresa el valor almacenado que corresponde a la llave indicada por el usuario.
     * Para hacerlo, se debe llamar a la función hashFunction2 que nos otorgará el valor del hash.
     * @params:
     *   String llave: La cadena con la que almacenamos el valor dentro de la hash table.
     * @returns:
     *   long: El valor almacenado con esa llave
     * */
    public java.lang.Long recuperarDobleHash(String llave){
        // Primero calculamos un hashValue
        int hashValue = hashFunction(llave);
        // Puesto que esta función utiliza double hashing, se requiere el término al que se sumará el hashValue inicial
        int termino = 1;
        // Atravesamos la Hash Table buscando el hashValue que obtuvimos y comparando el valor obtenido
        while (this.espacios[hashValue] != null){
            if (this.espacios[hashValue].llave.equals(llave)){
                return this.espacios[hashValue].valor;
            }
            // Si no se encuentra en la primera celda, se debe hacer el doble hasheo
            hashValue = (hashValue + termino * (this.PRIMO - (hashFunction2(llave) % this.PRIMO))) % this.tamaño;
            termino++;
        }
        System.out.println("\nNo se encontró el valor correspondiente a: " + llave);
        return null;
    }

    /* La función de borrado utiliza la técnica de doble hasheo para encontrar la llave-valor indicada por el usuario.
    * El elemento que corresponde a la llave indicada por el usuario apuntará a null. Si no existe la llave, se imprimirá
    * un mensaje indicando que la llave no existe en la Hash Table.
    * @params:
    *   String llave: La llave indicada por el usuario, es con esta llave que se buscará el elemento a borrar.
    * @returns:
    *   void. Pero se imprime un mensaje en cualquiera de los dos casos.
    * */
    public void borrar(String llave){
        // Primero calculamos un hashValue
        int hashValue = hashFunction(llave);
        // Puesto que esta función utiliza double hashing, se requiere el término al que se sumará el hashValue inicial
        int termino = 1;

        // Inicializamos una variable que almacenará temporalmente el valor que nos regrese el hash
        long temporal;

        // Atravesamos la Hash Table buscando el hashValue que obtuvimos y comparando el valor obtenido
        while (this.espacios[hashValue] != null){
            if (this.espacios[hashValue].llave.equals(llave)){
                temporal = this.espacios[hashValue].valor;
                this.espacios[hashValue].valor = null;
                System.out.println("Se ha borrado la llave-valor" + espacios[hashValue].llave + " - " + temporal);
            }
            // Si no se encuentra en la primera celda, se debe hacer el doble hasheo
            hashValue = (hashValue + termino * (this.PRIMO - (hashFunction2(llave) % this.PRIMO))) % this.tamaño;
            termino++;
            // Se resta el número de elementos
            this.numElementos--;
        }
        System.out.println("\nNo se encontró el valor correspondiente a: " + llave);
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
        // Puesto que esta función utiliza double hashing, se requiere el término al que se sumará el hashValue inicial
        int termino = 1;

        // Inicializamos una variable que almacenará temporalmente el valor original
        long temporal;

        // Atravesamos la Hash Table buscando el hashValue que obtuvimos y comparando el valor obtenido
        while (this.espacios[hashValue] != null){
            if (this.espacios[hashValue].llave.equals(llave)){
                temporal = this.espacios[hashValue].valor;
                this.espacios[hashValue].valor = nuevoValor;
                System.out.println("\nSe ha modificado el elemento contenido en" + espacios[hashValue].llave);
                System.out.println("El valor anterior era: " + temporal + ". El nuevo valor es: " + nuevoValor);
                return true;
            }
            // Si no se encuentra en la primera celda, se debe hacer el doble hasheo
            hashValue = (hashValue + termino * (this.PRIMO - (hashFunction2(llave) % this.PRIMO))) % this.tamaño;
            termino++;
        }
        System.out.println("\nNo se encontró el valor correspondiente a: " + llave);
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
        if (index >= 0 && index <= espacios.length) {
            return espacios[index];
        }
        return null;
    }

    /* Esta clase main nos sirve para probar los métodos de la clase, pero no instancía los métodos de Contactos.java.
    * */
    public static void main(String args[]){
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
    }
}