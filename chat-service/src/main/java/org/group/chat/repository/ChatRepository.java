package org.group.chat.repository;

import org.group.chat.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository  extends JpaRepository<Chat, Long> {

    List<Chat> findAllByIdUser(Long idUser);

}
