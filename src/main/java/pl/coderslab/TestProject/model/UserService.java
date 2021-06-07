package pl.coderslab.TestProject.model;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public interface UserService {
    long count();
    User findTheOldestWithPhoneNo();
    int[] addUsers(MultipartFile file);
    List<User> sortedByAge();
    List<User> findAll();
    List<User> findByLastName(String lastName);
    void deleteOne(long id);
    void deleteAll();

}
