package ua.nic.Practica.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.nic.Practica.model.*;
import ua.nic.Practica.service.IEntityService;
import ua.nic.Practica.service.ImagesService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SessionAttributes("tradingFloorEntity")
@Controller
public class AboutTradingFloorViewController {
    @Autowired
    private IEntityService tradingFloorService;
    @Autowired
    private IEntityService locatedService;
    @Autowired
    private IEntityService imageService;
    @Autowired
    private IEntityService customerService;
    @Autowired
    private ImagesService imagesService;

    private TradingFloorEntity tradingFloorEntity;
    private LocatedEntity locatedEntity;
    private List<ImageEntity> imageList;

    @GetMapping("/aboutTradingFloor")
    public String startPage (
            @RequestParam() int tradingFloorId,
            Model model
    ) {
        tradingFloorEntity = (TradingFloorEntity) tradingFloorService.getById(tradingFloorId);
        locatedEntity = (LocatedEntity) locatedService.getById(tradingFloorEntity.getLocatedId());
        imageList = new ArrayList();

        for (ImagesEntity imagesEntity : imagesService.getAllByTradingFloorId(tradingFloorId)) {
            imageList.add((ImageEntity) imageService.getById(imagesEntity.getImageId()));
        }

        model.addAttribute("tradingFloorEntity", tradingFloorEntity);
        model.addAttribute("locatedEntity", locatedEntity);
        model.addAttribute("imageList", imageList);
        model.addAttribute("subscriberEntity", new SubscriberEntity());
        model.addAttribute("customerEntity", new CustomerEntity());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]);
        model.addAttribute("userRole", SecurityContextHolder
                                                                .getContext()
                                                                .getAuthentication()
                                                                .getAuthorities().toArray()[0].toString());


        return "aboutTradingFloor.html";
    }

    @RequestMapping(value = "/changeTradingFloor", method = RequestMethod.GET, params = "action=change")
    public String changeTradingFloor (
            Model model,
            SessionStatus status
    ) {
        model.addAttribute("tradingFloorEntity", tradingFloorEntity);
        model.addAttribute("locatedEntity", locatedEntity);
        status.setComplete();
        return "changeTradingFloor.html";
    }

    /*@ModelAttribute("tradingFloorEntity")
    public TradingFloorEntity getTradingFloorEntity (
            @RequestParam("tradingFloorEntity") TradingFloorEntity tradingFloorEntity
    ) {
        return tradingFloorEntity;
    }*/

    @RequestMapping(value = "/customerTradingFloor", method = RequestMethod.POST, params = "action=customer")
    public ModelAndView customerTradingFloor (
            @ModelAttribute("tradingFloorEntity") TradingFloorEntity tradingFloorEntity,
            @Valid CustomerEntity customerEntity,
            ModelAndView modelAndView
    ) {
        customerEntity.setTradingFloorId(tradingFloorEntity.getId());
        customerService.add(customerEntity);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
