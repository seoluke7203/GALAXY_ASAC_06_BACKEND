package asac06.galaxy.config;


import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasyptConfigTest {
    @Value("${jasypt.encryptor.password}")
    private String encryptKey;

    @Test
    void test1() {
        // Using Jasypt
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(encryptKey);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);


        System.out.println("encryptKey : " + encryptKey);

        String encryptedText = encryptor.encrypt(encryptKey);
        System.out.println("encryptedText = " + encryptedText);

        String decryptedText = encryptor.decrypt(encryptedText);
        System.out.println("decryptedText = " + decryptedText);

        Assertions.assertThat(decryptedText).isEqualTo(encryptKey);
    }
}