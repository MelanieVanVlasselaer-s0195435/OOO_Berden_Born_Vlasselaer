package database;

public enum TestSourceEnum {
    TXT("database.TxtDBStrategy"),
    EXCEL("database.ExcelDBStrategy");

    private final String className;

    TestSourceEnum(String className){ this.className = className; }

    public String getClassName(){
        return className;
    }
}