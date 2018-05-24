package com.rko.myspring.repository;

import com.rko.myspring.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Long> {
}
