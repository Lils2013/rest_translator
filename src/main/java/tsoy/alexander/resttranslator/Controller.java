package tsoy.alexander.resttranslator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tsoy.alexander.resttranslator.entities.TranslationLog;
import tsoy.alexander.resttranslator.entities.TranslationStatus;
import tsoy.alexander.resttranslator.entities.YandexResponse;

import java.util.*;

@RestController
public class Controller {

    private final RestTemplate restTemplate;
    private final LogRepository logRepository;
    private final Environment environment;

    @Autowired
    public Controller(RestTemplate restTemplate, LogRepository logRepository, Environment environment) {
        this.restTemplate = restTemplate;
        this.logRepository = logRepository;
        this.environment = environment;
    }

    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public List<String> translate(@RequestParam(value = "text") String text,
                                  @RequestParam(value = "from", required = false) String from,
                                  @RequestParam(value = "to") String to) {

        String resourceUrl
                = environment.getProperty("yandex.api.url");
        String key = environment.getProperty("yandex.api.key");
        String lang = from == null ? to : from + "-" + to;
        TranslationLog log = new TranslationLog(text, null, lang);
        List<String> result = Collections.synchronizedList(
                new ArrayList<>());
        try {
            Arrays.asList(text.split(" ")).parallelStream().forEach(s -> {
                ResponseEntity<YandexResponse> response
                        = restTemplate.getForEntity(createUrlForGet(resourceUrl, key, s,
                        lang), YandexResponse.class);
                result.add(Objects.requireNonNull(response.getBody()).getText().get(0));
            });
            log.setTranslatedText(String.join(" ", result));
            log.setStatus(TranslationStatus.SUCCESS);
        } catch (Exception e) {
            log.setStatus(TranslationStatus.FAILURE);
            throw e;
        } finally {
            logRepository.save(log);
        }
        return result;
    }

    @RequestMapping(value = "/log")
    public List<TranslationLog> getLog() {
        return (List<TranslationLog>) logRepository.findAll();
    }

    private String createUrlForGet(String resourceUrl, String key, String text, String lang) {
        return resourceUrl + "?key=" + key + "&text=" + text + "&lang=" + lang;
    }
}
