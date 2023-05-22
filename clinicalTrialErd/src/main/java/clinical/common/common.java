package clinical.common;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class common {

	@Value("spring.jwt.secret")
	private String secretKey;
	//암호화
	public String encryptor(String pwdEncryptor) {
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
		SimpleStringPBEConfig config = new SimpleStringPBEConfig(); 
		
		config.setPassword("dreamcis"); 
		config.setAlgorithm("PBEWithMD5AndDES"); 
		config.setKeyObtentionIterations("1000"); 
		config.setPoolSize("1"); 
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); 
		config.setStringOutputType("base64"); 
		encryptor.setConfig(config);
		
		//암호화
		String encryptedText = encryptor.encrypt(pwdEncryptor); // 암호화
		return encryptedText; 
	}
	
	
	//복호화
	public String decrypt(String pwdDecrypt) {
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
		SimpleStringPBEConfig config = new SimpleStringPBEConfig(); 
		
		config.setPassword("dreamcis"); 
		config.setAlgorithm("PBEWithMD5AndDES"); 
		config.setKeyObtentionIterations("1000"); 
		config.setPoolSize("1"); 
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); 
		config.setStringOutputType("base64"); 
		encryptor.setConfig(config);
		
		//암호화
		String decryptText = encryptor.decrypt(pwdDecrypt); // 암호화
		
		return decryptText; 
	}
	
	//Jwts 토큰 설정
	public String createToken(String user_email) {
		
		//Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("KEY", "DreamCis");
		payloads.put("user_email", user_email);

        Long expiredTime = 1000 * 60L * 60L * 1L; // 토큰 유효 시간 (2시간)

        Date date = new Date(); // 토큰 만료 시간
        date.setTime(date.getTime() + expiredTime);

        SecretKey key = Keys.hmacShaKeyFor("DreamCisVueMaterialAdmin이며TOY프로젝트입니다.".getBytes(StandardCharsets.UTF_8));

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setSubject("Access-token") // 토큰 용도
                .setExpiration(date) // 토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact(); // 토큰 생성

        //System.out.println(">> jwt : " + jwt);
		return jwt;
	}

	// Jwt Token에서 User PK 추출
	public Claims getClamins(String token) {//JWT조회(?)
		Claims claims = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)//싸인이 포함된 jwt = jws
				.getBody();
		return claims;
	}

	// 토큰검증
//	fun verifyToken(token: String): Boolean {
//		return try {
//			val claims = getAllClaims(token)
//			val expiration = claims.expiration
//			expiration.after(Date())
//		} catch (e: JwtException) {
//			false
//		} catch (e: IllegalArgumentException) {
//			false
//		}
// 	}

}
