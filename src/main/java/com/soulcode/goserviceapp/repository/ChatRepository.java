package com.soulcode.goserviceapp.repository;

import com.soulcode.goserviceapp.domain.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    @Query("{ 'idAgendamento' : ?0 }")
    List<Chat> findByIdAgendamento(String idAgendamento);
}
