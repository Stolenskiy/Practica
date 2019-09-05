package ua.nic.Practica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nic.Practica.model.TradingFloorEntity;
import ua.nic.Practica.repository.ITradingFloorRepository;

import java.util.List;
import java.util.logging.Logger;



@Service
public class TradingFloorService implements IEntityService {
    
    private static Logger log = Logger.getLogger(TradingFloorService.class.getName());
    @Autowired
    ITradingFloorRepository tradingFloorRepository;


    @Override
    public TradingFloorEntity getById(Integer id) {
        log.info("In TradingFloorService getById{{}" + id);
        return tradingFloorRepository.getOne(id);
    }

    @Override
    public void add(Object tradingFloor) {
        log.info("In TradingFloorService save{}" + tradingFloor);

        tradingFloorRepository.save((TradingFloorEntity) tradingFloor);
    }

    @Override
    public void delete(Integer id) {
        log.info("In TradingFloorService delete{}" + id);
        tradingFloorRepository.deleteById(id);
    }

    @Override
    public List getAll() {
       return tradingFloorRepository.findAll();
    }
}
