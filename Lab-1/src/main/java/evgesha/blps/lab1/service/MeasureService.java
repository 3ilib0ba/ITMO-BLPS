package evgesha.blps.lab1.service;


import evgesha.blps.lab1.entity.Measure;
import evgesha.blps.lab1.exception.MeasureNotCorrectException;
import evgesha.blps.lab1.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureService {
    private final MeasureRepository measureRepository;

    public MeasureService(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }

    public Measure checkMeasure(Measure measure) {
        if (measure == null) {
            throw new MeasureNotCorrectException();
        }
        Measure result = measureRepository.getMeasuresByName(measure.getName());
        if (result == null) {
            throw new MeasureNotCorrectException();
        }
        return result;
    }
}
