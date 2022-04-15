# Virtualisierung

## Grundlagen Virtualisierung

Virtualisierung bezeichnet die Nachbildung der Funktionen von physischer Hardware oder auch Software mit Hilfe von
Abstraktion. Somit wird über eine Schnittstelle (Interface) Funktionalität von der zugrundeliegenden Implementierung
getrennt. Dabei sind die Eigenschaften der Funktionen von Schnittstelle und Implementierung gleich. Ein virtuelles
Interface arbeitet demzufolge wie ein phyisches.

    Virtualität:
    spezifiziert eine über ihre Eigenschaften konkretisierte Entität, die zwar nicht physisch, aber doch in ihrer
    Funktionalität vorhanden ist. Somit ist virtuell nicht das Gegenteil von real, sondern von physisch.

Motivation für Virtualisierung

- Inkompatible Komponentenausprägungen
    - Beispiel: Ansprechen von Datenspeichern und Windows und Linux

Ein virtuelles Interface erfordert die Abbildung auf ein oder mehrere Interfaces.

### API - Application Programming Interface

- entspricht System Library Interface + User-ISA
- User-ISA:
    - unprivilegierte Instruktionen des CPU Instruction Set Architecture Interface

### System Library Interface

- z.B. glibc Aufrufe als Wrapper für OS Aufrufe

### ABI: Application Binary Interface

- entspricht System Library Interface + User-ISA

### ISA: Instruction Set Architecture der CPU

- z.B. x86 Instruction Set, PowerPC Instruction Set

### System Call Interface des OS

Arten der Virtualisierung

- Applikationsvirtualisierung
    - JVM, .NET Runtime, Smalltalk, etc.
    - Architekturunabhängiger Bytecode
    - Bytecode-Interpreter
        - plattformunabhängig
        - Ausführungsgeschwindigkeit potentiell langsamer als ISA Instruktionen
- ThinApps
    - Keine Virtualisierung in dem Sinne, da auch "physische" Komponenten mitgeliefert werden
    - Sandbox
        - Anwendung wird mit der benötigten Laufzeitumgebung in eine Sandbox gepackt
        - portabel, aber nicht zwingend Plattform unabhängig
    - Snap Apps
    - Flatpak
    - AppImage
- Emulatoren/Emulation
    - Simulation des Interface/ der Funktionsweise eines Betriebssystems/Rechnersystems
    - detaillierte interne Realisierung kann stark abweichen
    - ursprüngliches Rechnersystem und Emulator können auf ganz unterschiedlicher Hardware laufen
    - interpretiert jede Instruktion der Software des Gasts, keine direkte Ausführung auf eigentlicher Host-Hardware
    - meist wird Hardware des Originalsystems in Software emuliert (Android-Emulator, CPU-Emulation, etc.)
    - Ausführung eines unveränderten Betriebssystems
    - decode / dispatch
    - Nachteil
        - geringere Ausführungsgeschwindigkeit aufgrund der Interpretation
        - Entwicklung aufwendig, da Details des Gasts vollständig nachgebildet werden müssen
    - Beispiele: Bochs, QEmu, Commodore C64, Spielekonsolen, Smartphones, etc.
- Virtualisierung "innerhalb des OS"
    - Isolierte Laufzeitcontainer für OS-Prozesse ohne Start eines neuen OS
        - Container
        - Jails
            - Reine Virtualisierung des Userspace. Isolierte Prozesshierarchien und Dateisysteme.
            - Nur ein Betriebssystem-Kern für alle Instanzen.
            - Damit auch Einschränkung auf genau diesen "Typ von" Betriebssystem.
            - Hohe Performance bei geringem Ressourcenbedarf.
            - „Sandbox“ für die Programme und Benutzer, Sicherheitsgewinn.
- Hardware-Virtualisierung: Techniken der CPU Virtualisierung, Hypervisor-Produkte
    - einzelne Komponenten oder auch ganze Rechner-Systeme
    - Dynamisches, flexibles Abbilden der virtuellen Komponenten auf real existierende Hardware-Komponenten.
    - Entkopplung von der physisch vorhandenen Plattform
    - Transparente Verwaltung der Rechner-(HW-)Ressourcen
    - Device-Virtualisierung
        - einzelne Komponenten werden virtualisiert
        - Netzwerk-Virtualisierung
    - Virtualisierung bei ähnlicher Hardware
        - CPU, Hauptspeicherverwaltung, I/O
        - Geschwindigkeitsmaximierung durch native Ausführung des Gast-OS auf dem Host
        - Popek, Goldberg, 1974:
          „A virtual machine is defined to be an efficient, isolated duplicate of a real
          machine.“
        - Ausnahmen / Interrupts
            - als Unterbrechung der aktuell ausgeführten Anwendung, um einen in der Regel
              kurzen, aber zeitlich kritischen System-Vorgang asynchron abzuarbeiten.
              Insbesondere Abarbeitung von I/O Events: Tastatur, Netzwerk, Disk, …
              Treten "zu jeder Zeit" auf. Asynchroner Interrupt erspart regelmäßiges Polling der I/O Hardware.
        - Interrupt Request (als Auslöser) & Interrupt Handler (als Behandlung).
        - Problemstellung: Sensitive Instruktionen der CPU
            - "wenn sie Probleme machen könnte" (ungewollte Seiteneffekte, z.B. Halt-Operation)
            - Control sensitive instructions:
              Those that attempt to change the configuration of resources in the system.
            - Behavior sensitive instructions:
              Those whose behavior or result depends on the configuration of resources (e.g. on
              the processors mode).
            - --> CPU Privilegienstufen, Prozessormodus, Ringe
            - Standard auf modernen Prozessoren
            - mind. 2: supervisor (system)/User oder Kernel Mode vs. User Mode
            - x86: vier Ringe, Ring 0 (höchste) - Ring 3 (niedrigste); 1 und 2 meist ungenutzt
            - CPU Instruktionen sind den Privilegien zugeordnet
            - Eine Instruktion aus der ISA der CPU ist sensitiv, wenn das Ergebnis ihrer
              Ausführung vom aktuellen Privilegienlevel der CPU abhängt, oder diesen
              Privilegienlevel ändert.
            - Privilegierte Instruktionen und bestimmte Ressourcen (CPU Flags, Speicherverwaltung,
              I/O) sind nur im SYSTEM Mode zugreifbar
                - den Prozessor-Privilegienlevel auslesen oder ändern,
                - Register oder Speicheradressen mit direktem Ressourcenbezug bzw.
                  rechnerweiter Relevanz lesen oder ändern: z.B. Clock-Register,
                  Interrupt-Register,
                - die Speicherverwaltung direkt beeinflussen,
                - I/O Instruktionen.
            - Zugriffsschutz: Prozesse sind Ringen zugeordnet.
              Prozess kann seine Privilegienstufe nicht selbst ändern.
               Betriebssystem im SYSTEM Mode, Applikationen im USER Mode
            - Betriebssystem im SYSTEM Mode hat alle Rechte
            - Führe SW des Gasts im USER Mode aus
            - Interrupt Handler können nur vom Betriebssystem im Ring 0 registriert werden.
              Automatischer Wechsel in privilegierten Modus bei Aufruf des Interrupt-Handlers.
              Zurückwechseln in den unprivilegierten Modus beim "Return from Interrupt".
              Also wie "Call Subroutine / Return from Subroutine", aber immer plus Privilegienwechsel
            - Protection Fault (auch: Privilege Exception)
                - von der CPU automatisch ausgelöster Interrupt (trap, exception, Ausnahme) bei
                  nicht ausreichendem Privilegien-Level für Instruktion oder Ressourcenzugriff
                - wird dann im höher-privilegierten Ring abgefangen und behandelt
- System-Virtualisierung, Host-Virtualisierung
    - Hardware-Virtualisierung aller Komponenten eines Rechners: CPU, Hauptspeicher, Festplatten, Netzwerkkarte, BIOS …
    - Hypervisor --> auch Virtual Machine Monitor
    - Partitionierung
        - Aufteilung der Ressourcen der VMs
        - Hypervisor übernimmt die Host-Ressourcenaufteilung
        - Änderungen oft auch zur Laufzeit möglich
        - Nachteil: gleiche "Gestalt" von virtueller und physischer Hardware
        - andere Hardware kann nicht zur Verfügung gestellt werden
    - Geschichte
        - 1974, Goldberg und Popek
        - ab 1960, IBM Mainframes
            - einzelne Rechner als VM vs. Multiuser-OS
            - CMS (Cambridge Monitoring System): Single-User OS in den VM's
            - zLinux

- Container- / OS-Virtualisierung: Docker
- Virtualisierungs-Management: Konzepte, Architekturen
- Technische Fokusthemen bei der Host-Virtualisierung: Storage, RAM, …
- Virtualisierungs-Cluster: Konzepte für Ausfallsicherheit & Load Balancing
- Spezifikation & automatisierte Erzeugung virtueller Systeme