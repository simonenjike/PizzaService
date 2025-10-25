<%-- 
    ===========================================================
    JSP-DATEI: startseite.jsp
    ===========================================================
    FUNKTION:
    Startseite des PizzaService-Webprojekts.
    Der Kunde kann hier:
      • Speisen aus der Speisekarte auswählen (mit Mengenangabe)
      • seine persönlichen Adressdaten eingeben
      • die Bestellung an das Servlet "/bestellen" absenden

    TECHNIK:
      - JSTL (Core + Format) für dynamische Ausgabe
      - UTF-8-Zeichencodierung für Umlaute
      - Bean "speisekarte" wird im Application-Scope bereitgestellt
        (durch SpeisekarteListener oder SpeisekarteServlet)
      - MVC: JSP = View, BestellungServlet = Controller

    AUTOR: Simone Njike
    DATUM: 24.10.2025
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="de">
    
<head>
    <meta charset="UTF-8">
    <title>Pizza Service – Bestellung</title>

    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 20px; 
            background-color: #fafafa;
        }
        h2 { 
            color: darkgreen; 
            margin-top: 40px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 10px;
        }
        th, td {
            border-bottom: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
        input[type="submit"] {
            margin-top: 20px;
            padding: 10px 15px;
            background-color: darkgreen;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: seagreen;
        }
    </style>
</head>

<body>
    <!-- Bean für Speisekarte wird im Application Scope erzeugt -->
    <jsp:useBean id="speisekarte" class="ibb.pizzaservice.model.Speisekarte" scope="application" />

    <!-- Bestellformular -->
    <form action="bestellen" method="post" accept-charset="UTF-8">

        <!-- ========================================================= -->
        <!-- Abschnitt 1: Speisekarte                                 -->
        <!-- ========================================================= -->
        <h2>🍕 Speisekarte</h2>
        <table>
            <thead>
                <tr>
                    <th>Speise</th>
                    <th>Preis (€)</th>
                    <th>Menge</th>
                    <th>Beschreibung</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="speise" items="${speisekarte.speisen}">
                    <tr>
                        <td>${speise.name}</td>
                        <td>
                            <fmt:formatNumber value="${speise.preis}" type="currency" currencySymbol="€" />
                        </td>
                        <td>
                            <input type="number" 
                                   name="menge_${speise.speiseId}" 
                                   value="0" 
                                   min="0" 
                                   max="10" 
                                   style="width: 60px;"
                                   aria-label="Menge für ${speise.name}">
                        </td>
                        <td>${speise.beschreibung}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- ========================================================= -->
        <!-- Abschnitt 2: Kundendaten                                 -->
        <!-- ========================================================= -->
        <h2>👤 Kundendaten</h2>
        <table>
            <tr>
                <th>Anrede:</th>
                <td>
                    <select name="anrede" required>
                        <option value="">-- bitte wählen --</option>
                        <option value="Frau">Frau</option>
                        <option value="Herr">Herr</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Vorname:</th>
                <td><input type="text" name="vorname" required></td>
            </tr>
            <tr>
                <th>Nachname:</th>
                <td><input type="text" name="nachname" required></td>
            </tr>
            <tr>
                <th>Straße:</th>
                <td><input type="text" name="strasse" required></td>
            </tr>
            <tr>
                <th>Hausnummer:</th>
                <td><input type="text" name="hausnummer" required></td>
            </tr>
            <tr>
                <th>PLZ:</th>
                <td>
                    <input type="text" name="plz" required pattern="[0-9]{5}" title="Bitte 5-stellige PLZ eingeben">
                </td>
            </tr>
            <tr>
                <th>Stadt:</th>
                <td><input type="text" name="stadt" required></td>
            </tr>
        </table>

        <!-- ========================================================= -->
        <!-- Abschnitt 3: Absenden                                                                        -->
        <!-- ========================================================= -->
        <input type="submit" value="Bestellen">
    </form>
</body>

</html>