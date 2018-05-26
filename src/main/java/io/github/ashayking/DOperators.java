package io.github.ashayking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import io.reactivex.Observable;

public class DOperators {

	static Observable<String> observable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

	public static void main(String[] args) {
		System.out.println("==================");
		supressingOptrs();
		System.out.println("==================");
		transformingOptrs();
		System.out.println("==================");
		reducingOptrs();
		System.out.println("==================");
		collectionOptrs();
		System.out.println("==================");
		errorRecoveryOptrs();
		System.out.println("==================");
	}

	private static void errorRecoveryOptrs() {
		// Default
		Observable.just(5, 2, 4, 0, 3, 2, 8)
			.map(i -> 10 / i)
			.subscribe(
					i -> System.out.println("RECEIVED: " + i),
					e -> System.out.println("RECEIVED ERROR: " + e)
			);
		// onErrorReturnItem
		Observable.just(5, 2, 4, 0, 3, 2, 8)
		.map(i -> 10 / i)
		.onErrorReturnItem(-1)
		//.onErrorReturn(e -> - 1)
		//.onErrorResumeNext(Observable.just(-1).repeat(3))
		.subscribe(
				i -> System.out.println("RECEIVED: " + i),
				e -> System.out.println("RECEIVED ERROR: " + e)
		);
	}

	private static void collectionOptrs() {
		System.out.println("=======TOLIST========");
		// toList	
		observable
			.toList()
			//.toSortedList()
			.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("=======TOMAP========");
		// toMap
		observable
			//.toMap(s -> s.charAt(0))
			.toMap(s -> s.charAt(0), String::length)
			.subscribe(s -> System.out.println("Received: " + s));
	
		System.out.println("=======COLLECT========");
		// collect
		observable
			.collect(HashSet::new, HashSet::add)
			.subscribe(s -> System.out.println("Received: " + s));
	}

	private static void reducingOptrs() {
		System.out.println("=======COUNT========");
		// count	
		observable
				.count()
				.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("=======REDUCE========");
		// reduce	
		Observable.just(5, 3, 7, 10, 2, 14)
			//.reduce((total, next) -> total + next)
			.reduce("", (total, next) -> total + (total.equals("") ? "" :",") + next)
			.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("=======ALL========");
		// all
		Observable.just(5, 3, 7, 11, 2, 14)
			.all(i -> i < 10)
			.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("=======ANY========");
		// any
		Observable.just(5, 3, 7, 11, 2, 14)
			.any(i -> i < 10)
			.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("=======CONTAINS========");
		// contains
		Observable.just(5, 3, 7, 11, 2, 14)
			.contains(8)
			.subscribe(s -> System.out.println("Received: " + s));
		
	}

	private static void transformingOptrs() {
		System.out.println("=======MAP========");
		// map
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
		Observable.just("1/3/2016", "5/9/2016", "10/12/2016").map(s -> LocalDate.parse(s, dtf))
				.subscribe(i -> System.out.println("RECEIVED: " + i));

		System.out.println("=======CAST========");
		// cast
		Observable.just("Alpha", "Beta", "Gamma").cast(Object.class)
				.subscribe(i -> System.out.println("RECEIVED: " + i));

		System.out.println("=======STARTSWITH========");
		// startsWith
		Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");
		menu.startWith("COFFEE SHOP MENU").subscribe(System.out::println);
		menu.startWithArray("COFFEE SHOP MENU", "----------------").subscribe(System.out::println);

		System.out.println("=======DEFAULTIFEMPTY========");
		// defaultIfEmpty
		observable.filter(s -> s.startsWith("Z")).defaultIfEmpty("None").subscribe(System.out::println);

		System.out.println("=======SWITCHIFEMPTY========");
		// switchIfEmpty
		observable.filter(s -> s.startsWith("Z")).switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
				.subscribe(System.out::println);

		System.out.println("=======SORTED========");
		// sorted
		Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3).sorted().subscribe(System.out::println);
		
		System.out.println("=======REPEAT========");
		// repeat
		observable.repeat(2)
		.subscribe(s -> System.out.println("Received: " + s));
		
		System.out.println("=======SCAN========");
		// scan
		Observable.just(5, 3, 7, 10, 2, 14)
			.scan((accumulator, next) -> accumulator + next)
			.subscribe(s -> System.out.println("Received: " + s));
	}

	private static void supressingOptrs() {
		System.out.println("=======FILTER========");
		// filter
		observable.filter(s -> s.length() != 5).subscribe(s -> System.out.println("RECEIVED: " + s));

		System.out.println("=======TAKE========");
		// take
		observable.take(3).subscribe(s -> System.out.println("RECEIVED: " + s));

		System.out.println("=======SKIP========");
		// skip
		Observable.range(1, 100).skip(90).subscribe(i -> System.out.println("RECEIVED: " + i));

		System.out.println("=======TAKEWHILE========");
		// takeWhile
		Observable.range(1, 100).takeWhile(i -> i < 5).subscribe(i -> System.out.println("RECEIVED: " + i));

		System.out.println("=======SKIPWHILE========");
		// skipWhile
		Observable.range(1, 100).skipWhile(i -> i <= 95).subscribe(i -> System.out.println("RECEIVED: " + i));

		System.out.println("=======DISTINCT========");
		// distinct
		observable.map(String::length).distinct()
				// .distinct(String::length)
				.subscribe(i -> System.out.println("RECEIVED: " + i));

		System.out.println("=======ELEMENTAT========");
		// elementAt
		observable.elementAt(3).subscribe(i -> System.out.println("RECEIVED: " + i));

	}
}
