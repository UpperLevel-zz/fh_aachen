package fh.aachen.praktikum2;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class URMStorage {

    private String programName;
    private Map<String, Integer> variables = new HashMap<>();
    private String outputVariable;
    private List<WhileStatement> whileStatements = new ArrayList<>();
    private List<ValueAssignment> assignZero = new ArrayList<>();
    private List<ValueAssignment> assignPlusOne = new ArrayList<>();
    private int whileLevel = 0;

    public void addVariable(String varName) {
        this.variables.put(varName, 0);
    }

    public void addAssignmentZero(String varName) {
        this.assignZero.add(new ValueAssignment(varName, whileLevel));
    }

    public void addAssignmentPlusOne(String varName) {
        this.assignPlusOne.add(new ValueAssignment(varName, whileLevel));
    }

    public void enterWhileLoop(String from, String to) {
        whileLevel++;
        this.whileStatements.add(new WhileStatement(from, to, whileLevel));
    }

    public void leaveWhileLoop(){
        this.whileLevel--;
    }

    record WhileStatement(String from, String to, int level) {
    }

    record ValueAssignment(String varName, int level) {
    }
}