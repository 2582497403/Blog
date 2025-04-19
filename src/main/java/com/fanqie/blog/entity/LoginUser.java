package com.fanqie.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class LoginUser implements UserDetails {

    /**
     * @Fields user : 存储用户的完整信息，通常是从数据库中查询得到的 TbUser 实体类。
     * 这个对象包含了用户的基本信息，例如 ID、用户名、密码等。
     */
    private TbUser user;

    /**
     * @return Collection<? extends GrantedAuthority>
     * @Description 获取用户的权限集合。
     * GrantedAuthority 是 Spring Security 中代表用户所拥有的权限的接口，通常是角色（Role）。
     * 在这个实现中，直接返回一个空的 List，意味着当前用户没有任何权限。
     * 在实际应用中，你需要根据用户的角色从数据库或其他来源加载权限信息，并将其封装成 GrantedAuthority 对象返回。
     * 例如，你可以创建一个实现了 GrantedAuthority 接口的类来表示用户的角色，并在这里返回包含这些角色对象的集合。
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * @return String
     * @Description 获取用户的密码。
     * 这个方法应该返回用户的加密后的密码，该密码通常存储在数据库中。
     * 在这个实现中，直接返回一个空字符串，这在实际应用中是不正确的。
     * 你需要从 `user` 对象中获取用户的密码，并确保返回的是加密后的形式。
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * @return String
     * @Description 获取用户的用户名。
     * 这个方法应该返回用户的唯一标识符，通常是登录时使用的用户名。
     * 在这个实现中，直接返回一个空字符串，这在实际应用中是不正确的。
     * 你需要从 `user` 对象中获取用户的用户名。
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * @return boolean
     * @Description 判断用户的账户是否已过期。
     * 如果返回 `true`，则表示账户未过期，用户可以正常登录。
     * 如果返回 `false`，则表示账户已过期，用户无法登录。
     * 在这个实现中，直接返回 `false`，意味着账户总是被视为已过期。
     * 在实际应用中，你需要根据用户的账户状态（例如，是否设置了过期日期）来判断。
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return boolean
     * @Description 判断用户的账户是否被锁定。
     * 如果返回 `true`，则表示账户未被锁定，用户可以正常登录。
     * 如果返回 `false`，则表示账户已被锁定，用户无法登录。
     * 账户可能由于多次登录失败等原因而被锁定。
     * 在这个实现中，直接返回 `false`，意味着账户总是被视为已锁定。
     * 在实际应用中，你需要根据用户的账户状态（例如，是否设置了锁定标志）来判断。
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return boolean
     * @Description 判断用户的凭证（密码）是否已过期。
     * 如果返回 `true`，则表示凭证未过期，用户可以正常登录。
     * 如果返回 `false`，则表示凭证已过期，用户可能需要重置密码。
     * 在这个实现中，直接返回 `false`，意味着凭证总是被视为已过期。
     * 在实际应用中，你可能需要根据密码的更新时间等来判断。
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return boolean
     * @Description 判断用户是否已启用。
     * 如果返回 `true`，则表示用户已启用，可以正常登录。
     * 如果返回 `false`，则表示用户已被禁用，无法登录。
     * 用户可能由于某些原因（例如，管理员禁用）而被禁用。
     * 在这个实现中，直接返回 `false`，意味着用户总是被视为未启用。
     * 在实际应用中，你需要根据 `user` 对象中的状态字段来判断用户是否已启用。
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
