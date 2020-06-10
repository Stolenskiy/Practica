package ua.nic.Practica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexViewController {

    @GetMapping("/")
    public String startPage (Model model) {
        model.addAttribute("imageList", getImageList());
        return "index.html";
    }
    @GetMapping("/selectedImage")
    public ModelAndView startPage(
            @RequestParam() String imageDir,
            ModelAndView modelAndView
    ) {

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }


    private List<String> getImageList() {
        List<String> imageList = new ArrayList<>();
        int imageCount=10;
        for (int i = 0; i < imageCount; i++) {
            imageList.add("image_db/" + i + ".jpg");
        }


        return imageList;
    }




}
