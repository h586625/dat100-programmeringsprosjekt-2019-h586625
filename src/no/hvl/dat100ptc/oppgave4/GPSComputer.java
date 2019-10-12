package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START

        for (int i = 0; i < gpspoints.length; i++) {
            if (i + 1 < gpspoints.length) {
                distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
            }
        }
        return distance;

		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START

        for (int i = 0; i < gpspoints.length; i++) {
            double currentElevation = gpspoints[i].getElevation();
            if (i > 0 && currentElevation > gpspoints[i - 1].getElevation()) {
                double prevElevation = gpspoints[i - 1].getElevation();
                elevation += currentElevation - prevElevation;

            }
        }
        return elevation;

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

        int time = 0;

        for (int i = 0; i < gpspoints.length; i++) {
            double currentTime = gpspoints[i].getTime();
            if (i > 0 && currentTime > gpspoints[i - 1].getElevation()) {
                double prevTime = gpspoints[i - 1].getTime();
                time += currentTime - prevTime;
            }

        }
        return time;

	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
        double[] speeds = new double[gpspoints.length - 1];

        for (int i = 0; i < gpspoints.length; i++) {
            if (i + 1 < gpspoints.length) {
                speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
            }
        }

        return speeds;

		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
		
        for (int i = 0; i < gpspoints.length; i++) {
            if (i + 1 < gpspoints.length) {
                double speed = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
                maxspeed = speed > maxspeed ? speed : maxspeed;
            }
        }

        return maxspeed;
		
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		
        average = (this.totalDistance() / this.totalTime() * 60 * 60) / 1000;
        return average;
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO - START
		
        double hours = secs / 3600.0;

        if (speedmph < 10) {
            met = 4;
        } else if (speedmph >= 10 && speedmph < 12) {
            met = 6;
        } else if (speedmph >= 12 && speedmph < 14) {
            met = 8;
        } else if (speedmph >= 14 && speedmph < 16) {
            met = 10;
        } else if (speedmph >= 16 && speedmph <= 20) {
            met = 12;
        } else {
            met = 16;
        }

        kcal = (met * weight * hours);
//        System.out.println(
//                        "met: " + met + "\nspeedmph: " + speedmph + "\nweight: " + 
//                        weight + "\nhours:" + hours + "\nsecs:"
//                        + secs + "\nkcal: " + kcal + "\n "
//        );

        return kcal;

		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		
        int totalTime = this.totalTime();
        double averageSpeed = this.averageSpeed();

        totalkcal = this.kcal(weight, totalTime, averageSpeed);

        return totalkcal;

		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START
        String totalDistance = GPSUtils.formatDouble(this.totalDistance() / 1000);
        String totalElevation = GPSUtils.formatDouble(this.totalElevation());
        String averageSpeed = GPSUtils.formatDouble(this.averageSpeed());
        String maxSpeed = GPSUtils.formatDouble(this.maxSpeed());
        String energy = GPSUtils.formatDouble(this.totalKcal(GPSComputer.WEIGHT));

        System.out.println("Total time     :   " + GPSUtils.formatTime(this.totalTime()));
        System.out.println("Total distance :   " + totalDistance + " km");
        System.out.println("Total elevation:   " + totalElevation + " m");
        System.out.println("Max speed      :   " + maxSpeed + " km/t");
        System.out.println("Average speed  :   " + averageSpeed + " km/t");
        System.out.println("Energy         :   " + energy + " kcal");

        System.out.println("==============================================");
		
		// TODO - SLUTT
		
	}

}
