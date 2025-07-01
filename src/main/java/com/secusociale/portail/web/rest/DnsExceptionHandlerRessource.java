package com.secusociale.portail.web.rest;


import com.secusociale.portail.domain.DnsExceptionHandler;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.service.DnsExceptionHandlerService;
import com.secusociale.portail.service.UserService;
import com.secusociale.portail.service.dto.DnsExceptionHandlerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/dns-exceptions")
public class DnsExceptionHandlerRessource {

    private final DnsExceptionHandlerService dnsExceptionHandlerService;
    private final UserService userService;

    @Autowired
    public DnsExceptionHandlerRessource(DnsExceptionHandlerService dnsExceptionHandlerService, UserService userService) {
        this.dnsExceptionHandlerService = dnsExceptionHandlerService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<DnsExceptionHandler> createException(@RequestBody DnsExceptionHandler dnsExceptionHandler) {
        DnsExceptionHandler saved = dnsExceptionHandlerService.save(dnsExceptionHandler);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

//    @PostMapping("/log")
//    public ResponseEntity<DnsExceptionHandler> logException(@RequestParam Long userId, @RequestBody String exception) {
//        Optional<User> userOpt = userService.findOne(userId);
//        if (!userOpt.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        DnsExceptionHandler saved = dnsExceptionHandlerService.logException(userOpt.get(), exception);
//        return new ResponseEntity<>(saved, HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllExceptions(DnsExceptionHandlerCriteria criteria, @RequestParam(required = false) Integer page,
                                                                @RequestParam(required = false) Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<DnsExceptionHandler> pageResult = dnsExceptionHandlerService.findAll(criteria,pageable);

        List<DnsExceptionHandler> list = pageResult.getContent();

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        // Ajout des informations à la réponse
        result.put("code", "200");
        result.put("list", list);

        // Informations de pagination
        pagination.put("page", pageResult.getNumber());
        pagination.put("size", pageResult.getSize());
        pagination.put("totalElements", pageResult.getTotalElements());

        result.put("pagination", pagination);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DnsExceptionHandler> getExceptionById(@PathVariable Long id) {
        Optional<DnsExceptionHandler> exception = dnsExceptionHandlerService.findById(id);
        return exception.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DnsExceptionHandler>> getExceptionsByUser(@PathVariable Long userId) {
        Optional<User> userOpt = userService.findOne(userId);
        if (!userOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<DnsExceptionHandler> exceptions = dnsExceptionHandlerService.findByUser(userOpt.get());
        return new ResponseEntity<>(exceptions, HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<DnsExceptionHandler>> getExceptionsByDateRange(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<DnsExceptionHandler> exceptions = dnsExceptionHandlerService.findByDateRange(start, end);
        return new ResponseEntity<>(exceptions, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DnsExceptionHandler>> searchExceptions(@RequestParam String text) {
        List<DnsExceptionHandler> exceptions = dnsExceptionHandlerService.findByExceptionText(text);
        return new ResponseEntity<>(exceptions, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DnsExceptionHandler> updateException(
        @PathVariable Long id,
        @RequestBody DnsExceptionHandler dnsExceptionHandler) {

        if (!dnsExceptionHandlerService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        dnsExceptionHandler.setId(id);
        DnsExceptionHandler updated = dnsExceptionHandlerService.save(dnsExceptionHandler);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteException(@PathVariable Long id) {
        if (!dnsExceptionHandlerService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        dnsExceptionHandlerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/set-treated-true/{id}")
    public ResponseEntity<DnsExceptionHandler> setTreatedFalse(@PathVariable Long id) {
        Optional<DnsExceptionHandler> existingException = dnsExceptionHandlerService.findById(id);
        if (!existingException.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        DnsExceptionHandler currentException = existingException.get();
        if (!currentException.isTreated()) {
            currentException.setTreated(true);
            DnsExceptionHandler updated = dnsExceptionHandlerService.save(currentException);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(currentException, HttpStatus.OK);
    }

}
