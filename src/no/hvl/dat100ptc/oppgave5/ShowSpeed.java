package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	@Override
    public void run() {

		int N = gpspoints.length-1; // number of data points
		
        makeWindow("Speed profile", 2 * MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
        setSpeed(1);

		showSpeedProfile(MARGIN + BARHEIGHT,N);

	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
				
		// TODO - START
		
        int speed;
        int averageSpeed = (int) gpscomputer.averageSpeed();
        int barWidth = 2;
        int barPadding = 0;
        int graphWidth = (N * timescaling * barWidth) - barWidth;

        for (int i = 0; i < gpscomputer.speeds().length; i++) {
            speed = (int) gpscomputer.speeds()[i];
            if (speed > 0 && speed < BARHEIGHT) {
                barPadding = i * timescaling * barWidth;
                setColor(0, 0, 255);
                fillRectangle(MARGIN + barPadding, (ybase - speed), barWidth, speed);
            }
        }

        // Draw average speed indicator
        setColor(0, 255, 0);
        fillRectangle(MARGIN, (ybase - averageSpeed), graphWidth, 3);
	
		// TODO - SLUTT
	}
}
