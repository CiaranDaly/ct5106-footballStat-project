package com.ct5106.swishStats.repository;

import com.ct5106.swishStats.domain.League;
import com.ct5106.swishStats.domain.Manager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends CrudRepository<Manager, Long>
{
    List<Manager> findManagersByNationality (String nationality);
    // fetch managers by ratings range
    @Query("select m from Manager m where m.managerRating between ?1 and ?2")
    List<Manager> findManagersByRatingRange(@Param("minRating") int minRating, @Param("maxRating") int maxRating);

}
