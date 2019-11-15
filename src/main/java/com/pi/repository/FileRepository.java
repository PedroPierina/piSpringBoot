package com.pi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.image.Image;

@Repository
public interface FileRepository extends JpaRepository<Image, String> {

}
