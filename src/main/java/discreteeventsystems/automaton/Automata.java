package discreteeventsystems.automaton;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public class Automata {

	@Getter @Setter Set<String>     alphabet;
	@Getter @Setter Set<State>      states;
	@Getter @Setter Set<Transition> transitions;
	@Getter @Setter State           initial;
	@Getter @Setter Set<State>      acceptStates;

	public Automata() {
		alphabet     = new HashSet<>();
		states       = new HashSet<>();
		transitions  = new HashSet<>();
		acceptStates = new HashSet<>();
	}
}
