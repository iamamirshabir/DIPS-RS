package com.pioneer.dips.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pioneer.dips.user.model.User;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT * FROM userac WHERE userac.userac_keycloak_id LIKE %:keycloak_id% FETCH FIRST ROW ONLY", nativeQuery = true)
	public Optional<User> findByKeycloakID(@Param("keycloak_id")String keycloak_id);
	

}
