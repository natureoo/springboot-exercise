#create a key pair of a private and public key
keytool -genkeypair -alias senderKeyPair -keyalg RSA -keysize 2048 \
  -dname "CN=Demo" -validity 365 -storetype PKCS12 \
  -keystore sender_keystore.p12 -storepass 123456

#using a self-signed certificate, we need only to export it from the Keystore file
keytool -exportcert -alias senderKeyPair -storetype PKCS12 \
  -keystore sender_keystore.p12 -file \
  sender_certificate.cer -rfc -storepass 123456

#Having access to the public key, a receiver can load it into their Keystore 
keytool -importcert -alias receiverKeyPair -storetype PKCS12 \
  -keystore receiver_keystore.p12 -file \
  sender_certificate.cer -rfc -storepass 123456
  
#list keystore
keytool -list -keystore sender_keystore.p12 -storepass 123456 
keytool -list -keystore receiver_keystore.p12 -storepass 123456 


#delete duplicated if needed
keytool -delete -alias myRsaKeyPair -keystore myrsa_keystore.p12 -storepass 123456
