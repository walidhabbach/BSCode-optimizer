package org.group.chat.helper.mappers;
import org.group.chat.dto.ChatDto;
import org.group.chat.model.Chat;

public class ChatMapper{
	public static ChatDto map(final Chat storage) {
		return ChatDto.builder()
				.id(storage.getId())
				.isInput (storage.getIsInput ())
				.text (storage.getText ())
				.idUser (storage.getIdUser ())
				.creationDate (storage.getCreationDate ())
				.build();
	}

	public static Chat map( final ChatDto storageDto) {
		return Chat.builder()
				.id(storageDto.getId())
				.isInput (storageDto.getIsInput ())
				.text (storageDto.getText ())
				.idUser (storageDto.getIdUser ())
				.build();
	}

}








