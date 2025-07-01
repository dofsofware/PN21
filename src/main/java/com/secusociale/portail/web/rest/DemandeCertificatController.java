package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.DemandeCertificat;
import com.secusociale.portail.service.DemandeCerificatService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/demande-certificat")
public class DemandeCertificatController {

    private final DemandeCerificatService demandeCerificatService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody  DemandeCertificat demandeCertificat){
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.OK.value());
        map.put("data", demandeCerificatService.save(demandeCertificat));
        return ResponseEntity.ok(map);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody  DemandeCertificat demandeCertificat){

        demandeCerificatService.update(demandeCertificat);

        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.OK.value());
        map.put("data", demandeCertificat);
        return ResponseEntity.ok(map);
    }
    @GetMapping("/by-agent")
    public ResponseEntity<?> findCertificatsByAgent(@RequestParam("page") int page, @RequestParam("size") int size){
        Page<DemandeCertificat> pages = demandeCerificatService.findCertificatsByAgent(page, size);
        return getResponseEntity(pages);
    }

    @GetMapping("/by-salaries")
    public ResponseEntity<?> findCertificatsBySalaries(@RequestParam("page") int page, @RequestParam("size") int size){
        Page<DemandeCertificat> pages = demandeCerificatService.findCertificatsBySalaries(page, size);
        return getResponseEntity(pages);
    }

    private ResponseEntity<?> getResponseEntity(Page<DemandeCertificat> pages) {
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", pages.getNumber());
        pagination.put("size", pages.getSize());
        pagination.put("totalElements", pages.getTotalElements());
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", HttpStatus.OK.value());
        result.put("list", pages.getContent());
        result.put("pagination", pagination);
        return ResponseEntity.ok(result);
    }
}
