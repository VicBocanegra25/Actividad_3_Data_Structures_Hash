/* Una implementación de una entrada Hash
* La clase almacena una variable llave -> String y una otra llamada  valor -> long.
* Se eligió el tipo long porque la variable int no puede almacenar números de más de 10 dígitos y nuestra aplicación
* es una agenda telefónica.
*
* */
    public class Entrada{
        public String llave;
        public java.lang.Long valor;

        public Entrada(String llave, java.lang.Long valor) {
            this.llave = llave;
            this.valor = valor;

        }
}
