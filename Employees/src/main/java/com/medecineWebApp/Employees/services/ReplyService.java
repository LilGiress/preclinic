package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.dto.ReplyDTO;
import com.medecineWebApp.Employees.models.Reply;

public interface ReplyService {
    ReplyDTO save(Reply reply);
}
