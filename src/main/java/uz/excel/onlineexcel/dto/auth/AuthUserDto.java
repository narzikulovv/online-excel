package uz.excel.onlineexcel.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import uz.excel.onlineexcel.dto.base.BaseDto;
import uz.excel.onlineexcel.dto.base.GenericDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto {

    private String username;
    private String password;

}
