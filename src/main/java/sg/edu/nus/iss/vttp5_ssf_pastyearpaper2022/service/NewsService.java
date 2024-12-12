package sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.*;
import sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.constants.Constant;
import sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.constants.Url;
import sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.model.Article;
import sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.repo.HashRepo;

@Service
public class NewsService {
    @Autowired
    HashRepo newsRepo;
    
    RestTemplate template = new RestTemplate();

    public List<Article> getArticles(){
        
        ResponseEntity<String> responseEntity = template.getForEntity(Url.cryptoURL, String.class);
        String response = responseEntity.getBody();

        JsonReader reader = Json.createReader(new StringReader(response));
        JsonObject jsonObjectOverall = reader.readObject();
        
        JsonArray jsonArrayData = jsonObjectOverall.getJsonArray("Data");

        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < jsonArrayData.size(); i++){
            
            JsonObject singleArticle = jsonArrayData.get(i).asJsonObject();
            
            String id = singleArticle.getString("id");
            Date publishedOn = new Date(Long.valueOf(singleArticle.getInt("published_on")));
            String title = singleArticle.getString("title");
            String url = singleArticle.getString("url");
            String imageUrl = singleArticle.getString("imageurl");
            String body = singleArticle.getString("body");
            String tags = singleArticle.getString("tags");
            String categories = singleArticle.getString("categories");
            
            Article article = new Article(id,publishedOn,title,url,imageUrl,body,tags,categories,false);

            articleList.add(article);
        }

        return articleList;

    }

    public void saveArticles(List<Article> articleList){
        for (Article article: articleList){
            JsonObject articleJson = convertArticlesToJson(article);
            newsRepo.put(Constant.redisKey, article.getId(), articleJson.toString());
        }   
        
    }

    public JsonObject convertArticlesToJson(Article article){
        
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonObject articleJson = job.add("id", article.getId())
                                    .add("publishedOn", article.getPublishedOn().getTime())
                                    .add("title", article.getTitle())
                                    .add("url", article.getUrl())
                                    .add("imageurl", article.getImageUrl())
                                    .add("body", article.getBody())
                                    .add("tags", article.getTags())
                                    .add("categories", article.getCategories())
                                    .add("save", article.getSave())
                                    .build();
        
        return articleJson;       

    }
}
