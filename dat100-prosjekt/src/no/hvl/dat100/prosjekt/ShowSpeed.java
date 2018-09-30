package no.hvl.dat100.prosjekt;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;

public class ShowSpeed extends EasyGraphics {
	
	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;
		
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t
	
	private static GPSComputer gpscomputer; 
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		
		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(filename);
		 
		gpscomputer = new GPSComputer(gpsdata);
		
		times = gpscomputer.times;
		latitudes = gpscomputer.latitudes;
		longitudes = gpscomputer.longitudes;
		elevations = gpscomputer.elevations;
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = times.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
		
		// hent hastigheter på de ulike deler av ruten
		double[] speeds = gpscomputer.speeds();
		
		// TODO:
		
		// OPPGAVE - START
		double averageSpeed = gpscomputer.averageSpeed();
        int x1, y1, x2, y2, yAverage;
        for (int i = 0; i < speeds.length; i++) {

        	x1 = i + (i * 1) + 1;
        	x2 = (x1 + 2) / (i + 2);
        	y1 = ybase - 100;
        	y2 = (int)Math.round(speeds[i]);
        	y1 = 100 - (y2-y1);
        	yAverage = ybase - (int)Math.round(averageSpeed);
        	setColor(0, 0, 255);
        	fillRectangle(x1, y1, x2, y2);
        	setColor(0, 255, 0);
        	drawLine(x1, yAverage, x2, yAverage);
        	

        }
		
		// OPPGAVE - SLUTT
	}
}
