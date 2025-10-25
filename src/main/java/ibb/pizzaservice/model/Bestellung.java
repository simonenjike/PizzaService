package ibb.pizzaservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Repräsentiert eine komplette Bestellung im PizzaService-System.
 * 
 * Eine Bestellung besteht aus:
 * <ul>
 *   <li>einem {@link Kunde}</li>
 *   <li>mehreren {@link Bestellposition}en</li>
 *   <li>der IP-Adresse des Bestellers</li>
 *   <li>der Session-ID zur Identifikation während der Bestellung</li>
 * </ul>
 * 
 * Diese Klasse dient als JavaBean und wird in der Regel im Session-Scope  * gespeichert, um während 
 * des Bestellvorgangs erhalten zu bleiben.
 * 
 * Beispiel:
 * <pre>
 * Bestellung bestellung = new Bestellung();
 * bestellung.setKunde(kunde);
 * bestellung.addPosition(new Bestellposition(pizza, 2));
 * </pre>
 * 
 * @author Simone Njike
 * @version 1.1
 * @since 24.10.2025
 */
public class Bestellung implements Serializable {

    private static final long serialVersionUID = 1L;

    private Kunde kunde;        /** Kunde, der die Bestellung aufgegeben hat */
    private List<Bestellposition> positionen = new  ArrayList<>();  /** Liste aller bestellten Positionen */
    private String ipAdresse;    /** IP-Adresse des Bestellers */
    private String sessionId;    /** Session-ID, zur Zuordnung innerhalb der Benutzersitzung */

    /**
     * Standardkonstruktor (wichtig für JavaBeans und JSP <useBean>).
     */    
    public Bestellung() { }
    
    // ------------------------------------------------------------
    // Getter und Setter
    // ------------------------------------------------------------    
    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public List<Bestellposition> getPositionen() {
        return positionen;
    }

    public void setPositionen(List<Bestellposition> positionen) {
        this.positionen = positionen;
    }

    public String getIpAdresse() {
        return ipAdresse;
    }

    public void setIpAdresse(String ipAdresse) {
        this.ipAdresse = ipAdresse;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Berechnet den Gesamtpreis der Bestellung, indem alle Positionen summiert werden.
     * 
     * @return Gesamtpreis der Bestellung als BigDecimal
     */
    public BigDecimal getGesamtpreis() {
        
        BigDecimal summe = BigDecimal.ZERO;
        
        for ( Bestellposition pos : positionen) {
            if (pos != null && pos.getGesamt() != null) {
                summe = summe.add(pos.getGesamt());
            }            
        }
        return summe;
    }
    
    /**
     * Fügt eine neue Bestellposition zur Liste hinzu.
     * 
     * @param pos die hinzuzufügende Bestellposition
     */
    public void addPosition(Bestellposition pos) {
        if (pos == null) {
            throw new IllegalArgumentException("Bestellposition darf nicht null sein.");
        }
        if (positionen == null) {
            positionen = new ArrayList<>();
        }        
        this.positionen.add(pos);
    }
    
    /**
     * Gibt eine textuelle Darstellung der Bestellung zurück.
     * 
     * @return Formatierte Zeichenkette mit Kunde, Positionen und Gesamtpreis
     */    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Bestellung von: ");
        if (kunde != null) {
            sb.append(kunde.getKundenname()).append("\n");
        }

        for (Bestellposition pos : positionen) {
            sb.append("  ").append(pos.toString()).append("\n");
        }

        sb.append("Gesamtpreis: ").append(getGesamtpreis()).append(" €");
        return sb.toString();
    }
    
    /**
     * Vergleicht zwei Bestellungen anhand von Session-ID und Kunde.
     * 
     * @param o das zu vergleichende Objekt
     * @return true, wenn Session-ID und Kunde übereinstimmen
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bestellung that = (Bestellung) o;
        return Objects.equals(sessionId, that.sessionId)
                && Objects.equals(kunde, that.kunde);
    }

    /**
     * Erzeugt einen Hashcode basierend auf Session-ID und Kunde.
     * 
     * @return Hashcode-Wert
     */
    @Override
    public int hashCode() {
        return Objects.hash(sessionId, kunde);
    }    
    
}