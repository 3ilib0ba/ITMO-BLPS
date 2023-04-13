package evgesha.blps.lab1.service;


import evgesha.blps.lab1.entity.Ingredient;
import evgesha.blps.lab1.repository.IngredientRepository;
import evgesha.blps.lab1.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    private final MeasureService measureService;

    public IngredientService(
            IngredientRepository ingredientRepository,
            MeasureService measureService
    ) {
        this.ingredientRepository = ingredientRepository;
        this.measureService = measureService;
    }

    public Ingredient checkIngredientForMeasureAndSave(Ingredient ingredient) {
        ingredient.setMeasure(
                measureService.checkMeasure(ingredient.getMeasure())
        );
        return ingredientRepository.save(ingredient);
    }

}
