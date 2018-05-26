package io.github.ashayking;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * 
 * @author Ashay S Patil
 *
 */
public class CColdVsHotObservable {
	public static void main(String[] args) {
		Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
		// first observer
		source.subscribe(s -> System.out.println("Observer 1 Received:" + s));
		// second observer
		source.subscribe(s -> System.out.println("Observer 2 Received:" + s));

		// Prevented Replay	
		ConnectableObservable<String> sourceHot = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").publish();
		// Set up observer 1
		sourceHot.subscribe(s -> System.out.println("Observer 1: " + s));
		// Set up observer 2
		sourceHot.map(String::length).subscribe(i -> System.out.println("Observer 2: " + i));
		// Fire!
		sourceHot.connect();
	}
}
