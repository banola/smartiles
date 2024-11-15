package com.example.crud.repository;

import com.example.crud.model.History;
import com.example.crud.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
// Esta interface é responsável por realizar a comunicação e as consultas no banco de dados. Essas consultas são realizadas pelas Entidades definidas nas classes de modelo.
// Ela utiliza a JpaRespository para realizar as consultas padrões como findAll() e save() por exemplo, mas também permite consultas personalizadas
// como por exemplo a consulta findAllOps(String id_user).
public interface HistoryRepository extends JpaRepository<History, String> {
    // Esta consulta retorna todos as operações com os seus dados que são referentes ao usuário em questão.
    // Para isso foi necessário realizar um JOIN com a entidade history que possui o id do usuário assim como o id das operações.
    @Query("SELECT o FROM operation o JOIN history h ON o.id = h.id_operations WHERE h.id_user = :id_user")
    List<Operation> findAllOps(@Param("id_user") String id_user);
}
