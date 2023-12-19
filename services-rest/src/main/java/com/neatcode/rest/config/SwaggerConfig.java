package com.neatcode.rest.config;

import com.neatcode.rest.properties.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {
    private static final String DEFAULT_TITLE = "KPA Test API";
    private static final String DEFAULT_DESCRIPTION = "KPA Test API";
    private static final String DEFAULT_VERSION = "1.0";
    private static final String DEFAULT_TERMS_OF_SERVICE_URL = "https://www.kpa.com.tw";
    private static final String DEFAULT_CONTACT_NAME = "KPA";
    private static final String DEFAULT_CONTACT_URL = "https://www.kpa.com.tw";
    private static final String DEFAULT_CONTACT_EMAIL = "";
    private static final String DEFAULT_LICENSE = "KPA";
    private static final String DEFAULT_LICENSE_URL = "https://www.kpa.com.tw";
    private static final String DEFAULT_BASE_PACKAGE = "com.kpa.test.api";
    private static final String DEFAULT_PATH = "/v2/api-docs";

    private final SwaggerProperties properties;

    public SwaggerConfig(SwaggerProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Docket api() {
        String basePackage = StringUtils.isEmpty(properties.getBasePackage()) ? DEFAULT_BASE_PACKAGE : properties.getBasePackage();
        String apiPath = StringUtils.isEmpty(properties.getPath()) ? DEFAULT_PATH : properties.getPath();

        return new Docket(DocumentationType.SWAGGER_2)
            .groupName(properties.getGroupName())
            .select().apis(RequestHandlerSelectors.basePackage(basePackage))
            .paths(PathSelectors.regex(apiPath))
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        String title = StringUtils.isEmpty(properties.getTitle()) ? DEFAULT_TITLE : properties.getTitle();
        String description = StringUtils.isEmpty(properties.getDescription()) ? DEFAULT_DESCRIPTION : properties.getDescription();
        String version = StringUtils.isEmpty(properties.getVersion()) ? DEFAULT_VERSION : properties.getVersion();
        String contactName = StringUtils.isEmpty(properties.getContactName()) ? DEFAULT_CONTACT_NAME : properties.getContactName();
        String contactUrl = StringUtils.isEmpty(properties.getContactUrl()) ? DEFAULT_CONTACT_URL : properties.getContactUrl();
        String contactEmail = StringUtils.isEmpty(properties.getContactEmail()) ? DEFAULT_CONTACT_EMAIL : properties.getContactEmail();

        return new ApiInfoBuilder()
            .title(title)
            .description(description)
            .version(version)
            .contact(new Contact(contactName, contactUrl, contactEmail))
            .build();
    }
}
