package ua.nic.Practica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nic.Practica.model.ImageEntity;
import ua.nic.Practica.model.LocatedEntity;
import ua.nic.Practica.model.TradingFloorEntity;
import ua.nic.Practica.model.TradingFloorPackage;
import ua.nic.Practica.service.IEntityService;
import ua.nic.Practica.service.ImagesService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexViewController {
    @Autowired
    private IEntityService tradingFloorService;
    @Autowired
    private IEntityService locatedService;
    @Autowired
    private IEntityService imageService;
    @Autowired
    private ImagesService imagesService;

    @GetMapping("/")
    public String startPage (Model model) {
        model.addAttribute("tradingFloorPackageList", getTradingFloorPackageList());
        return "index.html";
    }


    private List<TradingFloorPackage> getTradingFloorPackageList () {
        List<TradingFloorPackage> tradingFloorPackageList = new ArrayList<>();
        List<TradingFloorEntity> tradingFloorEntityList = tradingFloorService.getAll();

        for (TradingFloorEntity tradingFloorEntity : tradingFloorEntityList) {
            TradingFloorPackage tradingFloorPackage = new TradingFloorPackage();
            tradingFloorPackage.setTradingFloorEntity(tradingFloorEntity);
            tradingFloorPackage.setLocatedEntity((LocatedEntity) locatedService.getById(tradingFloorEntity.getLocatedId()));
            tradingFloorPackage.setImageDir("image_db/" + getMainImage(tradingFloorEntity.getId()));

            tradingFloorPackageList.add(tradingFloorPackage);
        }

//        for (TradingFloorPackage tradingFloorPackage : tradingFloorPackageList) {
//            System.out.println(tradingFloorPackage);
//        }
        return tradingFloorPackageList;
    }

    public String getMainImage (int tradingFloorId) {
        Integer imageMainId;
        imageMainId = imagesService.getAllByTradingFloorId(tradingFloorId).get(0).getImageId();

        ImageEntity imageEntity;
        imageEntity = (ImageEntity) imageService.getById(imageMainId);

        String imageName;
        imageName = imageEntity.getId() + "." + imageEntity.getExpancion();

        return imageName;
    }
}
