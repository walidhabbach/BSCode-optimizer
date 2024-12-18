package org.group.storage.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.group.storage.dto.StorageDto;
import org.group.storage.helper.mappers.StorageMapper;
import org.group.storage.repository.StorageRepository;
import org.group.storage.service.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Override
    public StorageDto save(StorageDto storageDto) {
        return StorageMapper.map(this.storageRepository.save(StorageMapper.map(storageDto)));
    }

    @Override
    public List<StorageDto> findAll() {
        System.out.println ("findAll");
        return this.storageRepository.findAll ()
                .stream ()
                .map (StorageMapper::map)
                .collect (Collectors.toList ());
    }

    @Override
    public List<StorageDto> findAllByIDUser(Long idUser) {
        return this.storageRepository.findAllByIdUser (idUser)
                .stream ()
                .map (StorageMapper::map)
                .collect (Collectors.toList ());
    }

}

