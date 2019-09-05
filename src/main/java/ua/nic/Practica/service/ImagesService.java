package ua.nic.Practica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nic.Practica.model.ImagesEntity;
import ua.nic.Practica.repository.IImagesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



@Service
public class ImagesService implements IEntityService {
    
    private static Logger log = Logger.getLogger(ImagesService.class.getName());
    @Autowired
    IImagesRepository imagesRepository;

    public ArrayList<ImagesEntity> getAllByTradingFloorId(int tradingFloorId){
        log.info("In ImagesService getAllByTradingFloorId{}" + tradingFloorId);
        return imagesRepository.getAllByTradingFloorId(tradingFloorId);
    }

    @Override
    public ImagesEntity getById(Integer id) {
        log.info("In ImagesService getById{}" + id);
        return imagesRepository.getOne(id);
    }

    @Override
    public void add(Object images) {
        log.info("In ImagesService save{}" + images);

        imagesRepository.save((ImagesEntity) images);
    }

    @Override
    public void delete(Integer id) {
        log.info("In ImagesService delete{}" + id);
        imagesRepository.deleteById(id);
    }

    @Override
    public List getAll() {
       return imagesRepository.findAll();
    }
}
