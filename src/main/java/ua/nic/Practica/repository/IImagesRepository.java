package ua.nic.Practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nic.Practica.model.ImagesEntity;

import java.util.ArrayList;

/*
    Для того, щоб забезпечити взаємодію із БД,
    потрібно створити репозиторій для кожної сущності
*/
public interface IImagesRepository extends JpaRepository<ImagesEntity, Integer> {
    public ArrayList<ImagesEntity> getAllByTradingFloorId(int tradingFloorId);
}
