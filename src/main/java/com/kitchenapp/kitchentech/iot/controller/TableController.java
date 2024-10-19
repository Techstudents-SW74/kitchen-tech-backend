package com.kitchenapp.kitchentech.iot.controller;

import com.kitchenapp.kitchentech.iot.model.Table;
import com.kitchenapp.kitchentech.iot.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/kitchentech/v1/table")
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    // URL: http://localhost:8080/api/kitchentech/v1/table
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Table>> getAllTables() {
        if (tableService.getAllTables().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tableService.getAllTables(), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/table/{tableId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{tableId}")
    public ResponseEntity<Table> getTableById(@PathVariable(name = "tableId") Long tableId) {
        if(tableService.getTableById(tableId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tableService.getTableById(tableId), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/table
    // Method: POST
    @Transactional
    @PostMapping
    public ResponseEntity<Table> createTable(@RequestBody Table table) {
        tableService.validateTable(table);
        return new ResponseEntity<>(tableService.createTable(table), HttpStatus.CREATED);
    }
}
