package com.example.crud.controllers;//package com.example.crud.controllers;

import com.example.crud.model.history.History;
import com.example.crud.repository.HistoryRepository;
import com.example.crud.model.operation.Operation;
import com.example.crud.repository.OperationRepository;
import com.example.crud.model.user.User;
import com.example.crud.request.RequestOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    @Autowired
    private OperationRepository operationRepository;
    private HistoryRepository historyrepository;

    @Autowired
    public OperationsController(OperationRepository operationRepository, HistoryRepository historyrepository) {
        this.operationRepository = operationRepository;
        this.historyrepository = historyrepository;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> save_history(@RequestBody RequestOperation operation,
                                             HttpSession session) {
        Operation new_op = new Operation(operation);

        operationRepository.save(new_op);

        User session_user = (User) session.getAttribute("user");
        String id_session = session_user.getId();

        History new_history = new History();
        new_history.setId_user(id_session);
        new_history.setId_operations(new_op.getId());
        historyrepository.save(new_history);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity get_all_ops(HttpSession session){

        User session_user = (User) session.getAttribute("user");
        String id_session = session_user.getId();

        var list_ops = historyrepository.findAllOps(id_session);

        return ResponseEntity.ok(list_ops);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove_op(@PathVariable String id){

        operationRepository.removeOp(id);
        return ResponseEntity.ok().build();
    }
}
