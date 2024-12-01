package asac06.galaxy.service;

import jakarta.servlet.http.Cookie;

import java.util.Map;

public interface AuthService {
    Map<String, String> reissue(Cookie refresh);
}
