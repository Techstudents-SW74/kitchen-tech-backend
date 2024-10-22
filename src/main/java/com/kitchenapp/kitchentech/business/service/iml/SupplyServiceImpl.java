package com.kitchenapp.kitchentech.business.service.iml;

import com.kitchenapp.kitchentech.business.model.Product;
import com.kitchenapp.kitchentech.business.model.Supply;
import com.kitchenapp.kitchentech.business.repository.ProductRepository;
import com.kitchenapp.kitchentech.business.repository.SupplyRepository;
import com.kitchenapp.kitchentech.business.service.ProductService;
import com.kitchenapp.kitchentech.business.service.SupplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {
    private final SupplyRepository supplyRepository;

    public SupplyServiceImpl(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    public List<Supply> getAllSupplies(Long restaurantId){
        return supplyRepository.findByRestaurantId(restaurantId);
    }
    @Override
    public Supply getSupplyById(Long id){
        return supplyRepository.findById(id).orElse(null);
    }
    @Override
    public Supply createSupply(Supply supply){

        return supplyRepository.save(supply);
    }
    @Override
    public void deleteSupply(Long id){
        supplyRepository.deleteById(id);
    }
    @Override
    public Supply updateSupply(Supply supply){
        Supply supplyToUpdate = supplyRepository.findById(supply.getId()).orElse(null);
        if (supplyToUpdate != null){
            supplyToUpdate.setStateOfSupply(supply.getStateOfSupply());
            supplyToUpdate.setSupplyName(supply.getSupplyName());
            supplyToUpdate.setSupplyCategory(supply.getSupplyCategory());
            supplyToUpdate.setCostPerUnit(supply.getCostPerUnit());
            supplyToUpdate.setCurrentlyInStock(supply.getCurrentlyInStock());
            supplyToUpdate.setEstimatedDailyUse(supply.getEstimatedDailyUse());
            supplyToUpdate.setMetricUnit(supply.getMetricUnit());
            return supplyRepository.save(supplyToUpdate);
        }
        else {
            return null;
        }
    }

    @Override
    public void validateSupply(Supply supply){
        if(supply == null){
            throw new IllegalArgumentException("El insumo no puede ser nulo");
        }
        if (supply.getSupplyName() == null || supply.getSupplyName().isEmpty()){
            throw new IllegalArgumentException("El nombre del insumo debe ser obligatorio");
        }
        if (supply.getSupplyName().length() > 50 ){
            throw new IllegalArgumentException("El nombre del insumo no debe exceder los 50 caracteres");
        }
        if (supply.getCostPerUnit() == null || supply.getCostPerUnit() <= 0){
            throw new IllegalArgumentException("El precio del insumo es obligatorio y mayor a 0");
        }
        if (supply.getSupplyCategory() == null || supply.getSupplyCategory().isEmpty()){
            throw new IllegalArgumentException("La categoria del insumo es obligatoria");
        }
        if (supply.getSupplyCategory().length() > 50 ){
            throw new IllegalArgumentException("La categoria del insumo no debe exceder los 50 caracteres");
        }
        if (supply.getStateOfSupply() == null || supply.getStateOfSupply().isEmpty()){
            throw new IllegalArgumentException("El estado del insumo es obligatorio");
        }
        if (supply.getStateOfSupply().length() > 50 ){
            throw new IllegalArgumentException("El estado del insumo no debe exceder los 50 caracteres");
        }
        if (supply.getMetricUnit() == null || supply.getMetricUnit().isEmpty()){
            throw new IllegalArgumentException("La unidad de medida del insumo es obligatoria");
        }
        if (supply.getMetricUnit().length() > 50){
            throw new IllegalArgumentException("La unidad de medida no debe exceder los 50 caracteres");
        }
        if (supply.getCurrentlyInStock() < 0){
            throw new IllegalArgumentException("El stock actual debe ser valido");
        }
        if (supply.getEstimatedDailyUse() == null || supply.getEstimatedDailyUse() < 0){
            throw new IllegalArgumentException("El uso diario estimado debe ser valido");
        }
    }
}
