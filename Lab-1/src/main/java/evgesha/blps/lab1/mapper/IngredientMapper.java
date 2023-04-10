package evgesha.blps.lab1.mapper;

import evgesha.blps.lab1.dto.IngredientDto;
import evgesha.blps.lab1.entity.Ingredient;
import evgesha.blps.lab1.repository.IngredientRepository;
import evgesha.blps.lab1.repository.MeasureRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {
    final IngredientRepository ingredientRepository;

    final MeasureRepository measureRepository;

    public IngredientMapper(IngredientRepository ingredientRepository, MeasureRepository measureRepository) {
        this.ingredientRepository = ingredientRepository;
        this.measureRepository = measureRepository;
    }

    public IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getCount(),
                ingredient.getName(),
                ingredient.getMeasure().getName()
        );
    }

    public List<IngredientDto> toDtoFromList(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Ingredient fromDto(IngredientDto ingredientDto) {
        return new Ingredient(
                0,
                ingredientDto.getCount(),
                ingredientDto.getName(),
                // TODO Перенести проверку на тип тары в сервис measureService
                //      В mapper не должно быть связей с repository
                measureRepository.getMeasuresByName(ingredientDto.getMeasure())
        );
    }

    public List<Ingredient> fromDtoFromList(List<IngredientDto> ingredientDtos) {
        return ingredientDtos.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

}
