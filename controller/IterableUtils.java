package controller;

import java.util.ArrayList;
import java.util.List;

public class IterableUtils {

	public static <T> List<T> asList(Iterable<? extends T> iterable) {
		List<T> result = new ArrayList<>();
		iterable.forEach(x -> result.add(x));
		return result;
	}

}
