package gr.softcom.microservices.limitsservice.resourcres;

import gr.softcom.microservices.limitsservice.Configuration;
import gr.softcom.microservices.limitsservice.resourcres.domain.LimitsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationResource {

    private Configuration configuration;

    public LimitsConfigurationResource(Configuration configuration) {
        this.configuration=configuration;
    }

    @GetMapping("limits")
    public LimitsConfiguration retrieveLimitsConfigurations(){
        return new LimitsConfiguration(configuration.getMinimum(),configuration.getMaximum());
    }
}
