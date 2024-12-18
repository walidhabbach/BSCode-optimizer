package org.group.storage.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.group.storage.dto.StorageDto;
import org.group.storage.service.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;


    @PostMapping("/save")
    public ResponseEntity<StorageDto> save(@RequestBody  StorageDto storageDto) {
         return ResponseEntity.status(HttpStatus.CREATED).body(this.storageService.save (storageDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<StorageDto>> findAll() {
        return ResponseEntity.ok(this.storageService.findAll());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<StorageDto>> findAllByIDUser(@PathVariable("id") Long UserId) {
        return ResponseEntity.ok(this.storageService.findAllByIDUser (UserId));
    }
}
