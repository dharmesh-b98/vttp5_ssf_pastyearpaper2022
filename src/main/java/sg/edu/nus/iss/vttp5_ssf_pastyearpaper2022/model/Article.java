package sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.model;

import java.util.Date;

public class Article {
    
    private String id;
    private Date publishedOn;
    private String title;
    private String url;
    private String imageUrl;
    private String body;
    private String tags;
    private String categories;
    private Boolean save;

    public Article() {
    }

    
    public Article(String id, Date publishedOn, String title, String url, String imageUrl, String body, String tags,
            String categories, Boolean save) {
        this.id = id;
        this.publishedOn = publishedOn;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.body = body;
        this.tags = tags;
        this.categories = categories;
        this.save = save;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public Boolean getSave() {
        return save;
    }


    public void setSave(Boolean save) {
        this.save = save;
    }


    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return id + "~*~" + publishedOn + "~*~" + title + "~*~" + url
                + "~*~" + imageUrl + "~*~" + body + "~*~" + tags + "~*~" + categories +"~*~" +save;
    }

    

    

    


}
