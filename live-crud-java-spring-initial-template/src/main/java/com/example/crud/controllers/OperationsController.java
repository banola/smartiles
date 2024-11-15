package com.example.crud.controllers;//package com.example.crud.controllers;

import com.example.crud.model.History;
import com.example.crud.repository.HistoryRepository;
import com.example.crud.model.Operation;
import com.example.crud.repository.OperationRepository;
import com.example.crud.model.User;
import com.example.crud.request.RequestOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

// Esta classe é responsável por realizar operações do tipo CRUD para todas as operações de cálculos realizados pelo usuário.

@RestController
@RequestMapping("/operations")
public class OperationsController {
    // Aqui foi necessário injetar valores de duas classes, a OperationRepository e HistoryRepository.
    // Operation -> Cálculo individual.
    // History -> Historico de todas as operações realizados pelo usuário.
    @Autowired
    private OperationRepository operationRepository;
    private HistoryRepository historyrepository;

    @Autowired
    public OperationsController(OperationRepository operationRepository, HistoryRepository historyrepository) {
        this.operationRepository = operationRepository;
        this.historyrepository = historyrepository;
    }

    // Este metodo é responsável por salvar a operação no histórico do usuário, é necessário um Body com os valores da operação à ser salva:
    @PostMapping("/save")
    public ResponseEntity<Void> save_history(@RequestBody RequestOperation operation,
                                             HttpSession session) {
        Operation new_op = new Operation(operation);
        // Salva na tabela de Operações do BD a nova operação:
        operationRepository.save(new_op);
        // Cria um objeto User com os valores da sessão atual:
        User session_user = (User) session.getAttribute("user");
        String id_session = session_user.getId();
        // Com os valores do id do usuário e da nova operação é criado um novo valor na tabela histórico:
        History new_history = new History();
        new_history.setId_user(id_session);
        new_history.setId_operations(new_op.getId());
        historyrepository.save(new_history);

        return ResponseEntity.ok().build();
    }

    // O metodo get_all_ops retorna uma lista de histórico com todas as operações do usuário:
    @GetMapping
    public ResponseEntity get_all_ops(HttpSession session){

        User session_user = (User) session.getAttribute("user");
        String id_session = session_user.getId();

        var list_ops = historyrepository.findAllOps(id_session);

        return ResponseEntity.ok(list_ops);
    }

    // O metodo remove_op simplesmente encontra a operação com o id específico e a deleta da tabela Operações,
    // por conta da função CASCADE definida na tabela Histórico a mesma operação também é removida desta tabela.
    @DeleteMapping("{id}")
    public ResponseEntity remove_op(@PathVariable String id){

        operationRepository.removeOp(id);
        return ResponseEntity.ok().build();
    }
}
