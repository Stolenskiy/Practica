package ua.nic.Practica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nic.Practica.model.CustomerEntity;
import ua.nic.Practica.model.LocatedEntity;
import ua.nic.Practica.repository.ICustomerRepository;
import ua.nic.Practica.repository.ILocatedRepository;

import java.util.List;
import java.util.logging.Logger;


@Service
public class CustomerService implements IEntityService {

    private static Logger log = Logger.getLogger(CustomerService.class.getName());
    @Autowired
    ICustomerRepository customerRepository;


    @Override
    public CustomerEntity getById(Integer id) {
        log.info("In LocatedService getById{{}" + id);
        return customerRepository.getOne(id);
    }

    @Override
    public void add(Object located) {
        log.info("In LocatedService save{}" + located);

        customerRepository.save((CustomerEntity) located);
    }

    @Override
    public void delete(Integer id) {
        log.info("In LocatedService delete{}" + id);
        customerRepository.deleteById(id);
    }

    @Override
    public List getAll() {
       return customerRepository.findAll();
    }
}
