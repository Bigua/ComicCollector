package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import me.bigua.comiccollector.AbstBase.Handlers.*;
import me.bigua.comiccollector.AbstBase.Models.*;
import me.bigua.comiccollector.Utils.AsyncDelegate;
import me.bigua.comiccollector.Utils.DealWithFiles;
import me.bigua.comiccollector.Utils.DownloadImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Bigua on 3/12/15.
 * bigua.kun@gmail.com
 */
public class DataProxy implements AsyncDelegate {

    Long id = null;
    ComicHandlers ch;
    private Context context;


    public DataProxy(Context context) {
        this.context = context;
    }

    /**
     * Salva um comic na base
     *
     * @param raw coleção dos dados recuperados do form
     * @return id do comic salvo
     */
    public Long persistComic(Map<String, String> raw) {


        ch = new ComicHandlers(this.context);

        String name = raw.get("name");
        Integer year = null;
        if (raw.get("year") != null) {
            year = Integer.parseInt(raw.get("year"));
        } else
            year = 0;

        String cover = raw.get("cover");
        String publi = raw.get("publi");
        String number = raw.get("number");
        String type = raw.get("type");
        String lang = raw.get("lang");
        Boolean complete = Boolean.valueOf(raw.get("complete"));
        Boolean have = Boolean.valueOf(raw.get("have"));

        DownloadImage di = new DownloadImage(this.context);
        di.setOnCompleteListener(this);

        if (cover != null) {
            di.execute(cover);
        } else {
            Log.wtf(" capa", " nula");
        }


        Comic comic = new Comic(name, year, cover, publi, number, type, lang, complete, have);
        id = ch.Insert(comic);
        comic.setId(id);

        if (raw.containsKey("author")) {
            dealWithAuthors(id, raw.get("author"));
        }
        if (raw.containsKey("galaxy")) {
            dealWithGalaxies(id, raw.get("galaxy"));
        }
        return id;
    }

    /**
     * Lida com a situação de multiplos autores
     *
     * @param id     id do comic salvo
     * @param author autor do comic salvo
     */
    private void dealWithAuthors(Long id, String author) {
        String[] authors = author.split(",");
        List<Long> IDs = new ArrayList<Long>();
        for (String auth : authors) {
            IDs.add(saveAuthor(auth.trim()));
        }
        for (Long AID : IDs) {
            saveComicAuthor(id, AID);
        }
    }

    private Long saveAuthor(String author) {
        AuthorHandlers ah = new AuthorHandlers(this.context);
        Author auth = new Author(author);
        Long id = ah.insertAuthor(auth);
        return id;
    }

    private void saveComicAuthor(Long CID, Long AID) {
        ComicAuthorHandlers cah = new ComicAuthorHandlers(this.context);
        comicAuthor ca = new comicAuthor(CID, AID);
        cah.insertComicAuthor(ca);
    }

    private void dealWithGalaxies(Long id, String galaxy) {
        String[] galaxies = galaxy.split(",");
        List<Long> IDs = new ArrayList<Long>();
        for (String gala : galaxies) {
            IDs.add(saveGalaxy(gala.trim()));
        }
        for (Long GID : IDs) {
            saveComicGalaxy(id, GID);
        }
    }

    private Long saveGalaxy(String galaxy) {
        GalaxyHandler gh = new GalaxyHandler(this.context);
        Galaxy gala = new Galaxy(galaxy);
        long id = gh.insertGalaxy(gala);
        return id;
    }

    private void saveComicGalaxy(Long CID, Long GID) {
        ComicGalaxyHandlers cgh = new ComicGalaxyHandlers(this.context);
        comicGalaxy cg = new comicGalaxy(CID, GID);
        cgh.insertComicGalaxy(cg);
    }

    @Override
    public void asyncComplete(Object success) {
        DealWithFiles deal = new DealWithFiles();
        String cover = deal.saveBitmap("cover" + System.currentTimeMillis(), (Bitmap) success);
        ch.Update("cover", cover, id);
    }
}
