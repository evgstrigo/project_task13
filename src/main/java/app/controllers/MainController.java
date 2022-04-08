package app.controllers;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@RestController
@Api(tags = "MainController")
public class MainController {

    @GetMapping("/")
    public ModelAndView index() {
        log.info("run controller \"index\" ");
        return new ModelAndView("index");
    }
}
