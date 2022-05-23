# Minimum Cost Flows

### Recap Maximale Flüsse/Einleitung/Begriffserläuterung

****

    https://cs.uni-paderborn.de/fileadmin/informatik/fg/ti/Lehre/WS_2016/ProEA/Teil8.pdf
    https://www.youtube.com/watch?v=5Q3rFLYcfjk
    https://i11www.iti.kit.edu/_media/teaching/winter2009/algotech/skript_r7.pdf
    http://www-home.htwg-konstanz.de/~bittel/ain_alda/Vorlesung/10_Graphen_Fluesse.pdf
    https://www.youtube.com/watch?v=7E9OWqcMTeI
    https://de-academic.com/dic.nsf/dewiki/1176117
    https://graphonline.ru/de/

## Optimalitätskriterium

- Residualgraph
- (f-)Augmentierte Wege/Zykel
- Kostenfunktion
- Abbildung 11.3 / Seite 190
- Optimalität nachweisen
- Satz 11.1: Optimalität
- Übungen einbringen

## Kostenfunktion

Kostenfunktion für zum Beispiel den Transport von Gütern, die günstigste Möglichkeit für den Transport von einem oder
Mehren Startpunkten (Quellen) durch ein Netzwerk zu einem oder mehreren Zielpunkten (Senken) zu bestimmen. Je nach
Struktur der Kostenfunktion ist der Problem np-hart oder es existieren polynomiell exakte Algorithmen. Im Allgemeinen
ist die Lösung von Min-Cost-Flow Problemen nicht eindeutig.

Bei einem Min-Cost-Flow Problem sucht man eine Flussfunktion, welche die Kostenformel minimiert. Lagrange-Multiplikator
nicht praktikabel

Lineare Kostenfunktion

- Linear Programing
- Simplex Algorithm

Konvexe Kostenfunktion

- Capacity-Scaling Algorithm --> Linearisierung

Allgemeine nichtlineare Kostenfunktion

- Für allgemeine nichtlineare Kostenfunktionen ist das Finden einer Lösung NP-schwer.

## Residuale Graphen

"Der Restgraph (Residualgraph) eines Flussnetzes ist ein Graph, der zusätzliche mögliche Flüsse anzeigt. Wenn es einen
Pfad von der Quelle zur Senke im Restgraphen gibt, dann ist es möglich, einen Fluss hinzuzufügen. Jede Kante eines
Restgraphen hat einen Wert, der Rest Kapazität, die gleich der ursprünglichen Kapazität der Kante abzüglich des
aktuellen Flusses ist."

https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/ (Stand: 23. Feb. 2022)
https://cs.stackexchange.com/questions/55041/residual-graph-in-maximum-flow/ (Stand: 22. May. 2017/ Mario Cervera)

## Augmentierende Wege

Ein Weg im Residualnetzwerk heißt augmentierender Weg oder auch:
Ein Weg von der Quelle zur Senke, entlang dessen der Fluss weiter vergrößert werden kann, ohne die
Kapazitätsbeschränkungen der Kanten zu verletzen. Ein solcher Weg wird auch als augmentierender Pfad bezeichnet.

https://de.wikipedia.org/wiki/Algorithmus_von_Ford_und_Fulkerson (Stand: 26. Jan. 2022)
https://de.wikipedia.org/wiki/Fl%C3%BCsse_und_Schnitte_in_Netzwerken (Stand: 4. Apr. 2022)

Statt eines f-augmentierenden Wegs (Maximale Flüsse) definieren wir nun einen f-augmentierenden Zykel Z. Ein
f-augmentierender Zykel ist ein gerichteter Kreis in dem Residualgraphen

Abb. 11.4: Buch

    Satz 11.1 (Optimalitätskriterium für MCFP, Klein 1967). Sei G = (V, E) ein gerichteter Graph, u die oberen
    Kantenkapazitäten, c eine Kostenfunktion auf den Kanten und b eine Balance. Dann ist f genau dann ein kostenminimaler
    Fluss, wenn kein f-augmentierender Zykel Z mit negativen Kosten in Gf existiert.

### Überleitung zu Algorithmen

- Cycle cancelling algorithms (negative cycle optimality)
- Successive Shortest Path algorithms (reduced cost optimality)
- Out-of-Kilter algorithms (complimentary slackness)
- Network Simplex
- Push/Relabel Algorithms
- Dual Cancel and Tighten
- Primal-Dual