package me.bigua.comiccollector.AbstBase;

import android.content.Context;
import me.bigua.comiccollector.Models.Author;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Bigua on 2/16/15.
 * bigua.kun@gmail.com
 */
public class authorHandler {
    private Context context;
    private BaseMaker baseMaker;

    public authorHandler(Context context) {
        this.context = context;
    }

    public Long insertAuthor(Author author) {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(
                baseMaker.getWritableDatabase())
                .put(author);
    }
    public void listAuthors(){
        baseMaker = new BaseMaker(this.context);
        List<Author> authors = cupboard().withDatabase(
                baseMaker.getWritableDatabase())
                .query(Author.class).list();
    }
}
