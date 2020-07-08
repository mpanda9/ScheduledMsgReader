package com.mp.msg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mp.msg.entity.MessageEntity;

@Repository
public interface MessageDao extends JpaRepository<MessageEntity, Integer> {

	@Query(value = "select msg from MESSAGE_TABLE msg where msg.delivered='N'")
	List<MessageEntity> getAllMessagesNotProcessed();
}
