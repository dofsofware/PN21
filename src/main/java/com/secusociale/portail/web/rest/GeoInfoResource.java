package com.secusociale.portail.web.rest;
import com.secusociale.portail.domain.GeoInfo;
import com.secusociale.portail.repository.GeoInfoRepository;
import com.secusociale.portail.service.GeoInfoService;
import com.secusociale.portail.service.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geo-infos")
public class GeoInfoResource {

    @Autowired
    private GeoInfoRepository geoInfoRepository;

    @Autowired
    private GeoInfoService geoInfoService;

    @GetMapping("/search")
    public ApiResponse<Object> searchMostRelevantGeoInfo(@RequestParam String searchValue) {
        GeoInfo geoInfo = geoInfoService.searchMostRelevantGeoInfo(searchValue);
        if (geoInfo != null) {
            return ApiResponse.success(geoInfo);
        }else {
            return ApiResponse.error404("Aucune correspondence trouvée ! pour la valeur : '" + searchValue + "'");
        }
    }
}
