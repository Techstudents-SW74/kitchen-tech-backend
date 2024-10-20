package com.kitchenapp.kitchentech.business.service.iml;

import com.kitchenapp.kitchentech.authentication.model.RegisterRequest;
import com.kitchenapp.kitchentech.business.model.Restaurant;
import com.kitchenapp.kitchentech.business.model.RestaurantRequest;
import com.kitchenapp.kitchentech.business.repository.RestaurantRepository;
import com.kitchenapp.kitchentech.business.service.RestaurantService;
import com.kitchenapp.kitchentech.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant createRestaurant(RestaurantRequest restaurantRequest) {

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantRequest.getName())
                .email(restaurantRequest.getEmail())
                .description(restaurantRequest.getDescription())
                .image(restaurantRequest.getImage())
                .logo(restaurantRequest.getLogo())
                .city(restaurantRequest.getCity())
                .district(restaurantRequest.getDistrict())
                .build();

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant existingRestaurant = getRestaurantById(restaurant.getId());

        if(existingRestaurant != null){
            Restaurant restaurantToUpdate = existingRestaurant;
            restaurantToUpdate.setName(restaurant.getName());
            restaurantToUpdate.setEmail(restaurant.getEmail());
            restaurantToUpdate.setDescription(restaurantToUpdate.getDescription());
            restaurantToUpdate.setImage(restaurantToUpdate.getImage());
            restaurantToUpdate.setLogo(restaurantToUpdate.getLogo());
            restaurantToUpdate.setCity(restaurantToUpdate.getCity());
            restaurantToUpdate.setDistrict(restaurantToUpdate.getDistrict());

            return restaurantRepository.save(restaurantToUpdate);
        }else{
            return null;
        }

    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public void existsRestaurantById(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ValidationException("No existe ningún restaurante");
        }
    }

    @Override
    public void validateRestaurant(RestaurantRequest restaurantRequest) {
        if(restaurantRequest.getName() ==  null || restaurantRequest.getName().isEmpty()){
            throw new ValidationException("El nombre del restaurante debe ser obligatorio");
        }
        if(restaurantRequest.getName().length() >150){
            throw new ValidationException("El nombre no debe de exceder de 150 caracteres");
        }
        if(restaurantRequest.getEmail() == null || restaurantRequest.getEmail().isEmpty()){
            throw new ValidationException("El email del restaurante debe ser obligatorio");
        }
        if(restaurantRequest.getEmail().length() >150){
            throw new ValidationException("El email no debe de exceder de 150 caracteres");
        }
        if(restaurantRequest.getDescription() == null || restaurantRequest.getDescription().isEmpty()){
            throw new ValidationException("La descripción del restaurante debe ser obligatorio");
        }
        if(restaurantRequest.getDescription().length() >300){
            throw new ValidationException("Descripción muy larga");
        }
        if(restaurantRequest.getCity() == null || restaurantRequest.getCity().isEmpty()){
            throw new ValidationException("La ciudad del restaurante debe ser obligatorio");
        }
        if(restaurantRequest.getCity().length() >100){
            throw new ValidationException("La ciudad no debe exceder los 100 caracteres");
        }
        if(restaurantRequest.getDistrict() == null || restaurantRequest.getDistrict().isEmpty()){
            throw new ValidationException("El distrito del restaurante debe ser obligatorio");
        }
        if(restaurantRequest.getDistrict().length() >100){
            throw new ValidationException("El distrito no debe exceder los 100 caracteres");
        }
    }

    @Override
    public void existsRestaurantByName(RestaurantRequest restaurantRequest) {
        if (restaurantRepository.existsByName(restaurantRequest.getName())) {
            throw new ValidationException("Ya existe un restaurante con el nombre " + restaurantRequest.getName());
        }
    }
}
