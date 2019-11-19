package com.pi.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pi.image.Image;

@Repository
public interface FileRepository extends CrudRepository<Image, Integer> {

}
