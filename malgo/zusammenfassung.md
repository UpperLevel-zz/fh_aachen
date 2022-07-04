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

Spricht man von einem Kreis in einem gerichteten Graphen, so muss dieser nicht in eine Richtung orientiert sein

Will man andeuten, dass alle Kanten eines Kreises in die gleiche Richtung zeigen, so bezeichnet man diesen als 
gerichteten Kreis oder als Zykel.

Ein Schnitt in einem gerichteten Graphen besteht aus Kanten, die zwischen zwei Knotenmengen X und V\X 
mit X != ∅ und X ( V verlaufen, wobei die Orientierung dieser Kanten keine Rolle spielt. In einem gerichteten Schnitt 
hingegen sind alle Kanten, die zwischen X und V \X verlaufen, in eine Richtung orientiert.

Ein gerichteter Graph heißt zusammenhängend, wenn sein zugrunde liegender ungerichteter Graph zusammenhängend ist.

Ein gerichteter Graph G = (V, E) heißt stark zusammenhängend, wenn zu je zwei Knoten s, t ∈ V ein gerichteter Weg 
von s nach t und von t nach s existiert. Eine starke Zusammenhangskomponente H von G ist ein maximal stark
zusammenhängender Teilgraph von G.

Sei G ein Digraph. Dann sind die folgenden Aussagen äquivalent:
1. G ist stark zusammenhängend.
2. G enthält keinen gerichteten Schnitt.
3. G ist zusammenhängend und jede Kante liegt auf einem gerichteten Kreis.

Satz 8.2. Ein ungerichteter Graph kann genau dann zu einem stark zusammenhängenden Digraphen orientiert werden, 
wenn er zusammenhängend ist und keine Brücke enthält.

Ein gerichteter Graph G heißt genau dann eulersch, wenn ein gerichteter Kreis in G existiert, der jede Kante 
des Graphen genau einmal enthält. Ein solcher Kreis wird wieder als Euler-Tour bezeichnet.

Satz 8.3. Ein gerichteter Graph G = (V, E) ist genau dann eulersch, wenn der zugrunde liegende ungerichtete Graph 
zusammenhängend ist und wenn d+(v) = d−(v) für jeden Knoten v ∈ V gilt.

Ein vollständiger Graph mit einer Orientierung heißt Turniergraph.

Sei G ein gerichteter Gaph. Ein gerichteter Weg, der jeden Knoten genau einmal enthält, heißt Hamilton-Weg.

In jedem Turniergraphen gibt es einen gerichteten Hamilton-Weg.

Ein Turniergraph heißt transitiv, wenn aus (a, b) und (b, c) ∈ E auch (a, c) in E folgt.

In transitiven Turniergraphen gibt es nur einen einzigen Hamilton-Weg.

Jeder stark zusammenhängende Turniergraph ist hamiltonsch.

Ein König in einem Digraphen ist ein Knoten, der alle anderen Knoten über einen gerichteten Weg mit einer 
maximalen Länge von zwei erreicht.

Satz 8.7 (Hühner-Satz von Landau). In jedem Turniergraphen existiert ein König.

Wir bezeichnen mit N+v die Knoten, die direkt von v aus erreichbar sind, und mit N−v die Menge der Knoten,
die v direkt über eine Kante erreichen.

Lemma 8.8. Sei G = (V, E) ein Turniergraph und s = maxv∈V {|N + v|}. Dann ist jeder Knoten v ∈ V mit |N + v| = s 
ein König.

Lemma 8.9. Jeder Knoten eines Turniergraphen, der eine eingehende Kante hat, wird auch direkt von einem König erreicht.

Ein Knoten v ist ein Diktator, wenn er keine eingehenden Kanten hat.

Satz 8.10. Ein Turniergraph hat genau dann nur einen König, wenn es einen Diktator gibt. 
Dieser Diktator ist dann auch der einzige König.

Satz 8.11. Es existiert kein Turniergraph mit genau zwei Königen.

Lemma 8.12. Jeder Turniergraph mit n ≥ 3 hat mindestens drei Könige, falls kein Knoten einen Ingrad von Null hat.

Satz 8.13. Für jedes n ∈ N\{2, 4} existiert ein Turniergraph mit n Knoten und n Königen.

# ShortestPathTree

Sei G = (V, E) ein Digraph (gerichteter Graph) mit einer Kantenbewertung c(e) ≥ 0 für jede Kante e ∈ E und 
seien s, t ∈ V zwei Knoten (s für engl. source und t für engl. target). Ein (s, t)-Weg in G heißt kürzester Weg, 
falls sein Gewicht verglichen mit dem Gewicht jedes anderen (s, t)-Wegs minimal ist. Das Gewicht c(p) eines 
Wegs p ist definiert als
c(p) = Summe c(e)
e∈p

Lemma 9.1. Sei p ein kürzester (s, t)-Weg. Dann ist jeder zusammenhängende Teilweg von p ein kürzester Weg.

Sei G = (V, E) ein Digraph und c(e) ≥ 0 eine Kantenbewertung für jede Kante e ∈ E und s ∈ V . 
Ein spannender gerichteter Baum T mit der Wurzel s ist ein Kürzester-Wege-Baum, wenn jeder (s, v)-Weg in T 
ein kürzester Weg in G ist.

Satz 9.2 (Existenz Kürzester-Wege-Baum). Sei G = (V, E) ein Digraph mit einer positiven Kantenbewertung. 
Dann sind die folgenden Bedingungen äquivalent:
1. Für jeden Knoten v ∈ V gibt es einen (s, v)-Weg in G.
2. Es existiert ein Kürzester-Wege-Baum mit der Wurzel s in G.

Satz 9.3 (Optimalität Kürzester-Wege-Baum). Sei G = (V, E) ein Digraph mit positiven Kantengewichten c(e) 
zu jeder Kante e ∈ E und sei s ∈ V . Ferner sei T ein gerichteter spannender Baum von G. Dann sind die 
folgenden Aussagen äquivalent:
1. Der Baum T ist ein Kürzester-Wege-Baum zum Knoten s.
2. Für alle Nicht-Baumkanten e = (v, w) gilt die Dreiecksungleichung

dist(v) + c(e) ≥ dist(w).

Der Name Dreiecksungleichung rührt daher, dass man die drei Knoten s, v und w als Dreieck auffasst und dabei beachtet, 
dass in Dreiecken grundsätzlich die Summe zweier Seiten größer oder gleich der dritten Seite ist

Satz 9.4. Der Dijkstra-Algorithmus berechnet in einem Digraphen mit positiver Kantenbewertung einen Kürzesten-Wege-Baum 
zu allen Knoten, die von der Wurzel erreichbar sind.

Ob ein kürzester Weg in einem Graphen existiert, hängt also davon ab, ob die gegebene Kostenfunktion einen 
negativen Zykel, d.h. einen gerichteten Kreis mit negativem Gewicht, in dem Graphen erzeugen. Kantenkosten c 
bezeichnen wir als konservativ, falls dies nicht der Fall ist. Für konservative Kantenbewertungen gelten die 
gleichen Aussagen wie für positive.

Satz 9.5. Sei G = (V, E) ein gerichteter Graph, s, t ∈ V und c eine konservative Kantenkostenfunktion. Dann gelten 
folgende Aussagen:
1. Jeder zusammenhängende Teilweg eines kürzesten (s, t)-Wegs ist ebenfalls ein
   kürzester Weg.
2. Es existiert ein Kürzester-Wege-Baum mit der Wurzel s in G, falls alle Knoten
   v von s aus erreichbar sind.
3. Ein spannender, gerichteter Baum vom Knoten s aus ist genau dann ein
Kürzester-Wege-Baum, wenn für jede Nicht-Baumkante e = (v, w) die Dreiecksungleichung

dist(v) + c((v, w)) ≥ dist(w)

erfüllt ist.

# MaximumFlow

P ist die Menge aller einfachen gerichteten Wege, die von s nach t gehen. Wie viele Einheiten nun entlang eines
Wegs p ∈ P geschickt werden sollen, geben wir mit x(p) an. Eine solche Zuordnung bezeichnet man auch als Wegfluss.

Einen Wegfluss, der die Kapazitäten einhält, nennen wir auch einen zulässigen Wegfluss.

Die Anzahl der Einheiten, die ein Wegfluss insgesamt von s nach t verschickt, wird auch als Flusswert 
oder Wert bezeichnet.

Sei G = (V, E) ein gerichteter Graph mit oberen Kapazitäten u(e) für jede Kante e ∈ E und zwei markierte Knoten 
s, t ∈ V (kurz: Netzwerk (G, u, s, t)). Ein (s, t)-Fluss ist eine Kantenbewertung f : E → R≥0, die die Kantenkapazitäten
einhält und für die an jedem Knoten v ∈ V \{s, t} Flusserhaltung gilt. Ein (s, t)-Fluss f hält die Kantenkapazitäten 
ein, wenn f(e) ≤ u(e) ∀e ∈ E. An einem Knoten v ∈ V liegt Flusserhaltung vor, wenn

Summe f(e)
e∈δ+(v)
Minus
Summe f(e) 
e∈δ−(v)
= 0

Mit δ−(v) werden die eingehenden Kanten in v bezeichnet und mit δ+(v) die ausgehenden Kanten. Der Wert (engl. value) 
eines (s, t)-Flusses ist definiert durch

value(f) =
Summe f(e)
e∈δ+(s)
Minus
Summe f(e)
e∈δ−(s)

Die Definition hat nichts mehr mit Wegen zu tun, sondern weist jeder Kante einen bestimmten Wert zu. 
Der Wert von f soll nun angeben, wie viel der Fluss von s nach t transportiert.

Satz 10.1 (Dekompositionssatz für (s, t)-Flüsse, Ford-Fulkerson 1962). Sei f ein (s, t)-Fluss in dem 
Netzwerk (G, u, s, t). Dann gilt:
Der Fluss f lässt sich in gerichtete (s, t)-Wege und gerichtete Kreise zerlegen. Die Anzahl der Wege und 
Kreise kann auf höchstens m beschränkt werden.

Sei G = (V, E) ein Graph. Mit e = (u, v) ∈ E bezeichnen wir die Vorwärtskanten und mit ←−e = (v, u) die Rückwärtskante 
von e. Der Graph G↔ enthält alle Kanten e ∈ E und alle Rückwärtskanten. Sei nun (G, u, s, t) ein Netzwerk und f 
ein (s, t)-Fluss. Die Residualkapazität u^f ist definiert für alle Kanten in G↔ durch

u^f(e) = u(e) − f(e) für alle e ∈ E
u^f(←−e ) = f(e) für alle e ∈ E.

Der Residualgraph G^f zu dem Netzwerk (G, u, s, t) und dem Fluss f ist ein Teilgraph von G↔, der alle Kanten enthält, 
deren Residualkapazität nicht Null ist.

Satz 10.2. Sei p ein einfacher (s, t)-Weg in Gf des Netzwerks (G, u, s, t) und des Flusses f und es gelte 
γ ≤ min e∈p u^f(e). Dann ist f′(e) :=
- f(e) + γ für alle Kanten e ∈ p
- f(e) + γ für alle Kanten ←−e ∈ p
- f(e) sonst

ein (s, t)-Fluss in (G, u, s, t). Für den Wert von f′ gilt value(f′) = value(f) + γ

Nach Aussage des Satzes lohnt sich eine Flussveränderung entlang eines (s, t)-Wegs in G^f auf jeden Fall. 
Aus diesem Grund bezeichnet man einen solchen Weg auch als f-augmentierenden Weg.

Lemma 10.3. Sei (G, u, s, t) ein Netzwerk mit u(e) ∈ N für alle e ∈ E. Dann existiert ein maximaler Fluss f in G 
mit f(e) ∈ N für alle e ∈ E.

Sei G = (V, E) ein gerichteter Graph, s, t ∈ V und ∅ != X !( V mit s ∈ X und t /∈ X eine Knotenmenge. 
Dann ist die Kantenmenge δ(X) := {(u, v) ∈ E | u ∈ X und v ∈ V \X bzw. v ∈ X und u ∈ V \X}
ein (s, t)-Schnitt. Die Vorwärtskanten des Schnittes sind die Kanten δ+(X) := {(u, v) ∈ E | u ∈ X und v ∈ V \X}
und die Rückwärtskanten δ−(X) := {(u, v) ∈ E | v ∈ X und u ∈ V \X}

Die Kapazität des (s, t)-Schnitts δ(X) im Netzwerk (G, u, s, t) beträgt
cap(X) := Summe u(e)
e∈δ+(X)

Lemma 10.4. Sei (G, u, s, t) ein Netzwerk. Für jeden (s, t)-Schnitt δ(X) und jeden (s, t)-Fluss f gilt:
1. Der Flusswert ergibt sich aus der Belastung der Vorwärtskanten abzüglich der
   Belastung der Rückwärtskanten des Schnittes, d.h.
   value(f) =

2. Die Kapazität des Schnittes beschränkt den Flusswert, d.h. value(f) ≤ cap(X)

Satz 10.5 (Optimalitätskriterium für maximale Flüsse). Sei (G, u, s, t) ein Netzwerk. Ein (s, t)-Fluss f ist genau 
dann maximal, wenn es keinen f-augmentierenden (s, t)-Weg in G^f gibt.

Satz 10.6. Der Algorithmus von Ford-Fulkerson berechnet in jedem Netzwerk (G, u, s, t) einen maximalen (s, t)-Fluss.

Satz 10.7 (Max-Fluss-Min-Schnitt-Satz, Ford-Fulkerson 1956, Elias, Feinstein, Shannon 1956). 
In einem Flussnetzwerk ist der maximale Flusswert eines (s, t)-Flusses gleich der 
minimalen Kapazität eines (s, t)-Schnittes.

# Minimum Cost FLow

Gilt b(v) > 0, so stellt dieser Knoten das Angebot (z.B. die Backwaren) b(v) zur Verfügung. Ein solcher Knoten
heißt auch Quelle. 

Gilt hingegen b(v) < 0, so existiert an dem Knoten v eine Nachfrage von b(v) Einheiten. Diese Knoten werden 
auch als Senken bezeichnet.

Ein Knoten v mit b(v) = 0 hat weder ein Angebot noch eine Nachfrage

Im Folgenden gehen wir davon aus, dass Summe b(v) = 0, v∈V gilt. Eine solche Knotenbewertung 
bezeichnen wir auch als Balance.

Sei G = (V, E) ein gerichteter Graph mit oberen Kapazitäten u(e) für jede Kante e ∈ E und b eine Balance. 
Ein b-Fluss ist eine Kantenbewertung f(e), e ∈ E, die die oberen Kapazitäten einhält und für die der Einfluss in einen
Knoten v abzüglich des Ausflusses in dem Knoten v dem Wert b(v) entspricht, d.h.

X
e∈δ+(v)
f(e) −
X
e∈δ−(v)
f(e) = b(v).

Sei G = (V, E) ein gerichteter Graph mit oberen Kapazitäten u(e) ∈ N und Kosten c(e) ∈ Z für jede Kante e ∈ E. 
Außerdem sei b eine Balance. Ein kostenminimaler Fluss f ist ein b-Fluss mit minimalen Kosten. Die Kosten eines
b-Flusses sind gegeben durch c(f) = Summe c(e) · f(e), e∈E

Ein f-augmentierender Zykel ist ein gerichteter Kreis in dem Residualgraphen G^f

Verändert man einen gegebenen b-Fluss f entlang eines solchen Zykels um den
Wert γ ≤ mine∈Z u^f(e), so erhält man wieder einen zulässigen b-Fluss f′. Dabei definiert man f'(e) =
- f(e) falls e !∈ Z
- f(e) + γ falls e ∈ Z
- f(e) − γ falls ←−e ∈ Z

Residualkosten c
f
. Die Vorwärtskanten erhalten dabei die ursprünglichen Kosten und die Rückwärtskanten den negativen
Wert ihrer Vorwärtskanten, d.h.
c^f(e) = c(e), ∀e ∈ E
c^f(←−e ) = −c(e), ∀e ∈ E.

Satz 11.1 (Optimalitätskriterium für MCFP, Klein 1967). Sei G = (V, E) ein gerichteter Graph, u die 
oberen Kantenkapazitäten, c eine Kostenfunktion auf den Kanten und b eine Balance. Dann ist f genau dann 
ein kostenminimaler Fluss, wenn kein f-augmentierender Zykel Z mit negativen Kosten in G^f existiert.

Satz 11.2 (Jewell 1958, Iri 1969, Busacker & Gowen 1961). Sei f ein kostenminimaler b-Fluss in dem Digraphen G 
mit oberen Kapazitäten u, Kantenkosten c und Balancen b. Sei p ein kürzester (s, t)-Weg in G^f
bzgl. der Kosten c^f und f′ der aus der Veränderung des Flusses f entlang des Wegs p um den Wert γ ≤ mine∈p u^f(e)
entstandene b′-Fluss. Dann ist f′ ein kostenminimaler Fluss zu den Balancen b′ mit b′(v)
- b(v), v ∈ V \{s, t}
- b(v) + γ, v = s
- b(v) − γ, v = t

# Matchings

Sei G = (V, E) ein ungerichteter Graph. Ein Matching in G ist eine Kantenmenge M ⊆ E, in der je zwei Kanten 
aus M keinen gemeinsamen Endknoten haben

Ein Matching M heißt maximal, wenn kein Matching M′ mit mehr Kanten existiert.

Ein Matching M heißt perfekt, falls jeder Knoten in G zu einer Kante aus M inzident ist.

Sei G ein Graph und M ein Matching in G. Ein M-alternierender Weg in G ist ein einfacher Weg, der abwechselnd 
eine Kante aus M und eine Kante, die nicht aus M ist, enthält. Ein Knoten in G heißt (bezüglich M) exponiert,
wenn er zu keiner Kante aus M inzident ist. Einen M-alternierenden Weg nennt man M-augmentierend, 
falls beide Endpunkte exponiert sind.

Sei M ein Matching in G und p ein M-augmentierender Weg. Dann hat das Matching M′, das durch das Vertauschen 
der Matching- und Nicht-Matching-Kanten im Weg p entsteht, eine Kante mehr als M

Satz 12.2 (Berge 1957). Ein Matching M in einem Graphen ist genau dann maximal, wenn es keinen 
M-augmentierenden Weg gibt.

Ein Graph G = (V, E) heißt bipartit, wenn seine Knoten in zwei Teilmengen A und B zerlegt werden können, 
sodass für alle Kanten e = (a, b) ∈ E ein Endknoten in der Menge A und einer in der Menge B liegt.

Sei G = (V, E) ein Graph und A ⊆ V. Ein Matching M überdeckt die Knotenmenge A, wenn jeder Konten aus A mit 
einer Kante aus M inzident ist.

Die Partner Γ(X) einer Menge X sind alle Knoten in B, die mit X durch eine Kante verbunden sind, also
Γ(X) = {y ∈ B | es gibt ein x ∈ X mit (x, y) ∈ E}.

Satz 12.3 (Hall 1935). Sei G = (A∪ ̇ B, E) bipartit. Dann sind folgende Aussagen äquivalent:
1. Der Graph G hat ein Matching, das A überdeckt.
2. Für jede Menge X ⊆ A gilt |X| ≤ |Γ(X)| (Heiratsbedingung).

Ein Graph heißt k-regulär, wenn jeder Knoten den Grad k hat.

Lemma 12.4. Sei G = (A∪ ̇ B, E) ein k-regulärer bipartiter Graph. Dann hat G ein perfektes Matching.

Sei G = (V, E) ein Graph. Eine Knotenüberdeckung (engl. vertex cover) ist eine Knotenmenge V′ ⊆ V, sodass jede Kante 
von G mindestens einen Endpunkt in V′ hat

Eine Knotenüberdeckung V′ ist minimal, wenn es keine andere Knotenüberdeckung V ̃ gibt, die weniger Knoten hat als V′

Satz 12.5 (König 1931). In einem bipartiten Graphen G ist die Kardinalität eines maximalen Matching ν(G) gleich 
der Kardinalität einer minimalen Knotenüberdeckung τ (G).

Satz 12.6. Sei G = (A∪ ̇ B, E) ein bipartiter Graph und G′ = (V′, E′) mit V′ = A∪ ̇ B ∪ {s, t} und 
E′ = E ∪ {(s, v) | v ∈ A} ∪ {(v, t) | v ∈ B}. Sei f ein maximaler Fluss in G′ mit den f(e) ∈ {0, 1}. Dann ist
M = {e ∈ E | f(e) = 1} ein maximales Matching.
