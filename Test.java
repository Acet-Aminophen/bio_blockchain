package bioBlockchain;

import bioBlockchain.bioInfo.BioInfo;
import bioBlockchain.bioInfo.FingerprintBioInfo;
import bioBlockchain.trans.Server;
import bioBlockchain.trans.TransBlock;
import bioBlockchain.trans.TransType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test
{
    String serverIp = "127.0.0.1";
    int serverPort = 3377;

    public Test(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public List<Block> receiveChain()
    {
        try
        {
            Socket socket = new Socket(serverIp, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(new TransBlock(TransType.GET_BLOCK, null));
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            List<Block> chain = (List<Block>) ois.readObject();

            oos.close();
            ois.close();
            socket.close();

            return chain;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void sendBlock(TransBlock tb)
    {
        try
        {
            Socket socket = new Socket(serverIp, serverPort);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(tb);
            oos.flush();

            oos.close();
            socket.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void test()
    {
        System.out.println("GETTING CHAIN INFO");
        List<Block> chain = receiveChain();
        System.out.println("CHAIN SIZE : " + chain.size());
        System.out.println("LAST BLOCK'S HASH : " + chain.get(chain.size() - 1).getHash());

        System.out.println("Mining...");
        String answer = Hash.getHashConflict(chain.get(chain.size() - 1).getHash());
        System.out.println("PRE HASH'S ANSWER : " + answer);


        FingerprintBioInfo fbi = new FingerprintBioInfo(false, null);
        fbi.setHash(Hash.sha256(Hash.getRandom()));
        List<BioInfo> lk = new ArrayList<>();
        lk.add(fbi);
        Block bl = new Block(chain.get(chain.size() - 1).getHash(), answer, 0, lk);
        TransBlock tb = new TransBlock(TransType.ADD_BLOCK, bl);

        System.out.println("BIO INFO TYPE : " + fbi.getType().toString());

        sendBlock(tb);
        System.out.println("SENDING SUCCESS");
    }


    public static void main(String[] args)
    {
        Test test = new Test(args[0], Integer.parseInt(args[1]));
        test.test();
    }
}
