package evgesha.blps.lab1.service;

import evgesha.blps.lab1.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TargerService {
    @Autowired
    private TargetRepository targetRepository;



}
