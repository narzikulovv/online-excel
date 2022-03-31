package uz.excel.onlineexcel.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class ConnectionProperties {

    private String url;

    private String username;

    private String password;

}
