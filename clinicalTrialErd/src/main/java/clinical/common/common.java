package clinical.common;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.stereotype.Component;

@Component
public class common {

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
}
