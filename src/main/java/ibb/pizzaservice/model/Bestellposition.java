package ibb.pizzaservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Repräsentiert eine einzelne Position in einer Bestellung.
 * 
 * Eine Bestellposition enthält eine bestimmte {@link Speise}, die bestellte Menge  * und den daraus 
 * resultierenden Gesamtpreis. Der Gesamtpreis wird automatisch berechnet, sobald Speise oder Menge 
 * geändert werden.
 * 
 * Beispiel:
 * <pre>
 * Speise speise = new Speise("Pi03", "Pizza Salami", "mit Rindersalami", new BigDecimal("7.95"));
 * Bestellposition pos = new Bestellposition(speise, 2);
 * </pre>
 * 
 * @author Simone Njike
 * @version 1.1
 * @since 24.10.2025
 */
public class Bestellposition implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Speise speise;          /** Die Speise, die in dieser Bestellposition enthalten ist */
    private int menge;               /** Die bestellte Menge dieser Speise */
    private BigDecimal gesamt;  /** Gesamtpreis für diese Position (Preis × Menge) */

    /**
     * Erstellt eine neue Bestellposition mit Speise und Menge.
     * Der Gesamtpreis wird automatisch berechnet.
     * 
     * @param speise die bestellte Speise
     * @param menge  die bestellte Menge (muss > 0 sein)
     * @throws IllegalArgumentException wenn menge < 1 oder speise null ist
     */    
    public Bestellposition(Speise speise, int menge) {
        
        if (speise == null) {
            throw new IllegalArgumentException("Speise darf nicht null sein.");
        }
        if (menge < 1) {
            throw new IllegalArgumentException("Die Menge muss mindestens 1 sein.");
        }       
        this.speise = speise;
        this.menge = menge;
        this.gesamt = speise.getPreis().multiply(BigDecimal.valueOf(menge));
    }

    /**
     * Gibt die Speise dieser Bestellposition zurück.
     * 
     * @return Speise-Objekt
     */
    public Speise getSpeise() {
        return speise;
    }
    
    /**
     * Setzt die Speise und aktualisiert automatisch den Gesamtpreis.
     * 
     * @param speise neue Speise
     * @throws IllegalArgumentException wenn speise null ist
     */
    public void setSpeise(Speise speise) {
        
        if (speise == null) {
            throw new IllegalArgumentException("Speise darf nicht null sein.");
        }        
        this.speise = speise;
        // falls man später das Gericht wechselt, Gesamtpreis neu berechnen
        this.gesamt = speise.getPreis().multiply(BigDecimal.valueOf(menge));
    }
    
    /**
     * Gibt die Menge der bestellten Speise zurück.
     * 
     * @return bestellte Menge
     */
    public int getMenge() {
        return menge;
    }
    
    /**
     * Setzt die Menge und aktualisiert den Gesamtpreis.
     * 
     * @param menge neue Menge (muss > 0 sein)
     * @throws IllegalArgumentException wenn menge < 1
     */
    public void setMenge(int menge) {
        // Validierung – damit kein negativer oder Null-Wert für menge gesetzt wird
        if (menge < 1) {
            throw new IllegalArgumentException("Die Menge muss mindestens 1 sein.");
        }
        this.menge = menge;
        // ändert man die Menge  (z. B. beim Bearbeiten der Bestellung), sollte sich auch der 
        // Gesamtpreis automatisch neu berechnen
        this.gesamt = speise.getPreis().multiply(BigDecimal.valueOf(menge));
    }
    
    /**
     * Gibt den Gesamtpreis dieser Bestellposition zurück.
     * 
     * @return Gesamtpreis als BigDecimal
     */
    public BigDecimal getGesamt() {
        return gesamt;
    }
    
    /**
     * Setzt den Gesamtpreis manuell.
     * 
     * @param gesamt neuer Gesamtpreis
     */
    public void setGesamt(BigDecimal gesamt) {
        this.gesamt = gesamt;
    }

    @Override
    public String toString() {
        return String.format("%d × %s = %.2f €", menge, speise.getName(), gesamt);
        // return menge + " × " + speise.getName() + " = " + gesamt + " €";
    }
    
    /**
     * Vergleicht zwei Bestellpositionen anhand von Speise und Menge.
     * 
     * @param o das zu vergleichende Objekt
     * @return true, wenn Speise und Menge übereinstimmen
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bestellposition that = (Bestellposition) o;
        return menge == that.menge && Objects.equals(speise, that.speise);
    }

    /**
     * Erzeugt einen Hashcode basierend auf Speise und Menge.
     * 
     * @return Hashcode-Wert
     */
    @Override
    public int hashCode() {
        return Objects.hash(speise, menge);
    }    
       
}