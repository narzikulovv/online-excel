package uz.excel.onlineexcel.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import uz.excel.onlineexcel.dto.base.BaseDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserCreateDto implements BaseDto {

    private String username;

    private String password;

    private String fullName;

    private String phone;
}

