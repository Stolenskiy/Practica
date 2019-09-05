package ua.nic.Practica.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nic.Practica.model.ImageEntity;
import ua.nic.Practica.model.ImagesEntity;
import ua.nic.Practica.model.LocatedEntity;
import ua.nic.Practica.model.TradingFloorEntity;
import ua.nic.Practica.service.IEntityService;
import ua.nic.Practica.service.ImagesService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AboutTradingFloorViewController {
    @Autowired
    private IEntityService tradingFloorService;
    @Autowired
    private IEntityService locatedService;
    @Autowired
    private IEntityService imageService;
    @Autowired
    private ImagesService imagesService;

    @GetMapping("/aboutTradingFloor")
    public String startPage (
            @RequestParam() int tradingFloorId,
            Model model
    ) {
        TradingFloorEntity tradingFloorEntity = (TradingFloorEntity) tradingFloorService.getById(tradingFloorId);
        LocatedEntity locatedEntity = (LocatedEntity) locatedService.getById(tradingFloorEntity.getLocatedId());
        List<ImageEntity> imageList = new ArrayList();

        for (ImagesEntity imagesEntity:imagesService.getAllByTradingFloorId(tradingFloorId)) {
            imageList.add((ImageEntity) imageService.getById(imagesEntity.getImageId()));
        }

        model.addAttribute("tradingFloorEntity", tradingFloorEntity);
        model.addAttribute("locatedEntity", locatedEntity);
        model.addAttribute("imageList", imageList);

        return "aboutTradingFloor.html";
    }
}
