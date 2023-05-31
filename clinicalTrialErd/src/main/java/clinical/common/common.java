package clinical.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class common {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
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




}
