package com.irrigationsystem.repository;

import com.irrigationsystem.entity.Land;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandRepository extends JpaRepository<Land, Long> {
}
