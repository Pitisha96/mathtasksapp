package by.itransition.mathtasksapp.repositories;

import by.itransition.mathtasksapp.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Long> {
}
