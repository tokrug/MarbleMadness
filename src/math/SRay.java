package math;

public class SRay implements Ray {

	private Vector4D _start;
	private Vector4D _direction;
	
	public SRay(Vector4D start, Vector4D direction) {
		_start = start;
		_direction = direction;
		_direction.normalize();
	}

	@Override
	public Vector4D getDirection() {
		return _direction;
	}

	@Override
	public Vector4D getStartingPoint() {
		return _start;
	}

	@Override
	public Vector4D getPointOnRay(double distance) {
		Vector4 direct = new Vector4(_direction);
		direct.multiply(distance);
		Vector4 point = new Vector4(_start);
		point.add(direct);
		return point;
	}
}
