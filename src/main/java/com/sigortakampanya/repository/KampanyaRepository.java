package com.sigortakampanya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigortakampanya.models.Kampanya;


@Repository
public interface KampanyaRepository extends JpaRepository<Kampanya, Integer> {
	

}
