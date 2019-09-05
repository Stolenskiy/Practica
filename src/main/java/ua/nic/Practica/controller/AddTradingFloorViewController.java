package ua.nic.Practica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.nic.Practica.model.*;
import ua.nic.Practica.service.IEntityService;
import ua.nic.Practica.service.ImagesService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddTradingFloorViewController {
    @Autowired
    private IEntityService tradingFloorService;
    @Autowired
    private IEntityService locatedService;
    @Autowired
    private IEntityService imageService;
    @Autowired
    private ImagesService imagesService;

    @GetMapping("/addTradingFloor")
    public String startPage (Model model) {
        model.addAttribute("tradingFloorEntity", new TradingFloorEntity());
        model.addAttribute("locatedEntity", new LocatedEntity());
        return "addTradingFloor.html";
    }


    @RequestMapping(value = "/addTradingFloor", method = RequestMethod.POST, params = "action=add")
    public ModelAndView addTradingFloor (
            ModelAndView modelAndView,
            @RequestParam("files") MultipartFile[] files,
//            @RequestParam("imageName") String imageName,
            @Valid TradingFloorEntity tradingFloorEntity,
            @Valid LocatedEntity locatedEntity,
            BindingResult result) {

        locatedService.add(locatedEntity);

        tradingFloorEntity.setLocatedId(locatedEntity.getId());

        tradingFloorService.add(tradingFloorEntity);
        for (MultipartFile file : files) { // зберігаю зображення в деректорії
            String filePath = "D:\\Programing\\Java\\Practica\\src\\main\\resources\\static\\image_db\\";

            String filesSplit[] = file.getOriginalFilename().split("\\.");
            String fileExpancion = filesSplit[filesSplit.length - 1];

            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setExpancion(fileExpancion);
            imageService.add(imageEntity);

            filePath += imageEntity.getId() + ".";
            filePath += fileExpancion;
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // з'єдную зображення із торговими площадками
            ImagesEntity imagesEntity = new ImagesEntity();
            imagesEntity.setImageId(imageEntity.getId());
            imagesEntity.setTradingFloorId(tradingFloorEntity.getId());
            imagesService.add(imagesEntity);
        }
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
    /*@RequestMapping(value = "/imageList", method = RequestMethod.POST, params = "action=delete")
    ModelAndView deleteImage (
            ModelAndView modelAndView,
            @Valid ImageEntity imageEntity,
            BindingResult result) {
        imageService.delete(imageEntity.getId());
        modelAndView.setViewName("redirect:/imageList");
        return modelAndView;
    }

    @RequestMapping(value = "/imageList", method = RequestMethod.POST, params = "action=add")
    ModelAndView addImage (
            ModelAndView modelAndView,
            @Valid ImageEntity imageEntity,
            BindingResult result) {
        imageService.add(imageEntity);
        modelAndView.setViewName("redirect:/imageList");
        return modelAndView;
    }

    @RequestMapping(value = "/imageList", method = RequestMethod.POST, params = "action=save")
    ModelAndView updateImage (
            ModelAndView modelAndView,
            @Valid ImageEntity imageEntity,
            BindingResult result) {

        if (!result.hasErrors()) {
            imageService.delete(imageEntity.getId());
            imageService.add(imageEntity);
            modelAndView.getModel().put("image", imageEntity);
            modelAndView.setViewName("redirect:/imageList");
        }
        return modelAndView;
    }*/
}
