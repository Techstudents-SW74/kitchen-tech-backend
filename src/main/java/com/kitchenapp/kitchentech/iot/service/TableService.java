package com.kitchenapp.kitchentech.iot.service;

import com.kitchenapp.kitchentech.iot.model.Table;

import java.util.List;

public interface TableService {
    public List<Table> getAllTables();
    public Table getTableById(Long id);
    public Table createTable(Table table);
    public void deleteTable(Long id);
    public Table updateTable(Table table);
    public void validateTable(Table table);
}
