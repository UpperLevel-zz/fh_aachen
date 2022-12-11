package fh.aachen.praktikum2;

import lombok.Data;

import java.util.*;

@Data
public class URMStorage {

    private String programName;
    private Map<String, String> variables = new HashMap<>();
    private String outputVariable;
    private List<Statement> statements = new ArrayList<>();

    private List<String> output = new ArrayList<>();
    private List<String> labels = new ArrayList<>();
    private Map<String, Integer> labelRowMapping = new HashMap<>();

    public void addVariable(String varName) {
        createAndAddVar(varName);
    }

    public void addAssignmentZero(String varName) {
        this.statements.add(new ValueAssignment(varName));
    }

    public void addAssignmentPlusOne(String varName) {
        this.statements.add(new ValueAssignmentIncrement(varName));
    }

    public String enterWhileLoop(String from, String to) {
        String label = generateLabel();
        this.statements.add(new EnterWhile(from, to, label));
        return label;
    }

    public void leaveWhileLoop(String from, String to, String label) {
        this.statements.add(new ExitWhile(from, to, label));
    }

    public String toURMCode() {
        output.add("; ".concat(programName));
        output.add("; in(".concat(buildInputVars()).concat(")"));
        statements.forEach(x -> output.addAll(makroSwitch(x)));
        for (int i = 0; i < output.size(); i++) {
            String row = output.get(i);
            for (String label : labels) {
                if (row.startsWith(label)) {
                    labelRowMapping.put(label, (i + 1 - 2));
                    String element = row.replaceAll(label, "").trim();
                    output.set(i, element);
                } else if (row.contains(String.format("; ENTER WHILE with label: %s", label))) {
                    labelRowMapping.put(label, (i + 1 - 2));
                }
            }
        }
        List<Integer> emptyLines = new ArrayList<>();
        for (int i = 0; i < output.size(); i++) {
            String row = output.get(i);
            for (String label : labels) {
                if (!row.contains("endWhile") && row.contains(label)) {
                    String element = row.replaceAll(label, labelRowMapping.getOrDefault(label, 0).toString()).trim();
                    output.set(i, element);
                }
            }
            if (output.get(i).isEmpty()) {
                emptyLines.add(i);
            }
        }
        emptyLines.sort(Comparator.reverseOrder());
        for (Integer emptyLine : emptyLines) {
            output.remove(emptyLine.intValue());
        }
        return String.join("\n", output);
    }

    private String buildInputVars() {
        return String.join(",", this.variables.values());
    }

    private List<String> makroSwitch(Statement statement) {
        return switch (statement.getClass().getSimpleName()) {
            case "EnterWhile" -> makroEqual(statement);
            case "ExitWhile" -> makroEndWhile(statement);
            case "ValueAssignment" -> makroValueAssignment(statement);
            case "ValueAssignmentIncrement" -> makroValueIncrement(statement);
            default -> throw new IllegalStateException("DAS SOLLTE NICHT PASSIEREN");
        };
    }

    private List<String> makroValueAssignment(Statement statement) {
        return List.of(String.format("%s = 0", variables.get(((ValueAssignment) statement).varName)));
    }

    private List<String> makroValueIncrement(Statement statement) {
        return List.of(String.format("%s++", variables.get(((ValueAssignmentIncrement) statement).varName)));
    }

    private List<String> makroEndWhile(Statement statement) {
        return List.of(String.format("goto %s \t\t; endWhile %s", ((ExitWhile) statement).label, ((ExitWhile) statement).label));
    }

    private List<String> makroEqual(Statement statement) {
        List<String> makro = new ArrayList<>();
        EnterWhile enterWhile = (EnterWhile) statement;
        String HFrom = createAndAddVar();
        String HTo = createAndAddVar();
        String rz = createAndAddVar();

        String label1 = generateLabel();
        String label2 = generateLabel();
        String label6 = generateLabel();
        String label7 = generateLabel();
        String label10 = generateLabel();

        makro.addAll(makroCopy(enterWhile.from, HFrom));
        makro.addAll(makroCopy(enterWhile.to, HTo));

        makro.add(String.format("%s if  %s == 0 goto %s \t\t; ENTER WHILE with label: %s", label1, HFrom, label7, enterWhile.label));
        makro.add(String.format("%s if  %s == 0 goto %s", label2, HTo, label6));
        makro.add(String.format("%s--", HFrom));
        makro.add(String.format("%s--", HTo));
        makro.add(String.format("goto %s", label1));
        makro.add(String.format("%s  %s++", label6, rz));
        makro.add(String.format("%s if  %s == 0 goto %s", label7, HTo, label10));
        makro.add(String.format("%s++", rz));
        makro.add(String.format("goto %s", label2));
        makro.add(String.format("%s", label10));

        return makro;
    }

    private List<String> makroCopy(String from, String to) {
        List<String> makro = new ArrayList<>();
        String rz = createAndAddVar();
        String label2 = generateLabel();
        String label6 = generateLabel();
        String label11 = generateLabel();
        makro.add(String.format("%s = 0 \t\t; COPY (from: %s to: %s)", variables.get(to), variables.get(from), variables.get(to)));
        makro.add(String.format("%s if  %s == 0 goto %s", label2, variables.get(from), label6));
        makro.add(String.format("%s--", variables.get(from)));
        makro.add(String.format("%s++", rz));
        makro.add(String.format("goto %s", label2));
        makro.add(String.format("%s if  %s == 0 goto %s", label6, rz, label11));
        makro.add(String.format("%s--", rz));
        makro.add(String.format("%s++", variables.get(to)));
        makro.add(String.format("%s++", variables.get(from)));
        makro.add(String.format("goto %s", label6));
        makro.add(String.format("%s %s-- \t\t; RANDOM-Variable without sense", label11, rz));
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

    record EnterWhile(String from, String to, String label) implements Statement {
    }

    record ExitWhile(String from, String to, String label) implements Statement {
    }

    record ValueAssignment(String varName) implements Statement {
    }

    record ValueAssignmentIncrement(String varName) implements Statement {
    }

    public interface Statement {
    }
}