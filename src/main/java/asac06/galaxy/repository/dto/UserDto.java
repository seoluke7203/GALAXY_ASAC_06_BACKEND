package asac06.galaxy.repository.dto;


import lombok.Data;

@Data
public class UserDto {

    private String login_id;
    private String name;
    private String password;
    private String email;

}
