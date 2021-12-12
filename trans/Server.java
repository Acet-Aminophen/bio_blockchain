package bioBlockchain.trans;

import bioBlockchain.Block;
import bioBlockchain.Hash;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server
{
    int SOCKET_PORT = 3377;
    List<Block> chain = new ArrayList<>();

    public void start()
    {
        Block firstBlock = new Block();
        firstBlock.setHash("9");
        chain.add(firstBlock);
        System.out.println("ORIGIN BLOCK CREATED(HASH : " + firstBlock.getHash() + "), WAITING FOR NEXT BLOCK");

        try
        {
            ServerSocket ss = new ServerSocket(SOCKET_PORT);
            Socket cl = null;
            PrintWriter pw;
            ObjectInputStream ois;

            while(true)
            {
                cl = ss.accept();
                //System.out.println("SOCKET ACCEPTED");
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cl.getOutputStream())));
                ois = new ObjectInputStream(cl.getInputStream());

                TransBlock tb = (TransBlock) ois.readObject();

                switch (tb.transType)
                {
                    case ADD_BLOCK:
                        try
                        {
                            String answerHash = Hash.sha256(tb.getBlock().getPreHashAnswer());
                            String preHash = chain.get(chain.size() - 1).getHash();
                            String answerHashFit = answerHash.substring(answerHash.length() - preHash.length(), answerHash.length());
                            if (answerHashFit.equals(preHash))
                            {
                                chain.add(tb.getBlock());
                                System.out.println("BLOCK(" + (chain.size() - 1) + ") APPLIED(ANSWER : " + tb.getBlock().getPreHashAnswer()+", HASH : " + tb.getBlock().getHash() + ")");
                            }
                            else
                            {
                                System.out.println("BLOCK FAILED");
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                            System.out.println("BLOCK FAILED");
                        }
                        break;
                    case GET_BLOCK:
                        ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                        oos.writeObject(chain);
                        oos.flush();
                        break;
                }

                pw.close();
                cl.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Server sv = new Server();
        sv.start();
    }
}
