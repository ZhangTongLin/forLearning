import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator.
 */
public class DiskDeleteTest {

    @Test
    public void deleteTest() {
        File f = new File("e:/2.txt");
        //f.delete();
        f.renameTo(new File("e:/5.txt"));
    }




}
