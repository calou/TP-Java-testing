package org.foo.core.model.stats;

import java.util.List;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public class Distribution {
	private List<Point> points;

	public Distribution() {
	}

	public Distribution(List<Point> points) {
		this.points = points;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
}
