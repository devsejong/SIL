package net.chandol.study.book.rxjava.chpater04;

import rx.Observable;

import java.security.SecureRandom;
import java.util.Random;

import static rx.Observable.defer;

public class RecursiveInfiniteObserver {
    private static final Random rnd = new SecureRandom();

    public static Observable<Integer> rndNumObserver() {
        // defer을 이용하는 경우, 실제 데이터가 필요한 순간까지 동작하는걸 늦출 수 있다.
        return defer(
                () -> Observable
                        .range(0, 23)
                        .map(c -> rnd.nextInt()))
                .concatWith(
                        defer(RecursiveInfiniteObserver::rndNumObserver)
                );
    }

    public static void main(String[] args) {
        rndNumObserver().take(100)
                .toList()
                .subscribe(a -> System.out.println(a));
    }
}
