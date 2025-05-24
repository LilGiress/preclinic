package com.medecineWebApp.Employees.services.impl;

import com.medecineWebApp.Employees.dto.ReplyDTO;
import com.medecineWebApp.Employees.mapper.ReplyMapper;
import com.medecineWebApp.Employees.models.Reply;
import com.medecineWebApp.Employees.repository.ReplyRepository;
import com.medecineWebApp.Employees.services.ReplyService;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;

    public ReplyServiceImpl(ReplyRepository replyRepository, ReplyMapper replyMapper) {
        this.replyRepository = replyRepository;
        this.replyMapper = replyMapper;
    }

    @Override
    public ReplyDTO save(Reply reply) {
        return replyMapper.replyToReplyDTO(replyRepository.save(reply));
    }
}
