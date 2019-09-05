package ua.nic.Practica.service;

import java.util.List;

public interface IEntityService {
    Object getById (Integer id);

    void add (Object object);

    void delete (Integer id);

    List getAll ();
}
