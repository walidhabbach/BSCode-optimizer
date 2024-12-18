package org.group.storage.helper.mappers;
import org.group.storage.dto.StorageDto;
import org.group.storage.model.Storage;

public class StorageMapper{
	public static StorageDto map(final Storage storage) {
		return StorageDto.builder()
				.id(storage.getId())
				.isInput (storage.getIsInput ())
				.text (storage.getText ())
				.idUser (storage.getIdUser ())
				.creationDate (storage.getCreationDate ())
				.build();
	}

	public static Storage map( final StorageDto storageDto) {
		return Storage.builder()
				.id(storageDto.getId())
				.isInput (storageDto.getIsInput ())
				.text (storageDto.getText ())
				.idUser (storageDto.getIdUser ())
				.build();
	}

}








