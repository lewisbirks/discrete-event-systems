package discreteeventsystems;

import java.util.ArrayList;
import java.util.Collection;

public class UniqueList<T> extends ArrayList<T> {

	@Override
	public boolean add(T t) {
		if (this.contains(t)) {
			return false;
		}

		return super.add(t);
	}

	@Override
	public void add(int index, T element) {
		if (!this.contains(element)) {
			super.add(index, element);
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		Collection<? extends T> difference = new ArrayList<>(c);
		difference.removeAll(this); // remove duplicates
		return super.addAll(difference);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		Collection<? extends T> retained = new ArrayList<>(c);
		retained.removeAll(this); // remove duplicates
		return super.addAll(index, retained);
	}

}
