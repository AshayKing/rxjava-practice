package io.github.ashayking;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class EMergeingObservable {

	public static void main(String[] args) {
		System.out.println("========");
		mergeOptr();

		System.out.println("========");
		Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
		Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");
		Observable.concat(source1, source2).subscribe(i -> System.out.println("RECEIVED: " + i));

		System.out.println("========");
		Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
		source.flatMap(s -> Observable.fromArray(s.split(""))).subscribe(System.out::println);
		source.concatMap(s -> Observable.fromArray(s.split(""))).subscribe(System.out::println);

		System.out.println("========");
		Observable<String> sourceA = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
		Observable<Integer> sourceB = Observable.range(1, 6);
		Observable.zip(sourceA, sourceB, (s, i) -> s + "-" + i).subscribe(System.out::println);
		sourceA.zipWith(sourceB, (s, i) -> s + "-" + i);

		System.out.println("========");
		Observable<GroupedObservable<Integer, String>> byLengths = source.groupBy(s -> s.length());
		byLengths.flatMapSingle(grp -> grp.toList()).subscribe(System.out::println);
	}

	private static void mergeOptr() {
		Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
		Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");
		Observable<String> source3 = Observable.just("Iota", "Kappa");

		Observable.merge(source1, source2).subscribe(i -> System.out.println("RECEIVED: " + i));

		source1.mergeWith(source2).subscribe(i -> System.out.println("RECEIVED: " + i));

		Observable.mergeArray(source1, source2, source3).subscribe(i -> System.out.println("RECEIVED: " + i));

		List<Observable<String>> sources = Arrays.asList(source1, source2, source3);
		Observable.merge(sources).subscribe(i -> System.out.println("RECEIVED: " + i));
	}
}
