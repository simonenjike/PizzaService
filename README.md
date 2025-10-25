# 🍕 PizzaService – Java Webanwendung

**Autorin:** Simone Njike  
**Kurs:** Java Webprogrammierung Basics  
**Datum:** 24.10.2025  

---

## 📖 Projektbeschreibung

Der **PizzaService** ist eine dynamische Java-Webanwendung, mit der Kund:innen online Speisen aus einer Speisekarte bestellen können.  
Die Anwendung basiert auf **JSP**, **Servlets**, **JavaBeans** und folgt konsequent dem **MVC-Architekturmuster**.

Ziel war die Erstellung einer vollständig funktionsfähigen Webapplikation, die alle Grundlagen moderner Java-Webentwicklung demonstriert.

---

## Architektur & Aufbau

### MVC-Struktur
| Ebene | Komponenten | Beschreibung |
|-------|--------------|---------------|
| **Model** | `Kunde`, `Speise`, `Bestellposition`, `Bestellung`, `Speisekarte` | Datenklassen (JavaBeans) |
| **View** | `startseite.jsp`, `rechnung.jsp`, `kueche.jsp` | JSP-Seiten für Benutzeroberfläche |
| **Controller** | `StartseiteServlet`, `BestellungServlet`, `SpeisekarteServlet`, `SpeisekarteListener` | Steuern die Datenflüsse zwischen Model und View |

---

## Technischer Ablauf

1. **Startseite (`startseite.jsp`)**  
   - Kunden wählen Speisen und geben ihre Adressdaten ein.  
   - Formular wird an `/bestellen` (BestellungServlet) gesendet.

2. **BestellungServlet**  
   - Liest Formulardaten aus  
   - Erstellt `Kunde`-, `Bestellung`- und `Bestellposition`-Objekte  
   - Speichert Bestellung in **Session-Scope**  
   - Leitet weiter zu `rechnung.jsp`

3. **Rechnung (`rechnung.jsp`)**  
   - Zeigt die generierte Rechnung mit Kundendaten, Mengen und Gesamtpreis an.  
   - Bietet Druck- und Navigationsfunktionen.

4. **Küche (`kueche.jsp`)**  
   - Simuliert die interne Küchenansicht: zeigt, was gekocht und wohin geliefert werden soll.

5. **SpeisekarteListener**  
   - Lädt beim Serverstart eine globale `Speisekarte` in den **Application-Scope**.

---

## Scopes im Projekt

| Scope | Verwendung |
|--------|-------------|
| **Application-Scope** | `Speisekarte` – global für alle Benutzer:innen |
| **Session-Scope** | `Bestellung` – individuelle Bestellung pro Kunde |
| **Request-Scope** | Übergabe von Daten zwischen Servlet und JSP |

---

## Klassenmodell (UML-Übersicht)

```
Kunde
 ├── anrede : String
 ├── vorname : String
 ├── nachname : String
 ├── strasse : String
 ├── hausnummer : String
 ├── plz : String
 └── stadt : String

Speise
 ├── speiseId : String
 ├── name : String
 ├── beschreibung : String
 └── preis : BigDecimal

Bestellposition
 ├── speise : Speise
 ├── menge : int
 └── gesamt : BigDecimal

Bestellung
 ├── kunde : Kunde
 ├── positionen : List<Bestellposition>
 ├── ipAdresse : String
 └── sessionId : String

Speisekarte
 └── speisen : List<Speise>
```

---

## Installation & Ausführung

### Voraussetzungen
- Java JDK 17+
- Jakarta EE 10-kompatibler Server (z. B. GlassFish 6 / Payara)
- IDE wie NetBeans, IntelliJ IDEA oder Eclipse EE

### Deployment
1. Projekt in IDE importieren  
2. Server (GlassFish/Payara) starten  
3. Anwendung deployen  
4. Im Browser öffnen:  
   ```
   http://localhost:8080/PizzaService
   ```

---

## Verzeichnisstruktur

```
PizzaService/
├── src/
│   ├── ibb/pizzaservice/model/
│   ├── ibb/pizzaservice/controller/
│   └── ibb/pizzaservice/listener/
├── web/
│   ├── startseite.jsp
│   ├── rechnung.jsp
│   ├── kueche.jsp
│   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── glassfish-web.xml
└── PizzaService.pdf (Dokumentation)
```

---

## Besondere Merkmale

- ✅ Vollständige MVC-Implementierung  
- ✅ Verwendung von Scopes (Session, Application, Request)  
- ✅ JavaBeans- und JSP-Konformität  
- ✅ UTF-8-Unterstützung für internationale Zeichen  
- ✅ Dynamische Rechnungserstellung mit JSTL  

---

## Erweiterungsmöglichkeiten

- Anbindung an eine relationale Datenbank (JPA / JDBC)  
- Benutzerverwaltung & Login-System  
- Bestellstatus-Tracking  
- REST-API für mobile Anwendungen  
- Internationalisierung (mehrsprachige Oberfläche)

---

## 🧑‍💻 Autorin

**Simone Njike**  
_Kurs: Java Webprogrammierung Basics_  
_Stand: 24. Oktober 2025_

---
