package ua.nic.Practica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nic.Practica.model.LocatedEntity;
import ua.nic.Practica.repository.ILocatedRepository;

import java.util.List;
import java.util.logging.Logger;



@Service
public class LocatedService implements IEntityService {

    private static Logger log = Logger.getLogger(LocatedService.class.getName());
    @Autowired
    ILocatedRepository locatedRepository;


    @Override
    public LocatedEntity getById(Integer id) {
        log.info("In LocatedService getById{{}" + id);
        return locatedRepository.getOne(id);
    }

    @Override
    public void add(Object located) {
        log.info("In LocatedService save{}" + located);

        locatedRepository.save((LocatedEntity) located);
    }

    @Override
    public void delete(Integer id) {
        log.info("In LocatedService delete{}" + id);
        locatedRepository.deleteById(id);
    }

    @Override
    public List getAll() {
       return locatedRepository.findAll();
    }
}
