package evgesha.blps.lab1.service;

import evgesha.blps.lab1.repository.TargetRepository;
import org.springframework.stereotype.Service;

@Service
public class TargetService {
    private final TargetRepository targetRepository;

    public TargetService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }
}
