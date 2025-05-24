package com.medecineWebApp.Employees.repository;

import com.medecineWebApp.Employees.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
