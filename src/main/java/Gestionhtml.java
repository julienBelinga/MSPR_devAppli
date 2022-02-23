import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Gestionhtml {

    public String GenererLiensAccueil() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/main/Txt/staff.txt"));
        String str;

        List<String> list = new ArrayList<String>();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }

        java.util.Collections.sort(list);
        StringBuilder AllHref = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            list.set(i, "<a href=\"Agents/" + list.get(i) + ".html\">" + list.get(i) + "</a></br>");
            AllHref.append(list.get(i)).append("\n");
        }

        return AllHref.toString();
    }

    public void InsertionHTML(String AncienneVar, String HTMLInsert) throws IOException {

        File HTMLSqueletteAccueil = new File("src/main/Html/Squelette/Accueil.html");
        File HTMLGenerated = new File("src/main/Html/Generation/Accueil.html");
        StringBuilder AncienHTML = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(HTMLSqueletteAccueil));

        String line = reader.readLine();
        while (line != null) {
            AncienHTML.append(line).append(System.lineSeparator());
            line = reader.readLine();
        }

        String NouveauHTML = AncienHTML.toString().replaceAll(AncienneVar, HTMLInsert);

        FileWriter writer = new FileWriter(HTMLGenerated);
        writer.write("");
        writer.write(NouveauHTML);
        reader.close();
        writer.close();

    }


    public void SupprimmerAgentsAvantMAJ() {
        File DossierAgents = new File("src/main/Html/Generation/Agents");

        File[] ListeFichiersAgents = DossierAgents.listFiles();
        assert ListeFichiersAgents != null;
        for (File file : ListeFichiersAgents) {
            System.out.println("Fichier \"" + file.getName() + "\" supprimé.");
            file.delete();
        }

    }

    public void GenererPageAgent() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/main/Txt/staff.txt"));
        String str;

        // suppression de chaque agent en parcourant staff.txt
        List<String> list = new ArrayList<String>();
        SupprimmerAgentsAvantMAJ();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }

        java.util.Collections.sort(list);
        String AllHref = "";

        for (String s : list) {
            File HTMLGenerated = new File("src/main/Html/Generation/Agents/" + s + ".html");
            FileWriter writer = new FileWriter(HTMLGenerated);
            writer.write("");
            writer.close();
        }

    }

    public void ajouterInfosAgent() throws IOException {
        BufferedReader staffTxt = new BufferedReader(new FileReader("src/main/Txt/staff.txt"));

        // pour chaque ligne de staff.txt
        String ligne;
        while ((ligne = staffTxt.readLine()) != null) {
            // on récupère le squelette HTML
            BufferedReader squeletteHtml = new BufferedReader(new FileReader("item-agent.html"));
            // substring sur la fin de la ligne de staff.txt pour retirer l'extension
            ligne.substring(ligne.length() - 5);
            // Création du fichier HTML
            BufferedReader fichierTxt = new BufferedReader(new FileReader("src/main/Txt/" + ligne + ".txt"));
            File fichierHtml = new File("src/main/Html/Generation/Agents/" + ligne + ".html");
            // Création d'un writer pour remplir le fichier
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierHtml));
            // La première ligne correspond au nom de l'agent
            String nomAgent = fichierTxt.readLine();
            // La deuxième est son prénom
            String prenomAgent = fichierTxt.readLine();
            // La troisième est sa mission
            String missionAgent = fichierTxt.readLine();

            // On skip le mot de passe et la ligne blanche
            int i = 0;
            while (i < 2) {
                fichierTxt.readLine();
                i++;
            }

            // On récupère l'équipement de l'agent
            StringBuilder equipementAgent = new StringBuilder();
            String equipement;
            while ((equipement = fichierTxt.readLine()) != null) {
                equipementAgent.append("<br><label>").append(equipement).append("<input type=\"checkbox\" name=\"\" checked>" + "</label>");
            }

            String ligneSquelette;
            StringBuilder fichierAgent = new StringBuilder();
            String strRecherche = "id=\"main-container\">";
            // Pour chaque ligne du squelette HTML
            while ((ligneSquelette = squeletteHtml.readLine()) != null) {
                fichierAgent.append(ligneSquelette);
                // On découpe la ligne en mots
                String[] mots = ligneSquelette.split(" ");
                // Pour chaque mot
                for (String mot : mots) {
                    // Si le mot actuel correspond à la string recherchée
                    if (mot.equals(strRecherche)) {
                        // On ajoute le nom, prénom, mission et l'équipement de l'agent dans le fichier HTML créé
                        fichierAgent.append(nomAgent).append(" ").append(prenomAgent).append(" - ").append(missionAgent).append(" <br>");
                        fichierAgent.append(equipementAgent);
                    }
                }
            }
            writer.write(String.valueOf(Jsoup.parse(String.valueOf(fichierAgent))));
            writer.flush();
            writer.close();
        }
        System.out.println("\nInformations des agents ajoutés");
    }
}
