package ma.tnbmaroc.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeys(RSAPublicKey publicKey) {
}
