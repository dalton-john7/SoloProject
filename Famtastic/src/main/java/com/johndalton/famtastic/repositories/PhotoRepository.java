package com.johndalton.famtastic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johndalton.famtastic.models.Album;
import com.johndalton.famtastic.models.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long>{
	
	List<Photo> findAllByAlbum(Album album);

}
