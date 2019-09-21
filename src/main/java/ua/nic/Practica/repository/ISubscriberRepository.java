package ua.nic.Practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nic.Practica.model.CustomerEntity;
import ua.nic.Practica.model.SubscriberEntity;

/*
    Для того, щоб забезпечити взаємодію із БД,
    потрібно створити репозиторій для кожної сущності
*/
public interface ISubscriberRepository extends JpaRepository<SubscriberEntity, Integer> {
}
