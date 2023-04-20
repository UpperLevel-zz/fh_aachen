package fh.aachen.praktikum2;

import lombok.Data;

import java.util.*;

@Data
public class URMStorage {

    private String programName;
    private Map<String, String> variables = new HashMap<>();
    private Set<String> inputVariables = new HashSet<>();
    private String outputVariable;
    private List<Statement> statements = new ArrayList<>();

    private List<String> urmOutput = new ArrayList<>();
    private List<String> labels = new ArrayList<>();
    private Map<String, Integer> labelRowMapping = new HashMap<>();

    public void addVariable(String varName) {
        inputVariables.add(varName);
        createAndAddVar(varName);
    }

    public void addOutputVariable(String varName) {
        createAndAddVar(varName);
        outputVariable = varName;
    }

    public void addAssignmentZero(String varName) {
        this.statements.add(new ValueAssignment(varName));
    }

    public void addAssignmentPlusOne(String varName) {
        this.statements.add(new ValueAssignmentIncrement(varName));
    }

    public EnterWhile enterWhileLoop(String from, String to) {
        String labelEnter = generateLabel();
        String labelInner = generateLabel();
        String labelExit = generateLabel();
        EnterWhile result = new EnterWhile(from, to, labelEnter, labelInner, labelExit);
        this.statements.add(result);
        return result;
    }

    public void leaveWhileLoop(EnterWhile statement) {
        this.statements.add(new ExitWhile(statement.labelEnter, statement.labelExit));
    }

    public String toURMCode() {
        urmOutput.add("; ".concat(programName));
        urmOutput.add("; in(".concat(String.join(",", String.format("%s", this.inputVariables))).concat(")"));
        urmOutput.add(String.format("; out(%s=%s)", outputVariable, variables.get(outputVariable)));
        urmOutput.add("; allVariables(".concat(String.join(",", String.format("%s", this.variables.entrySet()))).concat(")"));
        urmOutput.add(String.format("%s=0", variables.get(outputVariable)));
        statements.forEach(x -> urmOutput.addAll(makroSwitch(x)));
        replaceLabels();
        return String.join("\n", urmOutput);
    }

    private void replaceLabels() {
        for (int i = 0; i < urmOutput.size(); i++) {
            String row = urmOutput.get(i);
            for (String label : labels) {
                if (row.startsWith(label)) {
                    labelRowMapping.put(label, (i + 1 - 4));
                    String element = row.replaceAll(label, "").trim();
                    urmOutput.set(i, element);
                }
            }
        }
        List<Integer> emptyLines = new ArrayList<>();
        for (int i = 0; i < urmOutput.size(); i++) {
            String row = urmOutput.get(i);
            for (String label : labels) {
                if (row.contains(label)) {
                    String element = row.replaceAll(label, labelRowMapping.get(label).toString()).trim();
                    urmOutput.set(i, element);
                }
            }
            if (urmOutput.get(i).isEmpty()) {
                emptyLines.add(i);
            }
        }
        emptyLines.sort(Comparator.reverseOrder());
        for (Integer emptyLine : emptyLines) {
            urmOutput.remove(emptyLine.intValue());
        }
    }

    private List<String> makroSwitch(Statement statement) {
        return switch (statement.getClass().getSimpleName()) {
            case "EnterWhile" -> makroEnterWhile((EnterWhile) statement);
            case "ExitWhile" -> makroEndWhile((ExitWhile) statement);
            case "ValueAssignment" -> makroValueAssignment((ValueAssignment) statement);
            case "ValueAssignmentIncrement" -> makroValueIncrement((ValueAssignmentIncrement) statement);
            default -> throw new IllegalStateException("DAS SOLLTE NICHT PASSIEREN");
        };
    }

    private List<String> makroValueAssignment(ValueAssignment statement) {
        return List.of(String.format("%s=0 \t\t; Single-Assignment: %s=0", variables.get(statement.varName), statement.varName));
    }

    private List<String> makroValueIncrement(ValueAssignmentIncrement statement) {
        return List.of(String.format("%s++ \t\t; Single-Increment: %s++", variables.get(statement.varName), statement.varName));
    }

    private List<String> makroEndWhile(ExitWhile statement) {
        List<String> makro = new ArrayList<>();
        makro.add(String.format("goto %s \t\t; EXIT WHILE", statement.labelEnter));
        makro.add(String.format("%s %s=0 \t\t; NOOP AFTER WHILE", statement.labelExit, createAndAddVar()));
        return makro;
    }

    private List<String> makroEnterWhile(EnterWhile statement) {
        List<String> makro = new ArrayList<>();
        String HFrom = createAndAddVar();
        String HTo = createAndAddVar();
        String rz = createAndAddVar();

        String label1 = generateLabel();
        String label6 = generateLabel();
        String label7 = generateLabel();

        makro.add(String.format("%s %s=0 \t\t; ENTER WHILE %s != %s", statement.labelEnter, rz, statement.from, statement.to));
        makro.addAll(makroCopy(statement.from, HFrom));
        makro.addAll(makroCopy(statement.to, HTo));

        makro.add(String.format("%s=0 \t\t; Check while condition", rz));
        makro.add(String.format("%s if  %s == 0 goto %s", label1, HFrom, label6));
        makro.add(String.format("if  %s == 0 goto %s", HTo, label7));
        makro.add(String.format("%s--", HFrom));
        makro.add(String.format("%s--", HTo));
        makro.add(String.format("goto %s", label1));
        makro.add(String.format("%s if  %s != 0 goto %s \t\t; jump to next while iteration", label6, HTo, label7));
        makro.add(String.format("%s++", rz));
        makro.add(String.format("%s %s=0", label7, HFrom));
        makro.add(String.format("%s=0", HTo));
        makro.add(String.format("%s if  %s != 0 goto %s \t\t; jump out off while", statement.labelInner, rz, statement.labelExit));
        makro.add(String.format("%s %s=0\t\t; NOOP (enter inner while)", statement.labelInner, rz));

        return makro;
    }

    private List<String> makroCopy(String from, String to) {
        List<String> makro = new ArrayList<>();
        String rz = createAndAddVar();
        String label2 = generateLabel();
        String label6 = generateLabel();
        String label11 = generateLabel();
        makro.add(String.format("%s=0 \t\t; COPY (from: %s to: %s)", variables.get(to), variables.get(from), variables.get(to)));
        makro.add(String.format("%s if  %s == 0 goto %s", label2, variables.get(from), label6));
        makro.add(String.format("%s--", variables.get(from)));
        makro.add(String.format("%s++", rz));
        makro.add(String.format("goto %s", label2));
        makro.add(String.format("%s if  %s == 0 goto %s \t\t; leave copy makro", label6, rz, label11));
        makro.add(String.format("%s--", rz));
        makro.add(String.format("%s++", variables.get(to)));
        makro.add(String.format("%s++", variables.get(from)));
        makro.add(String.format("goto %s", label6));
        makro.add(String.format("%s %s=0 \t\t; NOOP (force URM-Sim-Action)", label11, rz));
        return makro;
    }

    private String createAndAddVar() {
        return createAndAddVar(null);
    }

    private String createAndAddVar(String originalName) {
        String urmName = "R" + (variables.size() + 1);
        variables.put((originalName != null ? originalName : urmName), urmName);
        return urmName;
    }

    private String generateLabel() {
        String label = UUID.randomUUID().toString();
        this.labels.add(label);
        return label;
    }

    record EnterWhile(String from, String to, String labelEnter, String labelInner,
                      String labelExit) implements Statement {
    }

    record ExitWhile(String labelEnter, String labelExit) implements Statement {
    }

    record ValueAssignment(String varName) implements Statement {
    }

    record ValueAssignmentIncrement(String varName) implements Statement {
    }

    public interface Statement {
    }
}