package com.johndalton.famtastic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johndalton.famtastic.models.Album;
import com.johndalton.famtastic.models.Photo;
import com.johndalton.famtastic.repositories.PhotoRepository;

@Service
public class PhotoService {
	@Autowired
	private final PhotoRepository photoRepository;
	
	public PhotoService(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}
	
	public List<Photo> getPhotos(Album album) {
		return photoRepository.findAllByAlbum(album);
	}
	
	public Photo addPhoto(Photo photo) {
		return photoRepository.save(photo);
	}

	public Photo updatePhoto(Photo photo) {
		return photoRepository.save(photo);
	}
	
	public void deletePhoto(Photo photo) {
		photoRepository.delete(photo);
	}
	
	
}
