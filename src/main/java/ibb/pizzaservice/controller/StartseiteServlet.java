package ibb.pizzaservice.controller;

import ibb.pizzaservice.model.Speisekarte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet zur Darstellung der Startseite des PizzaService.
 * 
 * Dieses Servlet dient als Controller im MVC-Muster und ist für das Laden
 * der {@link Speisekarte} verantwortlich. Es legt die Speisekarte in den
 * Request-Scope, damit sie in der JSP-Seite {@code startseite.jsp} angezeigt
 * werden kann.
 * 
 * Alternativ könnte die Speisekarte durch einen {@code ServletContextListener}
 * (z. B. {@link ibb.pizzaservice.listener.SpeisekarteListener}) einmalig beim
 * Start der Anwendung geladen und im Application-Scope gespeichert werden.
 * 
 * Ablauf:
 * <ol>
 *   <li>Erstellt eine neue Instanz der {@link Speisekarte}</li>
 *   <li>Setzt diese als Attribut im Request</li>
 *   <li>Leitet an {@code startseite.jsp} weiter</li>
 * </ol>
 * 
 * @author Simone Njike
 * @version 1.1
 * @since 24.10.2025
 */
@WebServlet("/startseite")
public class StartseiteServlet  extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /**
     * Behandelt GET-Anfragen, lädt die Speisekarte und leitet zur Startseite weiter.
     *
     * @param request  das {@link HttpServletRequest}-Objekt mit der aktuellen Anfrage
     * @param response das {@link HttpServletResponse}-Objekt für die Antwort
     * @throws ServletException falls beim Weiterleiten ein Servlet-Fehler auftritt
     * @throws IOException      falls beim Schreiben der Antwort ein Fehler auftritt
     */    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Speisekarte nur einmalig für diese Anfrage erzeugen
        Speisekarte speisekarte = new Speisekarte();
        request.setAttribute("speisekarte", speisekarte);

        // Weiterleitung an die JSP-Seite für die Darstellung der Startseite
        RequestDispatcher dispatcher = request.getRequestDispatcher("/startseite.jsp");
        dispatcher.forward(request, response);
    }
    
}