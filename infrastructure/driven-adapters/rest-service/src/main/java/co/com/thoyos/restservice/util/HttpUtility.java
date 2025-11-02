package co.com.thoyos.restservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpUtility {
    private final RestTemplate restTemplate;

    public <T> ResponseEntity<T> getForEntity(String url, HttpHeaders httpHeaders, Class<T> responseType,
                                              Object... uriVariables){
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity,responseType, uriVariables);

    }
}
