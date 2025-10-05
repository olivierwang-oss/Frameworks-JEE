package com.support.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/supportTechSupp")
public class SupportTechniqueServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Définir l'encodage pour gérer les caractères français
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Récupérer les paramètres du formulaire
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String logiciel = request.getParameter("logiciel");
        String systeme = request.getParameter("systeme");
        String description = request.getParameter("description");

        // Afficher les informations de la requête
        afficherRequete(response, prenom, nom, email, telephone,
                logiciel, systeme, description);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Veuillez utiliser le formulaire pour soumettre une requête</h2>");
        out.println("<a href='supportTechSupp.html'>Accéder au formulaire</a>");
        out.println("</body></html>");
    }

    private void afficherRequete(HttpServletResponse response, String prenom,
                                 String nom, String email, String telephone,
                                 String logiciel, String systeme, String description)
            throws IOException {

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Confirmation de requête</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f0f0f0; }");
        out.println(".container { background-color: white; padding: 20px; border-radius: 5px; max-width: 800px; margin: auto; }");
        out.println("h1 { color: #0066cc; }");
        out.println("h2 { color: #333; border-bottom: 2px solid #0066cc; padding-bottom: 5px; }");
        out.println("p { margin: 10px 0; }");
        out.println("strong { color: #555; }");
        out.println(".description { background-color: #f9f9f9; padding: 10px; border-left: 3px solid #0066cc; }");
        out.println("a { color: #0066cc; text-decoration: none; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>✓ Requête de support technique enregistrée</h1>");

        out.println("<h2>Informations du client</h2>");
        out.println("<p><strong>Prénom :</strong> " + escapeHtml(prenom) + "</p>");
        out.println("<p><strong>Nom :</strong> " + escapeHtml(nom) + "</p>");
        out.println("<p><strong>Email :</strong> " + escapeHtml(email) + "</p>");
        out.println("<p><strong>Téléphone :</strong> " + escapeHtml(telephone) + "</p>");

        out.println("<h2>Informations techniques</h2>");
        out.println("<p><strong>Logiciel :</strong> " + escapeHtml(logiciel) + "</p>");
        out.println("<p><strong>Système d'exploitation :</strong> " + escapeHtml(systeme) + "</p>");

        out.println("<h2>Description du problème</h2>");
        out.println("<div class='description'>");
        out.println("<p>" + escapeHtml(description).replace("\n", "<br/>") + "</p>");
        out.println("</div>");

        out.println("<br/>");
        out.println("<p><a href='supportTechSupp.html'>← Retour au formulaire</a></p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    // Méthode pour échapper les caractères HTML et prévenir les injections XSS
    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}