package net.chandol.study.book.rxjava.chapter03;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.functions.Func1;

import java.util.concurrent.TimeUnit;

import static rx.Observable.timer;


@Slf4j
public class ConcatMapSample {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(3, 2, 1, 5, 6, 8)
                .flatMap(input -> timer(input * 100, TimeUnit.MILLISECONDS).map(i -> input))
                .subscribe(a-> log.info("{}", a));

        TimeUnit.SECONDS.sleep(1);

        log.info("-----");

        Observable.just(3, 2, 1, 5, 6, 8)
                .concatMap(input -> timer(input * 100, TimeUnit.MILLISECONDS).map(i -> input))
                .subscribe(a-> log.info("{}", a));

        TimeUnit.SECONDS.sleep(3);
    }
}
