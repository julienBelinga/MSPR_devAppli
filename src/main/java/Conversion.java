import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Convertionhtml
{

    public String GenererPageAgent() throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("src/main/Txt/staff.txt"));
        String str;

        List<String> list = new ArrayList<String>();
        while ((str = in.readLine()) != null)
        {
            list.add(str);
        }

        java.util.Collections.sort(list);
        String AllHref = "";

        for (int i = 0; i < list.size(); i++)
        {
            File HTMLGenerated = new File("src/main/Html/Generation/" + list.get(i) + ".html");
        }

        return AllHref;

        File HTMLSqueletteAccueil = new File("src/main/Html/Squelette/Accueil.html");
        File HTMLGenerated = new File("src/main/Html/Generation/Accueil.html");
        String AncienHTML = "";
        BufferedReader reader = new BufferedReader(new FileReader(HTMLSqueletteAccueil));

        String line = reader.readLine();
        while (line != null)
        {
            AncienHTML = AncienHTML + line + System.lineSeparator();
            line = reader.readLine();
        }

        String NouveauHTML = AncienHTML.replaceAll(AncienneVar, HTMLInsert);

        FileWriter writer = new FileWriter(HTMLGenerated);
        writer.write("");
        writer.write(NouveauHTML);
        reader.close();
        writer.close();

    }


}