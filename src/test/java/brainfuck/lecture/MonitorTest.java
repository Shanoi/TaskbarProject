package brainfuck.lecture;

import brainfuck.memory.ComputationalModel;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by sebde on 08/01/2017.
 */
public class MonitorTest {

    private String path;

    @Before
    public void setUp() throws IOException {
        this.path = new File("").getAbsolutePath() + "/src/test/java/brainfuck/lecture/";

        ComputationalModel cmReset = new ComputationalModel();
        Fichiers fileReset = new Fichiers("");
        Monitor monitorReset = new Monitor(path);
        cmReset.Reset();
        fileReset.Reset();
        monitorReset.Reset();
    }

    @After
    public void tearDown() {
        ComputationalModel cmReset = new ComputationalModel();
        Fichiers fileReset = new Fichiers("");
        Monitor monitorReset = new Monitor();
        cmReset.Reset();
        fileReset.Reset();
        monitorReset.Reset();
    }

    @Test
    public void MonitorTest() throws Exception {
        Monitor monitor = new Monitor();
        monitor.Reset();
        monitor.updateData_Move();
        monitor.updateData_Read();
        monitor.updateData_Write();
        monitor.updateExec_Move();
        monitor.updateNb_Instr(1);
        assertEquals(monitor.toString(), "Nombre d'instructions: 1\n"
                + "Nombre de déplacements du pointeur d'instruction: 1\n"
                + "Nombre de déplacements dans la mémoire: 1\n"
                + "Nombre d'écritures dans la mémoire: 1\n"
                + "Nombre de lectures dans la mémoire: 1\n"
                + "Temps d'exécution: 0");
        monitor.Reset();
    }

    @Test
    public void traceLogTest() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.traceLog();

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

    @Test
    public void LogDecrTest() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsDecr();

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

    @Test
    public void LogIncrTest() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsIncr();

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

    @Test
    public void LogInTest() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsIn();

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

    @Test
    public void LogLeftTest() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsLeft();

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

    @Test
    public void LogOutTest() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsOut();

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

    @Test
    public void LogRightTest() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsRight();

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

    @Test
    public void LogImageTest() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsImage(1);

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

    @Test
    public void LogTxtTest() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsTxt("INSTR", 1);

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

    @Test
    public void LogWFTestF() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsWellformed(1, false);

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }
    
    @Test
    public void LogWFTestT() throws IOException {

        Monitor monitor = new Monitor(path + "fileTestMonitor.log");

        monitor.Reset();

        monitor.logsWellformed(1, true);

        assertTrue(new File(path + "fileTestMonitor.log").exists());

        monitor.Reset();

        new File(path + "fileTestMonitor.log").delete();

    }

}
