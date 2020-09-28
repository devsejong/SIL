package net.chandol.study.book.rxjava.chapter03;

import rx.Observable;

public class OperatorMap {

    public static void main(String[] args) {
        Observable.just(1, 2, 3)
                .map(num -> num * 2)
                .subscribe(integer -> System.out.println(integer));
    }

}
