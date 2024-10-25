package com.example.crud.repository;

import com.example.crud.model.history.History;
import com.example.crud.model.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, String> {
    @Query("SELECT o FROM operation o JOIN history h ON o.id = h.id_operations WHERE h.id_user = :id_user")
//    @Query("SELECT h.id_operations FROM history h WHERE h.id_user = :id_user")
    List<Operation> findAllOps(@Param("id_user") String id_user);
}
