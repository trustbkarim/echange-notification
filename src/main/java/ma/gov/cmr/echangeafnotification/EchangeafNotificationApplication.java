package ma.gov.cmr.echangeafnotification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import ma.gov.cmr.echangeafnotification.dao.models.WsPasSuiviEntity;
import ma.gov.cmr.echangeafnotification.dao.repositories.IWsPasSuiviRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;


@RestController
@AllArgsConstructor
@SpringBootApplication
public class EchangeafNotificationApplication {

    private IWsPasSuiviRepository wsPasSuiviRepository;

    public static void main(String[] args) {
        SpringApplication.run(EchangeafNotificationApplication.class, args);
    }

    @GetMapping("/")
    public List<WsPasSuiviEntity> index() {

        List<WsPasSuiviEntity> wsPasSuiviEntityList = wsPasSuiviRepository.findAll();

        if (wsPasSuiviEntityList.size() > 1000) {
            wsPasSuiviEntityList = wsPasSuiviEntityList.subList(0, 1000);
        }

        exportToJson(wsPasSuiviEntityList);
        return wsPasSuiviEntityList;
    }


    private void exportToJson(List<WsPasSuiviEntity> wsPasSuiviEntityList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            File outputFile = new File("cmr-echangeaf.json");
            objectMapper.writeValue(outputFile, wsPasSuiviEntityList);

            System.out.println("Les données sont exportées vers: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
