package no.hvl.dat100.prosjekt;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import java.util.Locale;

import java.util.Locale;


public class GPSComputer {
	
	public GPSComputer(GPSData gpsdata) {

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		this.times = converter.times;
		this.latitudes = converter.latitudes;
		this.longitudes = converter.longitudes;
		this.elevations = converter.elevations;
	}

	// tabeller for GPS datapunkter
	public int[] times;
	public double[] latitudes;
	public double[] longitudes;
	public double[] elevations;
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO
		// OPPGAVE - START

		
		
		for(int i = 1; i < times.length; i++) {
			
			
			distance = distance + GPSUtils.distance(latitudes[i-1], longitudes[i-1], latitudes[i], longitudes[i]);
		}
		
		
		// Hint: bruk distance-metoden fra GPSUtils.
		
		// OPPGAVE - SLUTT

		return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO
		// OPPGAVE - START
		
		for(int i = 1; i < times.length; i++) {
			if((elevations[i] - elevations[i-1]) > 0) {
			
			elevation = elevation + (elevations[i] - elevations[i-1]);
			}
			
			
		}

		// OPPGAVE - SLUTT
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int totaltime = 0;
		
		// TODO 
		// OPPGAVE START
		
		totaltime = totaltime + (times[times.length - 1] - times[0]);
		
		// OPPGAVE SLUTT
		
		return totaltime;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {

		double[] speeds = new double[times.length-1];
		
		// TODO
		// OPPGAVE - START
		
		for(int i = 1; i < times.length; i++) {
		speeds[i-1] = GPSUtils.speed(times[i] - times[i-1], latitudes[i-1], longitudes[i-1], latitudes[i], longitudes[i]);
		}
		
		// OPPGAVE - SLUTT
		return speeds;
	}

	// beregn maximum hastighet km/t
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO
		// OPPGAVE - START
		
		for(int i = 1; i < times.length; i++) {
			double speed = GPSUtils.speed(times[i] - times[i-1], latitudes[i-1], longitudes[i-1], latitudes[i], longitudes[i]);
			if (speed > maxspeed) {
				maxspeed = speed;
			}
			
			
		}
				
		// OPPGAVE - SLUTT
		
		return maxspeed;
	}
	
	// beregn gjennomsnittshasitiget for hele turen km/t
	public double averageSpeed() {

		double average = 0;
		
		// TODO
		// OPPGAVE - START
		
		
		average = totalDistance() * 3.6 / totalTime();
				
		// OPPGAVE - SLUTT
		
		return average;
	}


	// conversion factor kph (km/t) to miles per hour (mph)
	public static double MS = 0.62;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal = 0;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO
		// OPPGAVE START
		
		// Energy Expended (kcal) = MET x Body Weight (kg) x Time (h)
		
		double h = (double)secs / 3600;
		double kg = weight;
		
		if (speedmph < 10 && speedmph > 0) {
			met = 4;
		} else if (speedmph < 12 ){
			met = 6;
		} else if (speedmph < 14){
			met = 8;
		} else if (speedmph < 16) {
			met = 10;
		} else if (speedmph < 20) {
			met = 12;
		} else {
			met = 16;
		}
		
		kcal = met * kg * h;
		
		
		// OPPGAVE SLUTT
		
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO
		// OPPGAVE - START 
		
		// Hint: hent hastigheter i speeds tabellen og tider i timestabellen
		// disse er definer i toppen av klassen og lese automatisk inn
		
		
		for(int i = 0; i < speeds().length; i++) {
	            totalkcal = kcal(weight, (times[i]), speeds()[i]);
	            
	        }

		
		// OPPGAVE - SLUTT
		
		return totalkcal;
	}
	
	public static double WEIGHT = 80.0;
	
	// skriv ut statistikk for turen
	public void print() {
		
		// TODO
		// OPPGAVE - START
		
		
		System.out.println("Total time		: " + GPSUtils.printTime(totalTime()));
		System.out.println("Total distance	: " + String.format(Locale.ROOT, "%.2f", totalDistance()) + "km");
		System.out.println("Total elevation	: " + String.format(Locale.ROOT, "%.2f", totalElevation()) + "m");
		System.out.println("Max speed		: " + String.format(Locale.ROOT, "%.2f", maxSpeed()) + " km/t");
		System.out.println("Average speed: " + String.format(Locale.ROOT, "%.2f", averageSpeed()) + " km/t");
		System.out.println("Energy		: " + String.format(Locale.ROOT, "%.2f", totalKcal(WEIGHT)) + " kcal");
		
				
		// OPPGAVE - SLUT
	}
	
	// ekstraoppgaver
	public void climbs() {
		
	}
	
	public void maxClimb() {
		
	}
}
