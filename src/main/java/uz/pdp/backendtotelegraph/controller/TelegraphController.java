package uz.pdp.backendtotelegraph.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.backendtotelegraph.service.TelegraphService;

@RestController
@RequiredArgsConstructor
public class TelegraphController {
    private final TelegraphService telegraphService;
}
