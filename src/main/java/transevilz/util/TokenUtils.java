package transevilz.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

public class TokenUtils {
    @Value("${evil.app.jwtSecret}")
    private static String jwtSecret;

    public static Claims getClaim(String token){
        return Jwts.parser()
                .setSigningKey("evilprojectSecretKey")
                .parseClaimsJws(token).getBody();
    }

    public static String claimToAppUser(Claims claims) {
        String token = claims.get("sub", String.class);
        return token;
    }
}
