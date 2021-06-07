package pl.coderslab.TestProject.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long count() {
        log.info("Policzono uzytkownikow");
        return userRepository.count();

    }

    @Override
    public User findTheOldestWithPhoneNo() {
        log.info("Znaleziono najstarszego uzytkownika z nr tel");
        return userRepository.findFirstByPhoneNoNotNullOrderByBirthDateAsc();
    }

    @Override
    public List<User> sortedByAge() {
        return userRepository.findAllByOrderByBirthDateDesc();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public void deleteOne(long id) {
        userRepository.deleteById(id);
        log.info("Usunieto uzytkownika");
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
        log.info("Usunieto wszystkich");
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public int[] addUsers(MultipartFile multiFile) {
        int[] tab = {0,0,0,0,0};

        String fileName = multiFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        File file = null;
        try {
            file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(";");
                if (data.length < 3) {
                    tab[2]++;
                } else if (data.length < 4) {
                    if(data[2].length() != 10) {
                        String newDate = changeDateFormat(data[2]);
                        data[2] = newDate;
                    }
                    if (blank(data[0]) || blank(data[1]) || blank(data[2])) {
                        tab[2]++;
                    } else if (wrongDate(data[2])) {
                        tab[4]++;
                    } else {
                        tab[0]++;
                        saveUser(data);
                    }
                } else {
                    if(data[2].length() != 10) {
                        String newDate = changeDateFormat(data[2]);
                        data[2] = newDate;
                    }
                    if (blank(data[0]) || blank(data[1]) || blank(data[2])) {
                        tab[2]++;
                    } else if (wrongDate(data[2])) {
                        tab[4]++;
                    } else if (wrongPhone(data[3])) {
                        tab[1]++;
                        tab[0]++;
                        data[3] = null;
                        saveUser(data);
                    } else if (!uniquePhone(data[3])) {
                        tab[3]++;
                    } else {
                        tab[0]++;
                        saveUser(data);
                    }
                }
                log.info("Proba dodania uzytkownika");
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            File f = new File(file.toURI());
            f.delete();
        }
        return tab;
    }

    public void saveUser(String[] data) {
        User user = new User();
        user.setFirstName(trimAndUpperCaseFirstLetter(data[0]));
        user.setLastName(trimAndUpperCaseFirstLetter(data[1]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate date = LocalDate.parse(data[2], formatter);
        user.setBirthDate(date);
        if(data.length == 4) {
            if(data[3] != null) {
                user.setPhoneNo(Integer.parseInt(data[3]));
            }
        }
        userRepository.save(user);
        log.info("Dodano uzytkownika");
    }

    boolean blank (String str){
        return str == null || str.equals("");
    }

    boolean uniquePhone (String str){
        if(str == null || str.equals("")) {
            return true;
        }
        try {
            if(userRepository.findByPhoneNo(Integer.parseInt(str)) != null) {
                return false;
            }
        }catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    boolean wrongPhone (String str){
        if(str == null || str.equals("")) {
            return false;
        }
        try {
            Integer.parseInt(str);
        }catch (NumberFormatException e) {
            return true;
        }
        return str.length() != 9;
    }

    boolean wrongDate (String str){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            LocalDate.parse(str, formatter);
        }catch (DateTimeParseException e) {
            return true;
        }
        return false;
    }

    String changeDateFormat (String str){
        String[] tab = str.split("\\.");
        StringBuilder sb = new StringBuilder();
        for(String elem : tab) {
            String newElem;
            if(elem.length() == 1) {
                newElem = "0" + elem;
            }else{
                newElem = elem;
            }
            sb.append(newElem).append(".");
        }
        if( sb.length() > 0 ) {
            sb.deleteCharAt( sb.length() - 1 );
        }
        return sb.toString();
    }

    String trimAndUpperCaseFirstLetter (String str){
        String newStr = str.trim();
        return newStr.substring(0, 1).toUpperCase() + newStr.substring(1);
    }
}
