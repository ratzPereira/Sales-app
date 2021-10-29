package com.ratz.repository;


import com.ratz.entity.ItemOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsOrderedRepository extends JpaRepository<ItemOrdered, Integer> {
}
