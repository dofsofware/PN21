package com.secusociale.portail.service;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.DemandeCertificat;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.DemandeDeCertificatRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.service.utils.UtilsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.secusociale.portail.security.SecurityUtils.getCurrentUserLogin;

@Service
@AllArgsConstructor
public class DemandeCerificatService {

   private final DemandeDeCertificatRepository demandeDeCertificatRepository;
   private final UtilsService utilsService;
   private final UserRepository userRepository;
    private final SalarieRepository salarieRepository;

    public DemandeCertificat save(DemandeCertificat demandeCertificat){
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findOneByLogin(username).orElseThrow(()-> new RuntimeException("User not found"));
        Salarie salarie = salarieRepository.findByUserId(user.getId()).orElseThrow(()-> new RuntimeException("Salarie not found"));
        demandeCertificat.setSalarie(salarie);
        User agent = userRepository.findById(salarie.getAgentId()).orElseThrow(()-> new RuntimeException("Agent not found"));
        demandeCertificat.setAgent(agent);
       return demandeDeCertificatRepository.save(demandeCertificat);
   }

    public DemandeCertificat update(DemandeCertificat demandeCertificat) {

        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findOneByLogin(username).get();
        demandeCertificat.setAgent(user);
        demandeCertificat.setUpdatedBy(username);
        demandeCertificat.setDateUpdate(LocalDateTime.now());

       if(Objects.equals(demandeCertificat.getStatut(), "VALIDE")){
           if (Objects.equals(demandeCertificat.getTypeDemande(), Constants.CERTIFICAT_DE_NON_INSCRIPTION)){
               try {
                   //utilsService.generateCertificatDeNonInscription(demandeCertificat);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }else if (Objects.equals(demandeCertificat.getTypeDemande(), Constants.CERTIFICAT_DE_RADIATION)){
               try {
                   //utilsService.generateCertificatDeRadiation(demandeCertificat);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }
       return demandeDeCertificatRepository.save(demandeCertificat);
    }

    public  Page<DemandeCertificat> findCertificatsByAgent(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findOneByLogin(username).get();
        return demandeDeCertificatRepository.findByAgent(user, pageable);
    }

    public Page<DemandeCertificat> findCertificatsBySalaries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String username = getCurrentUserLogin().isPresent() ? getCurrentUserLogin().get() : "";
        User user = userRepository.findOneByLogin(username).get();
        Salarie salarie = salarieRepository.findByUserId(user.getId()).get();
        return demandeDeCertificatRepository.findBySalarie(salarie, pageable);
    }
}
