package tsoy.alexander.resttranslator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTranslatorApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void translate() throws Exception {
        String response = restTemplate.getForObject("http://localhost:" + port +
                "/translate?text=один два три четыре пять шесть семь&to=en", String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = mapper.readValue(response,
                new TypeReference<ArrayList<String>>() {
                });
        assertTrue(list.containsAll(Arrays.asList("six", "seven", "two", "five", "four", "one", "three")));
    }

    @Test
    public void translateFromSpanishToEnglish() throws Exception {
        String response = restTemplate.getForObject("http://localhost:" + port +
                "/translate?text=siete cinco una dos seis cuatro tres&to=en&from=es", String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<String> list = mapper.readValue(response,
                new TypeReference<ArrayList<String>>() {
                });
        assertTrue(list.containsAll(Arrays.asList("six", "seven", "two", "five", "four", "one", "three")));
    }
}