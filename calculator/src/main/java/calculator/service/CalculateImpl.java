package calculator.service;

import calculator.service.CalculatorService.CalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.server.ResponseStatusException;


@Service
@Slf4j
public class CalculateImpl implements CalculateService {

//    OkHttpClient client = new OkHttpClient();
//
//    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//    RequestBody body = RequestBody.create(mediaType, "input=sqrt(5%5E2%20-%204%5E2)");
//    Request request = new Request.Builder()
//            .url("https://wolframalphavolodimir-kudriachenkov1.p.rapidapi.com/createQuery")
//            .post(body)
//            .addHeader("content-type", "application/x-www-form-urlencoded")
//            .addHeader("X-RapidAPI-Key", "1445e32cf6msh406aa94fc761893p1c4711jsn9dfd59ed83af")
//            .addHeader("X-RapidAPI-Host", "WolframAlphavolodimir-kudriachenkoV1.p.rapidapi.com")
//            .build();
//
//    Response response = client.newCall(request).execute();


    private static final String url="https://wolframalphavolodimir-kudriachenkov1.p.rapidapi.com/";
    private static final String X_RapidAPI_Key="1445e32cf6msh406aa94fc761893p1c4711jsn9dfd59ed83af";
    private static final String X_RapidAPI_Host="WolframAlphavolodimir-kudriachenkoV1.p.rapidapi.com";

    private RestTemplate restTemplate;

    public CalculateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Object getAnswer(String expression) {

        String dynamicUrl=url+expression;
        try{
            HttpHeaders headers=new HttpHeaders();
            headers.set("X-RapidAPI-Key", X_RapidAPI_Key);
            headers.set("X-RapidAPI-Host", X_RapidAPI_Host);

            ResponseEntity<String> response=restTemplate.exchange(dynamicUrl, HttpMethod.GET,new HttpEntity<>(headers),String.class);
            log.info("Output form rapidAPI:{}",response.getBody());
            return response.getBody();
        }
        catch(MethodArgumentConversionNotSupportedException argumentConversionNotSupportedException){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "invalid input",
                    argumentConversionNotSupportedException
            );
        }
        catch(Exception e){
            log.error("Something went wrong while getting value from RapidAPI",e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "something went wrong",
                    e
            );
        }


    }
}
