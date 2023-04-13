/* En la clase Main se instanciará las clase HashTable para demostrar una aplicación real de esta estructura de datos.

* */

public class Main {
    public static void main(String[] args) {

        // Vamos a instanciar un objeto de la clase Contactos, que simula una agenda telefónica.
        System.out.println("Trabajando con una Hash Cerrada:");
        Contactos miAgenda = new Contactos();
        miAgenda.inicializar();

        // Ahora instanciamos un objeto de la clase ContactosAbierta, que simula una agenda telefónica pero que utiliza
        // una Hash Abierta
        System.out.println("Trabajando con una Hash Abierta:");
        ContactosAbierta miAgendaAbierta = new ContactosAbierta();
        miAgendaAbierta.inicializar();
    }
}