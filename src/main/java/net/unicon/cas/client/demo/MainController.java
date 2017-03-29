package net.unicon.cas.client.demo;

import net.unicon.cas.client.configuration.CasClientConfigurerAdapter;
import net.unicon.cas.client.configuration.EnableCasClient;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@EnableCasClient
public class MainController extends CasClientConfigurerAdapter {

    @Value("${casLogoutUrl}")
    private String casLogoutUrl;

    public String getCasLogoutUrl() {
        return casLogoutUrl;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "index";
    }

    @RequestMapping(value = "/protected", method = RequestMethod.GET)
    public String protected1(HttpServletRequest request, Model model) {
        AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
        model.addAttribute("principal", principal);
        return "protected";
    }


    /**
     * Example of customizing the filter config for any "exotic" properties that are not exposed via properties file
     */
    @Override
    public void configureValidationFilter(FilterRegistrationBean validationFilter) {
        //This is Groovy. Below this, is the example (commented out) on how to do it in Java lang.
        //validationFilter.initParameters.millisBetweenCleanUps = "120000";
        validationFilter.getInitParameters().put("millisBetweenCleanUps", "120000");
    }

    /**
     * Example of customizing the filter config for any "exotic" properties that are not exposed via properties file
     */
    @Override
    public void configureAuthenticationFilter(FilterRegistrationBean authenticationFilter) {
        //This is Groovy. Below this, is the example (commented out) on how to do it in Java lang.
//        authenticationFilter.initParameters.artifactParameterName = "casTicket"
//        authenticationFilter.initParameters.serviceParameterName = "targetService"
//        authenticationFilter.getInitParameters().put("artifactParameterName", "casTicket");
//        authenticationFilter.getInitParameters().put("serviceParameterName", "targetService");
    }
}
