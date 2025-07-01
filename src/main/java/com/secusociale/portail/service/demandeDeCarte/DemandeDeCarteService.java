package com.secusociale.portail.service.demandeDeCarte;

import com.secusociale.portail.service.dto.extraction.DemandeCarteCollectiveRequest;
import com.secusociale.portail.service.dto.extraction.DemandeDeCarteCollectiveResponse;
import com.secusociale.portail.service.dto.extraction.DemandeDeCarteDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DemandeDeCarteService {

    DemandeDeCarteDTO create(DemandeDeCarteDTO demandeDeCarteDTO,Boolean isCollective);
    DemandeDeCarteDTO readById(Long id);
    List<DemandeDeCarteDTO> readAll(int page, int size);

    List<DemandeDeCarteDTO> readDemandeForAgent(Long id, int page, int size);
    List<DemandeDeCarteDTO> readDemandeForSalarie(Long id, int page, int size);

    List <DemandeDeCarteDTO> demandeByAgent();

    DemandeDeCarteCollectiveResponse createCollective(DemandeCarteCollectiveRequest request);

    List<Map<String, Object>> getDemandesGroupedByLot(int page, int size, String searchTerm);
}
