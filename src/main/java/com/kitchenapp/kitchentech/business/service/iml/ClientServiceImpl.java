package com.kitchenapp.kitchentech.business.service.iml;

import com.kitchenapp.kitchentech.business.model.Client;
import com.kitchenapp.kitchentech.business.repository.ClientRepository;
import com.kitchenapp.kitchentech.business.service.ClientService;
import com.kitchenapp.kitchentech.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client updateClient(Client client) {
        Client clientToUpdate = getClientById(client.getId());
        if(clientToUpdate!=null){
            clientToUpdate.setName(client.getName());
            clientToUpdate.setDocument(client.getDocument());
            clientToUpdate.setType_document(clientToUpdate.getType_document());
            return clientRepository.save(clientToUpdate);
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getAllClients(Long restaurantId) {
        return clientRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public void existsClientById(Long id) {
        if(!clientRepository.existsById(id)){
            throw new ValidationException("No existe ningún cliente");
        }
    }

    @Override
    public void existsClientByDocument(Client client) {
        if(clientRepository.existsByDocumentAndRestaurantId(client.getDocument(),client.getRestaurantId())){
            throw new ValidationException("Ya existe un cliente con este numero de documento en el restaurante");
        }
    }

    @Override
    public void validateClient(Client client) {
        if(client == null){
            throw new ValidationException("El cliente no puede ser nulo");
        }
        if(client.getName() == null || client.getName().isEmpty()){
            throw new ValidationException("El nombre del cliente es obligatorio");
        }
        if(client.getName().length() > 150){
            throw new ValidationException("El nombre del cliente es muy largo");
        }
        if(client.getDocument() == null || client.getDocument().isEmpty()){
            throw new ValidationException("El número de documento es obligatorio");
        }
        if(client.getRestaurantId() == 0){
            throw new ValidationException("El restaurante asignado debe ser obligatorio");
        }
    }
}
