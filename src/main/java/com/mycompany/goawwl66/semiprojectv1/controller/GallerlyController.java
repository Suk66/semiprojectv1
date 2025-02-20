package com.mycompany.goawwl66.semiprojectv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gallerly")
public class GallerlyController {
    @GetMapping("/list")
    public String gellerly()
    {
        return "views/gallery/list";
    }

}
