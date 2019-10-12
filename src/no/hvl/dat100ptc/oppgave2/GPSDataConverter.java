package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // startindex for tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		// TODO
		// OPPGAVE - START
        String[] splitTimestr = timestr.split(":");
        String hrStr = splitTimestr[0];
        String secStr = splitTimestr[2];

        hrStr = hrStr.substring(hrStr.length() - 2);
        hr = Integer.parseInt(hrStr) * 60 * 60;
        min = Integer.parseInt(splitTimestr[1]) * 60;
        secStr = secStr.substring(0, 2);
        sec = Integer.parseInt(secStr);
        secs = hr + min + sec;

        return secs;

		// OPPGAVE - SLUTT
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;

		// TODO - START ;
        int time = GPSDataConverter.toSeconds(timeStr);
        double latitude = Double.parseDouble(latitudeStr);
        double longitude = Double.parseDouble(longitudeStr);
        double elevation = Double.parseDouble(elevationStr);
        gpspoint = new GPSPoint(time, latitude, longitude, elevation);
        return gpspoint;
		// OPPGAVE - SLUTT ;
	    
	}
	
}
