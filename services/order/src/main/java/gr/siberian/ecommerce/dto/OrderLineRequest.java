package gr.siberian.ecommerce.dto;

public record OrderLineRequest(

        Integer id,

        Integer orderId,

        Integer productId,

        double quantity

) {
}


