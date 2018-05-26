package io.github.ashayking;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 
 * @author Ashay S Patil
 *
 */
public class ABasics {
	public static void main(String[] args) {

		Observable<String> myStrings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
		myStrings.map(s -> s.length()).subscribe(s -> System.out.println(s));

		Observer<Integer> myObserver = new Observer<Integer>() {
			@Override
			public void onSubscribe(Disposable d) {
				// do nothing with Disposable, disregard for now
			}

			@Override
			public void onNext(Integer value) {
				System.out.println("RECEIVED: " + value);
			}

			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}

			@Override
			public void onComplete() {
				System.out.println("Done!");
			}
		};
		myStrings
			.map(String::length)
			.filter(i -> i >= 5)
			.subscribe(myObserver);
		
		
		myStrings
			.map(String::length)
			.filter(i -> i >= 5)
			.subscribe(
					i -> System.out.println("RECEIVED: " + i),
					Throwable::printStackTrace,
					()->System.out.println("Done!")
			);
	}

}
