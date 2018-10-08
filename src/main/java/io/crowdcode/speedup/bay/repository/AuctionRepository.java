package io.crowdcode.speedup.bay.repository;

import io.crowdcode.speedup.bay.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query("SELECT a FROM Auction a LEFT JOIN a.bids b WHERE b.email = :email")
    List<Auction> findAllAuctionWithABidFrom(@Param("email") String email);


}
