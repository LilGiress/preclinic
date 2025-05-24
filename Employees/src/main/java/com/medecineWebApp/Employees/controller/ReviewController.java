package com.medecineWebApp.Employees.controller;

import com.medecineWebApp.Employees.dto.ReplyDTO;
import com.medecineWebApp.Employees.dto.ReviewDTO;
import com.medecineWebApp.Employees.models.Reply;
import com.medecineWebApp.Employees.models.Review;
import com.medecineWebApp.Employees.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     *
     * @param review
     * @return
     */

    @PostMapping("/create")
    public ResponseEntity<ReviewDTO> addReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.saveReview(review));
    }

    /**
     *
     * @param doctorId
     * @return
     */

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsbyDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(reviewService.findReviewsbyDoctorId(doctorId));
    }

    /**
     *
     * @param patientId
     * @return
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsbyPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(reviewService.findReviewsbyPatientId(patientId));
    }

    /**
     *
     * @param reviewId
     * @param reply
     * @return
     */

    @PostMapping("/{reviewId}/reply")
    public ResponseEntity<ReplyDTO> addReply( @PathVariable Long reviewId, @RequestBody Reply reply) {
        return ResponseEntity.ok(reviewService.addReply(reviewId, reply));
    }

    /**
     *
     * @param reviewId
     */

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
