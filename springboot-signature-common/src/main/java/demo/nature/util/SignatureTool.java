package demo.nature.util;

import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;

/**
 * @author nature
 * @date 25/7/2020 5:06 下午
 * @email 924943578@qq.com
 * 1、JKS文件是一个java中的密钥管理库，里面可以放各种密钥文件,仓库当然会有一把锁，防范别人随便乱拿，这个就是JKS文件的密 码.里面存放的密钥也各有不同，每个密钥都有一个名字（在下面叫别名），一类就密钥对，一类叫公钥，一类叫私钥，密钥对就是包含公钥和私钥的。这里的公钥 只要你能进入仓库你就可以随便查看拿走，私钥则是有密码的。只允许有权限的人查看拿走.对于读取公钥只需要 知道JKS文件（仓库）的密码就可以了，但是在读取私钥时则必须有私钥的密码。
 *
 * 2、在加载证书文件的时候，keyStore.load(in, storePass.toCharArray());storePass为获取私钥文件的密码，即仓库密码，我们这里为abc@2018
 * 对于类型为PKCS12的密钥库, keypass == storepass
 */
public class SignatureTool {

    private String keyPass = "123456";

    private String storePass = "123456";

    private static SignatureTool tool;

    private String privateKeyFilePath = "/keys/sender_keystore.p12";

    private String privateKeyAlias = "senderKeyPair";

    private String publicKeyFilePath = "/keys/receiver_keystore.p12";

    private String publicKeyAlias = "receiverKeyPair";

    private PrivateKey privateKey;

    private PublicKey publicKey;

    private SignatureTool() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        privateKey = getPrivateKey();
        publicKey = getPublicKey();
    }

    public static SignatureTool getInstance() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        if(tool == null)
            tool = new SignatureTool();
        return tool;
    }

    private PrivateKey getPrivateKey() throws KeyStoreException, IOException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(getClass().getResourceAsStream(privateKeyFilePath), storePass.toCharArray());
        PrivateKey privateKey =
                (PrivateKey) keyStore.getKey(privateKeyAlias, keyPass.toCharArray());
        return privateKey;
    }

    private PublicKey getPublicKey() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(getClass().getResourceAsStream(publicKeyFilePath), storePass.toCharArray());
        Certificate certificate = keyStore.getCertificate(publicKeyAlias);
        PublicKey publicKey = certificate.getPublicKey();
        return publicKey;
    }

    public String sign(String message) throws NoSuchAlgorithmException, IOException, SignatureException, UnrecoverableKeyException, CertificateException, KeyStoreException, InvalidKeyException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        byte[] messageBytes = message.getBytes("UTF-8");
        signature.update(messageBytes);
        byte[] signatureBytes = signature.sign();
        String signatureMsg = Base64.getEncoder().encodeToString(signatureBytes);
        return signatureMsg;
    }

    public boolean verify(String message, String expectedSignature) throws NoSuchAlgorithmException, IOException, SignatureException, CertificateException, KeyStoreException, InvalidKeyException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        byte[] messageBytes = message.getBytes("UTF-8");
        signature.update(messageBytes);
        byte[] signatureBytes = Base64.getDecoder().decode(expectedSignature);
        boolean flags = signature.verify(signatureBytes);
        return flags;
    }

    public static void main(String[] args) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, SignatureException, InvalidKeyException {
        String message = "hello world";
        SignatureTool tool = SignatureTool.getInstance();
        String signature = tool.sign(message);
        System.out.println("after sign, signature: " + signature);

        boolean flags = tool.verify(message, signature);
        System.out.println("after verify, flags: " + flags);

    }
}
