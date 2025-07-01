package com.secusociale.portail.web.rest;

import com.secusociale.portail.domain.APGPaiment;
import com.secusociale.portail.repository.APGPaimentRepository;
import com.secusociale.portail.repository.ImmatriculationRecupereeRepository;
import com.secusociale.portail.repository.NouvelleImmatriculationRepository;
import com.secusociale.portail.web.rest.vm.APGDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class APGController {

    private final Logger log = LoggerFactory.getLogger(APGController.class);

    private final APGPaimentRepository apgPaimentRepository;

    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;

    private final NouvelleImmatriculationRepository nouvelleImmatriculationRepository;

    //    public String
    @Value("${portail.url}")
    String portailUrl;

    @PostMapping("/apg-callback")
    public String apgCallbackURL(@RequestBody String fromAPG, Model model) {
        APGDTO apgdto = new APGDTO();
        APGPaiment paiment = new APGPaiment();
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        // requestId=tElpx71M&transactionId=2882&amount=150000&currency=XOF&status=PENDING&message=PENDING
        try {
            query_pairs = splitQuery(fromAPG);
            log.info("paramAPGRECEIVE: {}", String.valueOf(query_pairs));
            log.info("brute: {}", fromAPG);
            String reference = query_pairs.get("requestId");
            String status = query_pairs.get("status");
            log.info("reference: {}", reference);
            log.info("status: {}", status);
            paiment = apgPaimentRepository.findByReferencePaiment(reference).orElse(new APGPaiment());
            paiment.statut(query_pairs.get("status"));
            query_pairs.put("facturesConcernees", paiment.getFacturesConcernees());
            query_pairs.put("datePaiement", paiment.getDate().toString());
            String pid = paiment.getPersonId();
            query_pairs.put("numeroUnique", pid);
            if (immatriculationRecupereeRepository.findByNumeroUnique(pid).isPresent()) {
                query_pairs.put("raisonSocial", immatriculationRecupereeRepository.findByNumeroUnique(pid).get().getRaisonSociale());
            } else if (nouvelleImmatriculationRepository.findByNinea(pid).isPresent()) {
                query_pairs.put("raisonSocial", nouvelleImmatriculationRepository.findByNinea(pid).get().getRaisonSociale());

            }
            apgPaimentRepository.save(paiment);
        } catch (UnsupportedEncodingException ignored) {
        }
        model.addAllAttributes(query_pairs);
        model.addAttribute("portailUrl", portailUrl);
        return "callback";
    }

    @GetMapping("/apg-show-recu/{transactionId}")
    public String apgShowRecu(@PathVariable String transactionId, Model model) {
        APGDTO apgdto = new APGDTO();
        APGPaiment paiment = new APGPaiment();
        Map<String, Object> query_pairs = new LinkedHashMap<String, Object>();
        // requestId=tElpx71M&transactionId=2882&amount=150000&currency=XOF&status=PENDING&message=PENDING
        try {
            paiment = apgPaimentRepository.findByReferencePaiment(transactionId).orElse(new APGPaiment());
            query_pairs.put("transactionId", transactionId);
            query_pairs.put("requestId", paiment.getReferencePaiment());
            query_pairs.put("status", paiment.getStatut());
            query_pairs.put("amount", paiment.getMontant());
            query_pairs.put("currency", paiment.getCurrency());
            query_pairs.put("facturesConcernees", paiment.getFacturesConcernees());
            query_pairs.put("datePaiement", paiment.getDate().toString());
            String pid = paiment.getPersonId();
            query_pairs.put("numeroUnique", pid);
            if (immatriculationRecupereeRepository.findByNumeroUnique(pid).isPresent()) {
                query_pairs.put("raisonSocial", immatriculationRecupereeRepository.findByNumeroUnique(pid).get().getRaisonSociale());
            } else if (nouvelleImmatriculationRepository.findByNinea(pid).isPresent()) {
                query_pairs.put("raisonSocial", nouvelleImmatriculationRepository.findByNinea(pid).get().getRaisonSociale());
            }
        } catch (Exception ignored) {
        }
        model.addAllAttributes(query_pairs);
        model.addAttribute("portailUrl", portailUrl);
        return "callback";
    }

    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

}
