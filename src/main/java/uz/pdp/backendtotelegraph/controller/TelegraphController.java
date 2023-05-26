package uz.pdp.backendtotelegraph.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.backendtotelegraph.entity.TelegraphEntity;
import uz.pdp.backendtotelegraph.entity.dto.TelegraphDto;
import uz.pdp.backendtotelegraph.service.TelegraphService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telegraph")
public class TelegraphController {
    private final TelegraphService telegraphService;

    @PostMapping("/add")
    public ResponseEntity<Object> add(
            @RequestBody TelegraphDto telegraphDto,
            @RequestParam(name = "user-id") UUID userId
    ) {
        telegraphService.add(telegraphDto,userId);
        return new ResponseEntity<>("Telegraph added", HttpStatus.OK);
    }
    @GetMapping("/my-post")
    public ResponseEntity<Object> getYourPosts(
            @RequestParam(defaultValue = "",name = "user_id") UUID userId,
            @RequestParam(defaultValue = "10",name = "page_size") int pageSize,
            @RequestParam(defaultValue = "1",name = "which_page") int whichPage
    ) {
        List<TelegraphEntity> telegraphEntities = telegraphService.getHisAllSorted(userId,pageSize,whichPage);
        return new ResponseEntity<>(telegraphEntities,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Object> search(
            @RequestParam(defaultValue = "") String date,
            @RequestParam(defaultValue = "") String title
    ) {
        List<TelegraphEntity> search = telegraphService.search(date, title);
        return new ResponseEntity<>(search,HttpStatus.OK);
    }
}
