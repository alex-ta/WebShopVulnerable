package database.extension;

public class ParseInteger implements ParseInterface<Integer> {

	@Override
	public Integer parse(Object o) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public Integer parse(String s) {
		return Integer.parseInt(s);
	}

}
