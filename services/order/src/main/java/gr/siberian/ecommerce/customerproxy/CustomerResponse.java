package gr.siberian.ecommerce.customerproxy;

public record CustomerResponse(

        String id,

        String firstname,

        String lastname,

        String email
) {
}
