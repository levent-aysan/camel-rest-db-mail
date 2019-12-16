package com.learncamel.routes;


import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CountryRestRouteTest extends CamelTestSupport {


    @Autowired
    private CamelContext context;

    @Autowired
    protected CamelContext createCamelContext() {
        return context;
    }

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private ConsumerTemplate consumerTemplate;

    @Override
    protected RouteBuilder createRouteBuilder(){
        return new RestCamelRoute();
    }

    @Autowired
    Environment environment;

    @Before
    public void setUp(){

    }

    @Test
    public void persistCountry(){
        String input = "{\"name\":\"Japan\",\"topLevelDomain\":[\".jp\"],\"alpha2Code\":\"JP\",\"alpha3Code\":\"JPN\",\"callingCodes\":[\"81\"],\"capital\":\"Tokyo\",\"altSpellings\":[\"JP\",\"Nippon\",\"Nihon\"],\"region\":\"Asia\",\"subregion\":\"Eastern Asia\",\"population\":126960000,\"latlng\":[36.0,138.0],\"demonym\":\"Japanese\",\"area\":377930.0,\"gini\":38.1,\"timezones\":[\"UTC+09:00\"],\"borders\":[],\"nativeName\":\"日本\",\"numericCode\":\"392\",\"currencies\":[{\"code\":\"JPY\",\"name\":\"Japanese yen\",\"symbol\":\"¥\"}],\"languages\":[{\"iso639_1\":\"ja\",\"iso639_2\":\"jpn\",\"name\":\"Japanese\",\"nativeName\":\"日本語 (にほんご)\"}],\"translations\":{\"de\":\"Japan\",\"es\":\"Japón\",\"fr\":\"Japon\",\"ja\":\"日本\",\"it\":\"Giappone\",\"br\":\"Japão\",\"pt\":\"Japão\",\"nl\":\"Japan\",\"hr\":\"Japan\",\"fa\":\"ژاپن\"},\"flag\":\"https://restcountries.eu/data/jpn.svg\",\"regionalBlocs\":[],\"cioc\":\"JPN\"}\n";

        String countryList = producerTemplate.requestBody("http://localhost:8081/country",input,String.class);

        assertNotNull(countryList);
    }
}
