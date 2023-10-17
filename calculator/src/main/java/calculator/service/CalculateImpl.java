package calculator.service;

import calculator.service.CalculatorService.CalculateService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Service
public class CalculateImpl implements CalculateService {

    private static final String url="https://wolframalphavolodimir-kudriachenkov1.p.rapidapi.com/";
    private static final String X_RapidAPI_Key="1445e32cf6msh406aa94fc761893p1c4711jsn9dfd59ed83af";
    private static final String X_RapidAPI_Host="WolframAlphavolodimir-kudriachenkoV1.p.rapidapi.com";

    private RestTemplate restTemplate;

    public CalculateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    /*
    OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
RequestBody body = RequestBody.create(mediaType, "input=%3CREQUIRED%3E");
Request request = new Request.Builder()
	.url("https://wolframalphavolodimir-kudriachenkov1.p.rapidapi.com/createQuery")
	.post(body)
	.addHeader("content-type", "application/x-www-form-urlencoded")
	.addHeader("X-RapidAPI-Key", "1445e32cf6msh406aa94fc761893p1c4711jsn9dfd59ed83af")
	.addHeader("X-RapidAPI-Host", "WolframAlphavolodimir-kudriachenkoV1.p.rapidapi.com")
	.build();

Response response = client.newCall(request).execute();
     */


    @Override
    public Object getAnswer(String expression) {

        String dynamicUrl=url+expression;
        try{
            HttpHeaders headers=new HttpHeaders();
            headers.set("X-RapidAPI-Key", X_RapidAPI_Key);
            headers.set("X-RapidAPI-Host", X_RapidAPI_Host);

            ResponseEntity<Object> response=restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(headers),Object.class);
            return response.getBody();
        }
        catch(Exception e){

        }

        return null;

    }
}
