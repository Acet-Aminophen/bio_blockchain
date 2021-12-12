package bioBlockchain;

import java.security.MessageDigest;
import java.util.Random;

public class Hash
{
    public static String sha256(String msg)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(msg.getBytes());
            return bytesToHex(md.digest());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String bytesToHex(byte[] bytes)
    {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static String getHashConflict(String target)
    {
        StringBuilder sb = new StringBuilder();
        while(true)
        {
            if (sb.length() > 20) sb.setLength(0);
            sb.append(getRandom());
            String ar = sha256(sb.toString());
            assert ar != null;
            ar = ar.substring(ar.length() - target.length(), ar.length());
            if (ar.equals(target)) return sb.toString();
        }
    }

    public static String getRandom()
    {
        Random rnd =new Random();
        if(rnd.nextBoolean()){
            char ch = (char) ((Math.random() * 26) + 97);
            return Character.toString(ch);
        }else{
            return Integer.toString(rnd.nextInt(10));
        }
    }
}
