package evgesha.blps.lab1.service;


import evgesha.blps.lab1.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;


}
