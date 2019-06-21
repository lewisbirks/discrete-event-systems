package discreteeventsystems.automaton.transition;

import static discreteeventsystems.utils.StringUtils.center;
import static discreteeventsystems.utils.StringUtils.centerWithCharacter;

import discreteeventsystems.UniqueList;
import discreteeventsystems.Alphabet;
import discreteeventsystems.automaton.State;
import java.util.Collection;
import java.util.List;

public class TransitionTable {

	private UniqueList<State> states; // rows
	private Alphabet          alphabet; // columns
	private State[][]         table;

	public TransitionTable(UniqueList<State> states, Alphabet alphabet) {
		this.states   = states;
		this.alphabet = alphabet;
		buildTable();
	}

	private void buildTable() {
		table = new State[states.size()][alphabet.size()];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				table[i][j] = null;
			}
		}
	}

	public void addTransition(Transition transition) {
		int row = states.indexOf(transition.from);
		int column = alphabet.indexOf(transition.character);
		table[row][column] = transition.to;
	}

	public void addTransitions(Collection<Transition> transitions) {
		transitions.forEach(this::addTransition);
	}

	public List<Transition> generateTransitions() {
		UniqueList<Transition> transitions = new UniqueList<>();
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (table[i][j] != null) {
					Transition transition = new Transition(states.get(i), table[i][j], alphabet.get(j));
					transitions.add(transition);
				}
			}
		}

		return transitions;
	}

	public void rebuild(Collection<Transition> transitions) {
		buildTable();
		addTransitions(transitions);
	}

	public void rebuild() {
		buildTable();
	}

	@Override
	public String toString() {
		// Create a table that looks like the following:
		// +----+----+----+----+----+----+
		// |    |    |    |    |    |    |
		// +----+----+----+----+----+----+
		// |    |    |    |    |    |    |
		// +----+----+----+----+----+----+


		StringBuilder builder = new StringBuilder();

		int columnWidth;
		int max = 1;
		for (State state : states) {
			int length = state.toString().length();
			if (length > max) {
				max = length;
			}
		}

		columnWidth = max + 2; // +2 for space either side

		builder.append(tableLine(alphabet.size() + 1, columnWidth)).append("\n");
		builder.append("|").append(center("", columnWidth)).append("|");
		for (Character character : alphabet) {
			builder.append(center(character, columnWidth)).append("|");
		}
		builder.append("\n").append(tableLine(alphabet.size() + 1, columnWidth)).append("\n");

		for (int i = 0; i < table.length; i++) {
			builder.append("|").append(center(states.get(i), columnWidth)).append("|");
			for (int j = 0; j < table[i].length; j++) {
				State state = table[i][j];
				if (state != null) {
					builder.append(center(state, columnWidth));
				} else {
					builder.append(center("?", columnWidth));
				}
				builder.append("|");
			}
			builder.append("\n").append(tableLine(alphabet.size() + 1, columnWidth))
			       .append("\n");
		}

		return builder.toString();
	}

	private static String tableLine(int columns, int columnWidth) {
		StringBuilder builder = new StringBuilder();
		String        sep     = "+";

		for (int i = 0; i < columns; i++) {
			builder.append(sep).append(centerWithCharacter("", columnWidth, '-')).append("+");
			sep = "";
		}

		return builder.toString();
	}
}
