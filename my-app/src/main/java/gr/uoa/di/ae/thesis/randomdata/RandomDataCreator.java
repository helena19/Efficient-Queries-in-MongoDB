package gr.uoa.di.ae.thesis.randomdata;

import java.util.GregorianCalendar;

import org.fluttercode.datafactory.impl.DataFactory;

public class RandomDataCreator {

	public static void main(String[] args) {

		DataFactory df = new DataFactory();
        for (int i = 0; i < 100; i++) {          
            String name = df.getFirstName() + " "+ df.getLastName() + " " + df.getEmailAddress() + " " + df.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime());
            System.out.println(name);
        }
		
	}

}
