package org.group.chat.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.group.chat.dto.ChatDto;
import org.group.chat.helper.mappers.ChatMapper;
import org.group.chat.repository.ChatRepository;
import org.group.chat.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository storageRepository;

    @Override
    public ChatDto save(ChatDto storageDto) {
        return ChatMapper.map(this.storageRepository.save(ChatMapper.map(storageDto)));
    }

    @Override
    public List<ChatDto> findAll() {
        System.out.println ("findAll");
        return this.storageRepository.findAll ()
                .stream ()
                .map (ChatMapper::map)
                .collect (Collectors.toList ());
    }

    @Override
    public List<ChatDto> findAllByIDUser(Long idUser) {
        return this.storageRepository.findAllByIdUser (idUser)
                .stream ()
                .map (ChatMapper::map)
                .collect (Collectors.toList ());
    }

}

