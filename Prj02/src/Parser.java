import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class Parser {
    public static void main(String[] args) {

        HashSet<String> pathOfCatalogs = new HashSet<>();

        pathOfCatalogs.add("D:\\Downloads");

        CatalogHTML catalogOfMP3FilesHTML = new CatalogHTML();
        ArrayList<Artist> artists = new ArrayList<Artist>();

        for (String catalog : pathOfCatalogs) {
            Reader reader = new Reader();
            artists.addAll(reader.getFile(new File("E:\\Downloads")));
        }

        catalogOfMP3FilesHTML.fillHTML(artists, "E:\\abc");
    }
}
