package discreteeventsystems.automaton;

import java.util.Objects;
import lombok.NonNull;

public class State {

	@NonNull private String name;

	public State(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof State)) {
			return false;
		}
		State state = (State) o;
		return Objects.equals(name, state.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return name;
	}
}
