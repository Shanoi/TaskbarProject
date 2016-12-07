package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.BACK;
import static brainfuck.command.EnumCommands.JUMP;
import brainfuck.memory.ComputationalModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Team Taskbar
 */
public class Fichiers {

    protected static List<EnumCommands> list = new ArrayList<>();
    protected static Map<Integer, Integer> jumpBack = new HashMap<>();
    protected int i = 0;

    protected ComputationalModel cm;

    protected String path;


    /**
     *
     *
     */
    public void setTableLoop() {
       	for( int i = 0; i < list.size(); i++) {
	    if( list.get(i) == JUMP )
      		jumpBack.put(i, jumpAssoc(i));
	    /*	    if( list.get(i) == BACK )
		    jumpBack.put(i, jumpAssoc(i));*/
	}
    }

    public Fichiers(String path) {

        cm = new ComputationalModel();
        this.path = path;

    }

    public void Read() throws FileNotFoundException, IOException {
    }

    public void Encod() {

    }



    /**
     * This method allows to get the associated Jump (number in the instruction)
     * @param i
     * @return
     */
    public int jumpAssoc(int i) {

        Stack<EnumCommands> stack = new Stack<>();
	//        int o = cm.getI();
	int o = i;

        stack.push(list.get(o));

        while (!stack.isEmpty()) {

            o++;

            if (list.get(o) == JUMP) {

                stack.push(list.get(o));

            }

            if (list.get(o) == BACK) {

                stack.pop();

            }
        }
        return o;
    }

    /**
     * This method allows to retrieve the associated back
     * @param i
     * @return
     */
    public int backAssoc(int i) {

        Stack<EnumCommands> stack = new Stack<>();
	//        int o = cm.getI();
	int o = i;

        stack.push(list.get(o));

        while (!stack.isEmpty()) {

            o--;

            if (list.get(o) == JUMP) {

                stack.pop();
            }

            if (list.get(o) == BACK) {

                stack.push(list.get(o));

            }
        }
        return o;
    }

    //=================
    //Getter and Setter
    //=================

    /**
     * Getter of the size of the list, NbI
     * @return
     */
    public int getNbI() {
        return list.size();
    }

    /**
     * Getter of the instructions
     * @return
     */
    public List<EnumCommands> getInstructions() {
        return list;
    }

    /**
     * Getter of the computational model
     * @return
     */
    public ComputationalModel getCm() {
        return cm;
    }

    

}
