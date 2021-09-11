package cz.abdykili.eshop.repository;

import cz.abdykili.eshop.domain.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}
