package calculator.service;

import calculator.service.CalculatorService.CalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


@Service
@Slf4j
public class CalculateImpl implements CalculateService {


    private static final String url="https://corona-virus-world-and-india-data.p.rapidapi.com/api";
    private static final String X_RapidAPI_Key="1445e32cf6msh406aa94fc761893p1c4711jsn9dfd59ed83af";
    private static final String X_RapidAPI_Host="corona-virus-world-and-india-data.p.rapidapi.com";

    private RestTemplate restTemplate;

    public CalculateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Object getAnswer(String expression) {

        //String dynamicUrl=url+expression;
        try{
            HttpHeaders headers=new HttpHeaders();
            headers.set("X-RapidAPI-Key", X_RapidAPI_Key);
            headers.set("X-RapidAPI-Host", X_RapidAPI_Host);

            ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(headers),String.class);
            log.info("Output form rapidAPI:{}",response.getBody());
            return response.getBody();
        }
        catch(Exception e){
            log.error("Something went wrong while getting value from RapidAPI",e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling endpoint of RapidAPI for corona",
                    e
            );
        }


    }
}
