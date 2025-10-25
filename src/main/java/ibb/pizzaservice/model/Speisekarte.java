package ibb.pizzaservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repräsentiert die Speisekarte des PizzaService-Systems.
 * 
 * Diese Klasse kapselt eine Liste von {@link Speise}-Objekten und stellt alle verfügbaren Gerichte zur 
 * Verfügung, die im Bestellformular angezeigt werden.
 * 
 * Die Speisen werden im Konstruktor initialisiert (könnten alternativ auch  * aus einer Datenbank oder 
 * Konfigurationsdatei geladen werden).
 * 
 * Verwendung:
 * <pre>
 * Speisekarte karte = new Speisekarte();
 * for (Speise s : karte.getSpeisen()) {
 *     System.out.println(s);
 * }
 * </pre>
 * 
 * @author Simone Njike
 * @version 1.1
 * @since 24.10.2025
 */
public class Speisekarte implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final List<Speise> speisen = new ArrayList<>();  /** Liste aller verfügbaren Speisen */
    
    /**
     * Erstellt eine neue Speisekarte mit einer vordefinierten Liste von Speisen.
     * 
     * Diese Speisen werden beim Start der Anwendung geladen und können anschließend über den 
     * Application-Scope (z. B. durch den {@link ibb.pizzaservice.listener.SpeisekarteListener}) für alle 
     * Benutzer zugänglich gemacht werden.
     */
    public Speisekarte() {
        
        speisen.add(new Speise("Pi01", "Pizzabrot", "mit Tomatensauce",
                new BigDecimal("3.50")));
        speisen.add(new Speise("Pi02", "Pizza Margherita",
                "mit Tomatensauce und frisch geriebenem Edamer-Käse",
                new BigDecimal("6.70")));
        speisen.add(new Speise("Pi03", "Pizza Salami", "mit Rindersalami",
                new BigDecimal("7.95")));
        speisen.add(new Speise("Pi04", "Pizza Spinaci", "mit Champignons, Spinat und Spiegelei",
                new BigDecimal("8.50")));
        speisen.add(new Speise("Pi05", "Pizza Bolognese", "mit Hackfleischsauce, Rindersalami und Jalapenos",
                new BigDecimal("9.50")));
        speisen.add(new Speise("Pi06", "Pizza Texas",
                "mit Zwiebeln, Jalapenos (scharf), Rindersalami und Barbecuesauce",
                new BigDecimal("9.80")));
        speisen.add(new Speise("Pi07", "Pizza Quattro Formaggi", "mit vier verschiedenen Käsesorten",
                new BigDecimal("10.90")));
        speisen.add(new Speise("Pi08", "Pizza Parma",
                "mit Schwarzwälder Schinken, frischem Rucola und geraspeltem Parmesan",
                new BigDecimal("10.95")));
    }
    
    /**
     * Gibt eine unveränderliche Liste aller Speisen zurück.
     * 
     * @return Unmodifiable List der Speisen
     */
    public List<Speise> getSpeisen() {
        return Collections.unmodifiableList(speisen);
    }
    
    /**
     * Fügt eine neue Speise zur Speisekarte hinzu.
     * 
     * @param speise neue Speise, die hinzugefügt werden soll
     * @throws IllegalArgumentException falls die Speise null ist
     */
    public void addSpeise(Speise speise) {
        if (speise == null) {
            throw new IllegalArgumentException("Speise darf nicht null sein.");
        }
        speisen.add(speise);
    }

    /**
     * Gibt eine textuelle Darstellung der gesamten Speisekarte zurück.
     * 
     * @return formatierte Liste aller Speisen als String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Speisekarte:\n");
        for (Speise s : speisen) {
            sb.append(" - ").append(s.toString()).append("\n");
        }
        return sb.toString();
    }    
    
}