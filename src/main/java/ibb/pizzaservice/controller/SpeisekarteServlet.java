package ibb.pizzaservice.controller;

import ibb.pizzaservice.model.Speisekarte;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

/**
 * Initialisiert die {@link Speisekarte} und legt sie im Application-Scope ab.
 * 
 * Dieses Servlet wird beim Start der Anwendung ausgeführt und sorgt dafür,  * dass alle JSPs und 
 * Servlets auf dieselbe Speisekarte zugreifen können.
 * 
 * Es dient somit als zentraler Datenlieferant für die View-Schicht (z. B.  * {@code startseite.jsp}, 
 * {@code rechnung.jsp}) im MVC-Muster.
 * 
 * <p><b>Ablauf:</b></p>
 * <ol>
 *   <li>Beim Start des Webservers wird {@code init()} aufgerufen</li>
 *   <li>Eine neue Instanz von {@link Speisekarte} wird erstellt</li>
 *   <li>Die Speisekarte wird im Application-Scope (ServletContext) gespeichert</li>
 *   <li>Alle JSPs können mit ${applicationScope.speisekarte} darauf zugreifen</li>
 * </ol>
 * 
 * <p>
 * Hinweis: Alternativ könnte die Initialisierung auch durch einen 
 * {@link jakarta.servlet.ServletContextListener} erfolgen (z. B. SpeisekarteListener).
 * </p>
 * 
 * @author Simone Njike
 * @version 1.1
 * @since 24.10.2025
 */
@WebServlet(urlPatterns = "/speisekarte", loadOnStartup = 1)
public class SpeisekarteServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    /**
     * Wird beim Start des Servers automatisch aufgerufen.
     * Erstellt eine {@link Speisekarte} und legt sie im Application-Scope ab.
     * 
     * @param config das ServletConfig-Objekt der Anwendung
     * @throws ServletException falls ein Initialisierungsfehler auftritt
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // Speisekarte einmalig erzeugen und im Application-Scope speichern
        Speisekarte speisekarte = new Speisekarte();
        getServletContext().setAttribute("speisekarte", speisekarte);

        // Optionales Logging für Serverkonsole:
        // System.out.println("Speisekarte erfolgreich im Application-Scope initialisiert.");
    }
    
}