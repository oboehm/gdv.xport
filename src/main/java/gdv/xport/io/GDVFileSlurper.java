package gdv.xport.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class reads the file in question in memory and returns an Array of ByteArrayInputstreams
 * @author U31051
 */
public class GDVFileSlurper
{
    private static final int    GDV_RECORD_LENGTH = 256;

    private static final byte[] ENDE_SATZ         = { '9', '9', '9', '9' };

    public static ArrayList<ByteArrayInputStream> slurp(String f)
        throws FileNotFoundException, IOException
    {
        return slurp(new File(f));
    }

    public static ArrayList<ByteArrayInputStream> slurp(File f)
        throws FileNotFoundException, IOException
    {
        ArrayList<ByteArrayInputStream> al   = new ArrayList<ByteArrayInputStream>();
        byte[]    buf  = new byte[GDV_RECORD_LENGTH];

        FileInputStream       fis   = new FileInputStream(f);
        BufferedInputStream   bis   = new BufferedInputStream(fis);
        PushbackInputStream   pbis  = new PushbackInputStream(bis);

        ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);

        while (true)
        {
            // read record data
            int numRead = pbis.read(buf);
            if (numRead <= 0) {
                addTo(al, bout);
                break;
            }

            byte[]    head = Arrays.copyOfRange(buf, 0, 4);
            bout.write(buf);

            // read line sepearator
            int ch;
            while ((ch = pbis.read()) == '\r' || ch == '\n')
               bout.write(ch);
            if (ch != -1)
                pbis.unread(ch);

            boolean endOfPaketFound = Arrays.equals(head, ENDE_SATZ);
            if (endOfPaketFound)
            {
                addTo(al, bout);
                bout = new ByteArrayOutputStream(4096);
            }
        }
        pbis.close();
        return al;
    }

    private static void addTo(ArrayList<ByteArrayInputStream> al, ByteArrayOutputStream bout) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bout.toByteArray());
        al.add(bais);
    }
}
