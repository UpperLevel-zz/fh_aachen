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

# Euler-Touren und -Wege

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

# Hamilton-Touren und -Wege

Ein Graph heißt hamiltonsch oder Hamilton-Graph, wenn in ihm ein Kreis existiert,
der jeden Knoten genau einmal enthält. Ein solcher Kreis heißt auch Hamilton-Kreis.

Lemma 5.1. Falls ein Graph hamiltonsch ist, dann ist er auch zusammenhängend.

Lemma 5.2. Ein hamiltonscher Graph enthält keinen Knoten mit Grad 1.

Satz 5.3. Für jeden hamiltonschen Graphen gilt: Wenn k Knoten aus dem Graphen gelöscht werden,
zerfällt der Graph in höchstens k Zusammenhangskomponenten.

Satz 5.4 (Dirac 1952). Sei G ein einfacher Graph mit mindestens drei Knoten, für den außerdem δ(G) ≥ 0, 5 · n gilt. 
Dann enthält der Graph einen Hamilton-Kreis.