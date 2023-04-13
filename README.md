
# Actividad_3_Data_Structures_Hash

* Repositorio para la implementación de una Hash Table en Java para la materia de Estructura de Datos de la UNIR México.

* Se implementan dos tipos de Hash: Abierta y Cerrada.

## Clases de la Tabla Hash Cerrada

1. HashTable: Contiene los métodos insertar(), recuperar(), agrandar(), borrar, obtenerElementos().

    * Se tienen tres versiones de los métodos insertar y recuperar, ya que uno utiliza el método para evitar colisiones conocido como linear probing, mientras que los otros métodos: (insertarCuadratica y recuperarCuadratica) utilizan el método de quadratic probing para evitarlas
    (insertarDobleHash y recuperarDobleHAsh) utilizan el método de double hashing para evitar colisiones.

    * Tenemos algunas funciones auxiliares como agrandar() y revisarCarga(). En una tabla Hash cerrada, conforme se insertan elementos se debe mantener control del tamaño y de los espacios disponibles. Es el propósito de estas funciones.

2. Entrada: Una clase auxiliar que nos sirve para representar cada entrada del hash table.
La llave es de tipo String, el valor a almacenar es entero (long).

3. Main: La clase principal en donde se instancia la clase Contactos y ContactosAbierta y se codifica un ejemplo de la vida real que las HashTables pueden resolver.

## Clases de la Hash Abierta

1. HashTableAbierta: Contiene los métodos insertar(), recuperar(), agrandar(), borrar, obtenerElementos().

    * Se utiliza la técnica de encadenamiento para evitar colisiones. Las cadenas son arrays de Entradas, por lo que si un HashValue está repetido, simplemente se almacenará en la celda siguiente del array. Se utiliza la función ArrayList nativa de java.

2. Entrada: Una clase auxiliar que nos sirve para representar cada entrada del hash table.
La llave es de tipo String, el valor a almacenar es entero (long).

3. Main: La clase principal en donde se instancia la clase Contactos y ContactosAbierta y se codifica un ejemplo de la vida real que las HashTables pueden resolver.

## Aplicación práctica

* Para la aplicación práctica se diseñó una clase llamada Contactos, que simula una lista de contactos de una teléfono celular. La función principal es inicializar(), que funciona como un bucle while, preguntándole al usuario qué operación desea realizar (Agregar contacto, llamar contacto, modificar contacto, eliminar contacto o ver contactos)

### Notas importantes

1. Las clases tienen su propio método main(), pero es sólo para fines de debuggeo, la clase Main es la importante para probar las aplicaciones reales (una agenda telefónica).
2. La clase Contactos y ContactosAbierta funcionan de la misma forma, sólo que su implementación por detrás es distinta. Una implementa una Hash Abierta y la otra una Hash Cerrada. El usuario no debe notar la diferencia entre una u otra y en el método main una se ejecuta seguida de la otra para que el usuario pueda comprobar su funcionamiento.

**Las clases se encuentran en la ruta: Actividad_3_Data_Structures_Hash/Hash_Table/src/**
