package org.ryanstrong.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ryanstrong on 8/27/17.
 */
@Controller
@RequestMapping("dater")
public class DaterController {

//    @Autowired
//    private DaterDao daterDao;

//    @Autowired
//    private ReportDao reportDao;

    @RequestMapping (value = ""
//            , method = RequestMethod.GET
    )
    public String index(Model model){
        model.addAttribute("title", "Time of Change");
//        model.addAttribute("dater", daterDao.findAll());
        return "dater/index";
    }
}

