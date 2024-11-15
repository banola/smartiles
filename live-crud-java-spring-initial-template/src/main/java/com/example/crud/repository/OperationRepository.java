package com.example.crud.repository;

import com.example.crud.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
// Esta interface é responsável por realizar a comunicação e as consultas no banco de dados.
// Essas consultas são realizadas pelas Entidades definidas nas classes de modelo.
public interface OperationRepository extends JpaRepository<Operation, String> {
    // A query remove_op deleta o campo específico do BD que possui o id definido na query.
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operations WHERE id = :id",
            nativeQuery = true)
    void removeOp(@Param("id") String id);
}
