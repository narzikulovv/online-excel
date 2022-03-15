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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.auth.*;
import uz.excel.onlineexcel.entity.AuthUser;
import uz.excel.onlineexcel.enums.AuthRole;
import uz.excel.onlineexcel.mapper.AuthUserMapper;
import uz.excel.onlineexcel.property.ServerProperties;
import uz.excel.onlineexcel.repository.AuthUserRepository;
import uz.excel.onlineexcel.response.AppErrorDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        AuthUser user = repository.findByUsername(phone).orElseThrow(() -> {
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

    public ResponseEntity<DataDto<SessionDto>> getToken(LoginDto dto) {

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

    public ResponseEntity<DataDto<Boolean>> create(AuthUserCreateDto dto) {
        try {
            AuthUser authUser = mapper.fromCreateDto(dto);
            authUser.setRole(AuthRole.EMPLOYEE);
            authUser.setPicture(dto.getPicture().getBytes());
            authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            repository.save(authUser);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .message(e.getMessage())
                    .status(HttpStatus.NOT_FOUND)
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<Void>> delete(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(new DataDto<>(),HttpStatus.OK);
    }

    public ResponseEntity<DataDto<AuthUserDto>>  get(Long id) {
       Optional<AuthUser> authUser = repository.findById(id);
        if (authUser.isPresent()){
            AuthUserDto authUserDto = mapper.toDto(authUser.get());
            return new ResponseEntity<>(new DataDto<>(authUserDto), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .message("USER_NOT_FOUND")
                    .status(HttpStatus.NOT_FOUND)
                    .build()));
        }
    }

    public ResponseEntity<DataDto<List<AuthUserDto>>>  getAll() {
        List<AuthUser> authUserList = repository.findAll();
        List<AuthUserDto> authUserDtoList = mapper.toDto(authUserList);
        return new ResponseEntity<>(new DataDto<>(authUserDtoList));
    }

    public ResponseEntity<DataDto<Long>> update(AuthUserUpdateDto dto){
        try{
            Optional<AuthUser> authUser = repository.findById(dto.getId());
            AuthUser authUser1 = mapper.fromUpdateDto(dto, authUser.get());
            if (Objects.nonNull(dto.getPicture())) {
                authUser1.setPicture(dto.getPicture().getBytes());
            }
            return new ResponseEntity<>(new DataDto<>(repository.save(authUser1).getId()), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .message("USER_NOT_FOUND")
                    .status(HttpStatus.NOT_FOUND)
                    .build()));
        }
    }
}
