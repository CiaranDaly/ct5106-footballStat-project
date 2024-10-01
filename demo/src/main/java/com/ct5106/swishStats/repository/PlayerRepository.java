package com.ct5106.swishStats.repository;

import com.ct5106.swishStats.domain.Player;
import org.springframework.data.repository.CrudRepository;

// grr!
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
