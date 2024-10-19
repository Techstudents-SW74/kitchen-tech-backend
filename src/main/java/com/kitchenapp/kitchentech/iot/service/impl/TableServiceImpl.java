package com.kitchenapp.kitchentech.iot.service.impl;

import com.kitchenapp.kitchentech.business.model.Product;
import com.kitchenapp.kitchentech.business.repository.ProductRepository;
import com.kitchenapp.kitchentech.iot.model.Table;
import com.kitchenapp.kitchentech.iot.repository.TableRespository;
import com.kitchenapp.kitchentech.iot.service.TableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    private final TableRespository tableRespository;
    public TableServiceImpl(TableRespository tableRespository) {
        this.tableRespository = tableRespository;
    }

    @Override
    public List<Table> getAllTables() {
        return tableRespository.findAll();
    }

    @Override
    public Table getTableById(Long id) {
        return tableRespository.findById(id).orElse(null);
    }

    @Override
    public Table createTable(Table table) {
        return tableRespository.save(table);
    }

    @Override
    public void deleteTable(Long id) {
        tableRespository.deleteById(id);
    }

    @Override
    public Table updateTable(Table table) {
        Table tableToUpdate = tableRespository.findById(table.getId()).orElse(null);
        if (tableToUpdate != null){
            tableToUpdate.setTableCapacity(table.getTableCapacity());
            tableToUpdate.setTableNumber(table.getTableNumber());
            tableToUpdate.setTableStatus(table.getTableStatus());
            tableToUpdate.setRestaurant(table.getRestaurant());
            return tableRespository.save(tableToUpdate);
        }
        else {
            return null;
        }
    }

    @Override
    public void validateTable(Table table) {
        if(table == null){
            throw new IllegalArgumentException("La mesa no puede ser nula");
        }
        if(table.getTableCapacity() == null || table.getTableCapacity() <= 0){
            throw new IllegalArgumentException("La mesa es obligatoria y debe ser mayor a 0");
        }
        if(table.getTableStatus() == null || table.getTableStatus().isEmpty()){
            throw new IllegalArgumentException("El estado de la mesa es obligatorio");
        }
        if(!table.getTableStatus().equals("available") &&
                !table.getTableStatus().equals("occupied") &&
                !table.getTableStatus().equals("need cleaning")) {
            throw new IllegalArgumentException("La mesa debe tener un estado válido(available, occupied, need cleaning)");
        }
        if(table.getTableNumber() == null || table.getTableNumber() <= 0){
            throw new IllegalArgumentException("El número de la mesa no debe ser nulo y debe ser mayor a 0");
        }
        if(table.getRestaurant() == null) {
            throw new IllegalArgumentException("La mesa debe estar sujeto a un restaurante");
        }
    }
}
