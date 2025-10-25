package ibb.pizzaservice.listener;

import ibb.pizzaservice.model.Speisekarte;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application-Listener zur Initialisierung der {@link Speisekarte}.
 * 
 * Diese Klasse implementiert {@link ServletContextListener} und sorgt dafür,  dass die 
 * {@link Speisekarte} beim Start der Webanwendung automatisch erstellt und im Application-Scope 
 * (ServletContext) gespeichert wird.
 * 
 * Damit steht die Speisekarte in allen JSPs und Servlets über  * {@code ${applicationScope.speisekarte}} 
 * zur Verfügung.
 * 
 * <p><b>Ablauf:</b></p>
 * <ol>
 *   <li>Beim Start des Servers wird {@code contextInitialized()} aufgerufen</li>
 *   <li>Eine neue {@link Speisekarte} wird erzeugt</li>
 *   <li>Diese wird im Application-Scope abgelegt</li>
 * </ol>
 * 
 * <p><b>Beispiel in JSP:</b></p>
 * <pre>
 * &lt;c:forEach var="speise" items="${applicationScope.speisekarte.speisen}"&gt;
 *     ${speise.name} - ${speise.preis} €
 * &lt;/c:forEach&gt;
 * </pre>
 * 
 * @author Simone Njike
 * @version 1.1
 * @since 24.10.2025
 */
@WebListener
public class SpeisekarteListener  implements ServletContextListener {

    /**
     * Wird automatisch beim Start der Anwendung aufgerufen.
     * Erstellt eine {@link Speisekarte} und legt sie im Application-Scope ab.
     * 
     * @param sce ServletContextEvent – enthält den Anwendungskontext
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Speisekarte speisekarte = new Speisekarte();
        sce.getServletContext().setAttribute("speisekarte", speisekarte);

        // Optionales Logging (nur Server-Konsole)
        System.out.println("✅ SpeisekarteListener: Speisekarte erfolgreich im Application-Scope geladen.");
    }

    /**
     * Wird beim Herunterfahren der Anwendung aufgerufen.
     * Kann genutzt werden, um Ressourcen (z. B. DB-Verbindungen) freizugeben.
     * 
     * @param sce ServletContextEvent – enthält den Anwendungskontext
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("🧹 Anwendung wird beendet. Application-Scope wird geleert.");
    }
        
}