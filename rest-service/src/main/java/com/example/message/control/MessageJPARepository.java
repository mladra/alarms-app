package com.example.message.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MessageJPARepository extends JpaRepository<MessageEntityJPA, Long> {
}
