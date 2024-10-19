package com.kitchenapp.kitchentech.iot.controller;

import com.kitchenapp.kitchentech.business.model.Product;
import com.kitchenapp.kitchentech.business.service.ProductService;
import com.kitchenapp.kitchentech.iot.model.Table;
import com.kitchenapp.kitchentech.iot.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/kitchentech/v1/table")
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    // URL: http://localhost:8080/api/kitchentech/v1/product
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<Table>> getAllTables() {
        if (tableService.getAllTables().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tableService.getAllTables(), HttpStatus.OK);
    }
}
