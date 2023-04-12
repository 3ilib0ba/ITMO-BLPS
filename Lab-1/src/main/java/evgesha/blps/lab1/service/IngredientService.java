package evgesha.blps.lab1.service;


import evgesha.blps.lab1.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;


    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
}
