/* Implementación de una Hash Table simple. Se hace uso de la clase Entrada.
*
* */

public class HashTable {
    private int tamaño;
    private Entrada[] espacios;
    private int numElementos;


    public HashTable(int tamaño) {
        this.tamaño = tamaño;
        this.espacios = new Entrada[this.tamaño];
        this.numElementos = 0;


    }

    /* El método HashFunction nos permite transformar una entrada (la llave) en un hashValue(entero).
    *  Es utilizando este hashValue que accedemos al índice (al valor) del elemento que buscamos
    * @params:
    *   String s: La llave 'string' que vamos a convertir en un hashValue.
    * @returns:
    *   int hv: El entero hashValue que se produce con la función. Se utilizará para acceder al valor de la HashTable.
    *  */
    private int HashFunction(String s) {
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

    /* El método insertar() nos sirve para colocar una nueva entrada en la HashTable.
    * @params:
    *   String llave: La llave que vamos a utilizar para localizar al elemento.
    *   int valor: El valor asociado con la llave.
    * @returns:
    *   void (También podría regresar un Booleano para comprobar si todo salió en orden).
    * */
    public void insertar(String llave, int valor){
        // Generamos una nueva entrada con llave, valor
        Entrada nuevoElemento = new Entrada(llave, valor);
        // Obtenemos el HashValue llamando a la función HashFunction
        int hashValue = HashFunction(llave);

        // Atravesamos los espacios en la HashTable hasta encontrar un espacio vacío o uno que ya esté ocupado por la misma llave
        while (espacios[hashValue] != null){
            // Si encontramos la llave en algún espacio, salimos del bucle
            if (espacios[hashValue].llave.equals(llave)){
                break;
            }
            // Vamos al siguiente espacio de forma circular
            hashValue = (hashValue + 1) % tamaño;
        }
        // Si el espacio estaba disponible, lo insertamos ahí e incrementamos la variable numElementos
        if (espacios[hashValue] == null){
            numElementos++;
        }
        espacios[hashValue] = nuevoElemento;

    }

}