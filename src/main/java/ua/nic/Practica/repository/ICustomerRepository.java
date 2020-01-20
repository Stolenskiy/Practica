package ua.nic.Practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.nic.Practica.model.CustomerEntity;

/*
    Для того, щоб забезпечити взаємодію із БД,
    потрібно створити репозиторій для кожної сутності
*/
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    @Transactional
    public void deleteAllByTradingFloorId (Integer tradingFloorId);
}
