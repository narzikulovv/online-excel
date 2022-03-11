package uz.excel.onlineexcel.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.mapper.AuthUserMapper;
import uz.excel.onlineexcel.repository.AuthUserRepository;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;

@Service
public class AuthUserService extends AbstractService<AuthUserMapper, AuthUserRepository> implements UserDetailsService, BaseService {

    public AuthUserService(AuthUserMapper mapper, AuthUserRepository repository) {
        super(mapper, repository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
