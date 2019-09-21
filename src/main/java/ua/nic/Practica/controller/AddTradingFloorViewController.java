package ua.nic.Practica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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
import ua.nic.Practica.service.MailService;

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
    private IEntityService subscriberService;
    @Autowired
    private IEntityService locatedService;
    @Autowired
    private IEntityService imageService;
    @Autowired
    private ImagesService imagesService;
    @Autowired
    private MailService mailService;


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
            @Valid TradingFloorEntity tradingFloorEntity,
            @Valid LocatedEntity locatedEntity,
            BindingResult result) {

        locatedService.add(locatedEntity);

        tradingFloorEntity.setLocatedId(locatedEntity.getId());

        tradingFloorService.add(tradingFloorEntity);
        ChangeTradingFloorViewController.addImagesToDB(files, tradingFloorEntity, imageService, imagesService);

        for (SubscriberEntity subscriberEntity :
                (List<SubscriberEntity>) subscriberService.getAll()) {
            try {
                String subject = "Pemodule add new Trading Floor";
                String text = "We have good news! Look for the new trading floor!\n Just click here -> ";
                text += "http://localhost:8080/aboutTradingFloor?tradingFloorId=" + tradingFloorEntity.getId();
                mailService.sendEmail(subscriberEntity.getEmail(), subject, text);
            } catch (MailException mailException) {
                System.out.println(mailException);
            }
        }

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
