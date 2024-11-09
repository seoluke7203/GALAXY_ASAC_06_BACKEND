package asac06.galaxy.controller;


import asac06.galaxy.repository.UserRepository;
import asac06.galaxy.repository.dto.UserDto;
import asac06.galaxy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){

        if (userRepository.findByEmail(userDto.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 등록된 이메일입니다. 다른 이메일을 사용해주세요.");
        } else{
            userService.registerUser(userDto);
            return ResponseEntity.ok("User registered successfully");
        }


    }
}
