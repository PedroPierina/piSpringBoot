package com.pi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.pi.placa.Placa;

@Repository
public interface Repositorio extends CrudRepository<Placa, Integer>{

}
