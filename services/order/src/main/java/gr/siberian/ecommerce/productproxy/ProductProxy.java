package gr.siberian.ecommerce.productproxy;

import gr.siberian.ecommerce.dto.PurchaseRequest;
import gr.siberian.ecommerce.dto.PurchaseResponse;
import gr.siberian.ecommerce.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * A user places an order, and the application calls purchaseProducts().
 * ProductProxy sends a POST request to http://localhost:8222/products/purchase with the order details in JSON.
 * The product service processes the purchase and returns a list of purchase details.
 * If the request is successful, the method returns the purchase responses.
 * If there's an error (e.g., service unavailable, bad request), an exception is thrown.
 */

@Service
@RequiredArgsConstructor
public class ProductProxy {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    /**
     * Takes a list of purchase requests and sends them to the product service.
     * Returns a list of purchase responses from the service.
     * Since the response is a list of objects (List<PurchaseResponse>),
     * we use ParameterizedTypeReference<> to tell RestTemplate what type to expect.
     */
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody){
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE , APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseRequest>> requestHttpEntity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<>() {};
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                POST,
                requestHttpEntity,
                responseType
        );
        if (responseEntity.getStatusCode().isError()){
            throw new BusinessException("Error occurred while processing purchase " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
