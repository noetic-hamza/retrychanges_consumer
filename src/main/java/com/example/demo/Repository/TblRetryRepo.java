package com.example.demo.Repository;


import com.example.demo.model.TblRetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblRetryRepo extends JpaRepository<TblRetryEntity, Long> {
}
