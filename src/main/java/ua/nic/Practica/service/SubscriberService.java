package ua.nic.Practica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nic.Practica.model.CustomerEntity;
import ua.nic.Practica.model.SubscriberEntity;
import ua.nic.Practica.repository.ICustomerRepository;
import ua.nic.Practica.repository.ISubscriberRepository;

import java.util.List;
import java.util.logging.Logger;


@Service
public class SubscriberService implements IEntityService {

    private static Logger log = Logger.getLogger(SubscriberService.class.getName());
    @Autowired
    ISubscriberRepository subscriberRepository;


    @Override
    public SubscriberEntity getById(Integer id) {
        log.info("In LocatedService getById{}" + id);
        return subscriberRepository.getOne(id);
    }

    @Override
    public void add(Object located) {
        log.info("In LocatedService save{}" + located);

        subscriberRepository.save((SubscriberEntity) located);
    }

    @Override
    public void delete(Integer id) {
        log.info("In LocatedService delete{}" + id);
        subscriberRepository.deleteById(id);
    }

    @Override
    public List getAll() {
       return subscriberRepository.findAll();
    }
}
