package fr.on.mange.quoi.user.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpringUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserEntity> optUserEntity = repository.findByLogin(login);

        if(optUserEntity.isPresent()){
            List<GrantedAuthority> roles =
                    List.of(new SimpleGrantedAuthority("USER"));

            return new User(optUserEntity.get().getLogin(),optUserEntity.get().getPassword(),
                    true, true, true, true, roles);
        }
        throw new UsernameNotFoundException("User with login "+ login +" not found");
    }
}
