public enum EnumArg{

    PRINT("-p"),
    REWRITE("--rewrite"),
    TRANSLATE("--translate"),
    INF("-i"),
    OUTF("-o"),
    CHECK("--check");

    private String arg;
    Map< String, EnumArg> comArg = new hashmap<String, EnumArg>();

    static{
	Arrays.asList(EnumArg.values()).forEach( val -> comArg.put(val.toString(), val));
    }
    
    EnumArg(String arg){
	this.arg = arg;
    }

    @Override
    public String toString(){
	return this.arg;
    }

    
}
