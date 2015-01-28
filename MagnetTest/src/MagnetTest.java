import lejos.nxt.*t;


public class MagnetTest {

	/**
	 * @param args
	 */
	public SensorPort port = new SensorPort(4);
	public static void main(String[] args) {

		for(int i = 0; i<100; i++) {
		SensorPort(4).activate();
		port.readRawValue();
		}

	}
	private static SensorPort SensorPort(int i) {
		lejos.nxt.SensorPort.S4.readRawValues(values)
	}

}
