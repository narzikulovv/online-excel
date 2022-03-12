package uz.excel.onlineexcel.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.auth.AuthUserCreateDto;
import uz.excel.onlineexcel.dto.auth.AuthUserDto;
import uz.excel.onlineexcel.dto.auth.SessionDto;
import uz.excel.onlineexcel.dto.response.AppErrorDto;
import uz.excel.onlineexcel.dto.response.DataDto;
import uz.excel.onlineexcel.entity.AuthUser;
import uz.excel.onlineexcel.enums.AuthRole;
import uz.excel.onlineexcel.mapper.AuthUserMapper;
import uz.excel.onlineexcel.properties.ServerProperties;
import uz.excel.onlineexcel.repository.AuthUserRepository;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class AuthUserService extends AbstractService<AuthUserMapper, AuthUserRepository>
        implements UserDetailsService, BaseService {

    private final ServerProperties serverProperties;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthUserService(AuthUserMapper mapper, AuthUserRepository repository, ServerProperties serverProperties, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        super(mapper, repository);
        this.serverProperties = serverProperties;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthority()
                )
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
    }

    public ResponseEntity<DataDto<SessionDto>> getToken(AuthUserDto dto) {

        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(serverProperties.getServerUrl() + "/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));
            HttpResponse response = httpclient.execute(httppost);
            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));
            if (json_auth.has("success") && json_auth.get("success").asBoolean()) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new ResponseEntity<>(new DataDto<>(sessionDto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new DataDto<>(objectMapper.readValue(json_auth.get("error").toString(),
                    AppErrorDto.class)), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .message(e.getLocalizedMessage())
                    .message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        }
    }

    public ResponseEntity<DataDto<SessionDto>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    public ResponseEntity<DataDto<Boolean>> create(AuthUserCreateDto dto){
        try{
            AuthUser authUser = mapper.fromCreateDto(dto);
            authUser.setRole(AuthRole.EMPLOYEE);
//            authUser.setPicture(dto.getPicture().getBytes());
            authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            repository.save(authUser);
        }catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .message(e.getMessage())
                    .status(HttpStatus.NOT_FOUND)
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(true),HttpStatus.OK);
    }
}
