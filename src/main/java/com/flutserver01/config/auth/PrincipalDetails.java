package com.flutserver01.config.auth;

import com.flutserver01.model.auth.CmmnUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@Data
public class PrincipalDetails implements UserDetails {

    private CmmnUser cmmnUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return cmmnUser.getMemRole();
            }
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return cmmnUser.getMemPw();
    }

    @Override
    public String getUsername() {
        return cmmnUser.getMemIdno();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
