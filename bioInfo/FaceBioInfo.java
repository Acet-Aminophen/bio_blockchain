package bioBlockchain.bioInfo;

public class FaceBioInfo extends BioInfo
{
    int resolutionX;
    int resolutionY;
    int[][] faceLineData;

    public FaceBioInfo() {
    }

    public FaceBioInfo(Boolean isEncrypted, String encryptedData)
    {
        super(isEncrypted, BioInfoType.FACE, encryptedData);
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public void setResolutionX(int resolutionX) {
        this.resolutionX = resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public void setResolutionY(int resolutionY) {
        this.resolutionY = resolutionY;
    }

    public int[][] getFaceLineData() {
        return faceLineData;
    }

    public void setFaceLineData(int[][] faceLineData) {
        this.faceLineData = faceLineData;
    }
}
