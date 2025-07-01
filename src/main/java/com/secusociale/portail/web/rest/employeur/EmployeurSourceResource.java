package com.secusociale.portail.web.rest.employeur;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.model.DmtModel;
import com.secusociale.portail.repository.UserRepository;
import com.secusociale.portail.security.AuthoritiesConstants;
import com.secusociale.portail.security.SecurityUtils;
import com.secusociale.portail.service.DMTService;
import com.secusociale.portail.service.DocumentUrlService;
import com.secusociale.portail.service.MailService;
import com.secusociale.portail.service.dto.DMTDTO;
import com.secusociale.portail.service.immatriculation.DmtService;
import com.secusociale.portail.service.immatriculation.InfosEmployeurService;
import com.secusociale.portail.service.soap.cmdmt.CmDmt;
import com.secusociale.portail.service.soap.employeurInfos.CMEMPLOYEURINFOS;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import static com.secusociale.portail.domain.BASE64DecodedMultipartFile.base64ToMultipart;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Controller
@RequestMapping("/api")
public class EmployeurSourceResource {

    private final Logger log = LoggerFactory.getLogger(EmployeurSourceResource.class);

    @Autowired
    private InfosEmployeurService infosEmployeurService;
    @Autowired
    private MailService mailService;

    @Autowired
    private DmtService dmtService;

    @Autowired
    private DocumentUrlService documentUrlService;

    @Autowired
    private DMTService dmtLocalService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/employeurInfos")
    public ResponseEntity<Holder<CMEMPLOYEURINFOS>> getEmployeurInfos(@RequestBody CMEMPLOYEURINFOS cmemployeurinfos) {
        try {
            return ResponseEntity.ok(infosEmployeurService.getEmployeurInfos(cmemployeurinfos));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/dmt")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<HashMap<String, Object>> sendDMT(@RequestBody DmtModel dmtModel) {
        HashMap<String, Object> result = new HashMap<>();
        DMTDTO dmtlocal = new DMTDTO();
        try {
            for (DmtModel.Employes employe : dmtModel.getEmployes()) {
                if (isEmpty(employe.getTypePiece())) {
                    result.put("code", "400");
                    result.put("erreur", "Vérifier que le type de pièce est renseigné pour tous les employés!");
                    return ResponseEntity.ok(result);
                }
            }
            Optional<String> loggedusername = SecurityUtils.getCurrentUserLogin();
            String username = loggedusername.orElse(null);
            Long userId = userRepository.findByLogin(username).getId();
            HashMap<String, String> extensions = new HashMap<>();
            extensions.put("vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx");
            extensions.put("msexcel", ".xls");
            String uuid = RandomStringUtils.randomAlphanumeric(7);
            String extension = isEmpty(dmtModel.getFile().split(";")[0].split("/")[1]) ? ".xlsx" : extensions.get(dmtModel.getFile().split(";")[0].split("/")[1]);
            String fileName = "DMT_" + dmtModel.getIdEmployeur() + "_" + uuid + extension;
            MultipartFile multipartFile = Objects.requireNonNull(base64ToMultipart(dmtModel.getFile(), "listeSalaries"));

            String path = documentUrlService.uploadedExcel(multipartFile, fileName);

            dmtlocal.idEmployeur(dmtModel.getIdEmployeur())
                .date(Instant.now())
                .status("PENDING")
                .raisonSocial(dmtModel.getRaisonSocial())
                .file(path)
                .userId(userId);
            dmtlocal = dmtLocalService.save(dmtlocal);
//            CmDmt dmt = dmtService.dmt(dmtModel);
//            if (dmt != null) {
//                dmtlocal.reponsebrute((new ObjectMapper()).writeValueAsString(dmt))
//                    .status("SUCCESS");
//                dmtLocalService.save(dmtlocal);
//            } else {
//                dmtlocal.status("FAILED");
//                dmtLocalService.save(dmtlocal);
//            }
            result.put("code", "200");
            result.put("data", dmtlocal);
            return ResponseEntity.ok(result);
        } catch (Exception exception) {
            dmtlocal.status("FAILED");
            dmtLocalService.save(dmtlocal);
            log.error(exception.getMessage(), exception);
            result.put("code", "400");
            result.put("erreur", exception.getMessage());
            return ResponseEntity.ok(result);
        }
    }
}
