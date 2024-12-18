package org.group.storage.repository;
import org.group.storage.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageRepository  extends JpaRepository<Storage, Long> {

    List<Storage> findAllByIdUser( Long idUser);

}
