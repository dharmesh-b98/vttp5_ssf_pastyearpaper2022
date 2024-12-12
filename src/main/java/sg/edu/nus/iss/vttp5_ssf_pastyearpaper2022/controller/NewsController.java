package sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.controller;

import java.util.List;

import javax.swing.plaf.FontUIResource;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/test")
public class NewsController {
    
    @Autowired
    NewsService newsService;

    @GetMapping("")
    public String getNewsArticles(HttpSession session, Model model){
        List<Article> articleList = newsService.getArticles();
        
        FrontPage frontPage = new FrontPage(articleList);
        //System.out.println(frontPage);
        session.setAttribute("frontPage", frontPage);
        model.addAttribute("frontPage", frontPage);

        //System.out.println(articleList.get(2));
        return "articlelistpage";
    }

    @PostMapping("/articles")
    public String getSavedArticles(/*@ModelAttribute("frontPage") FrontPage frontPage*/ HttpSession session){
        //System.out.println(frontPage);

        FrontPage sessionpage = (FrontPage)session.getAttribute("frontPage");
        List<Article> sessionArticleList = sessionpage.getArticleList();
        System.out.println(sessionArticleList.get(0));

        //List<Article> articleList = frontPage.getArticleList();
        //System.out.println(articleList);
        //List<Article> savedArticleList = articleList.stream().filter(article -> article.getSave()).toList();

        //System.out.println(savedArticleList);
        return "redirect:/test";
    }
}
