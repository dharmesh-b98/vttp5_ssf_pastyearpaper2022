package sg.edu.nus.iss.vttp5_ssf_pastyearpaper2022.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mnv")
public class HealthCheckController {
    
    @GetMapping("/health")
    public ModelAndView gethealthwithmodelandview(){
        ModelAndView mnv = new ModelAndView();

        try{
            checkHealth();
            
            mnv.setViewName("healthy");
            //mnv.setStatus(HttpStatus.ACCEPTED);
            mnv.setStatus(HttpStatusCode.valueOf(200));  //2 ways to put the status
        }
        catch(Exception e){
            mnv.setViewName("unhealthy");
            mnv.setStatus(HttpStatusCode.valueOf(500));
        }

        return mnv;
    }

    public void checkHealth() throws Exception{
        Random random = new Random();
        Integer randomValue = random.nextInt(0, 10);

        if (randomValue <= 5) {
            throw new Exception();
        }
    }
}
