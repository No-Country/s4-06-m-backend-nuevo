package ecommerce.eco.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import ecommerce.eco.model.paypal.Order;
import ecommerce.eco.service.PaypalService;
import ecommerce.eco.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/paypal")
public class PaypalController {
    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "/pay/success";
    public static final String CANCEL_URL = "/pay/cancel";
    private static final Logger LOGGER = LoggerFactory.getLogger(PaypalController.class);
  @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
        LOGGER.warn(order.getDescription());
        try {
            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(),
                    "http://localhost:8090/paypal" + CANCEL_URL,
                    "http://localhost:8090/paypal" + SUCCESS_URL);

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value ="CANCEL_UR")
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value ="SUCCESS_URL")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
