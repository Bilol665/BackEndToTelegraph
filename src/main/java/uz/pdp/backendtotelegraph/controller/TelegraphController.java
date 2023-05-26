package uz.pdp.backendtotelegraph.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.backendtotelegraph.entity.dto.TelegraphDto;
import uz.pdp.backendtotelegraph.service.TelegraphService;
import uz.pdp.backendtotelegraph.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telegraph")
public class TelegraphController {
    private final TelegraphService telegraphService;

    @PostMapping("/add")
    public ResponseEntity<Object> add(
            @RequestBody TelegraphDto telegraphDto,
            @RequestParam UUID userId
    ) {
        telegraphService.add(telegraphDto,userId);
        return new ResponseEntity<>("Tag added", HttpStatus.OK);
    }
}
