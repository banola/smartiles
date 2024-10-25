package com.example.crud.repository;

import com.example.crud.model.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OperationRepository extends JpaRepository<Operation, String> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operations WHERE id = :id",
            nativeQuery = true)
    void removeOp(@Param("id") String id);
}
