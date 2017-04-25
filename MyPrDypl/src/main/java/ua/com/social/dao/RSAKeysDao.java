package ua.com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.social.entity.RSAKeys;

public interface RSAKeysDao extends JpaRepository<RSAKeys, Integer> {

}
