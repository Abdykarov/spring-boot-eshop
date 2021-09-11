package cz.abdykili.eshop.repository;

import cz.abdykili.eshop.domain.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

}
