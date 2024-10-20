package com.kitchenapp.kitchentech.iot.service.impl;

import com.kitchenapp.kitchentech.iot.model.TableRestaurant;
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
    public List<TableRestaurant> getAllTables() {
        return tableRespository.findAll();
    }

    @Override
    public TableRestaurant getTableById(Long id) {
        return tableRespository.findById(id).orElse(null);
    }

    @Override
    public TableRestaurant createTable(TableRestaurant tableRestaurant) {
        return tableRespository.save(tableRestaurant);
    }

    @Override
    public void deleteTable(Long id) {
        tableRespository.deleteById(id);
    }

    @Override
    public TableRestaurant updateTable(TableRestaurant tableRestaurant) {
        TableRestaurant tableRestaurantToUpdate = tableRespository.findById(tableRestaurant.getId()).orElse(null);
        if (tableRestaurantToUpdate != null){
            tableRestaurantToUpdate.setTableCapacity(tableRestaurant.getTableCapacity());
            tableRestaurantToUpdate.setTableNumber(tableRestaurant.getTableNumber());
            tableRestaurantToUpdate.setTableStatus(tableRestaurant.getTableStatus());
            tableRestaurantToUpdate.setRestaurant(tableRestaurant.getRestaurant());
            return tableRespository.save(tableRestaurantToUpdate);
        }
        else {
            return null;
        }
    }

    @Override
    public void validateTable(TableRestaurant tableRestaurant) {
        if(tableRestaurant == null){
            throw new IllegalArgumentException("La mesa no puede ser nula");
        }
        if(tableRestaurant.getTableCapacity() == null || tableRestaurant.getTableCapacity() <= 0){
            throw new IllegalArgumentException("La mesa es obligatoria y debe ser mayor a 0");
        }
        if(tableRestaurant.getTableStatus() == null || tableRestaurant.getTableStatus().isEmpty()){
            throw new IllegalArgumentException("El estado de la mesa es obligatorio");
        }
        if(!tableRestaurant.getTableStatus().equals("available") &&
                !tableRestaurant.getTableStatus().equals("occupied") &&
                !tableRestaurant.getTableStatus().equals("need cleaning")) {
            throw new IllegalArgumentException("La mesa debe tener un estado válido(available, occupied, need cleaning)");
        }
        if(tableRestaurant.getTableNumber() == null || tableRestaurant.getTableNumber() <= 0){
            throw new IllegalArgumentException("El número de la mesa no debe ser nulo y debe ser mayor a 0");
        }
        if(tableRestaurant.getRestaurant() == null) {
            throw new IllegalArgumentException("La mesa debe estar sujeto a un restaurante");
        }
    }
}
