package discreteeventsystems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * An ArrayList with the set property of ensuring that the collection only contains unique
 * elements.
 *
 * @param <E> Type of the elements maintained in this list
 */
public class UniqueList<E> extends ArrayList<E> {

	@Override
	public boolean add(E e) {
		if (this.contains(e)) {
			return false;
		}

		return super.add(e);
	}

	@Override
	public void add(int index, E element) {
		if (!this.contains(element)) {
			super.add(index, element);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean               modified = false;
		Iterator<? extends E> iterator = c.iterator();
		int                   size     = c.size();

		while (--size >= 0) {
			modified |= add(iterator.next());
		}

		return modified;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean               modified = false;
		Iterator<? extends E> iterator = c.iterator();
		int                   size     = c.size();

		while (--size >= 0) {
			int sizeBefore = this.size();
			add(index, iterator.next());
			if (this.size() != sizeBefore) {
				modified = true;
				index++;
			}
		}

		return modified;
	}

}
