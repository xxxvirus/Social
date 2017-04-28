package ua.com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.social.entity.RSAKeysUser;

public interface RSAKeysUserDao extends JpaRepository<RSAKeysUser, Integer> {

}
