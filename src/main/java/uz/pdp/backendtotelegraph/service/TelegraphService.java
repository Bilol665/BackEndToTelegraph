package uz.pdp.backendtotelegraph.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.backendtotelegraph.repository.TelegraphRepository;

@Service
@RequiredArgsConstructor
public class TelegraphService {
    private final TelegraphRepository telegraphRepository;

}
