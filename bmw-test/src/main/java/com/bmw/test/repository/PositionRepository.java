package com.bmw.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bmw.test.domain.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

	public Position findBySession(String session);
}
