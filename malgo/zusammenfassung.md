# Basics

Ein Graph G = (V, E) besteht aus einer Menge V von Knoten (engl. vertices) und Kanten E (engl. edges),
wobei jede Kante e ∈ E zwei Knoten u, v aus V miteinander verbindet.
Eine Kante zwischen den Knoten u und v kann entweder mit uv, (u, v) oder {u, v} bezeichnet werden.
Die Endknoten einer Kante e = (u, v) müssen nicht verschieden sein. Gilt u = v, so spricht man von einer Schlinge.
Haben zwei Kanten dieselben Endknoten, so sind sie parallel.
Ein einfacher Graph enthält weder Schlingen noch parallele Kanten.
Sei G = (V, E) ein Graph mit u, v ∈ V und e ∈ E.
Ein Knoten v und eine Kante e inzidieren miteinander und heißen inzident, wenn v ein Endknoten von e ist.
Gilt u, v ∈ e, dann sind u und v adjazent oder benachbart in G und heißen Nachbarn.
Zwei Kanten e, f ∈ E mit e 6= f heißen adjazent oder benachbart in G, wenn sie einen gemeinsamen Knoten haben.
Der Grad d(v) eines Knotens v ∈ V ist die Anzahl des Auftretens von v als Endknoten einer Kante.
Ist G ein einfacher Graph, so entspricht der Grad von v der Anzahl der Nachbarn von v.
Der Wert δ(G) gibt den Minimalgrad in G an, d.h. die kleinste in G vorkommende Gradzahl,
und ∆(G) den Maximalgrad eines Knoten in G, d.h. die größte in G vorkommende Gradzahl.
Die Anzahl der Knoten eines Graphen bezeichnen wir mit n.
Die Anzahl der Kanten eines Graphen bezeichnen wir mit m.

Lemma 1.1 (Handshaking-Lemma). Die Summe der Knotengrade entspricht
zweimal der Anzahl der Kanten, d.h.
Summe d(v) = 2 · |E| = 2m
v∈V

Lemma 1.2. In einem Graphen ist die Anzahl der Knoten mit ungeradem Grad gerade.

Vollständige Graphen: Ein Graph G = (V, E) heißt vollständig, wenn jeder Knoten zu jedem anderen Knoten benachbart ist.
Da der Graph also nur von der Anzahl der Knoten n abhängt, bezeichnet man ihn auch als Kn.

Bipartite Graphen: Ein Graph G = (V, E) heißt bipartit, wenn seine Knoten in zwei Teilmengen A und B zerlegt werden
können, sodass nur Kanten zwischen den beiden Teilmengen und nicht innerhalb von einer Knotenmenge verlaufen.

Ein bipartiter Graph mit der maximalen Anzahl an Kanten heißt auch vollständiger bipartiter Graph.
Da ein vollständiger bipartiter Graph durch die Größen der Mengen A und B schon vollständig beschrieben ist,
wird er auch als K|A|,|B| bezeichnet.

Sei G = (V, E) ein Graph. Ein Graph G′ = (V ′ , E′) ist ein Teilgraph von G, wenn V′eine Teilmenge von V ist
und jede Kante aus E′ auch in E enthalten ist. Enthält E′ jede Kante aus E, die zwei Knoten aus V′ verbindet,
so spricht man von einem von V′ ⊆ V induzierten Teilgraphen oder auch Untergraphen von G.

Ein Kantenzug Z in G ist eine Folge von Knoten und Kanten aus G, die sich wie folgt schreiben lässt:
Z = x0e0x1e1x2e2...xk−1ek−1xk
mit ei = (xi, xi+1). (Die Definition schließt weder doppelte Knoten noch doppelte Kanten aus.)

Ein Kantenzug heißt Weg, wenn alle Kanten verschieden sind. Die Knoten x0 und xk eines Wegs p heißen Endknoten von p,
und die Anzahl der Kanten k gibt die Länge des Wegs an. Ein Weg heißt einfach, wenn kein Knoten doppelt durchlaufen
wird.
Ein Kreis ist ein Weg mit gleichem Anfangs- und Endknoten. Ein einfacher Kreis ist ein Kreis,
in dem außer den Endknoten kein Knoten doppelt durchlaufen wird

Satz 1.3. Jeder Graph G enthält einen einfachen Weg der Länge δ(G). Falls δ(G) ≥ 2 gilt,
dann enthält er auch einen einfachen Kreis von mindestens der Länge δ(G) + 1. Dabei gibt δ(G) den Minimalgrad von G an.

Definition. Ein Graph G heißt zusammenhängend, wenn zu je zwei Knoten u, v von G ein Weg von u nach v existiert.
Ist ein Graph nicht zusammenhängend, heißt er unzusammenhängend. Eine Zusammenhangskomponente H von G ist ein
maximal zusammenhängender Teilgraph von G.

Lemma 1.4. Sei G ein Graph und seien H1 = (V1, E1) und H2 = (V2, E2) zwei jeweils zusammenhängende Teilgraphen von G.
Wir nehmen an, dass H1 und H2 mindestens einen Knoten gemeinsam haben.
Sei H die Vereinigung der beiden Graphen H1 und H2, d.h. H = (V′, E′) mit V′ = V1 ∪V2 und E′ = E1 ∪E2.
Dann ist H zusammenhängend.

Lemma 1.5. Eine Kante ist genau dann eine Brücke, wenn sie auf keinem Kreis liegt.

Sei G = (V, E) ein Graph und ∅ != X !( V eine Teilmenge der Knoten. Der Schnitt δ(X) ist die Menge aller Kanten,
die je einen Endknoten in X und V \X haben, d.h.
δ(X) := {(u, v) ∈ E | u ∈ X, v ∈ V \X}.

Lemma 1.6. Ein Graph G = (V, E) ist genau dann zusammenhängend, wenn jeder Schnitt δ(X) zu einer beliebigen
Menge ∅ != X !( V mindestens eine Kante enthält.

Ein Graph heißt Baum, falls er zusammenhängend und kreisfrei ist. Ein Graph ist kreisfrei, wenn er keinen Kreis enthält.
Die Knoten vom Grad 1 eines Baums heißen Blätter.

Lemma 1.7. Sei G ein Baum mit mindestens zwei Knoten. Dann besitzt G mindestens einen Knoten vom Grad 1,
also mindestens ein Blatt.

Satz 1.8. Sei G ein Graph mit n Knoten. Folgende Aussagen sind äquivalent:

1. G ist ein Baum (d.h. kreisfrei und zusammenhängend).
2. G ist kreisfrei und hat n − 1 Kanten.
3. G ist zusammenhängend und hat n − 1 Kanten.

Lemma 1.9. Sei G kreisfrei mit n Knoten, m Kanten und p Zusammenhangskomponenten. Dann gilt n = m + p.

# Spanning Trees

Definition. Sei G = (V, E) ein Graph. Ein Teilgraph T = (V0, E0) von G, der ein Baum ist und für den V0 = V gilt,
heißt spannender Baum.

Satz 2.1. Jeder zusammenhängende Graph enthält einen spannenden Baum.

Satz 2.2. Wenn ein Graph zusammenhängend ist, so lässt sich ein spannender Baum mit der Breitensuche berechnen.

Satz 2.3. Ein Graph ist genau dann bipartit, wenn er keinen Kreis ungerader Länge enthält.

Satz 2.4. Sei G ein zusammenhängender Graph und T ein vom BFS-Algorithmus berechneter spannender Baum mit
dem Startknoten v0. Dann ist jeder einfache Weg in T von v0 aus ein kürzester Weg in G bezüglich der Anzahl der Kanten.

Sei G = (V, E) ein Graph mit Kan-
tengewichten c(e) für jede Kante e ∈ E. Ein spannender Baum mit minimalem

Gewicht wird auch als minimal spannender Baum bezeichnet (Abb. 3.3). Das Gewicht c(T) eines spannenden Baums T
ergibt sich durch die Summe seiner Kantengewichte, d.h.
c(T) = Summe c(e).
e∈E(T)

Satz 3.1 (Caley). In einem vollständigen Graphen Kn gibt es n^n−2 unterschiedliche spannende Bäume.

Sei T = (V, E(T)) ein spannender Baum in G = (V, E) und e ∈ E\E(T) eine Nicht-Baumkante.
Der von e erzeugte Fundamentalkreis Ce bezüglich T ist der einfache eindeutige Kreis, der in T + e entsteht.

Lemma 3.2. Der von der Nicht-Baumkante e erzeugte Fundamentalkreis bezüglich des spannenden Baums T im Graphen G
ist wohldefiniert.

Sei T = (V, E(T)) ein spannender Baum von G = (V, E) und e = (u, v) ∈ E(T) eine Baumkante.
Sei Xu die Menge der Knoten, die in T − e von u aus erreichbar ist. Der von e erzeugte Fundamentalschnitt bezüglich T
ist der Schnitt δ(Xu) in G.

Lemma 3.3. Der von der Baumkante e = (u, v) induzierte Fundamentalschnitt δ(Xu) bezüglich des spannenden Baums T
im Graphen G ist wohldefiniert. Außerdem ist e die einzige Baumkante in δ(Xu).

Satz 3.4 (Optimalitätskriterium für MST). Sei T ein spannender Baum des Graphen G = (V, E) und seien c(e) Kosten
auf den Kanten e ∈ E. Dann sind die folgenden Aussagen äquivalent:

1. Der Baum T ist ein minimal spannender Baum.
2. Für jede Nicht-Baumkante e gilt, e ist eine teuerste Kante in dem von e erzeugten Fundamentalkreis
   bzgl. T (Kreiskriterium).
3. Für jede Baumkante e gilt: e ist eine billigste Kante in dem von e erzeugten Fundamentalschnitt
   bzgl. T (Schnittkriterium).

Satz 3.5. Der Algorithmus von Kruskal berechnet einen MST.

Satz 3.6. Der Algorithmus von Prim berechnet einen MST.

# Touren

## Euler-Touren und -Wege

Ein Graph heißt eulersch oder ist ein Euler-Graph, wenn es in ihm eine Euler-Tour gibt.
Ein Euler-Kreis oder eine Euler-Tour in einem Graphen ist ein Kreis, der jede Kante genau einmal enthält.

Satz 4.1 (Euler 1736). Ein zusammenhängender Graph ist genau dann eulersch, wenn jeder Knoten einen geraden Grad hat.

Lemma 4.2. Gegeben sei ein Graph, in dem alle Knoten geraden Grad haben. Sei W ein Weg mit den Endknoten v0 und vk,
der keine Kante doppelt durchläuft. Falls jede zu vk inzidente Kante auch in W vorkommt, gilt v0 = vk. Der Weg W
ist also ein Kreis.

Satz 4.3. Gegeben sei ein zusammenhängender Graph, in dem alle Kanten geraden Grad haben.
Dann berechnet der Algorithmus von Hierholzer eine Euler-Tour.

Lemma 4.4. Wenn in einem Graphen alle Knotengrade gerade sind, dann hat er keine Brücke.

Ein Weg in einem Graphen, der alle Kanten genau einmal enthält, heißt Euler-Weg.

Satz 4.5. Ein Graph enthält genau dann einen Euler-Weg, wenn maximal zwei Knoten einen ungeraden Grad haben.

Zwei Wege, die keine gemeinsame Kante besitzen, heißen kantendisjunkt.

Satz 4.6. Gegeben sei ein zusammenhängender Graph mit 2k Knoten ungeraden Grades und k ≥ 1.
Dann ist die minimale Anzahl von paarweise kantendisjunkten Wegen, die jede Kante des Graphen enthalten, k.

## Hamilton-Touren und -Wege

Ein Graph heißt hamiltonsch oder Hamilton-Graph, wenn in ihm ein Kreis existiert,
der jeden Knoten genau einmal enthält. Ein solcher Kreis heißt auch Hamilton-Kreis.

Lemma 5.1. Falls ein Graph hamiltonsch ist, dann ist er auch zusammenhängend.

Lemma 5.2. Ein hamiltonscher Graph enthält keinen Knoten mit Grad 1.

Satz 5.3. Für jeden hamiltonschen Graphen gilt: Wenn k Knoten aus dem Graphen gelöscht werden,
zerfällt der Graph in höchstens k Zusammenhangskomponenten.

Satz 5.4 (Dirac 1952). Sei G ein einfacher Graph mit mindestens drei Knoten, für den außerdem δ(G) ≥ 0, 5 · n gilt.
Dann enthält der Graph einen Hamilton-Kreis.

(Travelling-Salesman-Problem, TSP). Gegeben sei ein vollständiger Graph Kn mit positiven Gewichten c(e) auf
den einzelnen Kanten e. Gesucht ist ein Hamilton-Kreis C in Kn mit minimalem Gewicht c(C), wobei gilt:
c(C) = Summe c(e)
e∈C

Satz 5.5. Ein vollständiger Graph enthält genau 0, 5 · (n − 1)! Hamilton-Kreise.

Die Dreiecksungleichung garantiert im vollständigen Graphen, dass für alle Knoten u, v und w gilt:
c(u, v) ≤ c(u, w) + c(w, v).

Satz 5.6. Sei Kn ein vollständiger Graph mit positiven Kantengewichten c(e) für jede Kante e, wobei die Gewichte
die Dreiecksungleichung erfüllen. Sei T ′ die Lösung des Doppelten-Baum-Algorithmus und OPT eine optimale Lösung.
Dann gilt c(T′) ≤ 2 · c(OPT).

## Komplexitätstheorie

Ein Entscheidungsproblem ist ein Problem, bei dem die Antwort entweder „ ja“ oder „nein“ lautet.

Bei einem Optimierungsproblem hingegen wird die beste Lösung unter allen korrekten Lösungen ausgesucht, wie das
beim Travelling-Salesman-Problem der Fall ist.

Ein Algorithmus ist eine Handlungsvorschrift zur Lösung eines Problems bzw. einer Kategorie von Problemen.

In der Theorie will man genauer untersuchen, wie lange der Algorithmus für alle unterschiedlichen Problemgrößen braucht.
Dazu zerlegt man den Algorithmus in sogenannte elementare Rechenschritte, wie die Addition, das Schreiben auf
einen Speicherplatz oder den Zugriff auf einen Speicherplatz. Die Laufzeit eines Algorithmus ist dann
die Anzahl der benötigten elementaren Rechenschritte.

Gilt zum Beispiel, dass die Laufzeit lA(n, m) eines Algorithmus A mit der Inputgröße n, m abschätzbar ist durch
lA(n, m) ≤ an^2 + bm + c
mit beliebigen Konstanten a, b, c, so interessiert uns nur der höchste Exponent.
Wir sprechen bei A von einer quadratischen Laufzeit wegen n^2.

Einen Algorithmus, dessen Laufzeit über ein beliebiges Polynom abschätzbar ist, bezeichnet man als Algorithmus
mit polynomieller Laufzeit. Solche Algorithmen heißen auch effiziente oder gute Algorithmen.

Zur Klasse P gehören nun alle Entscheidungsprobleme, für die es einen effizienten Algorithmus gibt,
der eine Ja- oder Nein-Antwort liefert.

In der Klasse NP sind alle Entscheidungsprobleme enthalten, für die ein schneller Beweis für die Korrektheit
einer Ja-Antwort geliefert werden kann, falls die Antwort Ja lautet.

Falls P != NP gilt, werden wir Mathematiker bis ans Ende unserer Tage gebraucht. Für viele Probleme aus der Praxis
ist nämlich noch kein polynomieller Algorithmus bekannt. Gleichzeitig drängen die Praktiker aber immer mehr darauf,
ihre Entscheidungen aufgrund von mathematischen Lösungen zu treffen.

Falls P = NP gilt, sind die Mathematiker jedoch auch nicht schlagartig beschäftigungslos. Es ist keinensfalls sicher,
dass sofort effiziente Algorithmen für alle Probleme aus NP bekannt sind. Das hängt davon ab, ob der Beweis konstruktiv
ist, d.h. ein Werkzeug für polynomielle Algorithmen bereitstellt, oder nicht.

# Planarität

Darstellung des Graphen auf einem Blatt Papier oder allgemeiner im R2, wenn die Knoten nicht übereinander liegen
und die Kanten außer ihrem Start- und Endknoten keine weiteren Knoten durchqueren.

Ein Graph heißt eben oder planar dargestellt, wenn er ohne Kantenkreuzungen in der Ebene, d.h. im R2 gezeichnet ist.
Ein Graph heißt planar, wenn er eine planare Darstellung hat.

Die Fläche F3 (siehe Buch) unterscheidet sich von den anderen darin, dass sie keine äußere Begrenzung oder
Umrahmung hat. Eine solche Fläche wird auch als äußere Fläche bezeichnet. Jede Darstellung hat genau eine äußere Fläche.
Die anderen Flächen werden innere Flächen genannt und sind immer durch einen Kreis im Graphen begrenzt.

Der Grad d(F) oder die Länge f(F) einer Fläche F ist die Anzahl der sie begrenzenden Kanten, wobei in das Gebiet
ragende Kanten doppelt gezählt werden.

Ein Graph ist outerplanar, wenn er eine planare Darstellung hat, in der alle Knoten inzident zur äußeren Fläche sind.
Eine solche Darstellung bezeichnet man auch als outerplanare Darstellung.

Lemma 6.1. Sei G = (V, E) ein Graph. Der Graph G′ = (V′, E′) entstehe aus G, indem ihm ein weiterer Knoten
hinzugefügt wird, der mit jedem Knoten aus V benachbart ist, d.h. V′ = V ∪ {v} und E′ = E ∪ {(u, v) | u ∈ V }.
Dann ist G genau dann outerplanar, wenn G′ planar ist.

Einen outerplanaren Graphen bezeichnen wir in diesem Sinn als maximal, wenn keine Kante mehr hinzugefügt werden kann,
ohne die Eigenschaft der Outerplanarität zu zerstören.

Satz 6.2. Alle Knoten eines maximal outerplanaren Graphen liegen auf einem einfachen Kreis, falls n ≥ 3.

Satz 6.3. In jedem einfachen outerplanaren Graphen existiert ein Knoten v mit d(v) ≤ 2.

In einem konvexen Polyeder gilt:
H + S = A + 2,
wobei H die Anzahl der Flächen, S die Anzahl der Ecken und A die Anzahl der Kanten angibt. Ein Polyeder ist ein Körper,
der ausschließlich von geraden Flächen begrenzt wird, wie das zum Beispiel beim Würfel der Fall ist. Er heißt konvex,
wenn die gradlinige Verbindung zwischen je zwei Punkten des Polyeders ebenfalls im Polyeder liegt. Jeder dieser Körper
kann als Graph aufgefasst werden. Dabei werden die Ecken des Körpers zu Knoten und die Kanten bleiben Kanten.

Satz 6.4 (Euler-Formel). In einem planaren, zusammenhängenden Graphen mit n Knoten, m Kanten und f Flächen gilt
n − m + f = 2.

Satz 6.5. Für einen planaren Graphen mit mehr als zwei Knoten gilt: m ≤ 3n − 6.

Um die obere Schranke so allgemein wie möglich zu halten, definieren wir die Taillenweite l eines Graphen als die Länge
seines kürzesten Kreises. Als maximale Anzahl seiner Kanten erhalten wir dann:

Satz 6.6. Sei G ein planarer Graph mit der Taillenweite l, der Anzahl der Knoten
n und der Anzahl der Kanten m. Dann gilt m ≤ l/(l − 2)*(n - 2)

Lemma 6.7. Der Petersen-Graph (Abb. 6.16) ist nicht planar.

Lemma 6.8. Jeder planare Graph hat mindestens einen Knoten mit höchstens fünf Nachbarn.

Lemma 6.9. In jedem planaren Graphen gilt
Summe(bis 2m)(6 − i) · fi = 6f − 2m
i=1
wobei fi die Anzahl der Flächen mit der Länge i angibt.

Lemma 6.10. In jedem planaren zusammenhängenden Graphen mit Minimalgrad δ(G) ≥ 3 gilt:
Summe(bis 2m) (6 − i)fi ≥ 12.
i=1

Lemma 6.11. Jeder planare zusammenhängende Graph mit Minimalgrad δ(G) ≥ 3, dessen Taillenweite l größer als 4 ist,
hat mindestens zwölf Flächen der Länge 5 und somit gilt l = 5.

Satz 6.12. Der K5 ist nicht planar.

Satz 6.13. Der K3,3 ist nicht planar.

Nimm zwei durch eine Kante verbundene Knoten nehmen, ziehe die Knoten zusammen und betrachte sie jetzt als
„einen“ Knoten. Diese Operation wird auch Kontraktion genannt. Ist der neue Graph nach der Kontraktion nicht planar,
dann war es der alte auch nicht.

Graphen, die durch die beiden Operationen, also das Löschen einer Kante oder eines Knotens und durch Kontraktion
einer Kante, aus einem gegebenen Graphen G entstehen, werden als Minoren von G bezeichnet.

Satz 6.14 (Kuratowski 1930; Wagner 1937). Ein Graph ist genau dann planar, wenn er weder einen K5 noch
einen K3,3 als Minor enthält.

Satz 6.15 (Chartand and Harary 1967). Ein Graph ist genau dann outerplanar, wenn er keinen K4 oder
keinen K2,3 als Minor enthält.

# Knotenfärbung

Eine (Knoten)-Färbung eines Graphen G = (V, E) ist eine Abbildung c : V → S, wobei S eine beliebige Menge ist,
sodass c(u) 6= c(v) für alle Knoten u, v ∈ V mit (u, v) ∈ E gilt. Die Elemente von S heißen Farben.
Ein Graph ist k-färbbar, falls es eine Färbung c : V → {1, 2, . . . , k} gibt. Das kleinste k, für das
G noch k-färbbar ist, ist die chromatische Zahl χ(G) von G.

Ein Rad ist ein Kreis mit einem zusätzlichen Knoten, der mit allen anderen Knoten verbunden ist.

Satz 7.1. Die chromatische Zahl eines Graphen G liegt immer zwischen eins und ∆(G) + 1, wobei ∆(G) den Maximalgrad
von G angibt.

Satz 7.2 (Brooks 1941). Sei G ein zusammenhängender Graph mit mehr als zwei Knoten. Dann gilt χ(G) ≤ ∆(G), 
außer G ist vollständig oder ein Kreis ungerader Länge. In diesen beiden Fällen gilt χ(G) = ∆(G) + 1.

Satz 7.3 (De Morgan). Es gibt keine fünf Länder, sodass jeweils zwei Länder benachbart sind.

Sei G ein planarer Graph. Der duale Graph G∗ zu einer planaren Darstellung von G hat in jeder Fläche von G 
genau einen Knoten. Des Weiteren hat G∗ für jede Kante e ∈ E(G) genau eine Kante e′. Diese Kante e′ verbindet
die Knoten der an e anliegenden Flächen X und Y

Lemma 7.4. Der duale Graph G∗ zu einem planaren Graphen G ist immer planar.

Vier-Farben-Vermutung: Jeder planare Graph lässt sich mit vier Farben färben.

Satz 7.5 (Fünf-Farben-Satz, Heawood 1890). Jeder planare Graph ist mit fünf Farben färbbar.

Satz 7.6 (Vier-Farben-Satz). Für jeden planaren Graphen G gilt χ(G) ≤ 4.

# Gerichtete Graphen

Ein Graph G = (V, E) mit einer Orientierung heißt gerichteter Graph oder Digraph. Eine Orientierung weist jeder Kante 
eine Richtung zu, d.h. einen Anfangs- und einen Endknoten. Eine Kante, die von u nach v orientiert ist, schreiben wir 
auch als Kante (u, v). Der Graph G ohne Orientierung wird als der zugrunde liegende ungerichtete Graph bezeichnet.

Manchmal unterteilen wir den Grad in den Ingrad d−(v), d.h. die Anzahl der Kanten, die v als Endknoten haben, 
und den Ausgrad d+(v), d.h. die Anzahl der Kanten, die v als Anfangsknoten haben.
