package fh.aachen.praktikum2;

import java.util.HashMap;
import java.util.Map;

public class URMStorage {

    private String programName;
    private Map<String, String> variables = new HashMap<>();

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
