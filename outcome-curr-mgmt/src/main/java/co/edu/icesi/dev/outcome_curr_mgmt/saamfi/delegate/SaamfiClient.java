package co.edu.icesi.dev.outcome_curr_mgmt.saamfi.delegate;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.LoginInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.LoginOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Component
public class SaamfiClient {
    //TODO add test coverage
    protected final Log logger = LogFactory.getLog(this.getClass());
    private final String saamfiUrl;
    private final String systemId;
    private final String institutionId;
    private RestTemplate template;

    @Getter
    private PublicKey publicKey;

    public SaamfiClient(@Value("${saamfi.url}") final String saamfiUrl,
            @Value("${saamfi.system.id}") final String system_id,
            @Value("${saamfi.institution.id}") final String institution_id) {
        this.saamfiUrl = saamfiUrl;
        this.systemId = system_id;
        this.institutionId = institution_id;
        template = new RestTemplate();

        try {
            publicKey = getRemotePublicKey();

        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

    public PublicKey getRemotePublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String key = template.getForEntity(saamfiUrl + "/public/publicKey", String.class).getBody();
        if (key != null) {
            key = key.replace("[", "");
            key = key.replace("]", "");
            String[] split = key.split(",");
            byte[] bytes = new byte[split.length];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = Byte.parseByte(split[i].trim());

            }
            X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(spec);
        }
        return null;
    }

    public PublicKey getRemotePublicKey(String filename)
            throws InvalidKeySpecException, IOException, NoSuchAlgorithmException {
        byte[] keyBytes = null;
        String externalPath =
                Environment.getProperties().getProperty("catalina.home") + File.separator + "conf" + File.separator
                        + "uccare" + File.separator + filename;
        try {
            File file = ResourceUtils.getFile(externalPath);
            keyBytes = Files.readAllBytes(file.toPath());
        } catch (Exception e2) {
            try {
                File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + filename);
                keyBytes = Files.readAllBytes(file.toPath());
            } catch (Exception e) {

                keyBytes = Files.readAllBytes(Paths.get(filename));
            }
        }

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public LoginOutDTO getUserLogin(LoginInDTO loginInDTO) {
        String endpoint = String.format("/public/institutions/%s/systems/%s/users/login", institutionId, systemId);
        try {
            return template.postForEntity(saamfiUrl + endpoint, loginInDTO, LoginOutDTO.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new OutCurrException(OutCurrExceptionType.USER_INVALID_CREDENTIALS);
        }
    }
}
