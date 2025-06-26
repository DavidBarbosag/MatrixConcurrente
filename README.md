# MatrixConcurrente

Simulación concurrente en consola inspirada en *The Matrix*. Neo debe llegar a un teléfono evitando a los Agentes en un entorno lleno de muros. Todos los elementos se mueven concurrentemente con hilos y lógica de búsqueda BFS.

---

## Características

-  Movimiento automático de Neo y Agentes usando **BFS (Breadth-First Search)**.
-  Gestión de concurrencia con múltiples hilos.
-  Muros y teléfonos colocados aleatoriamente en el tablero.
- Tablero de juego impreso por consola en tiempo real.

---

##  Requisitos

- Java 17 o superior
- Maven 3.x
- Spring Boot 3.x

---

## Instalación y Ejecución

1. Clona el repositorio:
   git clone https://github.com/tu-usuario/MatrixConcurrente.git
   
   cd MatrixConcurrente
  
   
2. Compila el proyecto:

    mvn clean package

3. Ejecuta el .jar generado:

    java -jar target/MatrixCon-0.0.1-SNAPSHOT.jar


## Estructura del Juego
Neo: personaje principal, busca llegar a un teléfono.

Agent: persigue a Neo.

Phone: objetivo de Neo.

Wall: obstáculo no transitable.

GameBoard: mantiene el estado del tablero y sincroniza el acceso concurrente.

PathFinder: algoritmo BFS para calcular rutas óptimas.

## Estructura del Proyecto

src/

├── main/

│   ├── java/

│   │   └── com.eci.ARSW.MatrixConcurrente.MatrixCon/

│   │       ├── MatrixConApplication.java

│   │       ├── GameBoard.java

│   │       ├── GameElement.java

│   │       ├── Neo.java

│   │       ├── Agent.java

│   │       ├── Phone.java

│   │       ├── Wall.java

│   │       ├── Position.java

│   │       ├── PathFinder.java

│   │       └── GameInitializer.java


## Concurrencia y Sincronización

Este juego simula múltiples entidades móviles que actúan al mismo tiempo. Para lograrlo, se utiliza programación concurrente en Java:

* Cada personaje móvil (Neo y los Agentes) se ejecuta en su propio hilo (Thread), lo que permite que actúen de manera paralela.

* El tablero (GameBoard) es compartido entre todos los hilos, por lo que se protege con un bloque synchronized y un lock para asegurar que solo un hilo pueda leer o modificar una celda a la vez.

* Las operaciones críticas, como verificar si una celda está libre (isFree), mover elementos (moveElement), y obtener objetos en una posición (getAt), están todas sincronizadas para evitar condiciones de carrera.

La sincronización asegura que:

* Neo no se mueva a una posición ocupada por otro Agente.

* Los Agentes no colisionen entre sí.

* El estado del tablero siempre se mantenga coherente al imprimirlo o al buscar caminos.


## Autor
David Alfonso Barbosa Gómez
