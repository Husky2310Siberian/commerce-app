package gr.siberian.ecommerce.payment;

import gr.siberian.ecommerce.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service",
             url = "${application.config.payment-url")

public interface IPaymentProxy {

    @PostMapping
    Integer requestOrderPayment(@RequestBody PaymentRequest request);

}
