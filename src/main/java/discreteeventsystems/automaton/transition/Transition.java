package discreteeventsystems.automaton.transition;

import discreteeventsystems.automaton.State;
import java.util.Objects;
import lombok.NonNull;
import lombok.Setter;

public class Transition {

	@NonNull @Setter State     from;
	@NonNull @Setter State     to;
	@NonNull @Setter Character character;

	public Transition(State from, State to, Character character) {
		setFrom(from);
		setTo(to);
		setCharacter(character);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Transition)) {
			return false;
		}
		Transition that = (Transition) o;
		return Objects.equals(from, that.from) &&
				Objects.equals(to, that.to) &&
				Objects.equals(character, that.character);
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to, character);
	}

	@Override
	public String toString() {
		return String.format("D(%s, %s) -> %s", to, character, from);
	}
}
