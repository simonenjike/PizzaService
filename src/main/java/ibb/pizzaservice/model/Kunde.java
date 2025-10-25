package ibb.pizzaservice.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * JavaBean für einen Kunden im PizzaService-System.
 * 
 * Zweck: Speicherung der Kundendaten in Session oder Application Scope sowie Verwendung in JSPs
 * und Servlets.
 * 
 * Enthält alle relevanten Informationen zum Besteller (Name, Adresse, Anrede usw.).
 * 
 * Beispiel:
 * <pre>
 * Kunde kunde = new Kunde("Frau", "Anna", "Schmidt", "Bahnhofstraße", "12a", "12345", "Berlin");
 * </pre>
 * 
 * @author Simone Njike
 * @version 1.1
 * @since 24.10.2025
 */
public class Kunde implements Serializable {

    private static final long serialVersionUID = 1L;

    private String anrede;            /** Anrede des Kunden, z. B. "Herr" oder "Frau" */
    private String vorname;          /** Vorname des Kunden */
    private String nachname;       /** Nachname des Kunden */
    private String strasse;           /** Straße, in der der Kunde wohnt */
    private String hausnummer;  /** Hausnummer der Adresse */
    private String plz;                 /** Postleitzahl der Stadt */
    private String stadt;              /** Stadtname */

    /**
     * Standardkonstruktor (wichtig für JavaBeans und JSP <useBean>).
     */
    public Kunde() { }

    /**
     * Erstellt ein neues Kundenobjekt mit allen Attributen.
     * 
     * @param anrede           Anrede des Kunden (z. B. Herr, Frau)
     * @param vorname         Vorname des Kunden
     * @param nachname      Nachname des Kunden
     * @param strasse           Straße der Kundenadresse
     * @param hausnummer  Hausnummer der Kundenadresse
     * @param plz                 Postleitzahl
     * @param stadt              Stadtname
     */
    public Kunde(String anrede, String vorname, String nachname, String strasse, String hausnummer, 
                            String plz, String stadt) {
        this.anrede = anrede;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.stadt = stadt;
    }

    // ------------------------------------------------------------
    // Getter und Setter (JavaBean-konform)
    // ------------------------------------------------------------
    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    /**
     * Liefert den vollständigen Kundennamen (inkl. Anrede).
     * 
     * @return Vollständiger Name des Kunden, z. B. "Frau Anna Schmidt"
     */
    public String getKundenname() {

        StringBuilder sb = new StringBuilder();        
        if (anrede != null)        sb.append(anrede).append(" ");
        if (vorname != null)      sb.append(vorname).append(" ");
        if (nachname != null)   sb.append(nachname);         
        return sb.toString().trim();
    }

    /**
     * Liefert die vollständige Kundenadresse als formatierter String.
     * 
     * @return Adresse, z. B. "Bahnhofstraße 12a, 12345 Berlin"
     */
    public String getKundenadresse() {
        
        StringBuilder sb = new StringBuilder(); 
        sb.append(strasse).append(" ").append(hausnummer).append("<br>");
        sb.append(plz).append(" ").append(stadt);       
        return sb.toString().trim();     
    }
    
    /**
     * Gibt eine textuelle Darstellung des Kunden zurück.
     */
    @Override
    public String toString() {
        return getKundenname() + " - " + getKundenadresse();
    }    
    
    /**
     * Vergleicht zwei Kundenobjekte anhand ihrer wesentlichen Attribute.
     * 
     * @param o das zu vergleichende Objekt
     * @return true, wenn alle Felder identisch sind
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kunde kunde = (Kunde) o;
        return Objects.equals(anrede, kunde.anrede) 
               && Objects.equals(vorname, kunde.vorname)
               && Objects.equals(nachname, kunde.nachname)
               && Objects.equals(strasse, kunde.strasse)
               && Objects.equals(hausnummer, kunde.hausnummer)
               && Objects.equals(plz, kunde.plz)
               && Objects.equals(stadt, kunde.stadt);
    }
    
    /**
     * Erzeugt einen Hashcode basierend auf den Kundendaten.
     * 
     * @return Hashcode-Wert
     */
    @Override
    public int hashCode() {
        return Objects.hash(anrede, vorname, nachname, strasse, hausnummer, plz, stadt);
    }
    
}