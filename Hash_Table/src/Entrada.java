/* Una implementación de una entrada Hash
* La clase almacena una variable K -> Key(llave) y una otra llamada  V -> Value(valor)
*
* */
    public class Entrada<K, V> {
        private K key;
        private V value;
        private Entrada<K, V> next;

        public Entrada(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
}
