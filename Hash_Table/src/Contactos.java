import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

/* Aplicación práctica de la clase HashTable. Se simulará una agenda telefónica en donde un usuario puede guardar sus contactos.
* Esta clase instancía la clase HashTable y le pone nombres distintos a los métodos: llamarContacto, agregarContacto, modificarContacto,
* eliminarContacto y verContactos.
* La agenda se inicializa con un espacio inicial de 100 contactos.
* */
public class Contactos {
    HashTable misContactos = new HashTable(100);

    public void agregarContacto(String nombre, long telefono){
        misContactos.insertarDobleHash(nombre, telefono);
        System.out.printf("Se ha agregado el contacto: %s, con número telefónico: %d", nombre, telefono);
    }

    public java.lang.Long llamarContacto(String nombre){
        if (misContactos.recuperarDobleHash(nombre) != null) {
            System.out.printf("Llamando a %s - %d", nombre, misContactos.recuperarDobleHash(nombre) );
            // Simulando una llamada
            try {
                System.out.println("...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return misContactos.recuperarDobleHash(nombre);
        }
        System.out.printf("%s no se encuentra en tu lista de contactos.", nombre);
        return null;
    }

    public boolean modificarContacto(String nombre, long telefono) {
        if (misContactos.recuperarDobleHash(nombre) != null) {
            System.out.printf("Se modificó el contacto %s - %d", nombre, misContactos.recuperarDobleHash(nombre));
            return true;
        }
        System.out.printf("%s no se encuentra en tu lista de contactos.", nombre);
        return false;
    }

    public boolean eliminarContacto(String nombre){
        if (misContactos.recuperarDobleHash(nombre) != null) {
            System.out.printf("Se eliminó el contacto %s - %d", nombre, misContactos.recuperarDobleHash(nombre));
            return true;
        }
        System.out.printf("%s no se encuentra en tus contactos.", nombre);
        return false;
    }

    public void verContactos(){
        System.out.println("\nMostrando los contactos en la agenda:\n");
        for (int i = 0; i < misContactos.obtenerTamaño(); i++){
            try {
                System.out.printf("%s - %d", misContactos.obtenerElementos(i).llave, misContactos.obtenerElementos(i).valor);
                System.out.println("");
            } catch (NullPointerException e){
                System.out.printf("");
            }
        }
    }

    public void inicializar(){
        System.out.println("\nBienvenido a tu agenda telefónica. ");
        Scanner in = new Scanner(System.in);


        while (true) {
            System.out.println("Por favor selecciona una opción:\n" +
                    "1) - Agregar nuevo contacto\n" +
                    "2) - Llamar contacto.\n" +
                    "3) - Modificar contacto existente.\n" +
                    "4) - Eliminar contacto.\n" +
                    "5) - Ver contactos.\n>> ");
            int opcion = -1;
            try {
                opcion = in.nextInt();
                in.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida. ");
            }
                switch (opcion) {
                case 1: {
                    System.out.println("Ingrese el nombre de su nuevo contacto: ");
                    String nombre = in.nextLine();

                    System.out.println("Ingrese el teléfono del nuevo contacto: ");
                    long telefono = in.nextLong();
                    in.nextLine();
                    agregarContacto(nombre, telefono);
                    break;
                }
                case 2: {
                    System.out.println("Ingrese el nombre del contacto al que desea llamar: ");
                    String nombre = in.nextLine();
                    llamarContacto(nombre);
                    verContactos();
                    break;
                }
                case 3: {
                    System.out.println("Ingrese el nombre del contacto que desea modificar: ");
                    String nombre = in.nextLine();
                    System.out.println("Ingrese nuevo teléfono del contacto: ");

                    long telefono = in.nextLong();
                    in.nextLine(); // Consume the newline character

                    modificarContacto(nombre, telefono);
                    verContactos();
                    break;
                }
                case 4: {
                    System.out.println("Ingrese el nombre del contacto que desea eliminar: ");
                    String nombre = in.nextLine();
                    eliminarContacto(nombre);
                    verContactos();
                    break;
                }
                case 5:
                {
                    verContactos();
                    break;
                }
                default : {
                    System.out.println("Opción inválida. ");
                }
                }
            System.out.println("\nPresione 'q' para salir de la aplicación.");
            String continuar = in.nextLine();
            if (continuar.equals("q")){
                break;
            }
        }
    }
}
