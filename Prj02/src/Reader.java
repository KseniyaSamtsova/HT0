import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class Reader {

    private ArrayList<Artist> artists = new ArrayList<>();
    private ArrayList<File> allFiles = new ArrayList<>();

    public ArrayList<Artist> getFile(File file){
        if (!file.exists()) {
            System.out.println("Path doesn't exist: " + file.getPath());
            return null;
        }
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].canRead()) {
                if (files[i].isFile()) {
                    int dot = files[i].getName().lastIndexOf(".");
                    if (dot > 0)
                        if (files[i].getName().substring(dot).equals(".mp3")) {
                            Artist artist = getInfoMp3WithSimpleLibFile(files[i]);
                        }
                } else {
                    getFile(files[i]);
                }
            }
        }

        return artists;
    }

    private Artist getInfoMp3WithSimpleLibFile( File file){
        InputStream input = null;
        try {
            input = new FileInputStream(new File(file.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        org.xml.sax.ContentHandler handler = new DefaultHandler();
        org.apache.tika.metadata.Metadata metadata = new org.apache.tika.metadata.Metadata();
        org.apache.tika.parser.Parser parser = new Mp3Parser();
        ParseContext parseCtx = new ParseContext();
        try {
            parser.parse(input, handler, metadata, parseCtx);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Artist artist = null;

        for (Artist currentArtist : artists) {
            if (currentArtist.getNameArtist().equals(metadata.get("xmpDM:artist"))) {
                artist = currentArtist;
                break;
            }
        }

        if (artist == null){
            artist = new Artist(metadata.get("xmpDM:artist"));
            artists.add(artist);
        }
        else {
            artist = artist;
        }

        List<Album> alboms = artist.getAlbums();

        SongInfo songInfo = new SongInfo(metadata.get("title"), metadata.get("xmpDM:duration"), file.getPath());

        int albumId = artist.getIdAlbum(metadata.get("xmpDM:album"));

        artist.addAlbum(metadata.get("xmpDM:album"), songInfo);

        return artist;

    }

    public String findCheckSum(File file) throws IOException {
        FileInputStream findMD5 = new FileInputStream(file);
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(findMD5);
    }

}
