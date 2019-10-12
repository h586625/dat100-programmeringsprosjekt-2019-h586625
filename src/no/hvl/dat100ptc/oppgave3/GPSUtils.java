package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

import java.util.Locale;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START

        min = da[0];

        for (double d : da) {
            if (d < min) {
                min = d;
            }
        }

        return min;

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
        double[] latitudes = new double[gpspoints.length];
        for (int i = 0; i < gpspoints.length; i++) {
            latitudes[i] = gpspoints[i].getLatitude();
        }
        return latitudes;
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

        double[] longitudes = new double[gpspoints.length];
        for (int i = 0; i < gpspoints.length; i++) {
            longitudes[i] = gpspoints[i].getLongitude();
        }
        return longitudes;
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START

        latitude1 = gpspoint1.getLatitude();
        longitude1 = gpspoint1.getLongitude();
        latitude2 = gpspoint2.getLatitude();
        longitude2 = gpspoint2.getLongitude();
        double latitude1Radians = toRadians(latitude1);
        double longitude1Radians = toRadians(longitude1);
        double latitude2Radians = toRadians(latitude2);
        double longitude2Radians = toRadians(longitude2);
        double deltaLatitude = latitude2Radians - latitude1Radians;
        double deltaLongitude = longitude2Radians - longitude1Radians;
        double a = pow(sin(deltaLatitude / 2), 2) 
                + cos(latitude1Radians) * cos(latitude2Radians)
                * pow(sin(deltaLongitude/2), 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        d = R * c;
		// TODO - SLUTT

        return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START

        secs = gpspoint2.getTime() - gpspoint1.getTime();
        // int hours = secs * 60 * 60;
        // double distanceInKm = distance(gpspoint1, gpspoint2) / 1000;
        // speed = distanceInKm / hours;
        speed = distance(gpspoint1, gpspoint2) / secs; // meter/sekunder
        speed = (speed * 60 * 60) / 1000; // ((meter/sekunder)*60*60)/1000
        return speed;

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START

        int hours = secs / 60 / 60;
        int minutes = secs / 60 % 60;
        int seconds = secs % 60 % 60;
        // String hoursFormatted = String.format("%tH", hours);
        String hh = (hours < 10 ? "0" : "") + hours;
        String mm = (minutes < 10 ? "0" : "") + minutes;
        String ss = (seconds < 10 ? "0" : "") + seconds;
        timestr = "  " + hh + TIMESEP + mm + TIMESEP + ss;
        return timestr;
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

        str = Double.toString(d);
        str = str.format(Locale.US, "%.2f", d);
        int strLength = str.length();

        if(strLength < 10) {
            int lengthDiff = TEXTWIDTH - strLength;
            for(int i = 0; i < lengthDiff; i++) {
                str = " " + str;
            }
        }
        
        return str;

		// TODO - SLUTT
		
	}
}
