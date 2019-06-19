package discreteeventsystems.automaton;

import lombok.Getter;
import lombok.Setter;

public class Transition {

	@Getter @Setter State  from;
	@Getter @Setter State  to;
	@Getter @Setter String character;

	public Transition(State from, State to, String character) {
		this.from      = from;
		this.to        = to;
		this.character = character;
	}
}
