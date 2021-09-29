package by.itransition.mathtasksapp.repositories;

import by.itransition.mathtasksapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    User getById(long id);
    @Query("select count(u) from User u join u.solvedTasks where u.id=(:id)")
    Long countSolvedTasks(long id);
}
