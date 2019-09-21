package ua.nic.Practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nic.Practica.model.CustomerEntity;

/*
    Для того, щоб забезпечити взаємодію із БД,
    потрібно створити репозиторій для кожної сущності
*/
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}
