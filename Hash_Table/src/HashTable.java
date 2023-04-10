/* Implementación de una Hash Table simple. Se hace uso de la clase Entrada.
*
* */

public class HashTable<K, V> {
    public int size;
    public Entrada<K, V>[] table;

    public HashTable(int size) {
        this.size = size;
        table = new Entrada[size];
    }


}