package org.group.chat.service.interfaces;


import org.group.chat.dto.ChatDto;

import java.util.List;

public interface ChatService {
	ChatDto optimize(ChatDto storageDto);
	List<ChatDto> findAll();
	List<ChatDto> findAllByIDUser( final Long idUser);
}

