package no.hvl.dat100.prosjekt;

import static java.lang.Math.*;
import java.util.Locale;

public class GPSUtils {

	public GPSUtils() {
	
	}
	
	// konverter sekunder til string på formen hh:mm:ss
	public static String printTime(int secs) {
		
		String timestr = "";
		String TIMESEP = ":";
		
		// TODO
		// OPPGAVE - START
		
		
		int hr = secs / 3600;
        String hrString = Integer.toString(hr);
        if (hr < 10) {
            hrString = "0" + hrString;
        }
        int min = (secs % 3600) / 60;
        String minString = Integer.toString(min);
        if (min < 10) {
            minString = "0" + minString;
        }
        int sec = (secs % 3600) % 60;
        String secString = Integer.toString(sec);
        if (sec < 10) {
            secString = "0" + secString;
        }
        timestr = hrString + TIMESEP + minString + TIMESEP + secString;
		
				
		// OPPGAVE - SLUTT
		
		return timestr;
	}
	
	// beregn maximum av en tabell av doubles med minnst et element
	public static double findMax(double[] da) {

		double max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	// beregn minimum av en tabell av doubles med minnst et element
	public static double findMin(double[] da) {

		// fjern = "0.0" når metoden implementeres for ikke få forkert minimum
		double min = da[0]; 

		// TODO
		// OPPGAVE - START
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
			
		}

		// OPPGAVE - SLUT
		return min;
	}
		
		

	
	private static int R = 6371000; // jordens radius
	
	// Beregn avstand mellom to gps punkter ved bruk av Haversine formlen
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

		double a,c,d; // fjern = 1.0
		
		// TODO:
		// OPPGAVE - START
		
		double radLatitude1 = toRadians(latitude1);
        double radLatitude2 = toRadians(latitude2);
        double trekantLatitude = toRadians(latitude2 - latitude1);
        double trekantLongitude = toRadians(longitude2 - longitude1);
        a = pow(sin(trekantLatitude/2.0), 2.0) + (cos(radLatitude1)) * (cos(radLatitude2)) * pow(sin(trekantLongitude/2.0), 2.0);
        c = 2 * atan2(sqrt(a), sqrt(1.0 - a));
        d = (double)(R) * c;
		
		// OPPGAVE - SLUTT

		return d;
	}
	
	// beregn gjennomsnits hastighet i km/t mellom to gps punkter
	public static double speed(int secs, double latitude1, double longitude1, double latitude2, double longitude2) {

		double speed = 0.0;

		// TODO:
		// OPPGAVE - START
		
		double secsD = (double) secs;
        double hastighet = distance(latitude1, longitude1, latitude2, longitude2) / secsD;
        double hastighetKmT = hastighet * 3.6;
        speed = hastighetKmT;
		
		// OPPGAVE - SLUTT

		return speed;
	}
	
	private static int TEXTWIDTH = 10;
	
	// konverter double til string med 2 decimaler og streng lengde 10
	// eks. 1.346 konverteres til "      1.35" og enhet til slutt
	// Hint: se på String.format metoden
	
	public static String printDouble(double d) {
		
		String str = "";
		
		// TODO
		// OPPGAVE - START
		
		str = String.valueOf(Math.round(d*100.0)/100.0);
		for(int aR = str.length(); aR < TEXTWIDTH; aR++) {
			str = " " + str;
		}
		
       // String dAsString = String.format("%8.2f", d);
        //str = dAsString;
		
		// OPPGAVE - SLUTT
		
		return str;
	}
}
