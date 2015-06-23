package br.com.pidgey.parser;

public class AppendableItem {
	
	private final int startPosition;
	private final int endPosition;
	private final Object value;

	public AppendableItem(int startPosition, int endPosition, 
			Object value) {
		super();
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.value = value;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "AppendableItem [startPosition=" + startPosition
				+ ", endPosition=" + endPosition + ", value=" + value + "]";
	}

}