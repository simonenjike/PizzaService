<%-- 
    ===========================================================
    JSP-DATEI: rechnung.jsp
    ===========================================================
    FUNKTION:
    Anzeige der automatisch generierten Rechnung nach einer Bestellung.

    BESCHREIBUNG:
    - Zeigt Kundendaten, Bestellpositionen und Gesamtsumme an.
    - Wird durch BestellungServlet aufgerufen, nachdem eine Bestellung erstellt wurde.
    - Enthält Funktionen zum Drucken und zur Navigation zurück zur Bestellung oder zur Küche.

    TECHNIK:
      - JSTL (Core + Format) für dynamische Anzeige.
      - Datenquelle: "bestellung" (im Request-/Session-Scope gespeichert).
      - Druckoptimiertes Layout (CSS @media print).
      - UTF-8-Zeichencodierung.

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
    <title>Rechnung - Pizza Service</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #fff;
            color: #333;
        }

        h2 {
            color: darkgreen;
        }

        h3 {
            color: #333;
            margin-top: 30px;
        }

        table {
            border-collapse: collapse;
            width: 70%;
            margin-top: 15px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 8px 12px;
            text-align: left;
        }

        th {
            background-color: #f5f5f5;
        }

        tfoot td {
            font-weight: bold;
            background-color: #fafafa;
        }

        small {
            color: gray;
        }

        @media print {
            .no-print {
                display: none;
            }
            body {
                margin: 0;
                color: black;
            }
        }

        button, input[type="submit"] {
            background-color: darkgreen;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover, input[type="submit"]:hover {
            background-color: seagreen;
        }

        .no-print {
            margin-top: 30px;
        }
    </style>
</head>

<body>

    <!-- ========================================================= -->
    <!-- Abschnitt 1: Rechnungskopf                                -->
    <!-- ========================================================= -->
    <h2>🧾 Rechnung – Pizza Service</h2>

    <p>
        <strong>${bestellung.kunde.kundenname}</strong><br>
        ${bestellung.kunde.kundenadresse}
    </p>

    <!-- ========================================================= -->
    <!-- Abschnitt 2: Bestellübersicht                             -->
    <!-- ========================================================= -->
    <h3>Bestellte Speisen:</h3>

    <table>
        <thead>
            <tr>
                <th>Speise</th>
                <th>Menge</th>
                <th>Einzelpreis (€)</th>
                <th>Gesamt (€)</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pos" items="${bestellung.positionen}">
                <tr>
                    <td>${pos.speise.name}</td>
                    <td>${pos.menge}</td>
                    <td>
                        <fmt:formatNumber value="${pos.speise.preis}" type="number" minFractionDigits="2" />
                    </td>
                    <td>
                        <fmt:formatNumber value="${pos.gesamt}" type="number" minFractionDigits="2" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3" align="right"><b>Gesamtpreis:</b></td>
                <td>
                    <b><fmt:formatNumber value="${bestellung.gesamtpreis}" type="number" minFractionDigits="2" /> €</b>
                </td>
            </tr>
        </tfoot>
    </table>

    <!-- ========================================================= -->
    <!-- Abschnitt 3: Zusatzinformationen                          -->
    <!-- ========================================================= -->
    <p>
        <small>
            IP-Adresse: ${bestellung.ipAdresse}<br>
            Session-ID: ${bestellung.sessionId}
        </small>
    </p>

    <!-- ========================================================= -->
    <!-- Abschnitt 4: Aktionen / Navigation                        -->
    <!-- ========================================================= -->
    <div class="no-print">
        <button type="button" onclick="history.back()">Zurück</button>
        &nbsp;&nbsp;&nbsp;
        <button type="button" onclick="window.print()">Drucken</button>
        &nbsp;&nbsp;&nbsp;
        <form action="kueche.jsp" method="get" style="display:inline;">
            <input type="submit" value="Zur Küche">
        </form>
    </div>
</body>

</html>