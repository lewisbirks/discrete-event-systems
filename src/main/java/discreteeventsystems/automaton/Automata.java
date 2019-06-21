package discreteeventsystems.automaton;

import static discreteeventsystems.utils.StringUtils.squareToBraces;

import discreteeventsystems.Alphabet;
import discreteeventsystems.UniqueList;
import discreteeventsystems.automaton.transition.Transition;
import discreteeventsystems.automaton.transition.TransitionTable;
import discreteeventsystems.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collection;
import lombok.NonNull;
import lombok.Setter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Automata {

	private final            Alphabet               alphabet;
	private                  UniqueList<State>      states;
	private                  UniqueList<Transition> transitions;
	@NonNull @Setter private State                  initial;
	private                  UniqueList<State>      acceptStates;
	private                  TransitionTable        transitionTable;

	public Automata() {
		alphabet        = new Alphabet();
		states          = new UniqueList<>();
		transitions     = new UniqueList<>();
		acceptStates    = new UniqueList<>();
		transitionTable = new TransitionTable(states, alphabet);
	}

	public void addState(State state) {
		states.add(state);
		transitionTable.rebuild();
	}

	public void addTransition(Transition transition) {
		transitions.add(transition);
		transitionTable.addTransition(transition);
	}

	/**
	 * Get a copy of the set of states in the automata
	 */
	public ArrayList<State> getStates() {
		return new ArrayList<>(states);
	}

	/**
	 * Get a copy of the alphabet used in the automata
	 */
	public Alphabet getAlphabet() {
		return new Alphabet(alphabet);
	}

	public Automata union(Automata automata) {
		throw new NotImplementedException();
	}

	public Automata intersection(Automata automata) {
		throw new NotImplementedException();
	}

	public Automata star() {
		throw new NotImplementedException();
	}

	public Automata concatenate(Automata automata) {
		throw new NotImplementedException();
	}

	public Automata negation(Automata automata) {
		throw new NotImplementedException();
	}

	public boolean run(Collection<Character> string) {
		throw new NotImplementedException();
	}

	@Override
	public String toString() {
		StringBuilder transitions = new StringBuilder();

		for (Transition transition : this.transitions) {
			transitions.append("\t").append(transition).append("\n");
		}

		String states       = squareToBraces(this.states.toString());
		String acceptStates = squareToBraces(this.acceptStates.toString());

		return "Q = {" + states + "}\n" + "E = {" + alphabet + "}\n" + "q0 = " + initial + "\n"
				+ "F = {" + acceptStates + "}" + "D = {\n" + transitions.toString() + "}";
	}
}
