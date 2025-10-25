package ibb.pizzaservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Repräsentiert eine einzelne Speise im PizzaService-System.
 * 
 * Eine Speise besteht aus einer eindeutigen ID, einem Namen, einer Beschreibung und einem Preis.
 * Sie wird in der {@link Speisekarte} verwaltet und kann als Bestandteil einer {@link Bestellposition} 
 * in einer {@link Bestellung} vorkommen.
 * 
 * Beispiel:
 * <pre>
 * Speise speise = new Speise("Pi03", "Pizza Salami", "mit Rindersalami", new BigDecimal("7.95"));
 * </pre>
 * 
 * @author Simone Njike
 * @version 1.0
 * @since  24.10.2025
 */
public class Speise implements Serializable {

    private static final long serialVersionUID = 1L;

    private String speiseId;            /** Eindeutige ID der Speise, z. B. "P03" */
    private String name;                /** Name der Speise, z. B. "Pizza Salami" */
    private String beschreibung;    /** Beschreibung der Speise, z. B. "mit Rindersalami" */
    private BigDecimal preis;         /** Preis der Speise in Euro (verwendet BigDecimal für Genauigkeit bei Geldwerten) */
    
    /**
     * Standardkonstruktor (wichtig für JavaBeans und JSP <useBean>).
     */
    public Speise() { }

    /**
     * Erstellt eine neue Speise mit allen Attributen.
     * 
     * @param speisetId         eindeutige ID der Speise
     * @param name               Name der Speise
     * @param beschreibung   optionale Beschreibung
     * @param preis                Preis der Speise in Euro
     */
    public Speise(String speisetId, String name, String beschreibung, BigDecimal preis) {
        this.speiseId = speisetId;
        this.name = name;
        this.beschreibung = beschreibung;
        this.preis = preis;
    }

    /**
     * Gibt die eindeutige ID der Speise zurück.
     * 
     * @return ID der Speise
     */
    public String getSpeiseId() {
        return speiseId;
    }

    /**
     * Setzt die eindeutige ID der Speise.
     * 
     * @param speiseId neue ID der Speise
     */
    public void setSpeiseId(String speiseId) {
        this.speiseId = speiseId;
    }
    
    /**
     * Gibt den Namen der Speise zurück.
     * 
     * @return Name der Speise
     */    
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Speise.
     * 
     * @param name neuer Name der Speise
     */    
    public void setName(String name) {
        this.name = name;
    }

     /**
     * Gibt die Beschreibung der Speise zurück.
     * 
     * @return Beschreibung der Speise
     */   
    public String getBeschreibung() {
        return beschreibung;
    }

     /**
     * Setzt die Beschreibung der Speise.
     * 
     * @param beschreibung neue Beschreibung (z. B. Zutaten)
     */   
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

     /**
     * Gibt den Preis der Speise zurück.
     * 
     * @return Preis der Speise als BigDecimal
     */   
    public BigDecimal getPreis() {
        return preis;
    }

    /**
     * Setzt den Preis der Speise.
     * 
     * @param preis neuer Preis in Euro (BigDecimal)
     */   
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    /**
     * Gibt eine textuelle Darstellung der Speise zurück.
     * 
     * @return formatierte Zeichenkette mit ID, Name und Preis
     */
    @Override
    public String toString() {
        return String.format("%s - %s (%.2f €)", speiseId, name, preis);
        //return speiseId + " : " + name + " à " + preis + " €";
    }
      
    /**     
     * Vergleicht zwei Speiseobjekte anhand ihrer ID.
     * 
     * @param obj das zu vergleichende Objekt
     * @return true, wenn die IDs identisch sind
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Speise speise = (Speise) obj;
        return Objects.equals(speiseId, speise.speiseId);
    }

    /**
     * Erzeugt einen Hashcode basierend auf der Speise-ID.
     * 
     * @return Hashcode-Wert
     */
    @Override
    public int hashCode() {
        return Objects.hash(speiseId);
    }   
    
}