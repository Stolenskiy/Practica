package ua.nic.Practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nic.Practica.model.TradingFloorEntity;

/*
    Для того, щоб забезпечити взаємодію із БД,
    потрібно створити репозиторій для кожної сущності
*/
public interface ITradingFloorRepository extends JpaRepository<TradingFloorEntity, Integer> {}
