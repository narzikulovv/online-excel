package uz.excel.onlineexcel.dto.auth;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginDto {
    private String phone;
    private String password;
}
