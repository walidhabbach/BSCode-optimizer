package org.group.chat.helper.mappers;
import org.group.chat.dto.ChatDto;
import org.group.chat.model.Chat;

public class ChatMapper{
	public static ChatDto map(final Chat storage) {
		return ChatDto.builder()
				.id(storage.getId())
				.isInput (storage.getIsInput ())
				.code (storage.getCode ())
				.idUser (storage.getIdUser ())
				.creationDate (storage.getCreationDate ())
				.build();
	}

	public static Chat map( final ChatDto storageDto) {
		return Chat.builder()
				.id(storageDto.getId())
				.isInput (storageDto.getIsInput ())
				.code (storageDto.getCode ())
				.idUser (storageDto.getIdUser ())
				.build();
	}

}








