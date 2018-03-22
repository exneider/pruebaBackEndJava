# Ejercicio de Java
Prueba técnica java Back-end


### Consigna
Existe un call center donde hay 3 tipos de empleados: operador, supervisor y director. El proceso de la atención de una llamada telefónica en primera instancia debe ser atendida por un operador, si no hay ninguno libre debe ser atendida por un supervisor, y de no haber tampoco supervisores libres debe ser atendida por un director.

&nbsp;
> **Requerimientos**
- Diseñar el modelado de clases y diagramas UML necesarios para documentar y comunicar el diseño.
- Debe existir una clase Dispatcher encargada de manejar las llamadas, y debe contener el método dispatchCall para que las asigne a los empleados disponibles.
- La clase Dispatcher debe tener la capacidad de poder procesar 10 llamadas al mismo tiempo (de modo concurrente).
- Cada llamada puede durar un tiempo aleatorio entre 5 y 10 segundos.
- Debe tener un test unitario donde lleguen 10 llamadas.

&nbsp;
> **Extras/Plus**
- Dar alguna solución sobre qué pasa con una llamada cuando no hay ningún empleado libre.
- Dar alguna solución sobre qué pasa con una llamada cuando entran más de 10 llamadas concurrentes.
- Agregar los tests unitarios que se crean convenientes.
- Agregar documentación de código

 

### Solución
----

- Diagrama de clases

![alt text](https://raw.githubusercontent.com/exneider/pruebaBackEndJava/master/callcenterUML.bmp)

### Notas sobre la resolución
---

. Se creó la clase Dispatcher la cual se encarga de orquestar las llamadas entrantes, esta clase tiene un ExecutorService el cual se le asigna un número de Threads (Permite atender x llamadas concurrentes). Si el número de llamadas es  > al número de Threads, estas se encolarán hasta que unos de los Threads esté disponible.

. La clase Dispatcher tiene una cola de prioridad (PriorityBlockingQueue) con la lista de empleados disponibles. Cuando no se tiene ningún empleado disponible la llamada queda en espera hasta de se libere un empleado y responda.

. Cada empleado tiene una prioridad de acuerdo al tipo (Operador, Supervisor y Director). Tienen la responsabilidad de atender una llamada.

### Ejecución 
---
. Para ejecutar la simulación de la solución se creó una clase de prueba CallCenterTest.java donde se procesan 10 llamadas concurrentes. Estos valores pueden ser modificados en las constantes.
  

