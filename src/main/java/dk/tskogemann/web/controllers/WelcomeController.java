package dk.tskogemann.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Klaus Groenbaek
 */
@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
