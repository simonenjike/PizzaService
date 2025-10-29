<%-- 
    ===========================================================
    JSP-DATEI: kueche.jsp
    ===========================================================
    FUNKTION:
    Simuliert die "Küchenansicht" des PizzaService-Systems.
    Zeigt alle aktuell bestellten Speisen, Mengen und die Lieferadresse an.

    BESCHREIBUNG:
    - Greift auf das im Session-Scope gespeicherte Objekt "bestellung" zu.
    - Gibt alle Bestellpositionen aus, die in der aktuellen Bestellung enthalten sind.
    - Zeigt Kundendaten und Gesamtpreis.
    - Ermöglicht Rückkehr zur Rechnung oder Startseite.

    TECHNIK:
      - JSTL (Core + Format)
      - Session-Scope-Datenzugriff über ${sessionScope.bestellung}
      - UTF-8-Zeichencodierung
      - Druck- und Navigationstasten

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
    <title>🍳 Küche - Bestellübersicht</title>

    <style>
        
        
        
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
            background-color: #fdfdfd;
        }

        h2 {
            color: darkgreen;
        }

        table {
            border-collapse: collapse;
            width: 70%;
            margin-top: 15px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 8px 12px;
        }

        th {
            background-color: #f5f5f5;
        }

        .highlight {
            background-color: #f9fff9;
        }

        button {
            background-color: darkgreen;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: seagreen;
        }
    </style>
</head>

<body>

    <!-- ========================================================= -->
    <!-- Abschnitt 1: Überschrift und Bestellliste                 -->
    <!-- ========================================================= -->
    <h2>🍽️ Bestellung für die Küche</h2>

    <table>
        <thead>
            <tr>
                <th>Speise</th>
                <th>Menge</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pos" items="${sessionScope.bestellung.positionen}">
                <tr class="highlight">
                    <td>${pos.speise.name}</td>
                    <td>${pos.menge}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- ========================================================= -->
    <!-- Abschnitt 2: Lieferinformationen                          -->
    <!-- ========================================================= -->
    <h3>Lieferung an:</h3>
    <p>
        ${sessionScope.bestellung.kunde.anrede}<br>
        ${sessionScope.bestellung.kunde.vorname} ${sessionScope.bestellung.kunde.nachname}<br>
        ${sessionScope.bestellung.kunde.kundenadresse}
    </p>

    <p>
        <b>Gesamtpreis: 
            <fmt:formatNumber value="${sessionScope.bestellung.gesamtpreis}" 
                              type="number" minFractionDigits="2" /> €</b>
    </p>

    <!-- ========================================================= -->
    <!-- Abschnitt 3: Navigation                                   -->
    <!-- ========================================================= -->
    <br>
    <button type="button" onclick="history.back()">Zurück</button>
    &nbsp;&nbsp;&nbsp;
    <form action="startseite.jsp" method="get" style="display:inline;">
        <button type="submit">Neue Bestellung</button>
    </form>

</body>

</html>