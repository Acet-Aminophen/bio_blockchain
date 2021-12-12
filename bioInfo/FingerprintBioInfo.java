package bioBlockchain.bioInfo;

public class FingerprintBioInfo extends BioInfo
{
    String data;

    public FingerprintBioInfo() {
    }

    public FingerprintBioInfo(Boolean isEncrypted, String encryptedData) {
        super(isEncrypted, BioInfoType.FINGERPRINT, encryptedData);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
