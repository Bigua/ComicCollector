package me.bigua.comiccollector.AbstBase.Handlers;

import android.content.Context;
import me.bigua.comiccollector.AbstBase.BaseMaker;
import me.bigua.comiccollector.AbstBase.Models.Author;

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
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).put(author);
    }

    public List<Author> List() {
        baseMaker = new BaseMaker(this.context);
        return cupboard().withDatabase(baseMaker.getWritableDatabase()).query(Author.class).list();
    }
}
