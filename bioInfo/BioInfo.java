package bioBlockchain.bioInfo;

import java.io.Serializable;

public class BioInfo implements Serializable
{
    protected String hash;
    protected Boolean isEncrypted;
    protected BioInfoType type;
    protected String encryptedData;

    // for Serialize
    public BioInfo() {
    }

    public BioInfo(Boolean isEncrypted, BioInfoType type, String encryptedData)
    {
        this.isEncrypted = isEncrypted;
        this.type = type;
        this.encryptedData = encryptedData;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Boolean getEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(Boolean encrypted) {
        isEncrypted = encrypted;
    }

    public BioInfoType getType() {
        return type;
    }

    public void setType(BioInfoType type) {
        this.type = type;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }
}
