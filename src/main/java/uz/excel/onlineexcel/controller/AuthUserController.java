package uz.excel.onlineexcel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.dto.auth.AuthUserCreateDto;
import uz.excel.onlineexcel.dto.auth.AuthUserDto;
import uz.excel.onlineexcel.dto.auth.SessionDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.service.AuthUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthUserController extends AbstractController<AuthUserService> {

    public AuthUserController(AuthUserService service) {
        super(service);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> getToken(@RequestBody AuthUserDto dto) {
        return service.getToken(dto);
    }

    @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
    public ResponseEntity<DataDto<SessionDto>> getToken(HttpServletRequest request, HttpServletResponse response) {
        return service.refreshToken(request, response);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Boolean>> create(@RequestBody AuthUserCreateDto dto) {
        return service.create(dto);
    }
}
