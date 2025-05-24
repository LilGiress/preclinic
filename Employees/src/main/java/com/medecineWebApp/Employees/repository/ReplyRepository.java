package com.medecineWebApp.Employees.repository;

import com.medecineWebApp.Employees.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository  extends JpaRepository<Reply, Long> {
}
