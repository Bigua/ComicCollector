package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.Models.BaseHelper;
import me.bigua.comiccollector.Models.Author;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class authorHandler {
    private Context context;
    private BaseHelper baseHelper;

    public authorHandler(Context context) {
        this.context = context;
    }

    public Long insertAuthor(Author author) {
        baseHelper = new BaseHelper(this.context);
        return cupboard().withDatabase(
                baseHelper.getWritableDatabase())
                .put(author);
    }
    public void listAuthors(){
        baseHelper = new BaseHelper(this.context);
        List<Author> authors = cupboard().withDatabase(
                baseHelper.getWritableDatabase())
                .query(Author.class).list();
    }
}
