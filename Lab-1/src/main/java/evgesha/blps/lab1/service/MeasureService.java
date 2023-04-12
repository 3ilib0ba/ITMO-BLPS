package evgesha.blps.lab1.service;


import evgesha.blps.lab1.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureService {
    private final MeasureRepository measureRepository;

    public MeasureService(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }
}
