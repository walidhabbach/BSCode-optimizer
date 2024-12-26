package org.group.chat.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.chat.FeignClient.ApiModelClient;
import org.group.chat.dto.ChatDto;
import org.group.chat.helper.mappers.ChatMapper;
import org.group.chat.model.Chat;
import org.group.chat.repository.ChatRepository;
import org.group.chat.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository storageRepository;

    private final ApiModelClient apiModelClient;

    @Override
    public ChatDto optimize(ChatDto chatDto){
        String optimizedCode = this.apiModelClient.optimizeCode (chatDto.getCode ());

        Chat input = this.storageRepository.save (ChatMapper.map (chatDto));
        Chat output = Chat.builder ().idUser (chatDto.getIdUser ()).code (optimizedCode).isInput (false).build ();
        System.out.println ("optimizedCode"+optimizedCode);
        output = this.storageRepository.save (output);
        System.out.println (output.getCode ());
        return ChatMapper.map (output);
    }

    @Override
    public List<ChatDto> findAll(){
        System.out.println ("findAll");
        return this.storageRepository.findAll ().stream ().map (ChatMapper::map).collect (Collectors.toList ());
    }

    @Override
    public List<ChatDto> findAllByIDUser(Long idUser){
        return this.storageRepository.findAllByIdUser (idUser).stream ().map (ChatMapper::map).collect (Collectors.toList ());
    }

}

