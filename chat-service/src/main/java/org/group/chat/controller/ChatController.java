package org.group.chat.controller;

import org.group.chat.dto.ChatDto;
import org.group.chat.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService storageService;


    @PostMapping("/save")
    public ResponseEntity<ChatDto> save(@RequestBody  ChatDto storageDto) {
         return ResponseEntity.status(HttpStatus.CREATED).body(this.storageService.save (storageDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatDto>> findAll() {
        return ResponseEntity.ok(this.storageService.findAll());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<ChatDto>> findAllByIDUser(@PathVariable("id") Long UserId) {
        return ResponseEntity.ok(this.storageService.findAllByIDUser (UserId));
    }
}
