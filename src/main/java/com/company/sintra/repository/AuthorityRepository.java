package com.company.sintra.repository;

import com.company.sintra.entity.Authority;
import com.company.sintra.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByRole(Role role);
}
