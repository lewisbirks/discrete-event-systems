package discreteeventsystems.automaton.transition;

import static discreteeventsystems.utils.AccessUtils.getField;
import static org.junit.jupiter.api.Assertions.assertEquals;

import discreteeventsystems.Alphabet;
import discreteeventsystems.UniqueList;
import discreteeventsystems.automaton.State;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransitionTableTest {

	private TransitionTable   transitionTable;
	private UniqueList<State> states;
	private Alphabet          alphabet;

	@BeforeEach
	void setUp() {
		states   = new UniqueList<>();
		alphabet = new Alphabet();

		states.add(new State("s0"));
		states.add(new State("s1"));

		alphabet.add('0');
		alphabet.add('1');

		transitionTable = new TransitionTable(states, alphabet);
	}

	@Test
	void constructor() throws NoSuchFieldException, IllegalAccessException {
		Field             statesField = getField(TransitionTable.class, "states");
		UniqueList<State> statesValue = (UniqueList<State>) statesField.get(transitionTable);

		Field    alphabetField = getField(TransitionTable.class, "alphabet");
		Alphabet alphabetValue = (Alphabet) alphabetField.get(transitionTable);

		Field     tableField = getField(TransitionTable.class, "table");
		State[][] tableValue = (State[][]) tableField.get(transitionTable);

		assertEquals(states, statesValue);
		assertEquals(alphabet, alphabetValue);
		assertEquals(states.size(), tableValue.length);
		assertEquals(alphabet.size(), tableValue[0].length);
	}

	@Test
	void addTransition() throws NoSuchFieldException, IllegalAccessException {
		transitionTable.addTransition(new Transition(new State("s0"), new State("s0"), '0'));

		Field     tableField = getField(TransitionTable.class, "table");
		State[][] tableValue = (State[][]) tableField.get(transitionTable);

		assertEquals(new State("s0"), tableValue[0][0]);

		transitionTable.addTransition(new Transition(new State("s0"), new State("s1"), '1'));
		tableValue = (State[][]) tableField.get(transitionTable);
		assertEquals(new State("s1"), tableValue[0][1]);
	}

	@Test
	void addTransitions() throws IllegalAccessException, NoSuchFieldException {
		ArrayList<Transition> transitions = new ArrayList<>();

		transitions.add(new Transition(new State("s1"), new State("s1"), '1'));
		transitions.add(new Transition(new State("s1"), new State("s0"), '0'));

		transitionTable.addTransitions(transitions);

		Field     tableField = getField(TransitionTable.class, "table");
		State[][] tableValue = (State[][]) tableField.get(transitionTable);

		assertEquals(new State("s1"), tableValue[1][1]);
		assertEquals(new State("s0"), tableValue[1][0]);
	}

	@Test
	void rebuild() throws NoSuchFieldException, IllegalAccessException {
		states.add(new State("s3"));
		transitionTable.rebuild();

		Field             statesField = getField(TransitionTable.class, "states");
		UniqueList<State> statesValue = (UniqueList<State>) statesField.get(transitionTable);

		Field    alphabetField = getField(TransitionTable.class, "alphabet");
		Alphabet alphabetValue = (Alphabet) alphabetField.get(transitionTable);

		Field     tableField = getField(TransitionTable.class, "table");
		State[][] tableValue = (State[][]) tableField.get(transitionTable);

		assertEquals(states, statesValue);
		assertEquals(alphabet, alphabetValue);
		assertEquals(states.size(), tableValue.length);
		assertEquals(alphabet.size(), tableValue[0].length);
	}

	@Test
	void toStringTest() {
		ArrayList<Transition> transitions = new ArrayList<>();

		transitions.add(new Transition(new State("s1"), new State("s1"), '1'));
		transitions.add(new Transition(new State("s1"), new State("s0"), '0'));

		transitionTable.addTransitions(transitions);

		String table = transitionTable.toString();

		String expected = "+----+----+----+\n"
				+ "|    | 0  | 1  |\n"
				+ "+----+----+----+\n"
				+ "| s0 | ?  | ?  |\n"
				+ "+----+----+----+\n"
				+ "| s1 | s0 | s1 |\n"
				+ "+----+----+----+\n";

		assertEquals(expected, table);
	}
}