package io.github.ashayking;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * 
 * @author Ashay S Patil
 *
 */
public class BObservableFactories {

	private static int start = 1;
	private static int count = 5;

	public static void main(String[] args) {
		// create()
		Observable<String> sourceCreate = Observable.create(emitter -> {
			emitter.onNext("Alpha");
			emitter.onNext("Beta");
			emitter.onNext("Gamma");
			emitter.onNext("Delta");
			emitter.onNext("Epsilon");
			emitter.onComplete();
		});
		sourceCreate.subscribe(s -> System.out.println("RECEIVED: " + s));

		// just()
		Observable<String> sourceJust = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
		sourceJust.map(String::length).filter(i -> i >= 5).subscribe(s -> System.out.println("RECEIVED: " + s));

		// iterable()
		List<String> items = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
		Observable<String> sourceItr = Observable.fromIterable(items);
		sourceItr.map(String::length).filter(i -> i >= 5).subscribe(s -> System.out.println("RECEIVED: " + s));

		// range()
		Observable.range(1, 10).subscribe(s -> System.out.println("RECEIVED: " + s));

		// empty()
		Observable<String> empty = Observable.empty();
		empty.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done!"));

		// error()
		Observable.error(new Exception("Crash and burn!")).subscribe(i -> System.out.println("RECEIVED: " + i),
				Throwable::printStackTrace, () -> System.out.println("Done!"));

		// defer()
		Observable<Integer> source = Observable.range(start, count);
		//Observable<Integer> source = Observable.defer(() -> Observable.range(start, count));
		source.subscribe(i -> System.out.println("Observer 1: " + i));
		// modify count
		count = 10;
		source.subscribe(i -> System.out.println("Observer 2: " + i));
		
		
		// Single
		Single.just("Hello")
		.map(String::length)
		.subscribe(System.out::println,
				Throwable::printStackTrace);
		
		
		// maybe
		// has emission
		Maybe<Integer> presentSource = Maybe.just(100);
		presentSource.subscribe(s -> System.out.println("Process 1 received: " + s),
		Throwable::printStackTrace,
		() -> System.out.println("Process 1 done!"));
		//no emission
		Maybe<Integer> emptySource = Maybe.empty();
		emptySource.subscribe(s -> System.out.println("Process 2 received: " + s),
		Throwable::printStackTrace,
		() -> System.out.println("Process 2 done!"));
		
		
	}
}
