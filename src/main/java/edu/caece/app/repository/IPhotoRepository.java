package edu.caece.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.caece.app.domain.Photo;

public interface IPhotoRepository extends JpaRepository<Photo, Long>{
	
	
}
