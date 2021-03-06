package io.github.amuse.sims_server_spring.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    boolean existsByUserId(String userId);
}
