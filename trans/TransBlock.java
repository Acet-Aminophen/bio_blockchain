package bioBlockchain.trans;

import bioBlockchain.Block;
import bioBlockchain.bioInfo.BioInfo;
import bioBlockchain.bioInfo.BioInfoType;

import java.io.Serializable;

public class TransBlock implements Serializable
{
    TransType transType;
    Block block;

    public TransBlock() {
    }

    public TransBlock(TransType transType, Block block) {
        this.transType = transType;
        this.block = block;
    }

    public TransType getTransType() {
        return transType;
    }

    public void setTransType(TransType transType) {
        this.transType = transType;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
