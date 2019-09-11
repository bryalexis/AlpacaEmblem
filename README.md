# Alpaca Emblem v1.1

Alpaca Emblem es un juego de estrategia por turnos. En él existen distintos tipos de unidades e items equipables, los cuales permiten la interacción entre las unidades mismas. A continuación se explicará a grandes rasgos la dinámica del juego, junto con las funcionalidades implementadas hasta esta versión.

## Unidades
El juego cuenta actualmente con 7 diferentes unidades, las cuales se clasifican en 4 grupos principales; *carrier*, *magic*, *physical* y *healer*. Todas las unidades tienen un comportamiento global similar, sin embargo, difieren en ciertas características específicas, como en la cantidad de items que pueden portar, su ataque y contraataque, los items que pueden (o no) equipar, etc. La siguiente tabla resume de manera general lo anterior:

<table>
  <tr>
    <td><b> Unidad </b></td>
    <td><b> Grupo </b></td>
    <td><b> Max. Items </b></td>
    <td><b> Tipo de Items Equipables </b></td>
  </tr>
  
  <tr>
    <td> Alpaca </td>
    <td><i> Carrier </i></td>
    <td><i> ∞ </i></td>
    <td> Ninguno </td>
  </tr>
  
  <tr>
    <td> Cleric </td>
    <td><i> Healer </i></td>
    <td><i> 3 </i></td>
    <td> Staff </td>
  </tr>
  
  <tr>
    <td> Sorcerer </td>
    <td><i> Magic </i></td>
    <td><i> 3 </i></td>
    <td> Spell Books </td>
  </tr>
  
  <tr>
    <td> Fighter </td>
    <td rowspan="4" ><i> Physical </i></td>
    <td><i> 3 </i></td>
    <td> Axe </td>
  </tr>
  
  <tr>
    <td> Archer </td>
    <td><i> 3 </i></td>
    <td> Bow </td>
  </tr>
  
  <tr>
    <td> Hero </td>
    <td><i> 3 </i></td>
    <td> Spear </td>
  </tr>
  
  <tr>
    <td> SwordMaster </td>
    <td><i> 3 </i></td>
    <td> Sword </td>
  </tr>
  
</table>

Los distintos tipos de items se describen en la sección siguiente.
Cada item se compone de las siguientes variables:
- **maxHitPoints:** Máxima cantidad de puntos de vida que puede alcanzar la unidad.
- **currentHitPoints:** Puntos de vida que tiene la unidad, por conveniencia y comodidad en la implementación, corresponde a un  *double*.
- **items:** Lista de items que porta la unidad.
- **equippedItem:** Item equipado, debe pertenecer a la lista de items.
- **maxItems:** Máxima cantidad de items que puede portar la unidad.
- **movement:** Distancia máxima que se puede desplazar la unidad.
- **location:** Ubicación de la unidad
- **alive:** Indicador de si la unidad está viva (una unidad muere cuando alcanza los 0 *hitpoints*).
- **inCombat:** Indicador de si una unidad se encuentra en combate con otra.

### Implementación General de las Unidades
En esta versión se añadió una nueva unidad, el *sorcerer*, el cual puede usar libros de magia para atacar a sus oponentes. 

Las dinámicas e interacciones de todos las unidades son muy similares, de modo que cada unidad solo desciende de un *AbstractUnit*, clase abstracta que define los metodos en común entre las unidades.

Los campos **maxHitPoints**, **maxItems**, **alive** e **inCombat**, fueron añadidos en esta ultima versión del programa. El objetivo del primero es almacenar los máximos **hitpoints** que puede tener la unidad, para evitar *overhealing* y curaciones infinitas. La segunda nueva variable fue creada con la intención de realizar futuras validaciones para interacciones entre *item-unit* y otros. El objetivo de **alive** es facilitar el testeo de casos bordes y *setear* cuando alguien muere como un estado. Mientras que, la ultima variable pareció interesante de definir para alguna aplicación futura, donde sea importante si una unidad se encuentra en combate en cierto momento.

## Items
Existen 3 tipos de items, *weapons*, *spellbooks* y *healing*. Algunos items son fuertes (o bien débiles) contra otros y se pueden equipar a distintas unidades de acuerdo al cuadro mostrado en la sección anterior. La clasificación de los items va de acuerdo a lo que muestra el siguiente cuadro:

<table>
  <tr>
    <td><b> Item </b></td>
    <td><b> Tipo </b></td>
    <td><b> Fuerte v/s Item </b></td>
    <td><b> Debil v/s Item </b></td>
  </tr>
  
  <tr>
    <td> Axe </td>
    <td rowspan="4"><i> Weapon </i></td>
    <td> Spear </td>
    <td> Sword </td>
  </tr>
  
  <tr>
    <td> Sword </td>
    <td> Axe </td>
    <td> Spear </td>
  </tr>
  
  <tr>
    <td> Spear </td>
    <td> Sword </td>
    <td> Axe </td>
  </tr>
  
  <tr>
    <td> Bow </td>
    <td> - </td>
    <td> - </td>
  </tr>
  
  <tr>
    <td> Darkness </td>
    <td rowspan="3"> Spellbook </td>
    <td> Spirit </td>
    <td> Light </td>
  </tr>
  
  <tr>
    <td> Spirit </td>
    <td> Light </td>
    <td> Darkness </td>
  </tr>
  
  <tr>
    <td> Light </td>
    <td> Darkness </td>
    <td> Spirit </td>
  </tr>
  
  <tr>
    <td> Staff </td>
    <td> Healing </td>
    <td> - </td>
    <td> - </td>
  </tr>
  
</table>

A su vez, todos los items de tipo *spellbook* son fuertes contra los no-*spellbook* y viceversa. 
Cada item se compone de las siguientes variables:

- **name:** nombre del item.
- **power:** poder del item (cuanto daño hace o cuanto logra curar a otra unidad).
- **minRange:** rango mínimo.
- **maxRange:** rango máximo.
- **owner:** unidad que es dueña del item.

### Implementación General de los Items
Dados los tipos de items disponibles en el juego, se crea una *interfaz* para ayudar a identificar ciertos tipos de ellos, de modo que existen las interfaces **ISpellBook** y **IHealing**,  donde la utilidad del primero es poder identificar todos los items tipo mágico junto con los metodos que podrían tener en común, mientras que la segunda no otorga una utilidad muy clara de momento, pues solo existe un objeto de tipo *healing*, pero podría ser útil si eventualmente se añade otro item de este tipo.

Se usan clases abstractas para definir métodos en común entre los items del mismo tipo. Por ejemplo, recibir el ataque de una espada puede ser diferente a recibir el de un acha si el arma receptora es una lanza, pero da exactamente lo mismo si el arma receptora es un *darkness book*.

## Interacciones

### Equipar Item
Cada unidad puede equipar (o no) cierto tipo de items específicos, tal como se mostró en el cuadro de unidades. Para que una unidad no se equipe un tipo de item incorrecto, se utiliza *double dispatch*, haciendo que el item se equipe a la unidad, desambiguando así el tipo de item y la unidad en cuestión.

### Dar Item
Una unidad puede entregar un item que esté portando siempre y cuando la unidad receptora porte menos de su máximo de items y ambas unidades esten a distancia 1. Si se regala un ítem que esté equipado, la unidad quedará sin item equipado. Además, cada vez que un item cambie de unidad, su dueño será la unidad que lo porte consigo.

### Usar Items

Todas las unidades que puedan tener un item equipado, pueden utilizarlo para interactuar con otra unidad bajo ciertas restricciones:
- Ambas unidades participantes  deben estar vivas.
- El item **debe** estar equipado.
- La unidad objetivo a recibir la interacción debe estar a una distancia que se encuentre dentro del rango del item.

#### Ataque
Se rige bajo las reglas del uso de items. Cada ataque desencadena a su vez un contraataque inmediato por parte de la unidad atacada, siempre y cuando cumpla con las mismas restricciones necesarias para cualquier uso de items. Las alpacas y los *clerics* no pueden ni atacar ni contraatacar, por lo que al ser atacados, solo reciben daño y el combate finaliza de inmediato sin respuesta alguna.

Si en el ataque, una unidad recibe más daño que sus *currentHitPoints*, esta muere y pasa a estar fuera de combate.

El daño recibido por un ataque puede variar según las armas de los participantes del encuentro, en particular se pueden dar 4 casos:
1. Si el arma del atacante es fuerte contra el arma de la unidad atacada, el daño será el poder del arma de ataque x1,5.
2. Si es débil, el daño será poder del arma de ataque -20 (en particular, si esto resulta en una curación, el daño será 0).
3. Si no hay una relación en particular entre las armas, el daño será el poder del arma de ataque.
4. Si el objetivo no posee un arma equipada, el daño será también el poder del arma de ataque.

En caso de que el daño recibido sea mayor a la vida de la unidad, sus *currentHitPoints* sólo disminuirán a 0, impidiendo niveles menores a 0.

#### Curaciones
Toda unidad que equipe un item de tipo *healing* puede curar a otras unidades que se encuentren dentro del rango del item. Actualmente, existe sólo un item de este tipo, el *staff*, y sólo puede ser equipado por un *cleric*. Cuando una unidad es curada, sus *currentHitPoints* se restauran de acuerdo al poder del *staff*. Cada unidad posee un máximo de puntos de vida, si al curar estos se pudieran sobrepasar, la unidad objetivo aumentará sus *currentHitPoints* sólo hasta el máximo permitido.

##### Diseño
El uso de un item para atacar o curar a otra unidad depende principalmente de los items involucrados en el encuentro. Por lo anterior, la implementación del uso de items, junto con los daños a otras unidades, curaciones, ataques fuertes, ataques débiles, etc, está delegado al ítem mismo. Cuando una unidad usa su item contra otra, dado que cada unidad porta un tipo de item específico, no se sabe a priori con qué arma se está realizando el *ataque/curación*, pero el arma sí sabe que tipo de arma es. De modo que el método *useItemOn* de las unidades, se delega al metodo *useOn* del item. El ítem sí sabe que es, de modo que es posible usar *double dispatch* para que el ítem del enemigo (*unknown*), reciba el *ataque/curación* del primer ítem (*known*).

### Ejecución y Tests
Las funcionalidades de Alpaca Emblem v1.1 descritas previamente sólo son ejecutables a partir de los *test* implementados. Estos prueban el correcto funcionamiento de los equipamientos e intercambios de items, los ataques y las curaciones. Se cuenta con un 99% de *coverage* donde se intentó testear casos borde que se espera haber resuelto en la implementación.

Los *tests* de ataque se implementan en cada unidad por separado, ya que existen ataques normales, fuertes y débiles para cada caso. Por otro lado, los de las curaciones se implementan en la clase abstracta que testea los *healings* para cada unidad existente.

Los *test* de interacciones entre items, se prueban en las clases abstractas del tipo de item si es una interacción general (por ejemplo, recibir el efecto de una arma fisica siendo un arma mágica), y si es una interacción más específica, el testeo se realiza para el item en particular (recibir un ataque de *darkness* siendo *light*).

# Alpaca Emblem v1.1
