import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Gestionhtml {

    public String GenererLiensAccueil() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/main/Txt/staff.txt"));
        String str;

        List<String> list = new ArrayList<String>();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }

        String AllHref = "";

        for (int i = 0; i < list.size(); i++) {
            list.set(i, "<a href=\"" + list.get(i) + ".html\">" + list.get(i) + "</a></br>");
            AllHref = AllHref + list.get(i) + "\n";
        }

        return AllHref;
        }

        public void InsertionHTML(String AncienneVar, String HTMLInsert) throws IOException {

            File HTMLSqueletteAccueil = new File("src/main/Html/Accueil.html");
            String AncienHTML = "";
            BufferedReader reader = new BufferedReader(new FileReader(HTMLSqueletteAccueil));

            String line = reader.readLine();
            while (line != null)
            {
                AncienHTML = AncienHTML + line + System.lineSeparator();
                line = reader.readLine();
            }

            String NouveauHTML = AncienHTML.replaceAll(AncienneVar, HTMLInsert);

            FileWriter writer = new FileWriter(HTMLSqueletteAccueil);
            writer.write(NouveauHTML);
            reader.close();
            writer.close();

        }


}