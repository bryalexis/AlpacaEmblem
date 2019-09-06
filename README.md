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
    <td rowspan="6"><i> 3 </i></td>
    <td> Staff </td>
  </tr>
  
  <tr>
    <td> Sorcerer </td>
    <td><i> Magic </i></td>
    <td> Spell Books </td>
  </tr>
  
  <tr>
    <td> Archer </td>
    <td><i> Healer </i></td>
    <td> Staff </td>
  </tr>
  
  <tr>
    <td> Fighter </td>
    <td rowspan="4" ><i> Physical </i></td>
    <td> Axe </td>
  </tr>
  
  <tr>
    <td> Hero </td>
    <td> Spear </td>
  </tr>
  
  <tr>
    <td> SwordMaster </td>
    <td> Sword </td>
  </tr>
  
</table>

Los distintos tipos de items se describen en la sección siguiente.
