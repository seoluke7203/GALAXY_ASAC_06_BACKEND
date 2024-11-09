package asac06.galaxy.service;

import asac06.galaxy.model.User;
import asac06.galaxy.repository.UserRepository;
import asac06.galaxy.repository.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDto userDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userDto.getPassword());

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encodedPassword);
        user.setLogin_id(userDto.getLogin_id());

        userRepository.save(user);
    }
}
