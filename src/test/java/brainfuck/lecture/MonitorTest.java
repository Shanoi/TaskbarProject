package brainfuck.lecture;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by sebde on 08/01/2017.
 */
public class MonitorTest {
    @Test
    public void MonitorTest() throws  Exception{
        Monitor monitor=new Monitor();
        monitor.Reset();
        monitor.updateData_Move();
        monitor.updateData_Read();
        monitor.updateData_Write();
        monitor.updateExec_Move();
        monitor.updateNb_Instr(1);
        assertEquals(monitor.toString(),"Nombre d'instructions: 1\n" +
                "Nombre de déplacements du pointeur d'instruction: 1\n" +
                "Nombre de déplacements dans la mémoire: 1\n" +
                "Nombre d'écritures dans la mémoire: 1\n" +
                "Nombre de lectures dans la mémoire: 1\n" +
                "Temps d'exécution: 0");
        monitor.Reset();
    }

}
