package sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.FontUIResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.model.Article;
import sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.model.FrontPage;
import sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.service.NewsService;

@Controller
@RequestMapping("")
public class NewsControllerNew {

    @Value("${secret.test-number}")
    String secretTestNumber;
    
    @Autowired
    NewsService newsService;

    @GetMapping("")
    public String getNewsArticles(HttpSession session, Model model){
        List<Article> articleList = newsService.getArticles();
        model.addAttribute("articleList", articleList);
        session.setAttribute("articleList", articleList);
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println(secretTestNumber);
        System.out.println("\n\n\n\n\n\n");
        model.addAttribute("secretTestNumber", secretTestNumber);
        return "articlelistpagenew";
    }

    @PostMapping("/articles")
    public String getSavedArticles(@RequestBody MultiValueMap<String,String> valueMap, HttpSession session){
        
        List<String> savedArticleIdList = valueMap.get("savedArticles");
        List<Article> articleList = (List<Article>)session.getAttribute("articleList");

        List<Article> savedArticleList = new ArrayList<>();
        for (String articleId : savedArticleIdList){
            Article savedArticle = articleList.stream().filter(a->a.getId().equals(articleId)).toList().getFirst();
            savedArticleList.add(savedArticle);
        }
        
        //System.out.println(savedArticleList);

        newsService.saveArticles(savedArticleList);
        return "redirect:/";
    }
}
