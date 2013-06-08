package org.crowdcomputer.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Snipets {

	/**
	 * @param args
	 */

	public void pollingBheaviour(String pars1) {
		try {
			System.out.println(pars1);
			Thread.sleep(1000);
			System.out.println(pars1);

			Thread.sleep(5000);

			System.out.println(pars1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void foo(String... args) {
		for (String arg : args) {
		}
		for (int i = 0; i < args.length - 1; i += 2) {
			System.out.println("" + args[i] + " ," + args[i + 1]);
		}
	}

	public static void main(String[] args) {
		Snipets snipets = new Snipets();
		snipets.pollingBheaviour("test");
//		snipets.date("0y 0mo 0w 365d 0h 0m 0s");
	}

}
