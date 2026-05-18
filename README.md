Tarea 2 - Simulador Gráfico de ELOTelTags y Find My
ELO329 - Diseño y Programación Orientados a Objetos
1er Semestre 2026

Integrantes:
Rodrigo Hernández
Ignacio Torres

Descripción:
Este programa simula gráficamente con JavaFX la interacción entre dispositivos EloTelTags, celulares y tablets sobre un mapa. Los equipos se mueven, rebotan en los bordes, y reportan sus posiciones a una nube. Se puede consultar la ubicación de los bienes de cada persona con la función FindMy.

Archivos entregados:
- Stage4.java         : Clase principal, lee config y arma la interfaz.
- Equipo.java         : Clase base de todos los dispositivos.
- Cellular.java       : Modelo del celular, reporta posiciones.
- CellularView.java   : Vista del celular con menú FindMy.
- EloTelTag.java      : Modelo del tag con señal de bip.
- EloTelTagView.java  : Vista del tag con radar y sonido.
- Tablet.java         : Modelo de la tablet con señal de bip.
- TabletView.java     : Vista de la tablet con radar y sonido.
- Territory.java      : Controla movimiento y detección de equipos.
- TerritoryView.java  : Vista del territorio con imagen de fondo.
- ETNube.java         : Almacén de posiciones reportadas.
- config.txt          : Archivo de configuración de ejemplo.
- Placeres.jpg        : Imagen de fondo del mapa.
- bip.wav             : Sonido del radar.

Todas las etapas se compilan y ejecutan de la misma forma, solo cambia el nombre de la clase principal (Stage1, Stage2, Stage3 o Stage4).
Compilar:
 javac --module-path "<direccion>/javafx-sdk-21.0.11/lib" --add-modules javafx.controls,javafx.media *.java
Ejecutar:
  java --module-path "<direccion>/javafx-sdk-21.0.11/lib" --add-modules javafx.controls,javafx.media --enable-native-access=javafx.graphics Stage4
La dirección corresponde donde está JavaFX

Salida:
Al ejecutar el programa se muestra una ventana con el mapa de Placeres y los equipos posicionados según el config.txt.
Al presionar Play, los equipos se mueven y rebotan en los bordes. Los tags y tablets emiten un radar visual (círculo azul que crece) y un sonido cada cierto tiempo. Al hacer click en un celular o tablet y seleccionar FindMy, se abre una ventana con la lista de bienes de esa persona y susúltimas posiciones conocidas, actualizada cada 1 segundo.

Supuestos:
- El archivo config.txt sigue el formato especificado en el  enunciado, sin líneas vacías ni datos faltantes.
- Los números en config.txt usan punto como separador decimal.  Si el sistema usa coma, el programa lo maneja internamente con useLocale(Locale.US).
- Si no se encuentra el archivo bip.wav, el programa funciona igual pero sin sonido, esto es por si el sonido no está en la carpeta.
- Los equipos comienzan a moverse solo al presionar Play.
- La ventana FindMy muestra la última posición reportada a la nube, no la posición en tiempo real del equipo.

Documentación:
La documentación del proyecto se encuentra en el archivo PDF adjunto (Documentacion_Tarea2_ELO329_Hernandez_Torres.pdf)e incluye:
- Diagrama de clases de la última etapa.
- Explicación de la interacción entre clases.
- 3 dificultades encontradas con sus soluciones.

Para generar la documentación javadoc de las clases Equipo, EloTelTag y EloTelTagView:
javadoc --module-path "<direccion>/javafx-sdk-21.0.11/lib" --add-modules javafx.controls,javafx.media -d doc *.java
La documentación se genera en su carpeta.
