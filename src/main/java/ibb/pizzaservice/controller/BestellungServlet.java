package ibb.pizzaservice.controller;

import ibb.pizzaservice.model.Bestellposition;
import ibb.pizzaservice.model.Bestellung;
import ibb.pizzaservice.model.Kunde;
import ibb.pizzaservice.model.Speise;
import ibb.pizzaservice.model.Speisekarte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller-Servlet zur Verarbeitung einer Bestellung.
 * 
 * Dieses Servlet empfängt die Formulardaten aus der Seite {@code startseite.jsp},  * erstellt daraus ein 
 * {@link Bestellung}-Objekt, ergänzt Kundendaten, Session-Informationen und berechnet alle 
 * {@link Bestellposition}en. Anschließend leitet es die Daten an {@code rechnung.jsp} weiter.
 * 
 * Ablauf:
 * <ol>
 *   <li>Speisekarte aus dem Application-Scope abrufen</li>
 *   <li>Kundendaten aus Formular lesen und als {@link Kunde} speichern</li>
 *   <li>Bestellpositionen basierend auf der Speisekarte aufbauen</li>
 *   <li>Bestellung in Session und Request ablegen</li>
 *   <li>Weiterleitung an {@code rechnung.jsp}</li>
 * </ol>
 * 
 * Fehlerhafte oder leere Mengenfelder werden automatisch übersprungen.
 * 
 * @author Simone Njike
 * @version 1.1
 * @since 24.10.2025
 */
@WebServlet("/bestellen")
public class BestellungServlet  extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Verarbeitet POST-Anfragen vom Bestellformular.
     * 
     * @param request  enthält Kundendaten und Mengenangaben aus dem Formular
     * @param response Antwortobjekt
     * @throws ServletException bei Fehlern in der JSP-Weiterleitung
     * @throws IOException      bei Kommunikationsfehlern
     */    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // ------------------------------------------------------------
        // 1. Speisekarte aus Application-Scope holen
        // ------------------------------------------------------------
        Speisekarte speisekarte = (Speisekarte) getServletContext().getAttribute("speisekarte");
        if (speisekarte == null) {
            // Falls Listener nicht korrekt initialisiert wurde, neue Speisekarte erzeugen
            speisekarte = new Speisekarte();
            getServletContext().setAttribute("speisekarte", speisekarte);
        }        

        // ------------------------------------------------------------
        // 2. Bestellung und Kunde aufbauen
        // ------------------------------------------------------------
        Bestellung bestellung = new Bestellung();
        
        // Kundendaten aus Formular lesen
        Kunde kunde = new Kunde(
            request.getParameter("anrede"),
            request.getParameter("vorname"),
            request.getParameter("nachname"),
            request.getParameter("strasse"),
            request.getParameter("hausnummer"),
            request.getParameter("plz"),
            request.getParameter("stadt")
        );
        bestellung.setKunde(kunde);

        // IP-Adresse und Session-ID speichern
        bestellung.setIpAdresse(request.getRemoteAddr());
        bestellung.setSessionId(request.getSession().getId());
        
        // --------------------------------------------------------------------------------------
        // 3. Bestellpositionen basierend auf der Speisekarte erstellen
        // --------------------------------------------------------------------------------------
        for (Speise speise : speisekarte.getSpeisen()) {
            String mengeStr = request.getParameter("menge_" + speise.getSpeiseId());
            if (mengeStr != null && !mengeStr.isBlank()) {
                try {
                    int menge = Integer.parseInt(mengeStr.trim());
                    if (menge > 0) {
                        bestellung.addPosition(new Bestellposition(speise, menge));
                    }
                } catch (NumberFormatException ignored) {
                    // Ungültige Eingabe ignorieren (z. B. leeres Feld)
                }
            }
        }

        // ------------------------------------------------------------
        // 4. Bestellung speichern und an Rechnung weiterleiten
        // ------------------------------------------------------------
        // Im Request-Scope (nur für rechnung.jsp)
        request.setAttribute("bestellung", bestellung);

        // Im Session-Scope (z. B. für kueche.jsp)
        request.getSession().setAttribute("bestellung", bestellung);

        // ------------------------------------------------------------
        // 5. Weiterleitung an die Rechnungsausgabe
        // ------------------------------------------------------------
        RequestDispatcher dispatcher = request.getRequestDispatcher("rechnung.jsp");
        dispatcher.forward(request, response);

        // Optional: Logging
        // System.out.println("Neue Bestellung: " + bestellung);             
    }
    
}