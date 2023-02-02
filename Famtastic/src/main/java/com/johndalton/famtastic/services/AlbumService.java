package com.johndalton.famtastic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johndalton.famtastic.models.Album;
import com.johndalton.famtastic.repositories.AlbumRepository;

@Service
public class AlbumService {
	@Autowired
	private final AlbumRepository albumRepository;
	
	public AlbumService(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}
	
	public Album addAlbum(Album album) {
		return albumRepository.save(album);
	}

	public Album updateAlbum(Album album) {
		return albumRepository.save(album);
	}
	public List<Album> getAll() {
		return albumRepository.findAll();
	}
	
	
//	verify photos are deleted from DB on delete
//	TO-DO - delete photos from S3 on album delete
	public void deleteAlbum(Album album) {
		albumRepository.delete(album);
	}
	
	public Album findById(Long id) {
		Optional<Album> optionalAlbum = albumRepository.findById(id);
		if(optionalAlbum.isPresent()) {
			return optionalAlbum.get();
		}else {
			return null;
		}
	}
}
