package ua.nic.Practica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nic.Practica.model.ImageEntity;
import ua.nic.Practica.repository.IImageRepository;

import java.util.List;
import java.util.logging.Logger;



@Service
public class ImageService implements IEntityService {
    
    private static Logger log = Logger.getLogger(ImageService.class.getName());
    @Autowired
    IImageRepository imageRepository;


    @Override
    public ImageEntity getById(Integer id) {
        log.info("In ImageService getById{}" + id);
        return imageRepository.getOne(id);
    }

    @Override
    public void add(Object image) {
        log.info("In ImageService save{}" + image);

        imageRepository.save((ImageEntity) image);
    }

    @Override
    public void delete(Integer id) {
        log.info("In ImageService delete{}" + id);
        imageRepository.deleteById(id);
    }

    @Override
    public List getAll() {
       return imageRepository.findAll();
    }
}
