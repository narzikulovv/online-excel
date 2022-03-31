package uz.excel.onlineexcel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.excel.onlineexcel.property.ConnectionProperties;
import uz.excel.onlineexcel.property.OpenApiProperties;
import uz.excel.onlineexcel.property.ServerProperties;

@SpringBootApplication
@EnableConfigurationProperties(
        {OpenApiProperties.class,ServerProperties.class, ConnectionProperties.class}
)
@OpenAPIDefinition
public class OnlineExcelApplication {

//    @Autowired
//    AuthUserService userService;
//    @Autowired
//    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(OnlineExcelApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run() {
//        return args -> {
//            userService.create(new AuthUserCreateDto("admin", "+998973130080", "Saydali@gmail.com",
//                    passwordEncoder.encode("admin123"), Department.ACADEMIC.name(), Role.ADMIN.name()));
//            userService.create(new AuthUserCreateDto("manager", "+998973130081", "Saydali1@gmail.com",
//                    passwordEncoder.encode("manager123"), Department.ACADEMIC.name(), Role.ADMIN.name()));
//            userService.create(new AuthUserCreateDto("employee", "+998973130082", "Saydali2@gmail.com",
//                    passwordEncoder.encode("employee123"), Department.ACADEMIC.name(), Role.ADMIN.name()));
//        };
//    }

}
