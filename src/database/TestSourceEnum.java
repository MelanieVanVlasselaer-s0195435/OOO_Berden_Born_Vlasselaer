package database;

public enum TestSourceEnum {
    TXT("database.TXT.TxtDBStrategy"),
    EXCEL("database.EXCEL.ExcelDBStrategy");

    private final String className;

    TestSourceEnum(String className){ this.className = className; }

    public String getClassName(){
        return className;
    }
}