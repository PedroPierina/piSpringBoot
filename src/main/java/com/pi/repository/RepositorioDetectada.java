package com.pi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.pi.placa.PlacaDetectada;

@Repository
public interface RepositorioDetectada extends CrudRepository<PlacaDetectada, Integer>{

}
