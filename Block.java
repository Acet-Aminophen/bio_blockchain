package bioBlockchain;

import bioBlockchain.bioInfo.BioInfo;
import bioBlockchain.exception.UnvalidHashAnswerException;

import java.io.Serializable;
import java.util.List;

public class Block implements Serializable
{
    String preBlockHash;
    String preHashAnswer;
    String hash;
    long timestamp;
    int difficulty = 1;
    List<? extends BioInfo> contents;

    public Block() {
    }

    public String getHash() {
        return hash;
    }

    public String getContentHash()
    {
        StringBuilder cntHash = new StringBuilder();
        for(BioInfo bi : contents)
        {
            cntHash.append(bi.getHash());
        }

        return Hash.sha256(preBlockHash + Long.toString(timestamp) + cntHash.toString());
    }

    public boolean chkValidContentsHash()
    {
        if (!hash.equals(getContentHash())) return false;
        else return true;
    }

    public Block(String preBlockHash, String preHashAnswer, long timestamp, List<? extends BioInfo> contents)
    {
        this.preBlockHash = preBlockHash;
        String hashedAnswer = Hash.sha256(preHashAnswer);
        String hashedAnswerFit = hashedAnswer.substring(hashedAnswer.length() - preBlockHash.length(), hashedAnswer.length());

        if (!hashedAnswerFit.equals(preBlockHash))
        {
            throw new UnvalidHashAnswerException();
        }
        else
        {
            this.preHashAnswer = preHashAnswer;
        }

        this.timestamp = timestamp;
        this.contents = contents;
        this.hash = getContentHash();
        this.hash = this.hash.substring(this.hash.length() - difficulty, this.hash.length());
    }

    public String getPreBlockHash() {
        return preBlockHash;
    }

    public void setPreBlockHash(String preBlockHash) {
        this.preBlockHash = preBlockHash;
    }

    public String getPreHashAnswer() {
        return preHashAnswer;
    }

    public void setPreHashAnswer(String preHashAnswer) {
        this.preHashAnswer = preHashAnswer;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<? extends BioInfo> getContents() {
        return contents;
    }

    public void setContents(List<? extends BioInfo> contents) {
        this.contents = contents;
    }
}
