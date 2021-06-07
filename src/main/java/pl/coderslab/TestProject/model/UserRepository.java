package pl.coderslab.TestProject.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    long countAllBy();
    List<User> findAllByOrderByBirthDateDesc();
    User findFirstByPhoneNoNotNullOrderByBirthDateAsc();
    User findByPhoneNo(int phone);
    List<User> findByLastName(String lastName);
}
