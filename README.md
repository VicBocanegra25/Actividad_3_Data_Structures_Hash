
# Actividad_3_Data_Structures_Hash

* Repositorio para la implementación de una Hash Table en Java para la materia de Estructura de Datos de la UNIR México.

* Se implementan dos tipos de Hash: Abierta y Cerrada.

## Clases de la Tabla Hash Cerrada

1. HashTable: Contiene los métodos insertar(), recuperar(), agrandar(), borrar, obtenerElementos().

    * Se tienen tres versiones de los métodos insertar y recuperar, ya que uno utiliza el método para evitar colisiones conocido como linear probing, mientras que los otros métodos: (insertarCuadratica y recuperarCuadratica) utilizan el método de quadratic probing para evitarlas
    (insertarDobleHash y recuperarDobleHAsh) utilizan el método de double hashing para evitar colisiones.

2. Entrada: Una clase auxiliar que nos sirve para representar cada entrada del hash table.
La llave es de tipo String, el valor a almacenar es entero (int).

3. Main: La clase principal en donde se instancian la clase HashTable y se codifica un ejemplo de la vida real que las HashTables pueden resolver.

## Aplicación práctica

* Para la aplicación práctica se diseñó una clase llamada Contactos, que simula una lista de contactos de una teléfono celular. La función principal es inicializar(), que funciona como un bucle while, preguntándole al usuario qué operación desea realizar (Agregar contacto, llamar contacto, modificar contacto, eliminar contacto o ver contactos)
