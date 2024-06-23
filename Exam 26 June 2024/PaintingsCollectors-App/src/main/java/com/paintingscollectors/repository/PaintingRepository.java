package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
//    @Query(value = "SELECT p.* FROM Painting p " +
//            "JOIN user_painting_ratings upr ON p.id = upr.painting_id " +
//            "GROUP BY p.id " +
//            "ORDER BY COUNT(upr.user_id) DESC " +
//            "LIMIT 2", nativeQuery = true)
//    List<Painting> findTop2PaintingsByRatings();
}
