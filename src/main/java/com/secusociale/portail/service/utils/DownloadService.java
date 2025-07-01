package com.secusociale.portail.service.utils;

/*import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;*/

import org.springframework.stereotype.Service;

@Service
public class DownloadService {

    /*public String downloadPreDNS() {
        try {
            InputStream srcStream = new ClassPathResource("maquettes/DNS.xlsx").getURL().openStream();
            Workbook workbook = new XSSFWorkbook(srcStream);
            Sheet sheet = workbook.getSheet("DNS");

            CreationHelper createHelper = workbook.getCreationHelper();
            short numberFormat = createHelper.createDataFormat().getFormat("#,0;\\-#,0");

            CellStyle numberStyle = sheet.getWorkbook().createCellStyle();
            numberStyle.setDataFormat(numberFormat);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }*/
}
