package org.group.storage.service.interfaces;


import org.group.storage.dto.StorageDto;
import org.group.storage.model.Storage;

import java.util.List;

public interface StorageService {
	StorageDto save(StorageDto storageDto);
	List<StorageDto> findAll();
	List<StorageDto> findAllByIDUser( final Long idUser);
}

