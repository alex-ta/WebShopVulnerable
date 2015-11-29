package database.extension;

public interface ParseInterface<ReturnType> {
	public ReturnType parse(Object o);
	public ReturnType parse(String s);
}
