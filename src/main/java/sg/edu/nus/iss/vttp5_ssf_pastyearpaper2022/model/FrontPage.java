package sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.model;

import java.util.ArrayList;
import java.util.List;

public class FrontPage {
    private List<Article> articleList;

    public FrontPage() {
        this.articleList = new ArrayList<>();
    }

    public FrontPage(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
    
}
