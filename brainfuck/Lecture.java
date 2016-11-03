public abstract class Lecture
{

    public abstract void execute();
    ShortSyntaxe shortSyntaxe = new ShortSyntaxe();
    LongSyntaxe longSyntaxe = new LongSyntaxe();
    String path;

    public Lecture(String path)
    {
	this.path = path;
    }

}
