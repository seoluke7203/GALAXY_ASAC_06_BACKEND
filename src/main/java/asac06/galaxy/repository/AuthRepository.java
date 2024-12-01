package asac06.galaxy.repository;

import asac06.galaxy.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);

}
