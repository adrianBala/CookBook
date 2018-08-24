package com.codecool.krk.dao;

import com.codecool.krk.model.Review;

public interface ReviewDao {

    boolean saveNewReview(Review review);
    boolean removeReview(long id);

    boolean updateReview(int rating, String opinion, long id);
}
