package me.bigua.comiccollector;

/**
 * Created by Bigua on 4/3/15.
 * bigua.kun@gmail.com
 */
public class UrlMixer {

    private String url;
    private Boolean selected;

    public UrlMixer(String url, Boolean selected) {
        this.url = url;
        this.selected = selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isSelected() {
        return selected;
    }
}
