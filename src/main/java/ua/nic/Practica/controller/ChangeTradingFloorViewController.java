package ua.nic.Practica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.nic.Practica.model.ImageEntity;
import ua.nic.Practica.model.ImagesEntity;
import ua.nic.Practica.model.LocatedEntity;
import ua.nic.Practica.model.TradingFloorEntity;
import ua.nic.Practica.service.IEntityService;
import ua.nic.Practica.service.ImagesService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ChangeTradingFloorViewController {

    @Autowired
    private IEntityService tradingFloorService;
    @Autowired
    private IEntityService locatedService;
    @Autowired
    private IEntityService imageService;
    @Autowired
    private ImagesService imagesService;


    @RequestMapping(value = "/changeTradingFloor", method = RequestMethod.POST, params = "action=delete")
    public ModelAndView deleteTradingFloor( ModelAndView modelAndView,
                                            @RequestParam("files") MultipartFile[] files,
                                            @Valid TradingFloorEntity tradingFloorEntity,
                                            @Valid LocatedEntity locatedEntity,
                                            BindingResult result) {
        locatedEntity.setId(tradingFloorEntity.getLocatedId());

        // Видаляю стару інформацію
        List<ImagesEntity> imagesList = imagesService.getAllByTradingFloorId(tradingFloorEntity.getId());
        for (ImagesEntity imagesEntity : imagesList) {
            imagesService.delete(imagesEntity.getId());
            imageService.delete(imagesEntity.getImageId());
        }

        tradingFloorService.delete(tradingFloorEntity.getId());
        locatedService.delete(tradingFloorEntity.getLocatedId());
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/changeTradingFloor", method = RequestMethod.POST, params = "action=change")
    public ModelAndView changeTradingFloor( ModelAndView modelAndView,
                                      @RequestParam("files") MultipartFile[] files,
                                      @Valid TradingFloorEntity tradingFloorEntity,
                                      @Valid LocatedEntity locatedEntity,
                                      BindingResult result) {


        locatedEntity.setId(tradingFloorEntity.getLocatedId());

        if (files[0].getOriginalFilename().toString().length() != 0) {
            List<ImagesEntity> imagesList = imagesService.getAllByTradingFloorId(tradingFloorEntity.getId());
            for (ImagesEntity imagesEntity : imagesList) {
                imagesService.delete(imagesEntity.getId());
                imageService.delete(imagesEntity.getImageId());
            }
            addImagesToDB(files, tradingFloorEntity, imageService, imagesService);
        }
        // Зберігаю нову інформацію
        locatedEntity.setId(tradingFloorEntity.getLocatedId());
        System.out.println(locatedEntity);
        locatedService.add(locatedEntity);
        System.out.println();
        tradingFloorService.add(tradingFloorEntity);


        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }




    static void addImagesToDB (@RequestParam("files") MultipartFile[] files, @Valid TradingFloorEntity tradingFloorEntity, IEntityService imageService, ImagesService imagesService) {
        for (MultipartFile file : files) { // зберігаю зображення в деректорії
            String filePath = "D:\\Programing\\Java\\Practica\\src\\main\\resources\\image_db\\";

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
    }

}
