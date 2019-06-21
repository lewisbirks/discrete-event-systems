package discreteeventsystems;

import static discreteeventsystems.utils.StringUtils.squareToBraces;

public class Alphabet extends UniqueList<Character> {

	public Alphabet() {
		super();
	}

	public Alphabet(Alphabet alphabet) {
		super();
		this.addAll(alphabet);
	}

	@Override
	public String toString() {
		return squareToBraces(super.toString());
	}
}
