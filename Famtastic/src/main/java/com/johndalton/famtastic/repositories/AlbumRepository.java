package com.johndalton.famtastic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johndalton.famtastic.models.Album;
import com.johndalton.famtastic.models.User;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
	
	List<Album> findAll();
	


}
