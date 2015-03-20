package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.Models.Author;
import me.bigua.comiccollector.Models.Comic;
import me.bigua.comiccollector.Models.Galaxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Bigua on 3/12/15.
 * bigua.kun@gmail.com
 */
public class DataProxy {
    private Context context;

    public DataProxy(Context context) {
        this.context = context;
    }

    public Long persistComic(Map<String, String> raw) {

        Long id = null;
        comicHandler ch = new comicHandler(this.context);

        String name = raw.get("name");
        Integer year = Integer.parseInt(raw.get("year"));
        String cover = raw.get("cover");
        String publi = raw.get("publi");
        String number = raw.get("number");
        String type = raw.get("type");
        String lang = raw.get("lang");
        Boolean complete = Boolean.valueOf(raw.get("complete"));
        Boolean have = Boolean.valueOf(raw.get("have"));

        Comic comic = new Comic(name, year, cover, publi, number, type, lang, complete, have);
        id = ch.insertComic(comic);
        comic.setId(id);

        List<Long> Authors;
        if (raw.containsKey("author")) {
            Authors = dealWithAuthors(raw.get("author"));
        }
        List<Long> Galaxies;
        if (raw.containsKey("galaxy")) {
            Galaxies = dealWithGalaxies(raw.get("galaxy"));
        }

        return id;
    }


    private List<Long> dealWithGalaxies(String galaxy) {
        String[] galaxies = galaxy.split(",");
        List<Long> ids = new ArrayList<Long>();
        for (String gala : galaxies) {
            ids.add(saveGalaxy(gala.trim()));

        }
        return ids;
    }


    private List<Long> dealWithAuthors(String author) {
        String[] authors = author.split(",");
        List<Long> ids = new ArrayList<Long>();
        for (String auth : authors) {
            ids.add(saveAuthor(auth.trim()));
        }
        return ids;
    }

    private Long saveAuthor(String author) {
        authorHandler ah = new authorHandler(this.context);
        Author auth = new Author(author);
        Long id = ah.insertAuthor(auth);
        return id;
    }

    private Long saveGalaxy(String galaxy) {
        GalaxyHandler gh = new GalaxyHandler(this.context);
        Galaxy gala = new Galaxy(galaxy);
        long id = gh.insertGalaxy(gala);
        return id;
    }
}
