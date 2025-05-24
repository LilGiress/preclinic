package com.medecineWebApp.Employees.services.impl;

import com.medecineWebApp.Employees.dto.ReplyDTO;
import com.medecineWebApp.Employees.dto.ReviewDTO;
import com.medecineWebApp.Employees.mapper.ReviewMapper;
import com.medecineWebApp.Employees.models.Reply;
import com.medecineWebApp.Employees.models.Review;
import com.medecineWebApp.Employees.repository.ReviewRepository;
import com.medecineWebApp.Employees.services.ReplyService;
import com.medecineWebApp.Employees.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ReplyService replyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, ReplyService replyService) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;

        this.replyService = replyService;
    }

    @Override
    public ReviewDTO saveReview(Review review) {
        return reviewMapper.reviewToReviewDTO(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDTO> findReviewsbyDoctorId(Long doctorId) {
        return reviewRepository.findAll().stream()
                .filter(review -> review.getDoctor().getId().equals(doctorId))
                .toList().stream().map(reviewMapper::reviewToReviewDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findReviewsbyPatientId(Long patientId) {
        return reviewRepository.findAll().stream()
                .filter(review -> review.getPatientId().equals(patientId))
                .map(reviewMapper::reviewToReviewDTO).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO findReviewById(Long id) {
        return reviewRepository.findById(id).map(reviewMapper::reviewToReviewDTO).orElse(null);
    }
    // Add a reply to a review

    public ReplyDTO addReply(Long reviewId, Reply reply) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
       // Review review = reviewService.getReviewsByDoctor(reviewId).stream().findFirst().orElseThrow(() -> new RuntimeException("Review not found"));
        reply.setReview(review);
        return replyService.save(reply);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

}
