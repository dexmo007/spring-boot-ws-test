package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformSchemaLocations(true);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(@Qualifier("countriesSchema") XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(CountryEndpoint.NAMESPACE_URI);
        wsdl11Definition.setSchema(countriesSchema);
//        final CommonsXsdSchemaCollection schemaCollection = new CommonsXsdSchemaCollection(countriesSchemaResource);
//        schemaCollection.setInline(true);
//        wsdl11Definition.setSchemaCollection(schemaCollection);
        return wsdl11Definition;
    }

    @Value("classpath:model.xsd")
    private Resource modelSchemaResource;

    @Value("classpath:countries.xsd")
    private Resource countriesSchemaResource;

    @Value("classpath:remove-countries.xsd")
    private Resource removeCountriesSchemaResource;

    @Bean
    public XsdSchema modelSchema() {
        return new SimpleXsdSchema(modelSchemaResource);
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(countriesSchemaResource);
    }

    @Bean(name = "removeCountries")
    public DefaultWsdl11Definition defaultWsdl11Definition2(@Qualifier("removeCountriesSchema") XsdSchema removeCountriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("RemoveCountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(CountryRepositoryEndpoint.NAMESPACE_URI);
        wsdl11Definition.setSchema(removeCountriesSchema);
//        wsdl11Definition.setSchemaCollection(new CommonsXsdSchemaCollection(removeCountriesSchemaResource, modelSchemaResource));
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema removeCountriesSchema() {
        return new SimpleXsdSchema(removeCountriesSchemaResource);
    }
}
