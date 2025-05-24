package com.medecineWebApp.Employees.services;

import com.medecineWebApp.Employees.dto.ReplyDTO;
import com.medecineWebApp.Employees.dto.ReviewDTO;
import com.medecineWebApp.Employees.models.Reply;
import com.medecineWebApp.Employees.models.Review;

import java.util.List;

public interface ReviewService {
    ReviewDTO saveReview(Review review);
    List<ReviewDTO> findReviewsbyDoctorId(Long doctorId);
    List<ReviewDTO> findReviewsbyPatientId(Long patientId);
    ReviewDTO findReviewById(Long id);
    ReplyDTO addReply(Long reviewId, Reply reply);
    void deleteReview(Long id);

}
